package com.whitebread.whitbreaddevelopertest.api;

import com.whitebread.whitbreaddevelopertest.models.ExploreObject;
import com.whitebread.whitbreaddevelopertest.models.SearchRequestObject;
import com.whitebread.whitbreaddevelopertest.models.Venue;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by miguel_santos on 27-04-2016.
 */
public class RestAPI {

    public static final int ITEMS_LIMIT = 10;
    public static final int NUM_PHOTOS = 1;
    public static final String IMAGE_SIZE = "100x100";


    private final static String BASE_URL = "https://api.foursquare.com/v2/";

    //Foursquare Public API Token and Version is here as this is oonly for demo purposes
    public static final String AUTH_TOKEN = "DKED51OZVZM1SZ5ZVPJE4KNBHTK0APDEHTFL3KZJZV2ZXBEM";
    public static final String VERSION = "20160426";


    private static ApiInterface sApiInterface;


    public static ApiInterface getClient(){

        if (sApiInterface == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            sApiInterface = retrofit.create(ApiInterface.class);

        }

        return sApiInterface;
    }


    public interface ApiInterface{

        // gets a list of venues by latitude and longitude
        @GET("venues/explore")
        Call<ExploreObject> getVenuesByLl(@Query("oauth_token") String authToken,
                                          @Query("v") String versionNum,
                                          @Query("limit") int limit,
                                          @Query("venuePhotos") int numPhotos,
                                          @Query("ll") String latLong);

        // gets a list of venues by location (City, Country)
        @GET("venues/search")
        Call<SearchRequestObject> searchVenue(@Query("oauth_token") String authToken,
                                              @Query("v") String versionNum,
                                              @Query("limit") int limit,
                                              @Query("near") String city,
                                              @Query("query") String whatToSearch);


    }

}
