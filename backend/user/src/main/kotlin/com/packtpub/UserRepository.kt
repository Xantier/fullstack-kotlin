package com.packtpub

import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<PacktUser, Long>{
    fun findByUsername(username: String): PacktUser
}