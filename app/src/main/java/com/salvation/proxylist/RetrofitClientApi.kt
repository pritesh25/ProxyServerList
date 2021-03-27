package com.salvation.proxylist

import androidx.viewbinding.BuildConfig
import com.salvation.proxylist.BuildConfig.BASE_URL
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

object RetrofitClientApi {

    private const val mTag = "RetrofitClient"

    fun getRetrofitClient(): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        //httpClient.addInterceptor(BasicAuthInterceptor("hp_api", "W5N@CG$4e*b9"))

        httpClient.connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        httpClient.readTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        httpClient.writeTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            httpClient.addInterceptor(logging)
        }
        val builder = Retrofit.Builder().baseUrl(BASE_URL)
        return builder.addConverterFactory(GsonConverterFactory.create()).client(httpClient.build())
            .build()
    }

    internal class BasicAuthInterceptor(user: String, password: String) : Interceptor {
        private val credentials: String = Credentials.basic(user, password)

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            val authenticatedRequest = request.newBuilder()
                .header("Authorization", credentials).build()
            return chain.proceed(authenticatedRequest)
        }
    }
}

const val CONNECTION_TIMEOUT = 15L

const val STATUS_200: Int = 200
const val STATUS_300: Int = 300
const val STATUS_404: Int = 404