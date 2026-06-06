package team.mcqueen.smartdisplay.service.impl

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import team.mcqueen.smartdisplay.domain.news.News
import team.mcqueen.smartdisplay.service.NewsService
import team.mcqueen.smartdisplay.ujin.generated.api.NewsApiClient

@Service
class UjinNewsService(
    private val newsApiClient: NewsApiClient,
    @Value("\${integration.ujin.access-token}")
    private val ujinToken: String,
): NewsService {

    override fun getNews(houseId: Long): List<News> {
        val response = newsApiClient.getNewsList(
            ujinToken,
            complexes = null,
            buildings = listOf(houseId.toInt()),
            type = null,
        )

        val news = response.body?.data?.items
            ?: throw IllegalStateException("Get news from ujin api error")

        return news.map {
            News(
                id = it.id?.toLong() ?: 0L,
                title = it.title ?: "N/a",
                text = it.text ?: "N/a",
                type = it.type?.title ?: "Новости",
                date = LocalDate.parse(it.date ?: "N/a", DATE_FORMATTER),
            )
        }
    }

    companion object {
        private const val DATE_FORMAT = "yyyy-MM-dd"
        private val DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT)
    }
}