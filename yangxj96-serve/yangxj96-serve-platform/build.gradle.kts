dependencies {
    // 本服务主要处理平台相关的内容,不需要流处理,也不需要动态数据库
    api(project(":yangxj96-serve"))
    api(project(":yangxj96-bean"))
    api(project(":yangxj96-common"))
    api(project(":yangxj96-starter:yangxj96-starter-common"))
    api(project(":yangxj96-starter:yangxj96-starter-dubbo"))
    api(project(":yangxj96-starter:yangxj96-starter-db"))
    api(project(":yangxj96-starter:yangxj96-starter-security"))
}