plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("com.google.devtools.ksp")
    id("androidx.room")
}

kotlin {
    applyDefaultHierarchyTemplate()
    
    jvm("desktop")
    androidTarget {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
        }
    }
    
    @OptIn(org.jetbrains.kotlin.gradle.ExperimentalWasmDsl::class)
    wasmJs {
        moduleName = "models"
        browser {
        }
    }

    js(IR) {
        browser()
    }

    iosArm64()
    iosSimulatorArm64()
    
    sourceSets {
        val ktorVersion = "3.0.0-rc-1"
        
        val commonMain by getting {
            dependencies {
                api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
                api("org.jetbrains.kotlinx:kotlinx-datetime:0.6.0")
                api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
                
                // Ktor
                api("io.ktor:ktor-client-core:$ktorVersion")
                api("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                api("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
            }
        }

        val nonWebMain by creating {
            dependsOn(commonMain)
            dependencies {
                // Room 2.7 Runtime (Mobile only)
                api("androidx.room:room-runtime:2.7.0-alpha11")
                api("androidx.room:room-common:2.7.0-alpha11")
                api("androidx.sqlite:sqlite:2.5.0")
                api("androidx.sqlite:sqlite-bundled:2.5.0")
            }
        }

        val webMain by creating {
            dependsOn(commonMain)
        }

        val androidMain by getting {
            dependsOn(nonWebMain)
            dependencies {
                implementation("io.ktor:ktor-client-okhttp:$ktorVersion")
                implementation("io.ktor:ktor-client-websockets:$ktorVersion")
                implementation("com.sun.mail:jakarta.mail:2.0.1")
                implementation("org.web3j:core:4.11.0")
                implementation("javax.inject:javax.inject:1")
            }
        }

        val iosMain by getting {
            dependsOn(nonWebMain)
            dependencies {
                implementation("io.ktor:ktor-client-darwin:$ktorVersion")
                implementation("io.ktor:ktor-client-websockets:$ktorVersion")
            }
        }

        val desktopMain by getting {
            dependsOn(nonWebMain)
            dependencies {
                implementation("io.ktor:ktor-client-cio:$ktorVersion")
                implementation("io.ktor:ktor-client-websockets:$ktorVersion")
                implementation("com.sun.mail:jakarta.mail:2.0.1")
                implementation("org.web3j:core:4.11.0")
                implementation("javax.inject:javax.inject:1")
            }
        }

        val wasmJsMain by getting {
            dependsOn(webMain)
            dependencies {
                implementation("io.ktor:ktor-client-js:$ktorVersion")
                implementation("io.ktor:ktor-client-websockets:$ktorVersion")
            }
        }

        val jsMain by getting {
            dependsOn(webMain)
            dependencies {
                implementation("io.ktor:ktor-client-js:$ktorVersion")
                implementation("io.ktor:ktor-client-websockets:$ktorVersion")
            }
        }
    }
}

room {
    schemaDirectory("$projectDir/schemas")
}

dependencies {
    constraints {
        commonMainImplementation("org.jetbrains.kotlin:kotlin-stdlib:2.1.0") {
            because("We want to stick to project Kotlin version")
        }
        commonMainImplementation("org.jetbrains.kotlin:kotlin-stdlib-wasm-js:2.1.0")
        commonMainImplementation("org.jetbrains.kotlin:kotlin-stdlib-js:2.1.0")
    }
    val roomCompiler = "androidx.room:room-compiler:2.7.0-alpha11"
    add("kspAndroid", roomCompiler)
    add("kspDesktop", roomCompiler)
    add("kspIosArm64", roomCompiler)
    add("kspIosSimulatorArm64", roomCompiler)
}

android {
    namespace = "net.wetheGoverned.model"
    compileSdk = 35
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
