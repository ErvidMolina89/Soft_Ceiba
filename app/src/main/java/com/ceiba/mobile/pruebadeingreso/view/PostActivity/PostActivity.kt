package com.ceiba.mobile.pruebadeingreso.view.PostActivity

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.ceiba.mobile.pruebadeingreso.Base.App
import com.ceiba.mobile.pruebadeingreso.Base.BaseActivity
import com.ceiba.mobile.pruebadeingreso.DataAccess.Data.ViewModels.PostViewModel
import com.ceiba.mobile.pruebadeingreso.DataAccess.DBLocal.ModelsDB.Publications
import com.ceiba.mobile.pruebadeingreso.DataAccess.DBLocal.ModelsDB.Users
import com.ceiba.mobile.pruebadeingreso.R
import com.ceiba.mobile.pruebadeingreso.Utils.hiddenProgress
import com.ceiba.mobile.pruebadeingreso.Utils.showProgress
import com.ceiba.mobile.pruebadeingreso.rest.Endpoints
import kotlinx.android.synthetic.main.activity_post.*

class PostActivity : BaseActivity() {

    private var postListViewModel : PostViewModel? = null
    private var adapterRecyclerView : PostRecyclerViewAdapter ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
        postListViewModel = ViewModelProviders.of(this).get(PostViewModel::class.java)
        postListViewModel?.user?.value = intent.extras?.get(Endpoints.PutExtra.EXTRA_USER) as Users
        onStyleRecycler()
        listenerViewModel()
        postListViewModel?.getListPublications()
        App.mContext?.showProgress()
    }

    private fun onStyleRecycler() {
        recyclerViewPostsResults!!.let {
            it.layoutManager  = LinearLayoutManager(App.mContext)

            this@PostActivity.adapterRecyclerView = PostRecyclerViewAdapter(
                this,
                emptyList<Publications>().toMutableList()
            )
            recyclerViewPostsResults.adapter = this@PostActivity.adapterRecyclerView
        }
    }

    private fun listenerViewModel(){
        postListViewModel?.Publications?.observeForever {
            if(it.size != 0) {
                adapterRecyclerView?.setData(it)
                App.mContext?.hiddenProgress()
            }
        }
        postListViewModel?.user?.observeForever {
            dataContentCard(it)
        }
    }

    private fun dataContentCard(user: Users){
        name.text   = user.name
        phone.text  = user.phone
        email.text  = user.email
    }
}
