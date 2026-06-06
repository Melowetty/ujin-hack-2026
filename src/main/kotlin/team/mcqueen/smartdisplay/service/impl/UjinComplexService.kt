package team.mcqueen.smartdisplay.service.impl

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpServerErrorException
import team.mcqueen.smartdisplay.generated.model.Complex
import team.mcqueen.smartdisplay.generated.model.House
import team.mcqueen.smartdisplay.service.ComplexService
import team.mcqueen.smartdisplay.ujin.generated.api.ComplexApi
import team.mcqueen.smartdisplay.ujin.generated.model.BuildingItem

@Service
class UjinComplexService(
    private val complexApi: ComplexApi,
    @Value("\${integration.ujin.access-token}")
    private val ujinToken: String,
): ComplexService {

    override fun getComplexList(): List<Complex> {
        val complexes = complexApi.getComplexList(ujinToken).body?.data?.items
            ?: throw RuntimeException("Error when fetching complex list from ujin api")

        val houses = getHousesInForeach()

        val housesByComplexId = houses.filterNot { it.complex == null }.groupBy {
            it.complex!!.id!!
        }

        return complexes.map { complex ->
            val houses = housesByComplexId[complex.id!!]
                ?: listOf()

            Complex(
                id = complex?.id?.toLong() ?: 0L,
                name = complex.title ?: "ЖК Данилиха",
                houses = houses.map {
                    House(
                        id = it.id!!.toLong(),
                        name = it.building?.title ?: "Дом",
                        address = it.building?.address?.fullAddress ?: "N/a",
                        floorsNumber = it.building?.floor ?: 0,
                        entrancesNumber = it.building?.entranceCount ?: 0,
                        city = it.building?.address?.city ?: "N/a"
                    )
                }
            )
        }
    }

    fun getHousesInForeach(): List<BuildingItem> {
        val firstPage = complexApi.getBuildingsList(
            ujinToken,
            perPage = 100,
            page = 1,
            complexId = null,
            search = null
        )

        val body = firstPage.body?.data
            ?: throw RuntimeException("Error when fetching houses from ujin api")

        val lastPage = body.meta?.lastPage
            ?: throw RuntimeException("Error when fetching houses from ujin api")

        val houses = body.buildings?.toMutableList()
            ?: mutableListOf()

        var currentPage = 1

        try {

            while (currentPage != lastPage) {
                currentPage += 1
                val buildings = complexApi.getBuildingsList(
                    ujinToken,
                    perPage = 100,
                    page = currentPage,
                    complexId = null,
                    search = null
                )

                houses.addAll(
                    buildings.body?.data?.buildings
                        ?: emptyList()
                )
            }
        } catch(e: RuntimeException) {
            e.printStackTrace()
        }

        return houses
    }
}