package com.example.marver_characters.data.network.util

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class AuthHelper {

    val publicKey: String =
        "0c44bd2da640b1f280b6fd3f7cae6612"

    private val privateKey: String =
        "eca4fb4c3bd898860e6ef2280f061f0846912ce9"

    val timestamp: String =
        System.currentTimeMillis().toString()

    val md5Key: String by lazy {
        val input: String = timestamp + privateKey + publicKey
        try {
            val digest = MessageDigest.getInstance("MD5")
            digest.update(input.toByteArray())
            val messageDigest = digest.digest()

            val hexString = StringBuilder()
            for (aMessageDigest in messageDigest) {
                var h = Integer.toHexString(0xFF and aMessageDigest.toInt())
                while (h.length < 2) h = "0$h"
                hexString.append(h)
            }
            return@lazy hexString.toString()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        return@lazy ""
    }

}