dependencies {
    api(project(":yangxj96-bean"))
    api(project(":yangxj96-common"))
    api(project(":yangxj96-starter"))

    api("org.springframework.boot:spring-boot-starter-data-redis")
    api(libs.mybatisPlus.get())

    compileOnly("org.springframework.boot:spring-boot-starter-web")
    compileOnly(libs.satokenCore.get())
}