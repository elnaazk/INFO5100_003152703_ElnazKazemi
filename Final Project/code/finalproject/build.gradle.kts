plugins {
    id("application")
    id("org.openjfx.javafxplugin") version "0.1.0"
}

repositories {
    mavenCentral()
}

dependencies {
}

javafx {
    version = "17.0.11"
    modules = listOf("javafx.controls", "javafx.fxml")
}

application {
    mainClass.set("edu.elnaazk.finalproject.MainApp")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}