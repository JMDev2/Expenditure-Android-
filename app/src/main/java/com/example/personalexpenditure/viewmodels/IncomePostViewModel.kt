package com.example.personalexpenditure.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.personalexpenditure.model.Expenditure
import com.example.personalexpenditure.model.PostData
import com.example.personalexpenditure.repository.IncomeRepository
import com.example.personalexpenditure.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class IncomePostViewModel @Inject constructor(private val repository: IncomeRepository): ViewModel(){

    private val incomeLiveData = MutableLiveData<Resource<PostData?>>()
    private val expenditureLiveData = MutableLiveData<Resource<Expenditure?>>()

    //income post
    fun postIncome(postData: PostData) = viewModelScope.launch {
        repository.createPost(postData).collect(){ response ->
            incomeLiveData.setValue(response)
        }
    }

    fun observePostIncomeLiveData(): LiveData<Resource<PostData?>> {
        return incomeLiveData
    }


    /*
    expenditure
    */
    fun postExpenditure(incomeId: String, expenditure: Expenditure) = viewModelScope.launch {
        repository.postExpenditure(incomeId, expenditure).collect(){
            expenditureLiveData.setValue(it)
        }
    }
    fun observePostExpenditureLiveData(): LiveData<Resource<Expenditure?>>{
        return expenditureLiveData
    }


}