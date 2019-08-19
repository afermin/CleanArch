package com.example.domain

/**
 * Created by Alexander Fermin (alexfer06@gmail.com) on 2019-08-19.
 */
data class ErrorResponse(

    val httpErrorType: HttpErrors,

    override val cause: Throwable? = null,

    override val message: String = "",

    val errorCode: Int = 0,

    val url: String = "",

    val method: String = "",

    val body: String = "",

    val headers: String = ""

): Exception()

enum class HttpErrors {
    TIME_OUT, NO_INTERNET, BACKEND, FORCE_LOGOUT, UNDEFINED
}
