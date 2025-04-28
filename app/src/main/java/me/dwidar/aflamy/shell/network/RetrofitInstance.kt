package me.dwidar.aflamy.shell.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitInstance
{
    private const val BASE_URL = "https://api.themoviedb.org/3/"
    const val apiToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3Njk5NTY2ODM2NDQ5MjM1YTEzMTk4Mzc5MWI5Y2M5YSIsIm5iZiI6MTY2OTI2OTIxNy45NDIwMDAyLCJzdWIiOiI2MzdmMDZlMWE0MTBjODAwODQ4MTQ4ZDAiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.TIhiXtehF5G18JqbhV9P35PnOeqll4PCutIZqVo4r0w"

    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())  // <<< this is super important
        .build()

    val api: APIService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(APIService::class.java)
    }
}