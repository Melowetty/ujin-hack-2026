package team.mcqueen.smartdisplay.service.impl

import java.time.LocalDateTime
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Service
import team.mcqueen.smartdisplay.domain.storage.StorageInfo
import team.mcqueen.smartdisplay.service.StorageService

@Service
@Primary
class CacheableStorageService(
    private val directStorageService: UjinStorageService
) : StorageService {

    private var cache: MutableMap<Long, StorageInfo> = mutableMapOf()
    private var cachedUntilAt: MutableMap<Long, LocalDateTime> = mutableMapOf()

    override fun getStorageInfo(houseId: Long): StorageInfo {
        val saved = cache[houseId]
        val savedUntilAt = cachedUntilAt[houseId]
        if (saved != null && savedUntilAt?.isAfter(LocalDateTime.now()) == true) {
            return saved
        }

        val actualData = directStorageService.getStorageInfo(houseId)
        cache[houseId] = actualData
        cachedUntilAt[houseId] = LocalDateTime.now().plusMinutes(5)
        return actualData
    }
}