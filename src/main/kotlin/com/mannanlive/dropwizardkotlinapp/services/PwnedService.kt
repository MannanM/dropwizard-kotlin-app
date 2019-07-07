package com.mannanlive.dropwizardkotlinapp.services

import com.mannanlive.dropwizardkotlinapp.clients.PwnedClient
import java.security.MessageDigest

data class PwnedResult(val input: String, val timesPwned: Int)

class PwnedService(private val client: PwnedClient) {
    fun getNumberOfTimesPwned(password: String): PwnedResult {
        val shaHash = hashString("SHA-1", password)
        val firstFiveChars = shaHash.substring(0, 5)
        val potentialMatches = client.getHashes(firstFiveChars)
        val numberOfTimesPwned: Int = potentialMatches[shaHash] ?: 0
        return PwnedResult(password, numberOfTimesPwned)
    }

    /**
     * Hashing Utils
     * @author Sam Clarke <www.samclarke.com>
     * @license MIT
     */
    private fun hashString(type: String, input: String): String {
        val HEX_CHARS = "0123456789ABCDEF"
        val bytes = MessageDigest
                .getInstance(type)
                .digest(input.toByteArray())
        val result = StringBuilder(bytes.size * 2)

        bytes.forEach {
            val i = it.toInt()
            result.append(HEX_CHARS[i shr 4 and 0x0f])
            result.append(HEX_CHARS[i and 0x0f])
        }

        return result.toString()
    }
}
