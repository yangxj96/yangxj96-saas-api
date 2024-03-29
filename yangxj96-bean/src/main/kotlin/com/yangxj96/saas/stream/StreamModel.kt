package com.yangxj96.saas.stream

/**
 * 流消息格式
 *
 * @author yangxj96
 * @version 1.0.0
 * @date 2023/4/20 14:52
 */
class StreamModel {

    var type: String? = null

    var message: Any? = null
    override fun toString(): String {
        return "StreamModel(type=$type, message=$message)"
    }
}
