package nilherman.funfacts.data.apiclient

import retrofit2.Call
import nilherman.funfacts.data.model.characters.Response as Characters
import nilherman.funfacts.data.model.comics.Response as Comics
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
                     nameStartsWith : String): Call<Characters>

    @Headers("Accept: application/json")
    @GET("/v1/public/characters")
    fun getCharacter(@Query("apikey")
                     apikey : String,
                     @Query("ts")
                     ts : String,
                     @Query("hash")
                     hash : String): Call<Characters>

    @Headers("Accept: application/json")
    @GET("/v1/public/comics")
    fun getComic(@Query("apikey")
                     apikey : String,
                     @Query("ts")
                     ts : String,
                     @Query("hash")
                     hash : String,
                     @Query("titleStartsWith")
                     titleStartsWith : String): Call<Comics>

    @Headers("Accept: application/json")
    @GET("/v1/public/comics")
    fun getComic(@Query("apikey")
                     apikey : String,
                 @Query("ts")
                     ts : String,
                 @Query("hash")
                     hash : String): Call<Comics>

}