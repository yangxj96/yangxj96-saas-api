package com.yangxj96.saas.starter.db.filters

import com.yangxj96.saas.starter.db.holder.DynamicDataSourceContextHolder
import jakarta.servlet.*
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.LoggerFactory
import org.springframework.core.Ordered
import java.io.IOException

/**
 * 设置当前租户数据源信息
 */
class DynamicDatasourceFilter : Filter, Ordered {

    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        var tenant = (request as HttpServletRequest).getHeader("tenant")
        log.info("租户ID:{}", tenant)
        if (tenant == null) {
            tenant = "default"
        }
        log.info("设置租户ID")
        DynamicDataSourceContextHolder.set(tenant)
        chain.doFilter(request, response)
        log.info("清理用户ID")
        DynamicDataSourceContextHolder.clear()
    }

    override fun getOrder(): Int {
        return Int.MIN_VALUE
    }
}
