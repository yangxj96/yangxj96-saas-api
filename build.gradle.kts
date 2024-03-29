import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.buildpack.platform.build.PullPolicy
import org.springframework.boot.gradle.tasks.bundling.BootBuildImage

plugins {
    java
    alias(libs.plugins.spring.boot) apply(false)
    alias(libs.plugins.spring.dependency.management) apply(false)
    alias(libs.plugins.kotlin.jvm) apply(false)
    alias(libs.plugins.kotlin.spring) apply(false)
}

allprojects {
    group = "com.yangxj96.saas"
    version = "0.0.1-SNAPSHOT"


    repositories {
        mavenLocal()
        maven { url = uri("https://maven.aliyun.com/repository/public/") }
        maven { url = uri("https://repo.spring.io/milestone") }
        maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots/") }
        mavenCentral()
    }

}

subprojects {
    // 此处的插件由于在最外层的plugins中声明了. 且声明了版本号,此处能直接使用apply()
    apply(plugin = "java")
    apply(plugin = rootProject.libs.plugins.spring.boot.get().pluginId)
    apply(plugin = rootProject.libs.plugins.spring.dependency.management.get().pluginId)
    apply(plugin = rootProject.libs.plugins.kotlin.jvm.get().pluginId)
    apply(plugin = rootProject.libs.plugins.kotlin.spring.get().pluginId)

    // java源码和目标文件版本
    java {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    // 等同于dependencyManagement {}
    configure<DependencyManagementExtension> {
        imports {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:${rootProject.libs.versions.springCloud.get()}")
            mavenBom("com.alibaba.cloud:spring-cloud-alibaba-dependencies:${rootProject.libs.versions.springCloudAlibaba.get()}")
        }
    }

    dependencies {
        // 测试 begin
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        // 测试 end
        compileOnly("org.jetbrains:annotations:${rootProject.libs.versions.jetbrains.get()}")
        // kotlin支持
        implementation("org.jetbrains.kotlin:kotlin-reflect")
    }

    // 指定中文编码
    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
    }

    // 测试
    tasks.named<Test>("test") {
        useJUnitPlatform()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs += "-Xjsr305=strict"
            jvmTarget = "17"
        }
    }

    // 官方提供的打包为OCI镜像的配置
    tasks.named<BootBuildImage>("bootBuildImage") {
        // 是否发布到docker
        publish.set(false)
        // 镜像名称
        // imageName.set("${project.name}:${project.version}")
        // 不存在的时候才下载
        pullPolicy.set(PullPolicy.IF_NOT_PRESENT)
        // 构建器,指定版本
        builder.set("paketobuildpacks/builder:0.1.383-tiny")
        runImage.set("paketobuildpacks/run:1.3.128-tiny-cnb")

        // 环境变量
        // -Duser.timezone=Asia/Shanghai
        environment.set(
            mapOf(
                "HTTP_PROXY" to "http://192.168.2.29:7890",
                "HTTPS_PROXY" to "http://192.168.2.29:7890",
                "BPE_APPEND_JAVA_TOOL_OPTIONS" to " -Duser.timezone=Asia/Shanghai -Xms256m -Xmx256m -Xmn100m"
            )
        )

        buildCache {
            volume {
                name.set("cache-${rootProject.name}.build")
            }
        }
        launchCache {
            volume {
                name.set("cache-${rootProject.name}.launch")
            }
        }
    }

    // java编译任务设置
    tasks.named("compileJava") {
        inputs.files(tasks.named("processResources"))
    }

    // java doc任务设置
    tasks.named<Javadoc>("javadoc") {
        options.encoding = "UTF-8"
    }
}


