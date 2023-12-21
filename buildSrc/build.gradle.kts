plugins {
    `kotlin-dsl`
}
dependencies {
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_20.toString()
    }
}
tasks.withType<JavaCompile>().configureEach {
    sourceCompatibility = JavaVersion.VERSION_20.toString()
    targetCompatibility = JavaVersion.VERSION_20.toString()
}
java {
    sourceCompatibility = JavaVersion.VERSION_20
    targetCompatibility = JavaVersion.VERSION_20
}
kotlin {
    jvmToolchain(20)
}