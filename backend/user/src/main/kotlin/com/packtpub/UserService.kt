package com.packtpub

interface UserService {
    fun getUser(id: Long): PacktUser?
}

internal class UserServiceImpl
(private val userRepository: UserRepository) : UserService {
    override fun getUser(id: Long): PacktUser? =
        userRepository.findById(id).orElse(null)
}