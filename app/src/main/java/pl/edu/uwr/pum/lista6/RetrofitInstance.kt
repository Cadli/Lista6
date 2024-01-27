package pl.edu.uwr.pum.lista6

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: RandomUserApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://random-data-api.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RandomUserApi::class.java)
    }
}
