package pl.edu.uwr.pum.lista6

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response


class UserViewModel : ViewModel() {
    private val repository = UserRepository()


    private var _users: MutableStateFlow<Resource<List<User>>> = MutableStateFlow(Resource.Loading())
    val users: StateFlow<Resource<List<User>>> = _users

    init {
        getUsersList()
    }

    private fun getUsersList() = viewModelScope.launch {
        _users.value = Resource.Loading()
        val response = repository.getUsers()
        _users.value = handleUsersResponse(response)
    }

    private fun handleUsersResponse(response: Response<List<User>>)
            : Resource<List<User>> {
        if (response.isSuccessful)
            response.body()?.let { return Resource.Success(it) }
        return Resource.Error(response.message())
    }


    fun getUserById(userId: String): User? {
        return when (val usersResource = _users.value) {
            is Resource.Success -> {
                usersResource.data?.find { it.id.toString() == userId }
            }
            else -> null
        }
    }
}