package byronlogin.controller.repository

import byronlogin.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository:JpaRepository<User,Int> {
    fun findByEmail(email:String):User?

}