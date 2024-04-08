import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.compose)
}

group = "com.shellwen.allpairs-kt"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

dependencies {
    // Note, if you develop a library, you should use compose.desktop.common.
    // compose.desktop.currentOs should be used in launcher-sourceSet
    // (in a separate module for demo project and in testMain).
    // With compose.desktop.common you will also lose @Preview functionality
    implementation(compose.desktop.currentOs) {
        // Exclude material 2 to avoid conflicts with material 3
        exclude(group = "org.jetbrains.compose.material", module = "material-desktop")
    }

    implementation(compose.materialIconsExtended)
    implementation(compose.material3)

    implementation(libs.allpairs4j)
}

compose.desktop {
    application {
        mainClass = "com.shellwen.allpairs.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "allpairs-kt"
            packageVersion = "1.0.0"
        }
    }
}
