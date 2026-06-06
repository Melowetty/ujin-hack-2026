package team.mcqueen.smartdisplay.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.time.OffsetDateTime
import team.mcqueen.smartdisplay.generated.api.UsersApi
import team.mcqueen.smartdisplay.generated.model.CreateUserRequest
import team.mcqueen.smartdisplay.generated.model.UpdateUserRequest
import team.mcqueen.smartdisplay.generated.model.UserPage
import team.mcqueen.smartdisplay.generated.model.UserResponse
import team.mcqueen.smartdisplay.generated.model.UserRole

@RestController
class UserController: UsersApi {

    override fun getUsers(
        page: Int,
        size: Int,
        search: String?,
    ): ResponseEntity<UserPage> {
        val stub = UserResponse(
            id = 1L,
            name = "Иван Иванов",
            email = "ivan@example.com",
            role = UserRole.USER,
            createdAt = OffsetDateTime.now(),
        )
        return ResponseEntity.ok(
            UserPage(
                content = listOf(stub),
                totalElements = 1L,
                totalPages = 1,
                page = page,
                propertySize = size,
            )
        )
    }

    override fun createUser(
        createUserRequest: CreateUserRequest,
    ): ResponseEntity<UserResponse> {
        val created = UserResponse(
            id = 42L,
            name = createUserRequest.name,
            email = createUserRequest.email,
            role = createUserRequest.role,
            createdAt = OffsetDateTime.now(),
        )
        return ResponseEntity.status(201).body(created)
    }

    override fun getUserById(id: Long): ResponseEntity<UserResponse> {
        val user = UserResponse(
            id = id,
            name = "Иван Иванов",
            email = "ivan@example.com",
            role = UserRole.USER,
            createdAt = OffsetDateTime.now(),
        )
        return ResponseEntity.ok(user)
    }

    override fun updateUser(
        id: Long,
        updateUserRequest: UpdateUserRequest,
    ): ResponseEntity<UserResponse> {
        val updated = UserResponse(
            id = id,
            name = updateUserRequest.name ?: "Иван Иванов",
            email = updateUserRequest.email ?: "ivan@example.com",
            role = updateUserRequest.role ?: UserRole.USER,
            createdAt = OffsetDateTime.now(),
        )
        return ResponseEntity.ok(updated)
    }

    override fun deleteUser(id: Long): ResponseEntity<Unit> {
        return ResponseEntity.noContent().build()
    }
}
