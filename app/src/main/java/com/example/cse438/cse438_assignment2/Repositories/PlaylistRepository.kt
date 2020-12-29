package com.example.cse438.cse438_assignment2.Repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cse438.cse438_assignment2.db.Playlist
import com.example.cse438.cse438_assignment2.db.PlaylistDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlaylistRepository(private val playlistDao: PlaylistDao) {
    public var playList:LiveData<List<Playlist>> = MutableLiveData()
    fun getPlayLists() {
            Log.v("里面", playlistDao.getPlaylists().toString())
           playList = playlistDao.getPlaylists()

    }

    fun insert(playlist: Playlist) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                playlistDao.insert(playlist)
            }catch (e:Exception){
                print("error")
            }

        }
    }
}