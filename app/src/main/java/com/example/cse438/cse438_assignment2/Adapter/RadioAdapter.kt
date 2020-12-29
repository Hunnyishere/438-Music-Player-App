package com.example.cse438.cse438_assignment2.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cse438.cse438_assignment2.Data.Radio
import com.example.cse438.cse438_assignment2.R
import com.example.cse438.cse438_assignment2.db.Track
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recyclerview_track_item.view.*

class RadioAdapter(private var context: Context?, private var RadioList: ArrayList<Radio>):

    RecyclerView.Adapter<RadioAdapter.ViewHolder>() {
    interface OnItemClickListener{
        fun OnItemClick(view: View, position: Int)
    }
    var onItemClickListener: OnItemClickListener?=null
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
        return RadioList.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.track_name.text= RadioList[position].title
        holder.itemView.track_artist.visibility = View.GONE
        Picasso.get().load(RadioList[position].picture_medium).into(holder.itemView.track_cover)
        holder.itemView.track_time.visibility=View.GONE
        //no display, won't take up memory
        holder.itemView.remove_track_button.visibility=View.GONE
        holder.itemView.track_genre.visibility = View.GONE
        holder.itemView.track_rating.visibility = View.GONE
        if (onItemClickListener != null){
            holder.itemView.setOnClickListener{
                onItemClickListener!!.OnItemClick(holder.itemView, position)
            }
        }
    }

}
