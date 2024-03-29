dependencies {
    api(project(":yangxj96-bean"))
    api(project(":yangxj96-common"))
    api(project(":yangxj96-starter"))
    api(project(":yangxj96-starter:yangxj96-starter-dubbo"))
    // 依赖dubbo,用于获取响应服务信息

    // satoken
    api(libs.bundles.satoken)

    // 编译的时候不打包这些依赖,具体的依赖服务会提供
    compileOnly("org.springframework.boot:spring-boot-starter-web")
    compileOnly("org.springframework.boot:spring-boot-starter-data-redis")
    compileOnly(libs.mybatisPlus.get())
}
