package com.diegaspar.recipesbook.base

import com.diegaspar.recipesbook.utils.JsonUtils.Companion.getJSON
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest

open class BaseMockServerTest : KoinTest {

    protected lateinit var mockServer: MockWebServer

    @Before
    open fun setUp() {
        mockServer = MockWebServer()
        mockServer.start()
    }

    @After
    open fun tearDown() {
        mockServer.shutdown()
        stopKoin()
    }

    fun mockHttpResponse(path: String, responseCode: Int) = mockServer.enqueue(
        MockResponse()
            .setResponseCode(responseCode)
            .setBody(getJSON(path))
    )
}