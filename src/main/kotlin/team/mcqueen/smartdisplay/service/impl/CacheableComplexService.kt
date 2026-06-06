package team.mcqueen.smartdisplay.service.impl

import java.time.LocalDateTime
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Service
import team.mcqueen.smartdisplay.generated.model.Complex
import team.mcqueen.smartdisplay.service.ComplexService

@Service
@Primary
class CacheableComplexService(
    private val directComplexService: UjinComplexService
): ComplexService {
    private var cache: List<Complex>? = null
    private var cachedUntilAt: LocalDateTime? = null

    @Synchronized
    override fun getComplexList(): List<Complex> {
        if (cache != null && cachedUntilAt?.isAfter(LocalDateTime.now()) == true) {
            return cache!!
        }

        val actualData = directComplexService.getComplexList()
        cachedUntilAt = LocalDateTime.now().plusHours(1)
        cache = actualData
        return actualData
    }
}