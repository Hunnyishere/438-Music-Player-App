package com.example.cse438.cse438_assignment2.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "playlists")
data class Playlist(
        @PrimaryKey(autoGenerate = true)  //autoGenerate=false allows db records with different ids and identical values
        val id: Int,
        @ColumnInfo(name = "name")
        val name: String,
        @ColumnInfo(name = "description")
        val description: String,
        @ColumnInfo(name = "genre")
        val genre: String,
        @ColumnInfo(name = "rating")
        val rating: String
)