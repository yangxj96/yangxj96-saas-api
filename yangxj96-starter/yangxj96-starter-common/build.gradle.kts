dependencies {
    api(project(":yangxj96-bean"))
    api(project(":yangxj96-common"))
    api(project(":yangxj96-starter"))

    compileOnly("org.springframework.boot:spring-boot-starter-web")
    compileOnly("org.springframework.boot:spring-boot-starter-actuator")
    compileOnly("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery")
    compileOnly(libs.satokenCore.get())
}