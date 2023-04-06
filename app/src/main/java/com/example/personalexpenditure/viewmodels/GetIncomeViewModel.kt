package com.example.personalexpenditure.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.personalexpenditure.model.IncomeResponse
import com.example.personalexpenditure.repository.GetIncomeRepository
import com.example.personalexpenditure.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import javax.inject.Inject

@HiltViewModel
class GetIncomeViewModel @Inject constructor(private val repository: GetIncomeRepository): ViewModel() {

    private var incomeLiveData = MutableLiveData<Resource<IncomeResponse?>>()
    private var expenditureLiveData = MutableLiveData<Resource<Int?>>()


    init {

        getIncome()
    }

    fun getIncome() = viewModelScope.launch {
        repository.getIncome().collect(){
            incomeLiveData.postValue(it)
        }

    }

    fun observeIncomeLiveData(): LiveData<Resource<IncomeResponse?>>{
        return incomeLiveData
    }



    fun getExpenditure(expenditureId: String) = viewModelScope.launch {
        repository.getExpenditure(expenditureId).collect() {
            expenditureLiveData.postValue(it)
        }
    }


        fun observeExpenditureLiveData(): LiveData<Resource<Int?>> {
            return expenditureLiveData
        }

}