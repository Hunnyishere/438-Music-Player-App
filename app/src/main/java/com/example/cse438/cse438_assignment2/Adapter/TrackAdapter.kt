package com.example.cse438.cse438_assignment2.Adapter

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.cse438.cse438_assignment2.Data.Search
import com.example.cse438.cse438_assignment2.R
import com.example.cse438.cse438_assignment2.db.Track
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_track_information.view.*
import kotlinx.android.synthetic.main.recyclerview_track_item.view.*

class TrackAdapter(private var context: Context?, private var TrackList: ArrayList<Track>):
     RecyclerView.Adapter<TrackAdapter.ViewHolder>() {
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
        return TrackList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.track_name.text= TrackList[position].title
        holder.itemView.track_artist.text= TrackList[position].artistname
        Picasso.get().load(TrackList[position].cover_medium).into(holder.itemView.track_cover)
        holder.itemView.track_time.visibility=View.GONE
        //no display, won't take up memory
        holder.itemView.remove_track_button.visibility=View.GONE
        holder.itemView.track_genre.visibility = View.GONE
        holder.itemView.track_rating.visibility = View.GONE
        //holder.itemView.track_genre.text=TrackList[position]
        //holder.itemView.rating_view.text=TrackList[position]

        if (onItemClickListener != null){
            holder.itemView.setOnClickListener{
                onItemClickListener!!.OnItemClick(holder.itemView, position)
            }
        }
    }


}