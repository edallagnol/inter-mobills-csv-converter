plugins {
    java
    kotlin("jvm") version "1.3.72"
}

group = "com.github.edallagnol"
version = "1.0-SNAPSHOT"


val junitVersion: String = "5.6.2"

repositories {
    mavenCentral()
}

tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("com.fasterxml.jackson.dataformat", "jackson-dataformat-csv", "2.11.2")
    implementation("com.fasterxml.jackson.module", "jackson-module-kotlin", "2.11.2")
    testCompile("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testCompile("org.junit.jupiter:junit-jupiter-params:$junitVersion")
    testRuntime("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
}
