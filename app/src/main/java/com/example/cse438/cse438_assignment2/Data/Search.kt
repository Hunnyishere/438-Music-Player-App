package com.example.cse438.cse438_assignment2.Data


data class Search(
    val id:Int,
    val readable:Boolean,
    val title:String,
    val title_short:String,
    val title_version:String,
    val link:String,
    val duration: Int,
    val rank:Int,
    val explicit_lyrics:Boolean,
    val explicit_content_lyrics: Int,
    val explicit_content_cover: Int,
    val preview:String,
    val artist: artist,
    val album: album,
    val type:String
)

data class SearchLoad(
    val data:List<Search>,
    val total:Int,
    val next:String
)

data class RadioTrack(
    val data:List<Search>
)