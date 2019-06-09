package com.losingtimeapps.fababellatest.domain.entity

data class User(val userName: String, val password: String = "") {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (userName != other.userName) return false
        if (password != other.password) return false

        return true
    }

    override fun hashCode(): Int {
        var result = userName.hashCode()
        result = 31 * result + password.hashCode()
        return result
    }
}