import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("com.android.library")
}

kotlin {
    android()

    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget = when {
        System.getenv("SDK_NAME")?.startsWith("iphoneos") == true -> ::iosArm64
        System.getenv("NATIVE_ARCH")?.startsWith("arm") == true -> ::iosSimulatorArm64
        else -> ::iosX64
    }

    iosTarget("ios") {
        binaries {
            framework {
                baseName = "shared"
            }
        }
    }
    sourceSets {
        val ktorVersion = "1.6.3"
        val commonMain by getting {
            dependencies {
                //Logger
                implementation("io.github.aakira:napier:1.4.1")

                // Ktor
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-logging:$ktorVersion")
                implementation("io.ktor:ktor-client-serialization:$ktorVersion")

                // Serialization
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.1.0")

                val coroutinesVersion = "1.5.1-native-mt"
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
//                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")

                // Koin
//                implementation("io.insert-koin:koin-core:3.1.2")
//                implementation("io.insert-koin:koin-android:3.1.2")
//                implementation("io.insert-koin:koin-androidx-compose:3.1.2")

            }
        }
//        with(Deps.Koin) {
//            api(core)
//            api(test)
//        }
        val androidMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-okhttp:$ktorVersion")
//                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.1-native-mt")
            }
        }
        val iosMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-ios:$ktorVersion")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))

                // Koin
//                implementation("io.insert-koin:koin-test:3.1.2")
//                implementation("io.insert-koin:koin-test-junit4:3.1.2")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }
        val iosTest by getting
    }
}

android {
    compileSdkVersion(31)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(31)
    }
}