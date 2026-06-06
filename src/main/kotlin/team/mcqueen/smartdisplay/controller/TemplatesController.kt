package team.mcqueen.smartdisplay.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import team.mcqueen.smartdisplay.generated.api.TemplatesApi
import team.mcqueen.smartdisplay.generated.model.LightTemplate
import team.mcqueen.smartdisplay.generated.model.Template
import team.mcqueen.smartdisplay.generated.model.TemplateInput
import team.mcqueen.smartdisplay.service.TemplateService

@RestController
class TemplatesController(
    private val templateService: TemplateService
) : TemplatesApi {
    override fun createTemplate(templateInput: TemplateInput): ResponseEntity<Template> {
        return ResponseEntity.ok(templateService.createTemplate(
            name = templateInput.name,
            body = templateInput.body
        ))
    }

    override fun deleteTemplate(templateId: Long): ResponseEntity<Unit> {
        return ResponseEntity.ok(templateService.deleteTemplate(templateId))
    }

    override fun getTemplateById(templateId: Long): ResponseEntity<Template> {
        return ResponseEntity.ok(templateService.getTemplateById(templateId))
    }

    override fun getTemplates(): ResponseEntity<List<LightTemplate>>  {
        return ResponseEntity.ok(templateService.getTemplates())
    }

    override fun updateTemplate(templateId: Long, templateInput: TemplateInput): ResponseEntity<Template> {
        return ResponseEntity.ok(templateService.updateTemplate(
            templateId = templateId,
            name = templateInput.name,
            body = templateInput.body
        ))
    }

}