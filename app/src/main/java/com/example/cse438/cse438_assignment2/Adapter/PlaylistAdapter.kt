package com.example.cse438.cse438_assignment2.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cse438.cse438_assignment2.R
import com.example.cse438.cse438_assignment2.db.Playlist
import kotlinx.android.synthetic.main.recyclerview_playlist_item.view.*


class PlaylistAdapter(private var context: Context?, private var PlaylistItems: ArrayList<Playlist>):
    RecyclerView.Adapter<PlaylistAdapter.ViewHolder>() {
//each ArrayList retrieve one column
    /**
     * Need to override the ViewHolder method
     */
    interface OnItemClickListener{
        fun OnItemClick(view: View, position: Int)
    }
    var onItemClickListener:OnItemClickListener?=null
    fun setOnItemClick(onItemClickListener: OnItemClickListener){
        this.onItemClickListener=onItemClickListener
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.recyclerview_playlist_item, parent, false)
        val holder = ViewHolder(itemView)
        return holder
    }

    override fun getItemCount(): Int {
        return PlaylistItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.playlist_name.text= "Name: "+PlaylistItems[position].name
        holder.itemView.playlist_description.text= "Description: "+PlaylistItems[position].description

        holder.itemView.playlist_rating.text = PlaylistItems[position].rating
        holder.itemView.playlist_genre.text = PlaylistItems[position].genre
        holder.itemView.playlist_rating.visibility = View.GONE
        holder.itemView.playlist_genre.visibility = View.GONE

        if (onItemClickListener != null){
            holder.itemView.setOnClickListener{
                onItemClickListener!!.OnItemClick(holder.itemView, position)
            }
        }
    }


}