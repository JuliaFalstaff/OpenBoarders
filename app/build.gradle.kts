plugins {
    id ("com.android.application")
    id ("kotlin-android")
    id ("kotlin-kapt")
    id ("kotlin-parcelize")
}

android {
    compileSdk = Config.compileSdkVersion
    buildToolsVersion = Config.buildToolsVersion

    defaultConfig {
        applicationId = Config.applicationId
        minSdk = Config.minSdkVersion
        targetSdk = Config.targetSdkVersion
        versionCode = Releases.versionCode
        versionName = Releases.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"


        kapt {
            arguments {
                arg("room.schemaLocation", "$projectDir/schemas")
            }
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

    }
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":model"))
    implementation(project(":utils"))
    implementation(project(":repository"))
    implementation(project(":historyscreen"))
    implementation(project(":favouritescreen"))
    //Kotlin
    implementation (Kotlin.stdLib)
    implementation (Kotlin.core)

    //SplashScreen
    implementation (SplashScreen.splashScreen)

    // Rx-Java
    implementation (RXJava.rxJava2Android)
    implementation (RXJava.rxJava2)

    // Retrofit 2
    implementation (Retrofit.retrofit)
    implementation (Retrofit.converterGson)
    implementation (Retrofit.loggingInterceptor)
    implementation (Retrofit.rxJava2Adapter)

    //Koin
    //Основная библиотека
    implementation (Koin.core)
    //Koin для поддержки Android (Scope,ViewModel ...)
    implementation (Koin.koinAndroid)
    //Для совместимости с Java
    implementation (Koin.androidCompat)

    //Coroutines Kotlin
    implementation (Coroutines.coroutinesCore)
    implementation (Coroutines.coroutinesAndroid)
    implementation (Coroutines.coroutinesAdapter)

    //Picasso
    implementation (Picasso.picasso)

    //Room
    implementation (Room.runtime)
    kapt (Room.compiler)
    implementation (Room.ktx)

    //Stetho
    implementation (Stetho.stetho)
    implementation (Stetho.stethoUrlConnection)
    implementation (Stetho.stethoJsRhino)
    implementation (Stetho.stethoOkhttp3)

    //SplashScree
    implementation (SplashScreen.splashScreen)

    // Test
    implementation (Design.androidxAppcompat)
    implementation (Design.material)
    implementation (Design.androidxConstraintlayout)
    testImplementation ("junit:junit:4.+")
    androidTestImplementation (Testing.testJunit)
    androidTestImplementation (Testing.espressoTest)

    //ExoPlayer
    implementation (ExoPlayer.exoPlayer)

    //Cicerone
    implementation ("com.github.terrakok:cicerone:7.1")
}