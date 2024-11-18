plugins {
    id("java")
    id("application")
}

repositories {
    mavenCentral()
    maven {
        url = uri("https://mvn.repo.rccookie.de/releases")
    }
}

sourceSets {
    main {
        resources {
            setSrcDirs(listOf("src/main/assets"))  // Add your custom sources directory here
        }
    }
}

dependencies {
    implementation("org.greenfoot:greenfoot:3.7.0.11")
    implementation("org.json:json:20210307")
}

version = "0.0.1"
group = "org.jantor"

tasks.register<JavaExec>("runScenario") {
    mainClass.set("greenfoot.export.GreenfootScenarioApplication")
    classpath = sourceSets["main"].runtimeClasspath
    args = emptyList()

    jvmArgs = listOf(
        "--module-path", "C:\\Program Files\\Greenfoot\\lib\\javafx\\lib",
        "--add-modules", "javafx.controls,javafx.fxml"
    )

    workingDir = file("$projectDir/src/main")
}

tasks.named("run") {
    dependsOn("runScenario")
}
