package com.example.cse438.cse438_assignment2.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.cse438.cse438_assignment2.Data.RadioTrack
import com.example.cse438.cse438_assignment2.Data.SearchLoad
import com.example.cse438.cse438_assignment2.Repositories.SearchRepository

class SearchViewModel(application: Application) : AndroidViewModel(application) {
    public  var searchList: MutableLiveData<SearchLoad> = MutableLiveData()
    public var searchRepository: SearchRepository =
        SearchRepository()
    public var trackSearchinRadio: MutableLiveData<RadioTrack> = MutableLiveData()

    fun SearchTrack(content :String, limit:Int){
        searchRepository.SearchTrack(searchList,content,limit)
    }



    fun getTracksInRadio(radioId:Int, limit: Int){
        searchRepository.getTracksByRadioId(trackSearchinRadio,radioId,limit)
    }


}