package team.mcqueen.smartdisplay.controller

import org.springframework.http.ResponseEntity
import team.mcqueen.smartdisplay.generated.api.TemplatesApi
import team.mcqueen.smartdisplay.generated.model.Template
import team.mcqueen.smartdisplay.generated.model.TemplateInput

class TemplatesController : TemplatesApi {
    override fun createTemplate(templateInput: TemplateInput): ResponseEntity<Template> {
        return ResponseEntity.ok(Template(
            id = 1,
            name = "Base template",
            body = "{tipa: json}"
        ))
    }

    override fun deleteTemplate(templateId: Long): ResponseEntity<Unit> {
        return ResponseEntity.ok().build()
    }

    override fun getTemplateById(templateId: Long): ResponseEntity<Template> {
        return ResponseEntity.ok(Template(
            id = 1,
            name = "Base template",
            body = "{tipa: json}"
        ))
    }

    override fun getTemplates(): ResponseEntity<List<Template>> {
        return ResponseEntity.ok(listOf(
            Template(
            id = 1,
            name = "Base template",
            body = "{tipa: json}"
        ),
            Template(
                id = 2,
                name = "Cute template",
                body = "{tipa: ne_jыson_vashe_nepon}"
            )
        ))
    }

    override fun updateTemplate(templateId: Long, templateInput: TemplateInput): ResponseEntity<Template> {
        return ResponseEntity.ok(Template(
            id = 1,
            name = "Base template",
            body = "{tipa: json}"
        ))
    }

}