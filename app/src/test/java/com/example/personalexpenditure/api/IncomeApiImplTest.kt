package com.example.personalexpenditure.api

import com.example.personalexpenditure.MockFileReader
import com.example.personalexpenditure.utils.Status
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.net.HttpURLConnection

@RunWith(JUnit4::class)
class IncomeApiImplTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var incomeApiImpl: IncomeApiImpl
    private var okHttpClient = OkHttpClient.Builder()
        .build()
    private val response = MockFileReader("get_income_response.json").content


    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        val incomePostApi = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IncomePostApiService::class.java)
        incomeApiImpl = IncomeApiImpl(incomePostApi)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }


    @Test
    fun `for income, returns income set`() = runTest {
        val expectedResponse = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(response)
        mockWebServer.enqueue(expectedResponse)

        val actualResponse = incomeApiImpl.getIncomes()
        assertEquals(Status.SUCCESS, actualResponse.status)
    }


    @Test
    fun `for server error, return`() = runTest {
        val expectedResponse = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_INTERNAL_ERROR)
            mockWebServer.enqueue(expectedResponse)

        val actualResponse = incomeApiImpl.getIncomes()
        assertEquals(Status.ERROR, actualResponse.status)
    }
}


