/*
 *  Copyright (c) 2021 - 2023
 *  作者：杨新杰(Jack Young)
 *  邮箱：yangxj96@gmail.com
 *  日期：2023-10-12 16:01:57
 *  Copyright (c) 2021 - 2023
 */

package com.yangxj96.saas.server.auth.controller

import com.baomidou.mybatisplus.core.metadata.IPage
import com.yangxj96.saas.bean.user.User
import com.yangxj96.saas.common.base.BaseController
import com.yangxj96.saas.server.auth.service.UserService
import jakarta.validation.constraints.NotBlank
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*


/**
 * 用户信息控制器
 */
@RestController
@RequestMapping("/user")
class UserController protected constructor(bindService: UserService) : BaseController<User, UserService>(bindService) {

    @PreAuthorize("hasAnyAuthority('USER_ALL','USER_INSERT')")
    @PostMapping
    override fun create(@Validated @RequestBody obj: User): User {
        return super.create(obj)
    }

    @PreAuthorize("hasAnyAuthority('USER_ALL','USER_DELETE')")
    @DeleteMapping("/{id}")
    override fun delete(@PathVariable @NotBlank(message = "需要删除的资源id不能为空") id: String) {
        super.delete(id)
    }

    @PreAuthorize("hasAnyAuthority('USER_ALL','USER_MODIFY')")
    @PutMapping
    override fun modify(@Validated @RequestBody obj: User): User {
        return super.modify(obj)
    }

    @PreAuthorize("hasAnyAuthority('USER_ALL','USER_QUERY')")
    @GetMapping
    override fun query(obj: User,
                       @RequestParam(defaultValue = "1") pageNum: Long,
                       @RequestParam(defaultValue = "10") pageSize: Long): IPage<User> {
        return super.query(obj, pageNum, pageSize)
    }

    @PreAuthorize("hasAnyAuthority('USER_ALL','USER_QUERY')")
    @GetMapping("/{id}")
    override fun getById(@PathVariable id: Long): User {
        return super.getById(id)
    }

}