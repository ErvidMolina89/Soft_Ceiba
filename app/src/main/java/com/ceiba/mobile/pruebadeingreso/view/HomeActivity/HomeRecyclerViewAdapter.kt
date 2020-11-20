package com.ceiba.mobile.pruebadeingreso.view.HomeActivity

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.ceiba.mobile.pruebadeingreso.DataAccess.Data.ViewModels.HomeViewModel
import com.ceiba.mobile.pruebadeingreso.DataAccess.DBLocal.ModelsDB.Users
import com.ceiba.mobile.pruebadeingreso.R
import kotlinx.android.synthetic.main.user_list_item.view.*

class HomeRecyclerViewAdapter  (
    private val context : Context,
    private var mValues: MutableList<Users>
) : RecyclerView.Adapter<HomeRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener
    private val homeListViewModel : HomeViewModel

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Users
        }

        homeListViewModel = ViewModelProviders.of(context as FragmentActivity).get(
            HomeViewModel::class.java)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item: Users = mValues[position]

        holder.textview_title.text = item.name
        holder.textview_phone.text = item.phone
        holder.textView_email.text = item.email

        ponerEscuchadores(holder,item)

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    private fun ponerEscuchadores(holder : ViewHolder, item : Users){
        holder
            .btn_view_post
            .setOnClickListener {
                homeListViewModel.setUser(item)
            }
    }

    override fun getItemCount(): Int = mValues.size

    fun setData(listUsers : MutableList<Users>){
        this.mValues = listUsers
        notifyDataSetChanged()
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {

        val textview_title: TextView = mView.name
        val textview_phone: TextView = mView.phone
        val textView_email: TextView = mView.email
        val btn_view_post : Button = mView.btn_view_post


        override fun toString(): String {
            return super.toString() + " '" + textview_phone.text + "'"
        }
    }
}