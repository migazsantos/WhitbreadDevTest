package com.whitebread.whitbreaddevelopertest.presenters;

import com.whitebread.whitbreaddevelopertest.api.RestAPI;
import com.whitebread.whitbreaddevelopertest.models.ExploreItem;
import com.whitebread.whitbreaddevelopertest.models.ExploreObject;
import com.whitebread.whitbreaddevelopertest.models.SearchRequestObject;
import com.whitebread.whitbreaddevelopertest.models.Venue;
import com.whitebread.whitbreaddevelopertest.views.MainMvpView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by miguel_santos on 27-04-2016.
 */
public class MainPresenter implements MainMvpPresenter {


    private MainMvpView mMainView;


    public MainPresenter(MainMvpView view){
        mMainView = view;
    }




    @Override
    public void getListOfVenuesByLocAndQuery(String location, String query) {

        // GET search venues
        RestAPI.getClient().searchVenue(RestAPI.AUTH_TOKEN, RestAPI.VERSION, RestAPI.ITEMS_LIMIT, location, query).enqueue(new Callback<SearchRequestObject>() {
            @Override
            public void onResponse(Call<SearchRequestObject> call, Response<SearchRequestObject> response) {
                if (response.isSuccessful()){
                    mMainView.showVenuesList(response.body().getSearchResponse().getVenues());
                }else{
                    mMainView.showNoResults();
                }
            }

            @Override
            public void onFailure(Call<SearchRequestObject> call, Throwable t) {
                mMainView.showNoResults();
            }
        });

    }

    @Override
    public void getListOfVenuesByLatLng(String location) {

        //GET explore venues
        RestAPI.getClient().getVenuesByLl(RestAPI.AUTH_TOKEN, RestAPI.VERSION, RestAPI.ITEMS_LIMIT, RestAPI.NUM_PHOTOS, location)
                .enqueue(new Callback<ExploreObject>() {
                    @Override
                    public void onResponse(Call<ExploreObject> call, Response<ExploreObject> response) {
                        if (response.isSuccessful()){
                            final List<Venue> venuesList =  getListOfVenuesFromItems(response.body().getResponse().getGroups().get(0).getItems());

                            mMainView.showRecommendedVenuesList(venuesList);
                        }else{
                            mMainView.showListNoResults();
                        }
                    }

                    @Override
                    public void onFailure(Call<ExploreObject> call, Throwable t) {
                        mMainView.showListNoResults();
                    }
                });
    }


    /*
     * Helper method to get all the venues in a list from a list of ExploreItem
     */
    private List<Venue> getListOfVenuesFromItems(List<ExploreItem> items) {
        final List<Venue> venueList = new ArrayList<>();
        for (ExploreItem item : items){
            venueList.add(item.getVenue());
        }

        return venueList;
    }


}
