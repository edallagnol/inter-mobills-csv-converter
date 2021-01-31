import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    java
    kotlin("jvm") version "1.3.72"
    id("com.github.johnrengelman.shadow") version "6.1.0"
    application
}

group = "com.github.edallagnol"
version = "1.0-SNAPSHOT"

val mainClass = "com.github.edallagnol.MainKt"

application {
    mainClassName = mainClass
}

val run by tasks.getting(JavaExec::class) {
    standardInput = System.`in`
    standardOutput = System.out
}

val junitVersion: String = "5.6.2"

repositories {
    mavenCentral()
}

tasks.withType<Test> {
    useJUnitPlatform()
}

val shadowJar: ShadowJar by tasks
shadowJar.apply {
    manifest.attributes.apply {
        put("Implementation-Title", "Gradle Jar File Example")
        put("Implementation-Version", version)
        put("Main-Class", mainClass)
    }

    baseName = project.name + "-all"
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("com.fasterxml.jackson.dataformat", "jackson-dataformat-csv", "2.11.2")
    implementation("com.fasterxml.jackson.module", "jackson-module-kotlin", "2.11.2")
    implementation("commons-cli", "commons-cli", "1.3.1")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junitVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
}
