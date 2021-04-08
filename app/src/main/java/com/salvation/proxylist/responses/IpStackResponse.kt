package com.salvation.proxylist.responses

import com.salvation.proxylist.model.IpStackModel
import okhttp3.ResponseBody

class IpStackResponse {
    var model: IpStackModel?
    var responseErrorBody: ResponseBody?
    var error: Throwable?
    var statusCode: Int?

    constructor(model: IpStackModel?, statusCode: Int?) {
        this.model = model
        this.statusCode = statusCode
        this.responseErrorBody = null
        error = null
    }

    constructor(responseBody: ResponseBody?, statusCode: Int?) {
        this.responseErrorBody = responseBody
        this.statusCode = statusCode
        error = null
        this.model = null
    }

    constructor(error: Throwable?) {
        this.error = error
        statusCode = null
        this.responseErrorBody = null
        this.model = null
    }
}