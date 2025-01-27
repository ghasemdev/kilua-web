import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kilua)
}

@OptIn(ExperimentalWasmDsl::class)
kotlin {
    js(IR) {
        useEsModules()
        browser {
            commonWebpackConfig {
                outputFileName = "main.bundle.js"
                sourceMaps = false
            }
            testTask {
                useKarma {
                    useChromeHeadless()
                }
            }
        }
        binaries.executable()
    }
    wasmJs {
        useEsModules()
        browser {
            commonWebpackConfig {
                outputFileName = "main.bundle.js"
                sourceMaps = false
            }
            testTask {
                useKarma {
                    useChromeHeadless()
                }
            }
        }
        binaries.executable()
    }
    sourceSets {
        commonMain.dependencies {
//            implementation(compose.animation)
            implementation(libs.kilua)
//            implementation(libs.kilua.routing)
            implementation(npm("beercss", "3.8.0")) // material3
            implementation(npm("swup", "4.8.1")) // animation
            implementation(npm("@swup/slide-theme", "2.0.1")) // animation
        }
    }
}
