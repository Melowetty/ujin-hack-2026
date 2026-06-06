package team.mcqueen.smartdisplay.domain.auth.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID
import team.mcqueen.smartdisplay.domain.auth.AuthRole

@Entity
@Table(name = "users")
class UserEntity(
    @Id
    var id: UUID = UUID.randomUUID(),
    var name: String,
    var login: String,
    var password: String,
    var role: AuthRole,
)