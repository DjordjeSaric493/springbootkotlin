package com.example.quizapi.repository

import com.example.quizapi.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository    //ovo mu dođe kao bean koji koristi Spring za pristup podaCima....
interface UserRepo : JpaRepository<User,Long>{ //generiši crud metode id je tipa Long
    fun findByUsername(username: String): User? //SELECT * FROM users WHERE username = ?
}
