package team.mcqueen.smartdisplay.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import team.mcqueen.smartdisplay.generated.api.ComplexesApi
import team.mcqueen.smartdisplay.generated.model.Complex
import team.mcqueen.smartdisplay.generated.model.House

@RestController
class ComplexesController : ComplexesApi {
    override fun getComplexes(): ResponseEntity<List<Complex>> {
        return ResponseEntity.ok(listOf(
            Complex(
                id = 1,
                name = "Скворцы",
                houses = listOf(
                    House(
                        id = 1,
                        name = "Дом на Ленине",
                        floorsNumber = 3,
                        address = "ул. Ленина 67",
                        entrancesNumber = 2
                    ),
                    House(
                        id = 3,
                        name = "Дом на Ленине",
                        address = "ул. Ленина 41",
                        floorsNumber = 10,
                        entrancesNumber = 5
                    )
                )
            )
        ));
    }
}