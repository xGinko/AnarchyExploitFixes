plugins {
    id("me.moomoo.anarchyexploitfixes.wrapper")
    alias(libs.plugins.shadow)
}

dependencies {
    compileOnly(libs.folia)
}

configure<JavaPluginExtension> {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
