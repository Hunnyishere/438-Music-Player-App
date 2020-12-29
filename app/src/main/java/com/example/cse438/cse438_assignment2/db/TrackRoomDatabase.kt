package com.example.cse438.cse438_assignment2.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = arrayOf(Playlist::class,Track::class),version = 2)
public abstract class TrackRoomDatabase :RoomDatabase(){
    //abstract fun
    abstract fun PlaylistDao(): PlaylistDao
    abstract fun TrackDao():TrackDao


    companion object{
        @Volatile
        private  var INSTANCE:TrackRoomDatabase?=null
        fun getDatabase(context: Context): TrackRoomDatabase{
            val temp=INSTANCE
            if (temp!=null)
                return temp
            synchronized(this){
                val instance=Room.databaseBuilder(
                    context.applicationContext,
                    TrackRoomDatabase::class.java,
                    "playList_database"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}