package io.github.yangxj96.bean.user

import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableName
import com.fasterxml.jackson.annotation.JsonIgnore
import io.github.yangxj96.common.base.BasicEntity
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.io.Serializable

/**
 * 用户表
 */
@TableName(value = "db_user.t_user")
class User : BasicEntity(), Serializable, UserDetails {

    /**
     * 用户名
     */
    @TableField(value = "username")
    @get:JvmName("username")
    var username: String = ""

    /**
     * 密码
     */
    @TableField(value = "\"password\"")
    @get:JvmName("password")
    var password: String = ""

    /**
     * 账号是否过期
     */
    @TableField(value = "access_expired")
    var accessExpired: Boolean? = null

    /**
     * 账号是否锁定
     */
    @TableField(value = "access_locked")
    var accessLocked: Boolean? = null

    /**
     * 账号是否启用
     */
    @TableField(value = "access_enable")
    var accessEnable: Boolean? = null

    /**
     * 密码是否过期
     */
    @TableField(value = "credentials_expired")
    var credentialsExpired: Boolean? = null

    /**
     * 权限列表
     */
    @JsonIgnore
    @TableField(exist = false)
    @get:JvmName("authorities")
    var authorities: MutableCollection<GrantedAuthority> = mutableListOf()

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return this.authorities
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return username
    }

    override fun isAccountNonExpired(): Boolean {
        return !accessExpired!!
    }

    override fun isAccountNonLocked(): Boolean {
        return !accessLocked!!
    }

    override fun isCredentialsNonExpired(): Boolean {
        return !credentialsExpired!!
    }

    override fun isEnabled(): Boolean {
        return accessEnable!!
    }
}