package team.mcqueen.smartdisplay.domain.widget

import jakarta.persistence.*
import team.mcqueen.smartdisplay.generated.model.FieldType

@Entity
@Table(name = "widget_field")
class WidgetFieldEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Long,

    @Column
    var name: String,

    @Column
    var type: FieldType,

    @Column
    var optional: Boolean,

    @ManyToOne()
    var widget: WidgetEntity
)