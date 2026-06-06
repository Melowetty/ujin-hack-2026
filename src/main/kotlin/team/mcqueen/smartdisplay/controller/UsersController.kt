package team.mcqueen.smartdisplay.controller

import java.util.UUID
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import team.mcqueen.smartdisplay.domain.auth.AuthRole
import team.mcqueen.smartdisplay.generated.api.UsersApi
import team.mcqueen.smartdisplay.generated.model.Role
import team.mcqueen.smartdisplay.generated.model.SetRoleRequest
import team.mcqueen.smartdisplay.generated.model.User
import team.mcqueen.smartdisplay.service.UserService

@RestController
class UsersController(
    private val userService: UserService,
): UsersApi {
    override fun getMe(): ResponseEntity<User> {
        val user = userService.getUserInfo()
        return ResponseEntity.ok(User(
            id = user.id,
            name = user.name,
            role = Role.forValue(user.role.name)
        ))
    }

    override fun setUserRole(userId: UUID, setRoleRequest: SetRoleRequest): ResponseEntity<Unit> {
        userService.setUserRole(userId, AuthRole.valueOf(setRoleRequest.role.name))
        return ResponseEntity.ok().build()
    }
}