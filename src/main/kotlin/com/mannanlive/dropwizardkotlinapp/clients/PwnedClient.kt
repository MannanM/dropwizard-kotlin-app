package com.mannanlive.dropwizardkotlinapp.clients

import com.google.common.cache.CacheBuilder
import com.google.common.cache.CacheLoader
import com.google.common.cache.LoadingCache
import java.util.concurrent.TimeUnit
import javax.ws.rs.client.Client

class PwnedClient(url: String, client: Client) {
    private val target = client.target(url)

    fun getHashes(hashPrefix: String): Map<String, Int> {
        println(hashPrefix)
        val response = target
                .path("range")
                .path(hashPrefix)
                .request()
                .get()
        val output = response.readEntity(String::class.java)
        return if (response.status == 200) {
            output.lines().map { line ->
                val sections = line.split(":")
                hashPrefix + sections[0] to sections[1].toInt()
            }.toMap()
        } else {
            throw RuntimeException("Error: ${response.status}, Body: $output")
        }
    }

    //bonus caching exercise
    private val cache: LoadingCache<String, Map<String, Int>> = CacheBuilder.newBuilder()
            .expireAfterWrite(24, TimeUnit.HOURS)
            .build(CacheLoader.from { inputHash -> getHashes(inputHash!!) })

    fun getHashesWithCache(inputHash: String): Map<String, Int> = cache.get(inputHash)
}


