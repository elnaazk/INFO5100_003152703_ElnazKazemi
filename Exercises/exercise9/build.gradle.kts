plugins {
    application
    id("org.openjfx.javafxplugin") version "0.1.0"
}

repositories { mavenCentral() }

java { toolchain { languageVersion.set(JavaLanguageVersion.of(17)) } }

application { mainClass.set("org.example.CalculatorApp") }

javafx {
    version = "21.0.3"
    modules = listOf("javafx.controls")
}
