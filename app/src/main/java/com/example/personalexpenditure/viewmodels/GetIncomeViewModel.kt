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
import javax.inject.Inject

@HiltViewModel
class GetIncomeViewModel @Inject constructor(private val repository: GetIncomeRepository): ViewModel() {

    private var incomeLiveData = MutableLiveData<Resource<IncomeResponse?>>()


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
}