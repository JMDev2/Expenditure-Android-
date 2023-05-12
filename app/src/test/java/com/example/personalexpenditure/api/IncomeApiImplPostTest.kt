package com.example.personalexpenditure.api

import com.example.personalexpenditure.MockFileReader
import com.example.personalexpenditure.utils.Status


import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class IncomeApiImplPostTest {
    private lateinit var apiService: IncomePostApiService
    private lateinit var incomeApiImpl: IncomeApiImpl

    private lateinit var mockWebServer: MockWebServer

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

        apiService = mock(IncomePostApiService::class.java)
        incomeApiImpl = IncomeApiImpl(apiService)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }



    @Test
    fun testPostIncome_Successful() = runBlocking {
        val postData = PostData(0, 1000)
        val responseBody = PostData(0, 1000)
        val response = Response.success(201, responseBody)
        `when`(apiService.postIncome(postData)).thenReturn(response)

        val result = incomeApiImpl.postIncome(postData)

     //   assertTrue(result is Resource.Error)
        //assertEquals(responseBody, result.data)
        assertEquals(Status.SUCCESS, result.status)
    }

    @Test
    fun testPostIncome_Unsuccessful() = runBlocking {
        val postData = PostData(0, 1000)
        val response = Response.error<PostData>(404, mock(ResponseBody::class.java))
        `when`(apiService.postIncome(postData)).thenReturn(response)

        val result = incomeApiImpl.postIncome(postData)

       // assertTrue(Resource.Error)
        assertEquals("Not posted", result.message)
        assertNull(result.data)
    }

}