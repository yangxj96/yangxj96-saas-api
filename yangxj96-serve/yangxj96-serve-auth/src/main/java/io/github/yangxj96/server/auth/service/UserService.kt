package io.github.yangxj96.server.auth.service

import io.github.yangxj96.bean.user.User
import io.github.yangxj96.common.base.BasicService
import org.springframework.security.core.GrantedAuthority

/**
 * 用户service层
 */
interface UserService : BasicService<User> {

    /**
     * 关联用户和角色
     *
     * @param user 用户id
     * @param role 角色id
     * @return 是否关联成功
     */
    fun relevance(user: Long, role: Long): Boolean

    /**
     * 根据用户id查询用户权限列表
     *
     * @param userId 用户id
     * @return 权限列表
     */
    fun getAuthoritiesByUserId(userId: Long): MutableList<GrantedAuthority>
}