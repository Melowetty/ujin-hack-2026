package team.mcqueen.smartdisplay.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import team.mcqueen.smartdisplay.domain.display.DisplayEntity
import team.mcqueen.smartdisplay.domain.log.LogEntity
import java.util.*

@Repository
interface DisplayRepository : JpaRepository<DisplayEntity, UUID> {

}