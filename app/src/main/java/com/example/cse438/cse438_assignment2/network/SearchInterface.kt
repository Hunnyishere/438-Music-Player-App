package com.example.cse438.cse438_assignment2.network

import com.example.cse438.cse438_assignment2.Data.*
import com.example.cse438.cse438_assignment2.db.Track
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchInterface {
    //for search
    @GET("search")
    suspend fun getTracksByQ(@Query("q") content:String,@Query("limit") limit:Int):Response<SearchLoad>
    @GET("track/{trackId}")
    suspend fun getTracksById(@Path("trackId") trackid:Int):Response<Track>
    @GET("chart/tracks")
    suspend fun getPopChart(@Query("limit") limit: Int):Response<popchart>

    //for radio
    @GET("radio/top")
    suspend fun getRadios(@Query("limit") limit:Int):Response<RadioLoad>
    @GET("radio/{radioId}/tracks")
    suspend fun getTracksByRadioId(@Path("radioId") radioid:Int,@Query("limit") limit:Int):Response<RadioTrack>
}