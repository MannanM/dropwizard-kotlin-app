package com.mannanlive.dropwizardkotlinapp.resources

import com.mannanlive.dropwizardkotlinapp.App
import com.mannanlive.dropwizardkotlinapp.configuration.AppConfig
import io.dropwizard.testing.FixtureHelpers.fixture
import io.dropwizard.testing.ResourceHelpers.resourceFilePath
import io.dropwizard.testing.junit.DropwizardAppRule
import org.assertj.core.api.Assertions.assertThat
import org.junit.ClassRule
import org.junit.Test
import javax.ws.rs.client.ClientBuilder

class EchoResourceIntegrationTest {
    companion object {
        @JvmField
        @ClassRule
        val RULE = DropwizardAppRule<AppConfig>(App::class.java,
                resourceFilePath("app-config.yml"))
    }

    @Test
    fun `can GET echo resource successfully`() {
        val response = ClientBuilder.newClient()
                .target("http://localhost:${RULE.localPort}/echo")
                .request()
                .get()!!

        assertThat(response.status).isEqualTo(200)
        assertThat(response.readEntity(String::class.java))
                .isEqualTo("{\"query\":\"default\"," +
                        "\"prop\":\"abc\"," +
                        "\"number\":123," +
                        "\"bool\":false," +
                        "\"array\":[456,789]," +
                        "\"map\":{\"rate\":11.5}," +
                        "\"date\":\"31-01-2019 10:20:42\"}")
    }

    @Test
    fun `can GET echo resource successfully with fixture`() {
        val response = ClientBuilder.newClient()
                .target("http://localhost:${RULE.localPort}/echo")
                .queryParam("query", "foo-bar")
                .request()
                .get()!!

        assertThat(response.status).isEqualTo(200)
        assertThat(response.readEntity(String::class.java))
                .isEqualToIgnoringWhitespace(fixture("fixtures/echo.json"))
    }
}
