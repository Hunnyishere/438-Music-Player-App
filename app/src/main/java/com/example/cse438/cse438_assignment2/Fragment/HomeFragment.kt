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
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cse438.cse438_assignment2.Adapter.TrackAdapter
import com.example.cse438.cse438_assignment2.Data.Search
import com.example.cse438.cse438_assignment2.Data.album

import com.example.cse438.cse438_assignment2.R
import com.example.cse438.cse438_assignment2.TrackInformationActivity
import com.example.cse438.cse438_assignment2.ViewModels.SearchViewModel
import com.example.cse438.cse438_assignment2.ViewModels.TrackViewModel
import com.example.cse438.cse438_assignment2.db.Track
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [HomeFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    lateinit var viewModel:SearchViewModel
    lateinit var trackViewModel: TrackViewModel
    lateinit var recyclerView: RecyclerView
    var searchResult:ArrayList<Track> = ArrayList()
    lateinit var adapter:TrackAdapter
    var albums : ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //recyclerview
        recyclerView=track_view
        adapter = TrackAdapter(context,searchResult)
        recyclerView.layoutManager=GridLayoutManager(context,2)
        recyclerView.adapter=adapter
        adapter.setOnItemClick(object :TrackAdapter.OnItemClickListener{
            override fun OnItemClick(view: View, position: Int) {
                val trackId = searchResult[position].id
                val artist = searchResult[position].artistname
                val cover=searchResult[position].cover_medium
                val intent= Intent(context,TrackInformationActivity::class.java)
                var bundle=Bundle()
                bundle.putInt("trackId",trackId)
                bundle.putString("artist",artist)
                bundle.putString("cover",cover)
                bundle.putString("album",albums[position])
                intent.putExtras(bundle)
                activity?.startActivity(intent)
            }
        })
        //config and show popchart
        ShowPopChart()
        //config and show tracks searched by users
        viewModel= ViewModelProviders.of(this).get(SearchViewModel::class.java)
        button_search.setOnClickListener(){
            var searchText =  search_text.text.toString().trim()
            if(searchText == ""){
                Toast.makeText(context, "Please enter valid values.", Toast.LENGTH_SHORT).show()
            }
            else {
                viewModel.SearchTrack(searchText, 16)
            }
        }

        //viewmodel searchList obeserve
        viewModel!!.searchList.observe(this, Observer {
            if(it.data.size==0){
                Toast.makeText(context, "No search results.", Toast.LENGTH_SHORT).show()
            }
            else{
                Log.v("bbb",it.data[0].toString())
                searchResult.clear()
                albums.clear()
                for (search in it.data){
                    //此处赋空值是因为我只要title和image
                    val track= Track(search.id,search.title,search.artist.name,0,0,0,0.0f,"","",0,search.album.cover_medium)
                    searchResult.add(track)
                    albums.add(search.album.title)
                }
                adapter.notifyDataSetChanged()
            }
        })
    }

    fun ShowPopChart(){
        trackViewModel= ViewModelProviders.of(this).get(TrackViewModel::class.java)
        trackViewModel.getPopChart(16)
        trackViewModel!!.poptracks.observe(this, Observer {
            Log.v("pop chart",it.tracks.data[0].toString())
            searchResult.clear()
            albums.clear()
            for (search in it.tracks.data){
                val track= Track(search.id,search.title,search.artist.name,0,0,0,0.0f,"","",0,search.album.cover_medium)
                searchResult.add(track)
                albums.add(search.album.title)
            }
            adapter.notifyDataSetChanged()
        })
    }
}


