package io.github.yangxj96.server.gateway.exception

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import io.github.yangxj96.common.respond.R
import io.github.yangxj96.common.respond.R.Companion.failure
import io.github.yangxj96.common.respond.R.Companion.specify
import io.github.yangxj96.common.respond.RStatus
import jakarta.annotation.Resource
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import java.nio.charset.StandardCharsets

/**
 * 网关异常通用处理器，只作用在webflux 环境下 ,
 *
 * 优先级低于 [org.springframework.web.server.handler.ResponseStatusExceptionHandler] 执行
 */
class GlobalExceptionHandle : ErrorWebExceptionHandler {


    @Resource
    private lateinit var om: ObjectMapper

    override fun handle(exchange: ServerWebExchange, ex: Throwable): Mono<Void> {
        val response = exchange.response
        if (response.isCommitted) {
            return Mono.error(ex)
        }
        ex.printStackTrace()
        // 设置异常返回类型
        response.headers.contentType = MediaType.APPLICATION_JSON
        response.setStatusCode(HttpStatus.OK)
        return response.writeWith(Mono.fromSupplier {
            try {
                val result = transition(ex)
                return@fromSupplier response.bufferFactory().wrap(om.writeValueAsBytes(result))
            } catch (e: JsonProcessingException) {
                return@fromSupplier response.bufferFactory().wrap(
                    """
                        {"code": -1,"message":"响应序列化错误"}
                        
                        """.trimIndent().toByteArray(StandardCharsets.UTF_8)
                )
            }
        })
    }

    /**
     * 翻译异常为响应内容
     *
     * @param ex 异常
     * @return 翻译后的 [R]
     */
    private fun transition(ex: Throwable): R {
        return when (ex.javaClass.getName()) {
            "org.springframework.cloud.gateway.support.NotFoundException" -> specify(RStatus.GATEWAY_NOT_FOUND)
            "org.springframework.web.server.ResponseStatusException" -> specify(RStatus.GATEWAY_RESPONSE_STATUS)
            "org.springframework.security.access.AccessDeniedException" -> specify(RStatus.SECURITY_ACCESS_DENIED)
            "org.springframework.security.authentication.AuthenticationServiceException" -> specify(RStatus.SECURITY_AUTHENTICATION)
            "java.lang.NullPointerException" -> specify(RStatus.NULL_POINTER)
            else -> failure()
        }
    }
}