package byronlogin.services

import byronlogin.controller.repository.UserRepository
import byronlogin.model.User
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {
    fun save (user: User): User {
        return this.userRepository.save(user)
    }

    fun findByEmail(email:String):User?{
        return this.userRepository.findByEmail(email)
    }

    fun getByid(id:Int):User{
        return this.userRepository.getById(id)

    }

    }