package com.example.marver_characters.data.network.interceptor

import com.cookiedev.move.data.network.util.ConnectionManager
import com.example.marver_characters.data.network.*
import okhttp3.Interceptor
import okhttp3.Response

internal class ResponseInterceptor(private val connectionManager: ConnectionManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        connectionManager.checkConnection()

        val request = chain.request()
        val response = chain.proceed(request)

        return response.checkResponse()
    }

    private fun Response.checkResponse(): Response {
        when (code()) {
            400 -> throw  BadRequestException()
            401 -> throw  UnauthorizedException()
            403 -> throw  ForbiddenException()
            404 -> throw  NotFoundException()
            500 -> throw  ServerProblemException()
        }

        return this
    }
}