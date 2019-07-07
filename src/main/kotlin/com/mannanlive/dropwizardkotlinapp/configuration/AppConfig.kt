package com.mannanlive.dropwizardkotlinapp.configuration

import com.fasterxml.jackson.annotation.JsonProperty
import io.dropwizard.Configuration
import io.dropwizard.client.JerseyClientConfiguration

class AppConfig(
        @JsonProperty("testProp") val testProp: String,
        //Part 5 - Connectivity
        @JsonProperty("haveIBeenPwnedUrl") val haveIBeenPwnedUrl : String,
        @JsonProperty("httpClient") val httpClient : JerseyClientConfiguration
) : Configuration()