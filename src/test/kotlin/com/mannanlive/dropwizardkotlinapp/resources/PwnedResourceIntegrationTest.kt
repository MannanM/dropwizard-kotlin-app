package com.mannanlive.dropwizardkotlinapp.resources

import com.mannanlive.dropwizardkotlinapp.App
import com.mannanlive.dropwizardkotlinapp.configuration.AppConfig
import io.dropwizard.testing.ResourceHelpers.resourceFilePath
import io.dropwizard.testing.junit.DropwizardAppRule
import org.assertj.core.api.Assertions.assertThat
import org.junit.ClassRule
import org.junit.Test
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.core.Response

data class ExpectedResult(
        val input: String? = null,
        val timesPwned: Int? = null)

class PwnedResourceIntegrationTest {
    companion object {
        @JvmField
        @ClassRule
        val RULE = DropwizardAppRule<AppConfig>(App::class.java,
                resourceFilePath("app-config.yml"))
    }

    @Test
    fun `can GET pwned password successfully`() {
        val response = sendRequest("hackme")
        assertThat(response.status).isEqualTo(200)
        val result = response.readEntity(ExpectedResult::class.java)
        assertThat(result.timesPwned).isGreaterThan(2000)
        assertThat(result.input).isEqualTo("hackme")
    }

    @Test
    fun `can GET unique password successfully`() {
        val response = sendRequest("hackme1014")
        assertThat(response.status).isEqualTo(404)
        val result = response.readEntity(ExpectedResult::class.java)
        assertThat(result.timesPwned).isEqualTo(0)
        assertThat(result.input).isEqualTo("hackme1014")
    }

    private fun sendRequest(inputPassword: String): Response = ClientBuilder.newClient()
            .target("http://localhost:${RULE.localPort}/pwned?password=$inputPassword")
            .request()
            .get()!!
}
