package com.yangxj96.saas.common.respond

import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import java.io.Serializable

/**
 * 通用响应
 */
class R(var code: Int, var msg: String) : Serializable {

    var data: Any? = null

    constructor(code: Int, msg: String, data: Any?) : this(code, msg) {
        this.data = data
    }

    companion object {

        fun success(): R {
            setHeader(RStatus.SUCCESS)
            return R(RStatus.SUCCESS.code, RStatus.SUCCESS.msg)
        }

        fun success(data: Any?): R {
            setHeader(RStatus.SUCCESS)
            return R(RStatus.SUCCESS.code, RStatus.SUCCESS.msg, data)
        }

        fun failure(): R {
            setHeader(RStatus.FAILURE)
            return R(RStatus.FAILURE.code, RStatus.FAILURE.msg)
        }

        fun specify(status: RStatus): R {
            setHeader(status)
            return R(status.code, status.msg)
        }

        fun specify(status: RStatus, data: Any?): R {
            setHeader(status)
            return R(status.code, status.msg, data)
        }

        /**
         * 设置请求头
         *
         * @param status 请求状态
         */
        private fun setHeader(status: RStatus) {
            val attributes = RequestContextHolder.getRequestAttributes() as ServletRequestAttributes?
            if (attributes != null) {
                val response = attributes.response
                response?.setIntHeader(RHttpHeadersExpand.RESULT_CODE, status.code)
            }
        }
    }
}