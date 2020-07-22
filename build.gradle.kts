plugins {
    java
    kotlin("jvm") version "1.3.72"
}

group = "dev.suresh"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
}

tasks {
    test {
        useJUnitPlatform()
        testLogging {
            events("passed", "skipped", "failed")
        }
    }
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation(enforcedPlatform("org.junit:junit-bom:5.6.2"))
    testImplementation(enforcedPlatform("org.testcontainers:testcontainers-bom:1.15.0-rc1"))

    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.testcontainers:testcontainers")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:cassandra")
}
