package team.mcqueen.smartdisplay.domain.widget

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.Id
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Column
import jakarta.persistence.FetchType
import jakarta.persistence.GenerationType
import jakarta.persistence.OneToMany
import team.mcqueen.smartdisplay.generated.model.WidgetField
import team.mcqueen.smartdisplay.generated.model.WidgetType

@Entity
@Table(name = "widget")
class WidgetEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Long,

    @Column
    var name: String,

    @Column
    var type: WidgetType,

    @OneToMany(mappedBy = "widget", targetEntity = WidgetFieldEntity::class)
    var fields: List<WidgetFieldEntity>
)