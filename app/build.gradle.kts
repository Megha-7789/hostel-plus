plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

}


android {
    namespace = "com.example.hostell_plus"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.hostell_plus"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    android {
        // Other configurations...

        packagingOptions {
            exclude("META-INF/INDEX.LIST")
            exclude ("META-INF/io.netty.versions.properties")
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

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.firebase:firebase-firestore-ktx:24.7.0")
    implementation("com.google.android.gms:play-services-wallet:19.2.0")
    implementation("com.google.firebase:firebase-messaging-ktx:23.2.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation(kotlin("stdlib-jdk8"))
    implementation("io.ktor:ktor-server-netty:1.6.3")
    implementation("io.ktor:ktor-jackson:1.6.3")
    implementation("org.jetbrains.exposed:exposed-core:0.35.2")
    implementation("org.jetbrains.exposed:exposed-dao:0.35.2")
    implementation("org.xerial:sqlite-jdbc:3.36.0.3")

    implementation ("com.google.android.material:material:1.4.0")

    implementation ("com.google.zxing:core:3.4.1")
    implementation ("com.journeyapps:zxing-android-embedded:4.2.0")
    implementation ("com.google.android.gms:play-services-wallet:18.1.1")
    implementation ("com.journeyapps:zxing-android-embedded:3.6.0")





    //cardview Library
    implementation("androidx.cardview:cardview:1.0.0")
}
