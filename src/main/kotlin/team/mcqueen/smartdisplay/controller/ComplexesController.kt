package team.mcqueen.smartdisplay.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import team.mcqueen.smartdisplay.generated.api.ComplexesApi
import team.mcqueen.smartdisplay.generated.model.Complex
import team.mcqueen.smartdisplay.generated.model.House
import team.mcqueen.smartdisplay.service.ComplexService

@RestController
class ComplexesController(
    private val complexService: ComplexService,
) : ComplexesApi {
    override fun getComplexes(): ResponseEntity<List<Complex>> {
        return ResponseEntity.ok(complexService.getComplexList())
    }
}