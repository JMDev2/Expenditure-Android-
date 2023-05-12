package com.example.personalexpenditure.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.personalexpenditure.MockFileReader
import com.example.personalexpenditure.TestCoroutineRule
import com.example.personalexpenditure.model.IncomeResponse
import com.example.personalexpenditure.repository.GetIncomeRepository
import com.example.personalexpenditure.utils.Resource
import com.google.gson.Gson
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GetIncomeViewModelTest {
    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    @ExperimentalCoroutinesApi
    @get:Rule
    val testCoroutinesRule = TestCoroutineRule()
    private lateinit var getIncomeViewModel: GetIncomeViewModel
    private lateinit var getIncomeRepository: GetIncomeRepository
    private val response = MockFileReader("get_income_response.json").content


    @Before
    fun setUp() {
        getIncomeRepository = mockk()
        getIncomeViewModel = GetIncomeViewModel(getIncomeRepository)
    }

    @Test
    fun `return income` (){
        val jsonResponse = Gson().fromJson(response, IncomeResponse::class.java)
        val responseResource = Resource.success(jsonResponse)
        coEvery {
            getIncomeRepository.getIncome()
        } returns flow {
            emit(Resource.loading(null))
            emit(responseResource)
        }
        getIncomeViewModel.getIncome()
        val income = getIncomeViewModel.observeIncomeLiveData().value
        assertEquals(responseResource, income)
    }

}