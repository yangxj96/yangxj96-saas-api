/*
 *  Copyright (c) 2021 - 2023
 *  作者：杨新杰(Jack Young)
 *  邮箱：yangxj96@gmail.com
 *  日期：2023-08-30 14:21:41
 *  Copyright (c) 2021 - 2023
 */

package com.yangxj96.saas.server.gateway.remote

import com.yangxj96.saas.bean.system.Route
import org.springframework.web.service.annotation.GetExchange
import org.springframework.web.service.annotation.HttpExchange
import reactor.core.publisher.Flux

/**
 * 测试
 */
@HttpExchange("/route")
interface SystemRemote {

    @GetExchange("/all")
    fun getRoutes(): Flux<Route>

}