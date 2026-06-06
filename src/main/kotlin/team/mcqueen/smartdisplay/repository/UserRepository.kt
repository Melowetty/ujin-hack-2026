package team.mcqueen.smartdisplay.repository

import java.util.UUID
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import team.mcqueen.smartdisplay.domain.auth.entity.UserEntity

@Repository
interface UserRepository : JpaRepository<UserEntity, UUID> {
    fun findByLoginAndPassword(login: String, password: String): UserEntity?
}