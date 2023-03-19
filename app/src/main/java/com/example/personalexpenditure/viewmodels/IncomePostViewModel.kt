package com.example.personalexpenditure.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.personalexpenditure.model.PostData
import com.example.personalexpenditure.repository.IncomeRepository
import com.example.personalexpenditure.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class IncomePostViewModel @Inject constructor(private val repository: IncomeRepository): ViewModel(){

    private val incomeLiveData = MutableLiveData<Resource<PostData?>>()


    fun postIncome(postData: PostData) = viewModelScope.launch {
        repository.createPost(postData).collect(){
            incomeLiveData.postValue(it)
        }
    }

    fun obserePostIncomeLiveData(): LiveData<Resource<PostData?>> {
        return incomeLiveData
    }


}