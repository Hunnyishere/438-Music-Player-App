package com.example.cse438.cse438_assignment2.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.cse438.cse438_assignment2.Data.RadioLoad
import com.example.cse438.cse438_assignment2.Repositories.RadioRepository

class RadioViewModel(application: Application): AndroidViewModel(application) {
    public val radioRepository: RadioRepository =
        RadioRepository()
    public var radioList: MutableLiveData<RadioLoad> = MutableLiveData()

    fun getRadioList(limit:Int){
        radioRepository.getRadios(radioList,limit)
    }



}