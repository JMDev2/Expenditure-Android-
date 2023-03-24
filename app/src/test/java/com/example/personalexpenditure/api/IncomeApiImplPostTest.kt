package com.example.personalexpenditure.api

import com.example.personalexpenditure.model.PostData
import com.example.personalexpenditure.utils.Resource


import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import retrofit2.Response
import java.io.IOException

class IncomeApiImplPostTest {
    private lateinit var apiService: IncomePostApiService
    private lateinit var incomeApiImpl: IncomeApiImpl


    @Before
    fun setUp() {
        apiService = mock(IncomePostApiService::class.java)
        incomeApiImpl = IncomeApiImpl(apiService)
    }

    @Test
    fun testPostIncome_Successful() = runBlocking {
        val postData = PostData(0, 1000)
        val responseBody = PostData(0, 1000)
        val response = Response.success(201, responseBody)
        `when`(apiService.postIncome(postData)).thenReturn(response)

        val result = incomeApiImpl.postIncome(postData)

     //   assertTrue(result is Resource.Error)
        assertEquals(responseBody, result.data)
        assertEquals(201, result.status)
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