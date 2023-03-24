package com.example.personalexpenditure.api

import com.example.personalexpenditure.MockFileReader
import com.example.personalexpenditure.model.PostData
import com.example.personalexpenditure.utils.Resource
import com.example.personalexpenditure.utils.Status
import kotlinx.coroutines.runBlocking
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
import org.mockito.Mock
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

    @Mock
    lateinit var postData: PostData
    private lateinit var api: IncomePostApiService

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

    @Test
    suspend fun `postIncome returns success response`() {
        // Create a mock response with a success status code and a JSON body
        val responseJson = """{"message": "Income posted successfully"}"""
        val response = MockResponse()
            .setResponseCode(201)
            .setBody(responseJson)
            .setHeader("Content-Type", "application/json")

        // Enqueue the mock response to be returned by the MockWebServer
        mockWebServer.enqueue(response)

        // Call the postIncome method and capture the result
        val result = incomeApiImpl.postIncome(postData)

        // Assert that the result is a success response with the correct data and status code
        assertEquals(Status.SUCCESS, result.status)
    }


}


