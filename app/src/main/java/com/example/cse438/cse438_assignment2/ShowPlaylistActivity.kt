package com.example.cse438.cse438_assignment2

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cse438.cse438_assignment2.Adapter.TrackAdapter
import com.example.cse438.cse438_assignment2.Adapter.TrackInPlaylistAdapter
import com.example.cse438.cse438_assignment2.Data.TrackInPlaylist
import com.example.cse438.cse438_assignment2.ViewModels.TrackViewModel
import kotlinx.android.synthetic.main.activity_show_playlist.*

class ShowPlaylistActivity : AppCompatActivity() {

    private lateinit var viewModel: TrackViewModel
    private lateinit var recyclerView: RecyclerView
    private var trackInPlaylistItems: ArrayList<TrackInPlaylist> = ArrayList()
    private lateinit var track_in_playlist_adapter: TrackInPlaylistAdapter
    private var playlistId: Int = 0
    private var playlistName: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_playlist)


    }

    override fun onStart() {
        super.onStart()

        //get data from PlaylistFragment
        var bundle: Bundle? = intent.extras
        playlistId = bundle!!.getInt("playlistId")
        playlistName = bundle!!.getString("playlistName")

        playlist_name.text = playlistName

        // assign adapter to recyclerview (show content)
        recyclerView = trackInPlaylistRecyclerView

        track_in_playlist_adapter = TrackInPlaylistAdapter(this, trackInPlaylistItems)
        recyclerView.layoutManager= LinearLayoutManager(this)
        recyclerView.adapter = track_in_playlist_adapter

        viewModel= ViewModelProvider(this).get(TrackViewModel::class.java)
        viewModel.getTracksInPlaylists(playlistId)

        viewModel!!.trackInPlaylist.observe(this, Observer {tracksInPlaylist->
            //在这取数据
            trackInPlaylistItems.clear()
            trackInPlaylistItems.addAll(tracksInPlaylist)
            Log.v("显示数据库",trackInPlaylistItems.toString())
            track_in_playlist_adapter.notifyDataSetChanged()
        })


        return_playlist_tab_button.setOnClickListener {
            //transfer back to PlaylistFragment by finishing this activity
            finish()
        }

        track_in_playlist_adapter.setOnItemClick(object :TrackInPlaylistAdapter.OnItemClickListener{
            override fun OnItemClick(view: View, position: Int) {
                Log.v("删除00","0")
                val trackId = trackInPlaylistItems[position].id
                Log.v("删除11",trackId.toString())
                viewModel.removeTrack(trackId,playlistId)
                //refresh page after delete
                refresh()
            }
        })

    }

    //refresh layout display from database
    private fun refresh(){
        //change the value for tracksInPlaylist in viewModel, will be observed by viewModel above
        viewModel.getTracksInPlaylists(playlistId)
    }

}
