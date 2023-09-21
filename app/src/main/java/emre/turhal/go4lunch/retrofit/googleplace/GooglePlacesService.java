package emre.turhal.go4lunch.retrofit.googleplace;

import static emre.turhal.go4lunch.utils.Constants.BASE_URL_GOOGLE_API;

import emre.turhal.go4lunch.models.googleplaces_gson.SearchPlace;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GooglePlacesService {

    Retrofit retrofit = new Retrofit.Builder()

            .baseUrl(BASE_URL_GOOGLE_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    @GET("/maps/api/place/nearbysearch/json")
    Call<SearchPlace> getNearbyRestaurants(@Query("location") String location,
                                           @Query("rankby") String distanceRanking,
                                           @Query("type") String type,
                                           @Query("key") String key);

}
