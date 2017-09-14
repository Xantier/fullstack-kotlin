package com.packtpub

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

interface UserRepository : CrudRepository<PacktUser, Long> {
    fun findOneByUsername(username: String): Optional<PacktUser>
}