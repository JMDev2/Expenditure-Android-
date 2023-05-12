package com.example.personalexpenditure.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.personalexpenditure.model.Expenditure
import com.example.personalexpenditure.model.Income

import com.example.personalexpenditure.repository.PostRepository
import com.example.personalexpenditure.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class IncomePostViewModel @Inject constructor(private val repository: PostRepository): ViewModel(){

    private val incomeLiveData = MutableLiveData<Resource<Income?>>()
    private val expenditureLiveData = MutableLiveData<Resource<Expenditure?>>()

    //income post
    fun postIncome(userId: String, postIncomeResponse: Income) = viewModelScope.launch {
        repository.createPost(userId, postIncomeResponse).collect(){ response ->
            incomeLiveData.setValue(response)
        }
    }

    fun observePostIncomeLiveData(): LiveData<Resource<Income?>> {
        return incomeLiveData
    }


    /*
    expenditure
    */
    fun postExpenditure(userId: String, expenditure: Expenditure) = viewModelScope.launch {
        repository.postExpenditure(userId, expenditure).collect(){
            expenditureLiveData.setValue(it)
        }
    }
    fun observePostExpenditureLiveData(): LiveData<Resource<Expenditure?>>{
        return expenditureLiveData
    }


}