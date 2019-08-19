package com.example.data

import android.content.Context
import android.util.Log
import com.example.domain.ErrorResponse
import com.example.domain.HttpErrors
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okio.Buffer
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.net.SocketTimeoutException

/**
 * Created by Alexander Fermin (alexfer06@gmail.com) on 2019-08-19.
 */
class ApiErrorResponseInterceptor : Interceptor, KoinComponent {

    private val context: Context by inject()

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        try {
            return proceedRequest(chain, request)
        } catch (exception: Exception) {
            Log.e("ApiErrorResponse", "message = ${exception.message}", exception)
            val reportError = transformToErrorResponse(exception, request)
            throw reportError
        }
    }

    private fun proceedRequest(chain: Interceptor.Chain, request: Request): Response {
        val response = chain.proceed(request)
        when (response.code) {
            in 200..299 -> return response
            in 400..403 union 405..499 -> {
                val jsonResponse = JsonParser().parse(response.body?.string() ?: "{}").asJsonObject
                throw createBackendErrorResponse(response, jsonResponse)
            }
            else -> throw createErrorResponse(request, null, HttpErrors.UNDEFINED)
        }
    }

    private fun transformToErrorResponse(exception: Exception, request: Request): ErrorResponse {
        return when (exception) {
            is ErrorResponse -> exception
            is SocketTimeoutException -> createErrorResponse(request, exception, HttpErrors.TIME_OUT)
            else -> createErrorResponse(request, exception, HttpErrors.UNDEFINED)
        }
    }

    private fun createBackendErrorResponse(response: Response, jsonResponse: JsonObject): ErrorResponse {
        return ErrorResponse(
            httpErrorType = HttpErrors.BACKEND,
            errorCode = response.code,
            message = jsonResponse["error"].asJsonObject["message"].asString
        )
    }

    private fun createErrorResponse(request: Request, exception: Exception?, httpError: HttpErrors): ErrorResponse {
        return ErrorResponse(
            httpErrorType = httpError,
            message = getErrorMessage(httpError),
            cause = exception,
            url = request.url.toString(),
            method = request.method,
            body = Buffer().apply {
                request.body?.writeTo(this)
            }.readUtf8()
        )
    }

    private fun getErrorMessage(httpError: HttpErrors): String {
        return when (httpError) {
            HttpErrors.TIME_OUT -> context.getString(R.string.error_time_out)
            HttpErrors.NO_INTERNET -> context.getString(R.string.error_internet)
            else -> context.getString(R.string.error_generic)
        }
    }
}
