package com.example.cse438.cse438_assignment2.Repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.cse438.cse438_assignment2.Data.RadioLoad
import com.example.cse438.cse438_assignment2.network.ApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException


class RadioRepository {
    val service=
        ApiClient.makeRetrofitService()

    fun getRadios(resBody:MutableLiveData<RadioLoad>,limit:Int){
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getRadios(limit)
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