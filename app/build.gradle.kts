import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.5.4"
    id("io.spring.dependency-management") version "1.1.7"
    kotlin("jvm") version "2.2.0"
    kotlin("plugin.spring") version "2.2.0"
}

group = "springfuckup"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:3.5.3")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.19.1")
    implementation("org.jetbrains.kotlin:kotlin-reflect:2.2.0")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.9")
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.5.3")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5:2.2.0")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.13.3")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    compilerOptions {
        freeCompilerArgs.add("-Xjsr305=strict")
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21)
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

springBoot {
    mainClass.set("springfuckup.AppKt")
}
