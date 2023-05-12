package com.example.personalexpenditure.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.personalexpenditure.model.Expenditure
import com.example.personalexpenditure.model.Income

import com.example.personalexpenditure.model.TotalResponse


import com.example.personalexpenditure.repository.GetIncomeRepository
import com.example.personalexpenditure.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetIncomeViewModel @Inject constructor(private val repository: GetIncomeRepository): ViewModel() {

    private var totalLiveData = MutableLiveData<Resource<TotalResponse?>>()
    private var incomeLiveData = MutableLiveData<Resource<Income?>>()



    fun getTotalExpenditure(userId: String) = viewModelScope.launch {
        repository.getTotal(userId).collect(){
            totalLiveData.postValue(it)
        }

    }

    fun observeTotalExpenditureLiveData(): LiveData<Resource<TotalResponse?>>{
        return totalLiveData
    }



//    fun getTotalIncome(userId: String) = viewModelScope.launch {
//        repository.getTotalIncome(userId).collect() {
//            incomeLiveData.postValue(it)
//        }
//    }


//        fun observeTotalIncomeLiveData(): LiveData<Resource<Income?>> {
//            return incomeLiveData
//        }

}