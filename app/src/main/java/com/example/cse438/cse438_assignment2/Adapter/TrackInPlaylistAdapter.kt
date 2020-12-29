package com.example.cse438.cse438_assignment2.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.cse438.cse438_assignment2.Data.Search
import com.example.cse438.cse438_assignment2.Data.TrackInPlaylist
import com.example.cse438.cse438_assignment2.R
import com.example.cse438.cse438_assignment2.db.Track
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_track_information.view.*
import kotlinx.android.synthetic.main.recyclerview_track_item.view.*

class TrackInPlaylistAdapter(private var context: Context?, private var TrackInPlaylistItems: ArrayList<TrackInPlaylist>):
     RecyclerView.Adapter<TrackInPlaylistAdapter.ViewHolder>() {
//each ArrayList retrieve one column
    /**
     * Need to override the ViewHolder method
     */
    interface OnItemClickListener{
        fun OnItemClick(view:View, position: Int)
    }
    var onItemClickListener:OnItemClickListener?=null
    fun setOnItemClick(onItemClickListener: OnItemClickListener){
        this.onItemClickListener=onItemClickListener
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.recyclerview_track_item, parent, false)
        val holder = ViewHolder(itemView)
        return holder
    }

    override fun getItemCount(): Int {
        return TrackInPlaylistItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.track_name.text= "Track Name: "+ TrackInPlaylistItems[position].title
        holder.itemView.track_artist.text= "Track Artist: "+TrackInPlaylistItems[position].artistname
        holder.itemView.track_time.text = "Track Released Time: "+TrackInPlaylistItems[position].release_date
        holder.itemView.track_genre.text = "Playlist Genre: "+TrackInPlaylistItems[position].genre
        holder.itemView.track_rating.text = "Playlist Rating: "+TrackInPlaylistItems[position].rating
        //no display, won't take up memory
        holder.itemView.track_cover.visibility = View.GONE

        if (onItemClickListener != null){
            holder.itemView.remove_track_button.setOnClickListener{
                //only happens when click on button in an item
                onItemClickListener!!.OnItemClick(holder.itemView, position)
            }
        }
    }


}