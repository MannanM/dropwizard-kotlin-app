package com.mannanlive.dropwizardkotlinapp

import com.mannanlive.dropwizardkotlinapp.clients.PwnedClient
import com.mannanlive.dropwizardkotlinapp.configuration.AppConfig
import com.mannanlive.dropwizardkotlinapp.resources.EchoResource
import com.mannanlive.dropwizardkotlinapp.resources.PwnedResource
import com.mannanlive.dropwizardkotlinapp.services.PwnedService
import io.dropwizard.Application
import io.dropwizard.client.JerseyClientBuilder
import io.dropwizard.setup.Environment

class App : Application<AppConfig>() {

    companion object {
        @JvmStatic fun main(args : Array<String>) = App().run(*args)
    }

    override fun run(config: AppConfig, env: Environment) {
        env.jersey().register(EchoResource(config.testProp))

        //Part 5 - Connectivity
        val client = JerseyClientBuilder(env).using(config.httpClient).build("httpClient")
        val pwnedClient = PwnedClient(config.haveIBeenPwnedUrl, client)
        env.jersey().register(PwnedResource(PwnedService(pwnedClient)))
    }
}