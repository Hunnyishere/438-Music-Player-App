package com.example.cse438.cse438_assignment2.Data

import com.example.cse438.cse438_assignment2.db.Track


data class Radio(
    val id:Int,
    val title:String,
    val picture:String,
    val picture_small:String,
    val picture_medium:String,
    val picture_big:String,
    val picture_xl: String,
    val tracklist:String,
    val type:String
)

data class RadioLoad(
    val data:List<Radio>,
    val total:Int,
    val next:String
)