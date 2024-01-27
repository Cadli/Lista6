package pl.edu.uwr.pum.lista6

import com.google.gson.annotations.SerializedName

data class User (

    val id: Int,

    val avatar: String,
    val username: String,
    val email: String,

    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,

    val gender: String,
    @SerializedName("date_of_birth")
    val dateOfBirth: String,
    val address: Address

) {
    data class Address(

        val city: String,
        @SerializedName("street_name")
        val streetName: String,
        @SerializedName("street_address")
        val streetAddress: String,
        @SerializedName("zip_code")
        val zipCode: String,
        val state: String,
        val country: String
    )
}