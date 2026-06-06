package team.mcqueen.smartdisplay.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import team.mcqueen.smartdisplay.domain.display.DisplayEntity
import team.mcqueen.smartdisplay.domain.emergency.EmergencyEntity
import team.mcqueen.smartdisplay.domain.log.LogEntity
import team.mcqueen.smartdisplay.generated.model.Display
import team.mcqueen.smartdisplay.generated.model.Emergency
import java.util.*

@Repository
interface EmergencyRepository : JpaRepository<EmergencyEntity, Long> {

}