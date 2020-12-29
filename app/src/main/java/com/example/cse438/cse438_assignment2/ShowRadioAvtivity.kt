package com.example.cse438.cse438_assignment2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cse438.cse438_assignment2.Adapter.TrackAdapter
import com.example.cse438.cse438_assignment2.Adapter.TrackInPlaylistAdapter
import com.example.cse438.cse438_assignment2.Data.SearchLoad
import com.example.cse438.cse438_assignment2.Data.TrackInPlaylist
import com.example.cse438.cse438_assignment2.ViewModels.SearchViewModel
import com.example.cse438.cse438_assignment2.ViewModels.TrackViewModel
import com.example.cse438.cse438_assignment2.db.Track
import kotlinx.android.synthetic.main.activity_show_playlist.*
import kotlinx.android.synthetic.main.activity_show_radio_avtivity.*
import kotlinx.android.synthetic.main.fragment_radio_list.*

class ShowRadioAvtivity : AppCompatActivity() {

    private lateinit var viewModel: SearchViewModel
    private lateinit var recyclerView: RecyclerView
    private var trackInRadio: ArrayList<Track> = ArrayList()
    private lateinit var trackadapter: TrackAdapter
    var albums : ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_radio_avtivity)

        val bundle = intent.extras
        val radioId=bundle.getInt("radioId")
        val radioName = bundle.getString("radioName")
        radio_name.text = radioName + ": Tops"
        recyclerView = radiotrack_view
        trackadapter = TrackAdapter(this, trackInRadio)
        recyclerView.layoutManager= GridLayoutManager(this,2)
        recyclerView.adapter = trackadapter

        viewModel= ViewModelProvider(this).get(SearchViewModel::class.java)
        viewModel.getTracksInRadio(radioId,20)
        viewModel!!.trackSearchinRadio.observe(this, Observer {
            if(it.data.size==0){
                Toast.makeText(this, "No search results.", Toast.LENGTH_SHORT).show()
            }else{
                trackInRadio.clear()
                albums.clear()
                for ( tracksearch in it.data){
                    val track = Track(tracksearch.id,tracksearch.title,tracksearch.artist.name,0,0,0,0.0f,"","",0,tracksearch.album.cover_medium)
                    trackInRadio.add(track)
                    albums.add(tracksearch.album.title)
                }
                trackadapter.notifyDataSetChanged()
            }
        })
        trackadapter.setOnItemClick(object : TrackAdapter.OnItemClickListener{
            override fun OnItemClick(view: View, position: Int) {
                val trackId = trackInRadio[position].id
                val artist = trackInRadio[position].artistname
                val cover = trackInRadio[position].cover_medium
                val intent= Intent(this@ShowRadioAvtivity,TrackInformationActivity::class.java)
                var bundle=Bundle()
                bundle.putInt("trackId",trackId)
                bundle.putString("artist",artist)
                bundle.putString("cover",cover)
                bundle.putString("album",albums[position])
                intent.putExtras(bundle)
                startActivity(intent)
            }

        })

        refresh_button.setOnClickListener(){
            viewModel.getTracksInRadio(radioId,12)
        }
        return_to_radio.setOnClickListener(){
            finish()
        }
    }

    override fun onStart() {
        super.onStart()

    }
}
