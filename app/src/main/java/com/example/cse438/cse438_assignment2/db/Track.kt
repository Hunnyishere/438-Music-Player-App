package com.example.cse438.cse438_assignment2.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.cse438.cse438_assignment2.Data.album
import com.example.cse438.cse438_assignment2.Data.artist

@Entity(tableName = "tracks", primaryKeys = arrayOf("id","playlist_id"))
data class Track(
        val id: Int,
        @ColumnInfo(name = "title")
        val title: String,
        @ColumnInfo(name = "artistname")
        var artistname: String,
        @ColumnInfo(name = "duration")
        val duration:Int,
        @ColumnInfo(name = "track_position")
        val track_position:Int,
        @ColumnInfo(name = "rank")
        val rank:Int,
        @ColumnInfo(name = "gain")
        val gain:Float,
        @ColumnInfo(name = "preview")
        val preview:String,
        @ColumnInfo(name = "release_date")
        val release_date: String,
        var playlist_id: Int,
        @ColumnInfo(name= "cover_medium")
        var cover_medium:String
)
