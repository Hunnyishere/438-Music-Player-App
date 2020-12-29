package com.example.cse438.cse438_assignment2.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.cse438.cse438_assignment2.Data.TrackInPlaylist

@Dao
interface TrackDao {

    @Query("SELECT tracks.id, tracks.title, tracks.artistname, playlists.genre, tracks.release_date, playlists.rating FROM tracks INNER JOIN playlists ON tracks.playlist_id = playlists.id AND `playlist_id` =:playlist_id")
    fun getTracksFromPlaylist(playlist_id: Int): LiveData<List<TrackInPlaylist>>

//    @Query("SELECT * FROM tracks WHERE `playlist_id`=:playlist_id")


    @Insert
    fun insert(track: Track)

    @Query("DELETE FROM tracks WHERE `id`=:trackId AND `playlist_id` = :playlist_id")
    fun delete(trackId: Int,playlist_id: Int)
}