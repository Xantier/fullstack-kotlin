package com.packtpub


interface UserService {
    fun getUser(id: Long): PacktUser?
    fun getUserByName(name: String): PacktUser?
}
internal class UserServiceImpl
(private val userRepository: UserRepository) : UserService {
    override fun getUserByName(name: String): PacktUser? =
        userRepository.findOneByUsername(name).orElse(null)

    override fun getUser(id: Long): PacktUser? =
        userRepository.findById(id).orElse(null)
}