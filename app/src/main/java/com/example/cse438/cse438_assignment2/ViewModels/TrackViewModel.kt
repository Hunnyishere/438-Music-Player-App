package com.example.cse438.cse438_assignment2.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cse438.cse438_assignment2.Data.TrackInPlaylist
import com.example.cse438.cse438_assignment2.Data.popchart
import com.example.cse438.cse438_assignment2.db.Track
import com.example.cse438.cse438_assignment2.Repositories.TrackRepository
import com.example.cse438.cse438_assignment2.db.TrackRoomDatabase

class TrackViewModel(application: Application) : AndroidViewModel(application) {
    public var track: MutableLiveData<Track> = MutableLiveData()

    public var poptracks:MutableLiveData<popchart> = MutableLiveData()
    public var trackRepository: TrackRepository =
        TrackRepository(
            TrackRoomDatabase.getDatabase(application).TrackDao()
        )
    public var trackInPlaylist: LiveData<List<TrackInPlaylist>> = MutableLiveData()

    fun SearchTrackById(trackId:Int){
        trackRepository.SearchTrackById(track,trackId)
    }

    fun getPopChart(limit:Int){
        trackRepository.getPopChart(poptracks,limit)
    }

    fun getTracksInPlaylists(playlist_id: Int){
        trackRepository.getTracksFromPlaylist(playlist_id)
        trackInPlaylist = trackRepository.tracksByPlaylist
    }
    fun addToPlayList(track:Track, playlist_id: Int){
        trackRepository.insert(track,playlist_id)
    }

    fun removeTrack(trackId: Int,playlist_id: Int){
        trackRepository.delete(trackId,playlist_id)
    }

}