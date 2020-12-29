package com.example.cse438.cse438_assignment2

import android.content.BroadcastReceiver
import android.util.Log
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.cse438.cse438_assignment2.Adapter.PlaylistAdapter
import com.example.cse438.cse438_assignment2.ViewModels.PlaylistViewModel
import com.example.cse438.cse438_assignment2.db.Playlist
import kotlinx.android.synthetic.main.activity_add_playlist.*

class AddPlayListActivity : AppCompatActivity() {

    private lateinit var viewModel: PlaylistViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_playlist)
    }

    override fun onStart() {
        super.onStart()

        //init view model
        viewModel= ViewModelProvider(this).get(PlaylistViewModel::class.java)

        submit_new_playlist_button.setOnClickListener {
            var newPlaylistName:String = new_playlist_name.text.toString().trim()
            var newPlaylistDescription:String = new_playlist_description.text.toString().trim()
            var newPlaylistGenre:String = new_playlist_genre.text.toString().trim()
            var newPlaylistRating:String = new_playlist_rating.text.toString()
            if(newPlaylistName == "" || newPlaylistDescription=="" || newPlaylistGenre=="" || newPlaylistRating==""){
                Toast.makeText(this, "Please enter valid values.", Toast.LENGTH_SHORT).show()
            }
            else {

                val new_playlist = Playlist(
                    0,
                    newPlaylistName,
                    newPlaylistDescription,
                    newPlaylistGenre,
                    newPlaylistRating
                )
                viewModel.insertPlayList(new_playlist)
//            Log.v("加入数据库",new_playlist.toString())
//            Log.v("所有playlist",playlistItems.toString())

                //transfer back to HomeActivity, and navigate to PlaylistFragment
                // val intent = Intent(this, MainActivity::class.java)
                //var bundle = Bundle()
                //bundle.putInt("position", 1)
                //intent.putExtras(bundle)
                //startActivity(intent)

                //transfer back to PlaylistFragment by finishing this activity
                val intent = Intent()
                intent.action="ACTION_TEST"
                intent.putExtra("type",1)
                sendBroadcast(intent)
                finish()
            }


        }

    }

}
