package com.ceiba.mobile.pruebadeingreso.Models


class MessageResponse() : BaseModel() {
    var Code: String? = null
    var Message: String? = null

    constructor(code: String, message: String?) : this() {
        this.Code = code
        this.Message = message
    }

}