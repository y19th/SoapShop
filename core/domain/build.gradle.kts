plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.domain"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    val navigationVersion = "2.7.6"
    val daggerVersion = "2.48"
    val retrofitVersion = "2.9.0"



    implementation(project(":core:data"))

    //navigation
    implementation("androidx.navigation:navigation-compose:$navigationVersion")

    implementation(platform("androidx.compose:compose-bom:2023.08.00"))

    // retrofit
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")


    //dagger
    implementation("com.google.dagger:dagger:$daggerVersion")
    implementation ("com.google.dagger:dagger-android-support:$daggerVersion")
    ksp("com.google.dagger:dagger-compiler:$daggerVersion")


    //hilt
    implementation ("com.google.dagger:hilt-android:$daggerVersion")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")
    ksp ("com.google.dagger:hilt-compiler:$daggerVersion")


    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}