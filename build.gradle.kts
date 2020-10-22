import org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val kotlinVersion = plugins.getPlugin(KotlinPluginWrapper::class.java).kotlinPluginVersion

repositories {
    mavenCentral()
    jcenter()
    maven ("https://repo.spring.io/snapshot")
    maven ("https://repo.spring.io/release")
}

plugins {
    kotlin("jvm") version "1.4.10"

    id("java")
    idea
    id("com.github.johnrengelman.shadow") version "6.0.0"
    id("maven-publish")
    id("com.jfrog.bintray") version "1.8.5"
    `java-library`

    id("com.google.protobuf") version "0.8.13"
    kotlin("kapt") version "1.4.10" // used by dsl-json
}

project.group = "com.test"
project.version = "1.0"

idea {
    module {
        isDownloadJavadoc = true
        isDownloadSources = true
    }
}

dependencies {
    implementation(kotlin("stdlib-jdk8", kotlinVersion))
    implementation(kotlin("reflect", kotlinVersion))

    implementation("com.dslplatform:dsl-json-java8:1.9.6")
    kapt("com.dslplatform:dsl-json-java8:1.9.6")

    testImplementation(kotlin("test-junit5", kotlinVersion))
    testImplementation("org.junit.jupiter:junit-jupiter:5.7.0")
}

configurations {
    implementation {
        resolutionStrategy.failOnVersionConflict()
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_14
    targetCompatibility = JavaVersion.VERSION_14
}

tasks {
    withType<KotlinCompile> {
        // https://github.com/JetBrains/kotlin/blob/master/compiler/util/src/org/jetbrains/kotlin/config/LanguageVersionSettings.kt
        kotlinOptions.jvmTarget = "13"
        kotlinOptions.freeCompilerArgs = listOf(
                "-Xjsr305=strict",
                "-progressive",
                "-Xjvm-default=enable",
                "-XXLanguage:+NewInference",
                "-XXLanguage:+SamConversionForKotlinFunctions",
                "-XXLanguage:+SamConversionPerArgument",
                "-Xinline-classes",
                "-Xjvm-default=enable",
                "-Xuse-experimental=kotlin.ExperimentalUnsignedTypes")
        kotlinOptions.apiVersion = "1.4"
        kotlinOptions.languageVersion = "1.4"
    }

    withType(Test::class.java) {
        testLogging.showStandardStreams = true
        testLogging.showExceptions = true
        useJUnitPlatform {
        }
    }

    withType(Wrapper::class.java).configureEach {
        gradleVersion = "6.4.1"
        distributionType = Wrapper.DistributionType.BIN
    }
}
