package com.example.cse438.cse438_assignment2.Fragment

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cse438.cse438_assignment2.Adapter.PlaylistAdapter
import com.example.cse438.cse438_assignment2.AddPlayListActivity
import com.example.cse438.cse438_assignment2.R
import com.example.cse438.cse438_assignment2.ShowPlaylistActivity
import com.example.cse438.cse438_assignment2.ViewModels.PlaylistViewModel
import com.example.cse438.cse438_assignment2.db.Playlist
import kotlinx.android.synthetic.main.fragment_playlist.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import android.util.Log.v as v1

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [PlaylistFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [PlaylistFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PlaylistFragment : Fragment() {
    private lateinit var viewModel: PlaylistViewModel
    private lateinit var recyclerView: RecyclerView
    private var playlistItems: ArrayList<Playlist> = ArrayList()
    private lateinit var playlist_adapter: PlaylistAdapter
    private lateinit var broadcastReceiver:myBroadcastReceiver
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_playlist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        broadcastReceiver = myBroadcastReceiver()
//        val filter = IntentFilter()
//        activity?.registerReceiver(broadcastReceiver,filter)
        // assign adapter to recyclerview (show content)
        recyclerView = playlistRecyclerView
        playlist_adapter = PlaylistAdapter(context, playlistItems)
        recyclerView.layoutManager= LinearLayoutManager(context)
        recyclerView.adapter = playlist_adapter

        playlist_adapter.setOnItemClick(object: PlaylistAdapter.OnItemClickListener{
            //transfer to ShowPlaylistActivity
            override fun OnItemClick(view: View, position: Int) {
                val playlistId = playlistItems[position].id
                val playlistName = playlistItems[position].name

                val show_intent= Intent(context,ShowPlaylistActivity::class.java)
                var show_bundle=Bundle()
                show_bundle.putInt("playlistId",playlistId)
                show_bundle.putString("playlistName",playlistName)
                show_intent.putExtras(show_bundle)
                activity?.startActivity(show_intent)
            }
        })

        viewModel=ViewModelProvider(this).get(PlaylistViewModel::class.java)
        //get playlists from database
        viewModel.getAllPlaylist()

        add_playlist_button.setOnClickListener(){
            //val playlist=Playlist(500,"hiha","heha","rockn","ngood")
            //viewModel.insertPlayList(playlist)
            //Log.v("插入数据库","1")

            //transfer to AddPlaylistActivity
            val add_intent= Intent(context,AddPlayListActivity::class.java)
            activity?.startActivity(add_intent)
        }

        viewModel!!.playList.observe(this, Observer { playlists ->
            //在这取数据
            playlistItems.clear()
            playlistItems.addAll(playlists)
            Log.v("显示数据库",playlistItems.toString())
            // playlistItems: [Playlist(id=50, name=hiha, description=heha, genre=rockn, rating=ngood), Playlist(id=500, name=hiha, description=heha, genre=rockn, rating=ngood)]
            playlist_adapter.notifyDataSetChanged()
        })
    }

    //After finish other pages, return to this fragment, refresh layout from database
    private fun refresh(){
        //change the value for playList in viewModel, will be observed by viewModel above
        viewModel.getAllPlaylist()
    }

    private inner class myBroadcastReceiver: BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            if(intent!=null){
                val msg = intent.getStringExtra("type")
                refresh()
            }
        }
    }

}
