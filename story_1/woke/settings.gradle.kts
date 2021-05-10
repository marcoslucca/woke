rootProject.name = "woke"
pluginManagement {
    repositories {
        maven {
            url = uri("https://packages.confluent.io/maven/")
        }
        maven {
            name = "JCenter Gradle Plugins"
            url = uri("https://dl.bintray.com/gradle/gradle-plugins")
        }
        gradlePluginPortal()
    }
}
