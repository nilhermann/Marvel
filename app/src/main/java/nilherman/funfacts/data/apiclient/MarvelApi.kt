package nilherman.funfacts.data.apiclient

import nilherman.funfacts.data.model.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MarvelApi {

    @Headers("Accept: application/json")
    @GET("/v1/public/characters")
    fun getCharacter(@Query("apikey")
                     apikey : String,
                     @Query("ts")
                     ts : String,
                     @Query("hash")
                     hash : String,
                     @Query("nameStartsWith")
                     nameStartsWith : String): Call<Response>

    @Headers("Accept: application/json")
    @GET("/v1/public/characters")
    fun getCharacter(@Query("apikey")
                     apikey : String,
                     @Query("ts")
                     ts : String,
                     @Query("hash")
                     hash : String): Call<Response>

}