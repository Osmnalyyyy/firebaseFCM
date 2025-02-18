package com.osman.firebasefcm.service

import com.osman.firebasefcm.util.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitObject {

    companion object {

        private val retrofit by lazy {

            Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        }

        val api: NotificationService by lazy {
            retrofit.create(NotificationService::class.java)
        }
    }
}