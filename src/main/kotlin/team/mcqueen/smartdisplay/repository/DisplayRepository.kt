package team.mcqueen.smartdisplay.repository

import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import team.mcqueen.smartdisplay.domain.display.DisplayEntity
import team.mcqueen.smartdisplay.domain.log.LogEntity
import team.mcqueen.smartdisplay.generated.model.Display
import java.util.*

@Repository
interface DisplayRepository : JpaRepository<DisplayEntity, Long> {
    fun getDisplayEntityById(displayId: Long): DisplayEntity?
    fun getAllByHouseId(houseId: Long): List<DisplayEntity>?
    fun getByToken(token: String): DisplayEntity?
    @Modifying
    @Transactional
    @Query("UPDATE DisplayEntity d SET d.templateId = :templateId WHERE d.houseId = :houseId")
    fun updateTemplateIdByHouseId(@Param("houseId") houseId: Long, @Param("templateId") templateId: Long): Int
}