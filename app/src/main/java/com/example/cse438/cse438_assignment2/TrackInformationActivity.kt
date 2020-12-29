package com.example.cse438.cse438_assignment2


import android.content.DialogInterface
import android.content.DialogInterface.OnMultiChoiceClickListener
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.cse438.cse438_assignment2.ViewModels.PlaylistViewModel
import com.example.cse438.cse438_assignment2.ViewModels.TrackViewModel
import com.example.cse438.cse438_assignment2.db.Playlist
import com.example.cse438.cse438_assignment2.db.Track
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_track_information.*



@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class TrackInformationActivity : AppCompatActivity() {

    private lateinit var trackViewModel:TrackViewModel
    private lateinit var playList:List<Playlist>
    private lateinit var track: Track
    private lateinit var artist:String
    private lateinit var cover:String
    private lateinit var album : String
    private var removeIds = ArrayList<Int>()
    private lateinit var mediaPlayer: MediaPlayer
    private var music_path:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_track_information)

        val intent= getIntent()
        val bundle=intent.extras
        val trackId = bundle.get("trackId") as Int
        album = bundle.get("album").toString()
        artist = bundle.get("artist").toString()
        cover=bundle.get("cover").toString()
        val playListViewModel = ViewModelProviders.of(this).get(PlaylistViewModel::class.java)
        playListViewModel.getAllPlaylist()
        playListViewModel.playList.observe(this, Observer {
            playList = it
        })
        showTrackDetail(trackId, artist,cover)

        // play the track
        mediaPlayer = MediaPlayer()
//        mediaPlayer.setOnPreparedListener{
//            mediaPlayer.start()
//        }
        mediaPlayer.setOnErrorListener(object: MediaPlayer.OnErrorListener{
            override fun onError(mp: MediaPlayer?, what: Int, extra: Int): Boolean {
                mediaPlayer.reset()
                return false
            }
        })
        play_music_button.setOnClickListener{
            //mediaPlayer.setDataSource(music_path)
            //mediaPlayer.prepareAsync()
            mediaPlayer.start()
        }
        pause_music_button.setOnClickListener{
            mediaPlayer.pause()
        }
        stop_music_button.setOnClickListener{
            mediaPlayer.stop()
            mediaPlayer.prepareAsync()  //prepare for replay
        }
    }

    private fun showTrackDetail(trackId: Int, artist:String, cover:String) {
        trackViewModel= ViewModelProviders.of(this).get(TrackViewModel::class.java)
        trackViewModel.SearchTrackById(trackId)
        trackViewModel!!.track.observe(this, Observer {
            Log.v("打印track", it.toString())
            trackName.text = it.title
            artist_view.text = "Artist: " + artist
            album_view.text = album
            Picasso.get().load(cover).into(image_track)
            duration_view.text = "Length: " + it.duration.toString()
            position_view.text = "Position: " + it.track_position.toString()
            released_time.text = "Released: " + it.release_date
            rank_view.text = "Rank: " + it.rank.toString()
            gain_view.text = "Gain: " +it.gain.toString()
            track=it
            track.artistname = artist
            track.cover_medium= cover

            music_path = it.preview
            mediaPlayer.setDataSource(music_path)
            mediaPlayer.prepareAsync()

        })
            back_to_home.setOnClickListener(){
                mediaPlayer.stop()
                finish()
            }
            add_to_playlist.setOnClickListener(){
                if (playList.size == 0){
                    Toast.makeText(this,"You have to create a new playlist first" ,Toast.LENGTH_LONG).show()
                }else{
                    // Dialog  choose playlist
                    val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                    var playListItems = arrayOfNulls<CharSequence>(playList.size)
                    Log.v("listsize:",playListItems.size.toString())
                    for (i in 0..playList.size-1){
                        Log.v("which",i.toString())
                        playListItems[i]=playList[i].name
                    }
                    builder.setTitle("Add to a playList")
                    builder.setMultiChoiceItems(playListItems, null,
                        OnMultiChoiceClickListener { dialog, which, isChecked ->
                            Log.v("choose list:" , which.toString() )
                            removeIds.add( which+1 )
                        })
                    builder.setPositiveButton("YES",
                        DialogInterface.OnClickListener { dialog, which ->
                            for (removeId in removeIds){
                                trackViewModel.addToPlayList(track,removeId)
                            }
                            Toast.makeText(this,"You have added track: " + track.title + " to playLists" ,Toast.LENGTH_LONG).show()
                             })
                    builder.setNegativeButton("NO",DialogInterface.OnClickListener({dialog,which ->
                    }))
                    val  dialog: AlertDialog= builder.create()
                    dialog.show()
                }
            }//listener
    }
}
