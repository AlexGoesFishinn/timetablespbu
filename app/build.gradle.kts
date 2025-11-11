plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.pluginSerialization)
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")

}


android {
    namespace = "org.alexgoesfishinn.timetablespbu"
    compileSdk = 35

    defaultConfig {
        applicationId = "org.alexgoesfishinn.timetablespbu"
        minSdk = 28
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        setProperty("archivesBaseName", "timetable-$versionName")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    viewBinding {
        enable = true
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    // Network
    implementation(libs.retrofit)
    implementation(libs.okhttp)
    implementation(libs.converter.gson)
    implementation(libs.kotlinx.serialization.json)
    //Navigation
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui)
    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
//Dependency injection
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
//    Room
    implementation(libs.room.runtime)
    ksp(libs.room.compiler)
    implementation(libs.room.ktx)


}