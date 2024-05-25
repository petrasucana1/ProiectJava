plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation ("junit:junit:4.12");
    implementation ("org.hibernate:hibernate-core:5.5.7.Final")
    implementation ("org.hibernate:hibernate-entitymanager:5.5.7.Final") // Optional, if you're using JPA EntityManager
    implementation ("javax.persistence:javax.persistence-api:2.2")// JPA API
    implementation ("org.slf4j:slf4j-api:1.7.32") // SLF4J API for logging (optional)
    implementation ("org.slf4j:slf4j-simple:1.7.32") // Simple implementation of SLF4J (optional)
    implementation("org.jgrapht:jgrapht-core:1.5.1")
    implementation ("org.apache.commons:commons-csv:1.8")
    implementation ("com.mchange:c3p0:0.9.5.5")// Dependința pentru C3P0
    implementation ("org.postgresql:postgresql:42.2.5")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

