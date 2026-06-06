package team.mcqueen.smartdisplay.domain.news

import java.time.LocalDate

data class News(
    val id: Long,
    val title: String,
    val text: String,
    val type: String,
    val date: LocalDate,
)
