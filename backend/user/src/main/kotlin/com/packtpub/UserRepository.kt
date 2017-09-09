package com.packtpub

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<PacktUser, Long>{
    fun findByUsername(username: String): PacktUser
}