dependencies {

    compileOnly("org.springframework.boot:spring-boot-starter-web")
    compileOnly("org.springframework.boot:spring-boot-starter-validation")
    compileOnly(libs.mybatisPlus.get())
    // 工具
    api(libs.bundles.commonTool)
}