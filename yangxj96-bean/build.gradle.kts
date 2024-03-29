dependencies {
    compileOnly(project(":yangxj96-common"))

    compileOnly("org.springframework.boot:spring-boot-starter-web")
    compileOnly("org.springframework.boot:spring-boot-starter-validation")
    compileOnly(libs.mybatisPlus.get())

    compileOnly(libs.bundles.satoken)

    compileOnly("org.springframework.cloud:spring-cloud-starter-gateway")
}