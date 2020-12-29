package com.example.cse438.cse438_assignment2.Repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.cse438.cse438_assignment2.Data.RadioTrack
import com.example.cse438.cse438_assignment2.Data.SearchLoad
import com.example.cse438.cse438_assignment2.network.ApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException


class SearchRepository {
    val service=
        ApiClient.makeRetrofitService()

    fun SearchTrack(resBody:MutableLiveData<SearchLoad>, content:String, limit:Int){
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getTracksByQ(content,limit)
            withContext(Dispatchers.Main){
                try {
                    if (response.isSuccessful){
                        Log.v("aaaa","跑到这里")
                        resBody.value=response.body()
                    }
                }catch (e:HttpException){
                    println("http error")
                }

            }
        }
    }

    fun getTracksByRadioId(resBody:MutableLiveData<RadioTrack>, radioId:Int, limit: Int){
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getTracksByRadioId(radioId,limit)
            withContext(Dispatchers.Main){
                try {
                    if (response.isSuccessful){
                        Log.v("aaaa","跑到这里")
                        resBody.value=response.body()
                    }
                }catch (e:HttpException){
                    println("http error")
                }

            }
        }
    }

}