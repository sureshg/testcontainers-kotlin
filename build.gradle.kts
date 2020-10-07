plugins {
    java
    kotlin("jvm") version "1.4.10"
    id("com.github.ben-manes.versions") version "0.33.0"
}

group = "dev.suresh"
version = "1.0.0"

repositories {
    mavenCentral()
}

tasks {
    test {
        useJUnitPlatform()
        testLogging {
            events("passed", "skipped", "failed")
            showStandardStreams = true
        }
    }
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    testImplementation(enforcedPlatform("org.junit:junit-bom:5.7.0-RC1"))
    testImplementation(enforcedPlatform("org.testcontainers:testcontainers-bom:1.15.0-rc2"))

    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:testcontainers")
    testImplementation("org.testcontainers:cassandra")
    testImplementation("org.slf4j:slf4j-simple:1.7.30")
}
