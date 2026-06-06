package team.mcqueen.smartdisplay.service

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Service
import team.mcqueen.smartdisplay.domain.template.TemplateEntity
import team.mcqueen.smartdisplay.exception.NotFoundException
import team.mcqueen.smartdisplay.generated.model.LightTemplate
import team.mcqueen.smartdisplay.generated.model.Template
import team.mcqueen.smartdisplay.repository.TemplateRepository

@Service
class TemplateService(
    private val templateRepository: TemplateRepository,
    private val objectMapper: ObjectMapper
) {
    fun createTemplate(name: String, body: Any): Template {
        val template = TemplateEntity(
            name = name,
            body = body.let {
                objectMapper.writeValueAsString(it)
            }
        )
        val newTemplate = templateRepository.save(template)
        return Template(
            newTemplate.id,
            newTemplate.name,
            template.body.let {
                objectMapper.readTree(it)
            }
        )
    }

    fun deleteTemplate(templateId: Long) {
        templateRepository.deleteById(templateId)
    }

    fun getTemplateById(templateId: Long): Template {
        val template = templateRepository.getTemplateEntityById(templateId)
            ?: throw NotFoundException("Template")
        return Template(
            id = template.id,
            name = template.name,
            body = template.body.let {
                objectMapper.readTree(it)
            }
        )
    }

    fun getTemplates(): List<LightTemplate> {
        val templates = templateRepository.findAll()
        return templates.map{ entity -> LightTemplate(
            id = entity.id,
            name = entity.name
        ) }
    }

    fun updateTemplate(templateId: Long, name: String, body: Any): Template {
        val template = templateRepository.getTemplateEntityById(templateId)
            ?: throw NotFoundException("Template")
        template.name = name
        template.body = body.let {
            objectMapper.writeValueAsString(it)
        }
        var newEntity = templateRepository.save(template)
        return Template(
            id = newEntity.id,
            name = newEntity.name,
            body = newEntity.body.let {
                objectMapper.readTree(it)
            }
        )
    }
}