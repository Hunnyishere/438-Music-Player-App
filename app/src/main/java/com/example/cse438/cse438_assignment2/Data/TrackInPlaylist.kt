package com.example.cse438.cse438_assignment2.Data

import androidx.room.Entity

//@Entity
data class TrackInPlaylist(
    val id: Int,
    val title:String,
    val artistname:String,
    val genre: String,
    val release_date:String,
    val rating:String
)