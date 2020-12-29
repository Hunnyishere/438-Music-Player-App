package com.example.cse438.cse438_assignment2.Fragment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cse438.cse438_assignment2.Adapter.RadioAdapter
import com.example.cse438.cse438_assignment2.Adapter.TrackAdapter
import com.example.cse438.cse438_assignment2.Data.Radio
import com.example.cse438.cse438_assignment2.Data.RadioLoad

import com.example.cse438.cse438_assignment2.R
import com.example.cse438.cse438_assignment2.ShowRadioAvtivity
import com.example.cse438.cse438_assignment2.ViewModels.RadioViewModel
import kotlinx.android.synthetic.main.fragment_radio_list.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [RadioListFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [RadioListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RadioListFragment : Fragment() {


    lateinit var radioviewModel:RadioViewModel
    var radioList:ArrayList<Radio> = ArrayList()
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: RadioAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_radio_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //recylcerview
        recyclerView= radio_view
        adapter = RadioAdapter(context,radioList)
        recyclerView.layoutManager= GridLayoutManager(context,2)
        recyclerView.adapter= adapter
        adapter.setOnItemClick(object :RadioAdapter.OnItemClickListener{
            override fun OnItemClick(view: View, position: Int) {
                val intent = Intent(context,ShowRadioAvtivity::class.java)
                var bundle= Bundle()
                bundle.putInt("radioId",radioList[position].id)
                bundle.putString("radioName",radioList[position].title)
                intent.putExtras(bundle)
                activity?.startActivity(intent)
            }
        })
        Log.v("跑到这里","")
        radioviewModel = ViewModelProviders.of(this).get(RadioViewModel::class.java)
        radioviewModel.getRadioList(12)
        radioviewModel!!.radioList.observe(this, Observer {
            radioList.clear()
            radioList.addAll(it.data)
            adapter.notifyDataSetChanged()
        })

    }


}
