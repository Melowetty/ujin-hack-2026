package team.mcqueen.smartdisplay.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import team.mcqueen.smartdisplay.domain.template.TemplateEntity

@Repository
interface TemplateRepository : JpaRepository<TemplateEntity, Long> {
    fun getTemplateEntityById(templateId: Long): TemplateEntity?

}