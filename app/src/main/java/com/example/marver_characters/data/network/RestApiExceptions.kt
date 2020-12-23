package com.example.marver_characters.data.network

open class RestApiException : Exception()

class NetworkConnectionException : RestApiException()
class BadRequestException : RestApiException()
class UnauthorizedException : RestApiException()
class ForbiddenException : RestApiException()
class NotFoundException : RestApiException()
class ServerProblemException : RestApiException()
class TimeOutException: RestApiException()