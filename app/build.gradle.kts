plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.mapexample"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.mapexample"
        minSdk = 30
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
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //library required to display map
    implementation("com.tomtom.online:sdk-maps:2.4692")
    //library required for search
    implementation("com.tomtom.online:sdk-search:2.4692")
    //library required for routing
    implementation("com.tomtom.online:sdk-routing:2.4692")
    //library required for traffic
    implementation("com.tomtom.online:sdk-traffic:2.4692")
    //library required for geofencing
    implementation("com.tomtom.online:sdk-geofencing:2.4692")
    //extention library for map custom style and ui support
    implementation("com.tomtom.online:sdk-maps-ui-extensions:2.4692")
    //extention library for rx-java2
    implementation("com.tomtom.online:sdk-maps-rx-extensions:2.4692")
    //extention library for kotlin support
    implementation("com.tomtom.online:sdk-maps-ktx-extensions:2.4692")
    //extention library for displaying static map
    implementation("com.tomtom.online:sdk-maps-static-image:2.4692")
    //extention library for driving features
    implementation("com.tomtom.online:sdk-maps-driving-extensions:2.4692")

    implementation("com.tomtom.online:sdk-routing-rxjava2:2.4712")
}