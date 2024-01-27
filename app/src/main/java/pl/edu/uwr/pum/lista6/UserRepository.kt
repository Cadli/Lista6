package pl.edu.uwr.pum.lista6

class UserRepository {
    private val api = RetrofitInstance.api

    suspend fun getUsers() = api.users()

}
