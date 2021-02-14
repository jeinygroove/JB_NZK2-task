plugins {
    kotlin("jvm") version "1.4.30"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    testImplementation("junit:junit:4.12")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.4.30")
}
