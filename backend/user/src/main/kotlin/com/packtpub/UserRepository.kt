package com.packtpub

import org.springframework.data.repository.CrudRepository
import java.util.*


internal interface UserRepository : CrudRepository<PacktUser, Long> {
    fun findOneByUsername(username: String): Optional<PacktUser>
}