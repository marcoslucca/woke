import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.4.5"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("org.jmailen.kotlinter") version "3.3.0"
    id("io.gitlab.arturbosch.detekt") version "1.16.0-RC1"
    id("application")
    id("jacoco")
    id("com.adarshr.test-logger") version "2.1.1"
    id("org.unbroken-dome.test-sets") version "2.2.1"
    kotlin("plugin.spring") version "1.4.32"
    kotlin("plugin.jpa") version "1.4.32"
    kotlin("jvm") version "1.4.32"
}

group = "br.com.woke"
version = "0.1"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

val testContainerVersion by extra { "1.15.2" }
val kluentVersion = "1.65"
val wiremockVersion by extra { "2.27.2" }
val springCloudVersion by extra { "2020.0.2" }

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

application {
    mainClassName = "br.com.woke.WebApplicationKt"
}

testSets {
    create("componentTest") {
        dirName = "component-test"
    }
}

val componentTestImplementation: Configuration by configurations.getting {
    extendsFrom(configurations.implementation.get())
}

dependencies {
    // KOTLIN
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // ARROW
    implementation("io.arrow-kt:arrow-core:0.10.5")
    implementation("io.arrow-kt:arrow-syntax:0.10.5")

    // SPRING BOOT
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")

    // SPRING CLOUD
    implementation("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
    implementation("org.springframework.cloud:spring-cloud-starter-sleuth")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")

    // JACKSON
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.12.1")

    // LOGS
    implementation("ch.qos.logback:logback-classic:1.2.3")
    implementation("net.logstash.logback:logstash-logback-encoder:6.6")

    // DATABASE
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("org.postgresql:postgresql")
    implementation("org.flywaydb:flyway-core:7.1.1")

    implementation("com.github.kittinunf.result:result:4.0.0")

    // WIRE MOCK
    implementation("com.github.tomakehurst:wiremock-jre8:$wiremockVersion")

    // TEST ENGINE
    testImplementation("io.mockk:mockk:1.10.6")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
    testImplementation("com.github.tomakehurst:wiremock-jre8:$wiremockVersion")
    testImplementation("org.testcontainers:junit-jupiter:$testContainerVersion")
    testImplementation("org.testcontainers:postgresql:$testContainerVersion")
    testImplementation("org.mockito:mockito-inline:3.7.7")
    testImplementation("org.amshove.kluent:kluent:1.65")

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }

    testImplementation("org.testcontainers:localstack:1.15.3")

    componentTestImplementation(sourceSets.test.get().output)
    componentTestImplementation("org.jetbrains.kotlin:kotlin-test-junit")
    componentTestImplementation("com.github.tomakehurst:wiremock-jre8:$wiremockVersion")
    componentTestImplementation("org.testcontainers:junit-jupiter:$testContainerVersion")
    componentTestImplementation("org.testcontainers:postgresql:$testContainerVersion")

    "componentTestImplementation"(sourceSets["test"].output)
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

kotlinter {
    ignoreFailures = true
    indentSize = 4
    reporters = arrayOf("checkstyle", "plain")
    experimentalRules = false
    disabledRules = emptyArray()
}

tasks.withType(Test::class.java) {
    useJUnitPlatform()
    extensions.configure(JacocoTaskExtension::class.java) {
        setDestinationFile(file("$buildDir/jacoco/jacocoTest.exec"))
    }
}

tasks {
    jacocoTestReport {
        reports {
            xml.isEnabled = true
            csv.isEnabled = false
        }
    }

    assemble { dependsOn(formatKotlin) }

    test {
        systemProperty("user.timezone", "UTC")
        environment("AWS_ACCESS_KEY_ID", "minioadmin")
        environment("AWS_SECRET_KEY", "minioadmin")

        System.getProperty("test.type")?.let {
            if (it == "unit") exclude("**/*integration*")
            if (it == "integration") exclude("**/*unit*")
        }
    }

    testlogger {
        showFullStackTraces = true
        showSimpleNames = true
        showStandardStreams = true
        showPassedStandardStreams = false
        showSkippedStandardStreams = true
    }

    detekt {
        input = files("src/main/kotlin", "src/test/kotlin")
        config = files("./detekt-config.yml")
        autoCorrect = true
    }
}
