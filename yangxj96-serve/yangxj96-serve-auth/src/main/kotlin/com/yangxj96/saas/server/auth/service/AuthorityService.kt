package com.yangxj96.saas.server.auth.service

import cn.hutool.core.lang.tree.Tree
import com.yangxj96.saas.bean.user.Authority
import com.yangxj96.saas.common.base.BaseService


/**
 * 权限service层
 */
interface AuthorityService : BaseService<Authority> {

    /**
     * 返回树格式的权限列表
     *
     * @return 树结构的权限列表
     */
    fun tree(): List<Tree<String>>
}
