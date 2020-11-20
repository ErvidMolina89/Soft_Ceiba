package com.ceiba.mobile.pruebadeingreso.view.PostActivity

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.ceiba.mobile.pruebadeingreso.DataAccess.Data.ViewModels.HomeViewModel
import com.ceiba.mobile.pruebadeingreso.DataAccess.DBLocal.ModelsDB.Publications
import com.ceiba.mobile.pruebadeingreso.R
import kotlinx.android.synthetic.main.post_list_item.view.*

class PostRecyclerViewAdapter  (
    private val context : Context,
    private var mValues: MutableList<Publications>
) : RecyclerView.Adapter<PostRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener
    private val homeListViewModel : HomeViewModel

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Publications
        }

        homeListViewModel = ViewModelProviders.of(context as FragmentActivity).get(
            HomeViewModel::class.java)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.post_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item: Publications = mValues[position]

        holder.textview_title.text = item.title
        holder.textview_body.text = item.body

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    fun setData(listPublications : MutableList<Publications>){
        this.mValues = listPublications
        notifyDataSetChanged()
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val textview_title: TextView = mView.title
        val textview_body: TextView = mView.body
    }
}