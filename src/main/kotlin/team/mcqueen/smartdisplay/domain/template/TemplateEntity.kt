package team.mcqueen.smartdisplay.domain.template

import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.Id
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Column
import jakarta.persistence.GenerationType

@Entity
@Table(name = "template")
class TemplateEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long = 0,

    @Column
    var name: String,

    @Column
    var body: String
)