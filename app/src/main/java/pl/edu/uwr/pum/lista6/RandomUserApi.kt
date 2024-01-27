package pl.edu.uwr.pum.lista6

import retrofit2.Response
import retrofit2.http.GET

interface RandomUserApi {

    @GET("v2/users?size=20")
    suspend fun users(): Response<List<User>>

}
