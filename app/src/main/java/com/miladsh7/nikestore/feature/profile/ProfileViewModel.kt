package com.miladsh7.nikestore.feature.profile

import com.miladsh7.nikestore.common.NikeViewModel
import com.miladsh7.nikestore.data.TokenContainer
import com.miladsh7.nikestore.data.repo.UserRepository


class ProfileViewModel(private val userRepository: UserRepository) : NikeViewModel() {
    val username: String
        get() = userRepository.getUserName()

    val isSignedIn: Boolean
        get() = TokenContainer.token != null

    fun signOut() = userRepository.signOut()
}