package com.ceiba.mobile.pruebadeingreso.view.HomeActivity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.ceiba.mobile.pruebadeingreso.Base.App
import com.ceiba.mobile.pruebadeingreso.Base.BaseActivity
import com.ceiba.mobile.pruebadeingreso.DataAccess.Data.ViewModels.HomeViewModel
import com.ceiba.mobile.pruebadeingreso.DataAccess.DBLocal.ModelsDB.Users
import com.ceiba.mobile.pruebadeingreso.R
import com.ceiba.mobile.pruebadeingreso.Utils.hiddenProgress
import com.ceiba.mobile.pruebadeingreso.rest.Endpoints
import com.ceiba.mobile.pruebadeingreso.view.PostActivity.PostActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private var usersViewModel: HomeViewModel? = null
    private var listUsers: MutableList<Users> = emptyList<Users>().toMutableList()
    private var adapterRecyclerView : HomeRecyclerViewAdapter ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        usersViewModel =  ViewModelProviders.of(this).get(HomeViewModel::class.java)
        onStyleRecycler()
        listenerViewModel()
        usersViewModel?.validateDB()
    }

    private fun onStyleRecycler() {
        recyclerViewSearchResults!!.let {
            it.layoutManager  = LinearLayoutManager(App.mContext)
            this@MainActivity.adapterRecyclerView = HomeRecyclerViewAdapter(
                this,
                emptyList<Users>().toMutableList()
            )
            recyclerViewSearchResults.adapter = this@MainActivity.adapterRecyclerView
        }
    }

    private fun listenerViewModel(){
        usersViewModel?.userProfile?.observeForever {
            listUsers = it
            if(it.size != 0){
                recyclerViewSearchResults.post {
                    adapterRecyclerView?.setData(it)
                    App.mContext?.hiddenProgress()
                    if(include_list_empty.visibility == View.VISIBLE){
                        include_list_empty.visibility = View.GONE
                    }
                }
            }else {
                recyclerViewSearchResults.post {
                    App.mContext?.hiddenProgress()
                    include_list_empty.visibility = View.VISIBLE
                }
            }
        }
        usersViewModel?.user?.observeForever {
            val intent = Intent(this, PostActivity::class.java)
            intent.putExtra(Endpoints.PutExtra.EXTRA_USER, it)
            startActivity(intent)
        }
        editTextSearch.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                adapterRecyclerView?.setData(filterListUser(s.toString()))
            }
        })
    }

    private fun filterListUser (valor: String) :MutableList<Users>{
        val list = listUsers.filter {
            return@filter it.name?.toLowerCase()?.contains(valor.toLowerCase())!!
        }
        if(list.size ==  0){
            include_list_empty.visibility = View.VISIBLE
        } else{
            if(include_list_empty.visibility == View.VISIBLE){
                include_list_empty.visibility = View.GONE
            }
        }
        return list.toMutableList()
    }
}
