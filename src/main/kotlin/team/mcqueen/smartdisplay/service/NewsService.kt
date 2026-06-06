package team.mcqueen.smartdisplay.service

import team.mcqueen.smartdisplay.domain.news.News

interface NewsService {
    fun getNews(houseId: Long): List<News>
}