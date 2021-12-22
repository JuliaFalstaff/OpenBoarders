object Config {
   const val applicationId = "com.example.androidprofessional"
   const val minSdkVersion = 23
   const val targetSdkVersion = 31
   const val compileSdkVersion = 31
   const val buildToolsVersion = "30.0.3"
}

object Releases {
    const val versionCode = 1
    const val versionName = "1.0"
}

object Versions {
   const val ktxVersion = "1.7.0"
   const val kotlinStdLibVersion = "1.4.2"
   const val rxJava2AndroidVersion = "2.1.0"
   const val rxJava2Version = "2.1.0"
   const val retrofit2Version = "2.6.0"
   const val converterGsonVersion = "2.6.0"
   const val okhhtp3LoggingInterceptorVersion = "3.12.1"
   const val retrofitAdapterVersion = "1.0.0"
   const val coroutinesVersion = "1.5.1"
   const val coroutinesAndroidVersion = "1.5.0"
   const val coroutinesAdapter = "0.9.2"
   const val appcompatVersion = "1.3.1"
   const val materialVerson = "1.4.0"
   const val constraintlayoutVersion = "2.1.1"
   const val junitVersion = "1.1.3"
   const val epressoCoreVersion = "3.4.0"
   const val koinVersion = "3.1.2"
   const val picassoVersion ="2.71828"
   const val roomVersion ="2.3.0"
   const val stethoVersion = "1.6.0"
}

object Kotlin {
    const val core = "androidx.core:core-ktx:${Versions.ktxVersion}"
    const val stdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinStdLibVersion}"
}

object RXJava {
    const val rxJava2Android = "io.reactivex.rxjava2:rxandroid:${Versions.rxJava2AndroidVersion}"
    const val rxJava2 = "io.reactivex.rxjava2:rxjava:${Versions.rxJava2Version}"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit2Version}"
    const val converterGson = "com.squareup.retrofit2:converter-gson:${Versions.converterGsonVersion}"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhhtp3LoggingInterceptorVersion}"
    const val rxJava2Adapter = "com.jakewharton.retrofit:retrofit2-rxjava2-adapter:${Versions.retrofitAdapterVersion}"
}

object Koin {
    const val core = "io.insert-koin:koin-core:${Versions.koinVersion}"
    const val koinAndroid = "io.insert-koin:koin-android:${Versions.koinVersion}"
    const val androidCompat= "io.insert-koin:koin-android-compat:${Versions.koinVersion}"
}

object Coroutines {
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesVersion}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesAndroidVersion}"
    const val coroutinesAdapter = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.coroutinesAdapter}"
}

object Picasso {
    const val picasso = "com.squareup.picasso:picasso:${Versions.picassoVersion}"
}

object Room {
    const val runtime = "androidx.room:room-runtime:${Versions.roomVersion}"
    const val compiler = "androidx.room:room-compiler:${Versions.roomVersion}"
    const val ktx = "androidx.room:room-ktx:${Versions.roomVersion}"
}

object Stetho {
    const val stetho = "com.facebook.stetho:stetho:${Versions.stethoVersion}"
    const val stethoUrlConnection = "com.facebook.stetho:stetho-urlconnection:${Versions.stethoVersion}"
    const val stethoJsRhino = "com.facebook.stetho:stetho-js-rhino:${Versions.stethoVersion}"
    const val stethoOkhttp3 = "com.facebook.stetho:stetho-okhttp3:${Versions.stethoVersion}"
}

object Design {
    const val androidxAppcompat = "androidx.appcompat:appcompat:${Versions.appcompatVersion}"
    const val material = "com.google.android.material:material:${Versions.materialVerson}"
    const val androidxConstraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintlayoutVersion}"
}

object Testing {
    const val testJunit = "androidx.test.ext:junit:${Versions.junitVersion}"
    const val espressoTest = "androidx.test.espresso:espresso-core:${Versions.epressoCoreVersion}"
}

object SplashScreen {
    const val splashScreen = "androidx.core:core-splashscreen:1.0.0-alpha02"
}