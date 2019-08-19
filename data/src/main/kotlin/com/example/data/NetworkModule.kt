package com.example.data

import android.provider.Settings.System.DATE_FORMAT
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Alexander Fermin (alexfer06@gmail.com) on 2019-08-19.
 */

val networkModule = module {

    single<Gson> {
        GsonBuilder().serializeSpecialFloatingPointValues()
            .excludeFieldsWithoutExposeAnnotation()
            .setDateFormat(DATE_FORMAT)
            .setLenient()
            .create()
    }

    single {
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    factory<OkHttpClient.Builder> {
        OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(get<ApiErrorResponseInterceptor>())
            .addInterceptor(get<HttpLoggingInterceptor>())
    }

    single<OkHttpClient> {
        get<OkHttpClient.Builder>().build()
    }

    factory<OkHttpClient> {
        get<OkHttpClient.Builder>()
            .build()
    }

    factory<Retrofit.Builder> {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(get()))
    }

    factory<Retrofit> {
        get<Retrofit.Builder>()
            .client(get<OkHttpClient>())
            .build()
    }

}