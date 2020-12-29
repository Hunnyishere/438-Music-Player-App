package com.example.cse438.cse438_assignment2.Repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cse438.cse438_assignment2.Data.TrackInPlaylist
import com.example.cse438.cse438_assignment2.Data.popchart
import com.example.cse438.cse438_assignment2.db.Track
import com.example.cse438.cse438_assignment2.db.TrackDao
import com.example.cse438.cse438_assignment2.network.ApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class TrackRepository(private var trackDao: TrackDao) {

    val service= ApiClient.makeRetrofitService()
    var tracksByPlaylist: LiveData<List<TrackInPlaylist>> = MutableLiveData()

    fun getTracksFromPlaylist(playlist_id: Int){
        tracksByPlaylist = trackDao.getTracksFromPlaylist(playlist_id)
    }

    fun insert(track: Track, playlist_id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
                try {
                    track.playlist_id=playlist_id
                    trackDao.insert(track)
                }catch (e:Exception){
                    println("error")
                }
        }
    }

    fun delete(trackId: Int,playlist_id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
                try {
                    trackDao.delete(trackId,playlist_id)
                }catch (e:Exception){
                    println("error")
                }

        }
    }

    fun SearchTrackById(resBody:MutableLiveData<Track>, trackId:Int){
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getTracksById(trackId)
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


    fun getPopChart(resBody:MutableLiveData<popchart>,limit:Int){
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getPopChart(limit)
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