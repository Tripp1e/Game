plugins {
    id("java")
}

repositories {
    mavenCentral()
    maven {
            url = uri("https://mvn.repo.rccookie.de/releases")
    }

}

dependencies {
    implementation("org.greenfoot:greenfoot:3.7.0.11")
    implementation("org.json:json:20210307")
}

version = "0.0.1"
group = "org.jantor"