package com.example.cse438.cse438_assignment2.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cse438.cse438_assignment2.db.Playlist
import com.example.cse438.cse438_assignment2.Repositories.PlaylistRepository
import com.example.cse438.cse438_assignment2.db.TrackRoomDatabase

class PlaylistViewModel(application: Application):AndroidViewModel(application) {
    public val playlistRepository: PlaylistRepository
    public var playList:LiveData<List<Playlist>> = MutableLiveData()
    init {
        playlistRepository=
            PlaylistRepository(
                TrackRoomDatabase.getDatabase(application).PlaylistDao()
            )
    }

    fun getAllPlaylist(){
        playlistRepository.getPlayLists()
        playList = playlistRepository.playList
    }


    fun insertPlayList(playlist: Playlist){
        playlistRepository.insert(playlist)
    }

}