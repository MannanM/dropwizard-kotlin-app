package com.mannanlive.dropwizardkotlinapp.resources

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.ObjectWriter
import org.junit.Assert
import org.junit.Test

class EchoResourceTest {
    val mapper: ObjectWriter = ObjectMapper().writerWithDefaultPrettyPrinter()

    @Test
    fun `echo resource prints json correctly`() {
        val echoResource = EchoResource("unit-test")

        val response = echoResource.get("query")

        val json = mapper.writeValueAsString(response)

        Assert.assertEquals("""
            {
              "query" : "query",
              "prop" : "unit-test",
              "number" : 123,
              "bool" : false,
              "array" : [ 456, 789 ],
              "map" : {
                "rate" : 11.5
              },
              "date" : "31-01-2019 10:20:42"
            }
        """.trimIndent(), json)
    }
}
