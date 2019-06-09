package com.losingtimeapps.fababellatest.domain.entity

import com.losingtimeapps.fababellatest.domain.utils.Error


class ResponseModel<T> {


    constructor(data: T) {
        this.data = data
    }

    constructor(error: Error) {
        this.error = error
    }

    var data: T? = null

    var error: Error? = null

    fun dataIsEmpty(): Boolean = data == null


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ResponseModel<*>

        if (data != other.data) return false
        if (error != other.error) return false

        return true
    }

    override fun hashCode(): Int {
        var result = data?.hashCode() ?: 0
        result = 31 * result + (error?.hashCode() ?: 0)
        return result
    }
}