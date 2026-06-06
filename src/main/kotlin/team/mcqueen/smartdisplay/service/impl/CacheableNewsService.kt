package team.mcqueen.smartdisplay.service.impl

import java.time.LocalDateTime
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Service
import team.mcqueen.smartdisplay.domain.news.News
import team.mcqueen.smartdisplay.service.NewsService

@Service
@Primary
class CacheableNewsService(
    private val ujinNewsService: UjinNewsService,
): NewsService {

    private var cache: MutableMap<Long, List<News>> = mutableMapOf()
    private var cachedUntilAt: MutableMap<Long, LocalDateTime> = mutableMapOf()

    override fun getNews(houseId: Long): List<News> {
        val saved = cache[houseId]
        val savedUntilAt = cachedUntilAt[houseId]
        if (saved != null && savedUntilAt?.isAfter(LocalDateTime.now()) == true) {
            return saved
        }

        val actualData = ujinNewsService.getNews(houseId)
        cache[houseId] = actualData
        cachedUntilAt[houseId] = LocalDateTime.now().plusHours(1)
        return actualData
    }

}