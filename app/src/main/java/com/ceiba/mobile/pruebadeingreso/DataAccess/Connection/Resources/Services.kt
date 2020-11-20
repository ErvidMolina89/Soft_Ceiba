package com.ceiba.mobile.pruebadeingreso.DataAccess.Connection.Resources

import com.ceiba.mobile.pruebadeingreso.DataAccess.Connection.Handler.Interfaces.IServiceParameters
import com.ceiba.mobile.pruebadeingreso.rest.Endpoints

enum class Services (url : String,
                     method : IServiceParameters.Methods)
    : IServiceParameters {

    get_list_user(Endpoints.Url.GET_USERS,IServiceParameters.Methods.GET),
    get_list_posts(Endpoints.Url.GET_POST_USER,IServiceParameters.Methods.GET),
    get_list_posts_id(Endpoints.Url.GET_POST_USER_ID,IServiceParameters.Methods.GET)
    ;

    private val url : String
    private val method : IServiceParameters.Methods
    private var complement: String = ""

    init {
        this.url = url
        this.method = method
    }

    override fun getURL(): String {
        return url + complement
    }

    override fun getMethods(): IServiceParameters.Methods {
        return method
    }

    override fun getUrlWithComplement(complement: String): Services {
        this.complement = complement
        return this
    }
}