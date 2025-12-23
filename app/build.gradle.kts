plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.ilkproje.proje3viewsactivity"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.ilkproje.proje3viewsactivity"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildFeatures {
            compose = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion = "1.5.3" // Compose sürümüyle uyumlu
        }

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
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation("androidx.compose.ui:ui:1.5.3")
    implementation("androidx.compose.material3:material3:1.2.0-alpha05")
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.3")
    debugImplementation("androidx.compose.ui:ui-tooling:1.5.3")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation ("androidx.navigation:navigation-compose:2.7.3")
    implementation ("androidx.compose.runtime:runtime:1.5.2") // Compose için gerekli
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    dependencies {
        implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.0")
    }
    implementation ("androidx.compose.foundation:foundation:1.5.3")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.0")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.google.code.gson:gson:2.8.9")
    dependencies {
        implementation ("com.squareup.retrofit2:retrofit:2.9.0") // Retrofit kütüphanesi
        implementation ("com.squareup.retrofit2:converter-gson:2.9.0") // JSON için GSON dönüştürücü
        implementation ("com.squareup.okhttp3:logging-interceptor:4.9.0") // (isteğe bağlı) OkHttp interceptor, hata ayıklama için
        implementation ("com.squareup.okhttp3:okhttp:4.9.0")  // OkHttp (Retrofit'in ihtiyaç duyduğu HTTP istemcisi)
    }
}

