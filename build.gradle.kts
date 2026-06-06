import org.openapitools.generator.gradle.plugin.tasks.GenerateTask

plugins {
    kotlin("jvm") version "2.1.21"
    kotlin("plugin.spring") version "2.1.21"
    id("org.springframework.boot") version "3.5.14"
    id("io.spring.dependency-management") version "1.1.7"
    id("org.openapi.generator") version "7.10.0"
    kotlin("plugin.jpa") version "2.1.21"
    kotlin("plugin.allopen") version "2.1.21"
}
val springCloudVersion by extra("2025.0.2")

group = "team.mcqueen"
version = "0.0.1-SNAPSHOT"
description = "smart-display"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

val openApiOutputDir = layout.buildDirectory.dir("generated/openapi").get().asFile.absolutePath

openApiGenerate {
    generatorName.set("kotlin-spring")
    inputSpec.set("$rootDir/src/main/resources/openapi/api.yaml")
    outputDir.set(openApiOutputDir)
    apiPackage.set("team.mcqueen.smartdisplay.generated.api")
    modelPackage.set("team.mcqueen.smartdisplay.generated.model")
    configOptions.set(
        mapOf(
            "useSpringBoot3"     to "true",
            "useResponseEntity" to "false",
            "interfaceOnly"      to "true",
            "useTags"            to "true",
            "enumPropertyNaming" to "UPPERCASE",
            "serializationLibrary" to "jackson",
            "gradleBuildFile"    to "false",
            "exceptionHandler"   to "false",
            "reactive"           to "false",
        )
    )
}

val generateUjinApi = tasks.register<GenerateTask>("generateUjinApi") {
    generatorName.set("kotlin-spring")
    inputSpec.set("$rootDir/src/main/resources/openapi/ujin-api.yaml")
    outputDir.set(openApiOutputDir)
    apiPackage.set("team.mcqueen.smartdisplay.ujin.generated.api")
    modelPackage.set("team.mcqueen.smartdisplay.ujin.generated.model")
    library.set("spring-cloud")
    skipValidateSpec.set(true)
    configOptions.set(
        mapOf(
            "useSpringBoot3"     to "true",
            "interfaceOnly"      to "true",
            "useTags"            to "true",
            "enumPropertyNaming" to "UPPERCASE",
            "serializationLibrary" to "jackson",
        )
    )
}

sourceSets {
    main {
        kotlin {
            srcDir("$openApiOutputDir/src/main/kotlin")
        }
    }
}

tasks.named("compileKotlin") {
    dependsOn("openApiGenerate")
    dependsOn("generateUjinApi")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.6")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
    testImplementation("org.springframework.security:spring-security-test")

    runtimeOnly("org.postgresql:postgresql")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    developmentOnly("org.springframework.boot:spring-boot-docker-compose")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation(kotlin("stdlib"))
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}
dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.Embeddable")
    annotation("javax.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.Embeddable")
    annotation("jakarta.persistence.MappedSuperclass")
}