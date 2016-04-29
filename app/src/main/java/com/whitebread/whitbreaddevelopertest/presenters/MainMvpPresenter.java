package com.whitebread.whitbreaddevelopertest.presenters;

import com.whitebread.whitbreaddevelopertest.views.MainMvpView;

/**
 * Created by miguel_santos on 27-04-2016.
 */
public interface MainMvpPresenter {

    void getListOfVenuesByLocAndQuery(String location, String query);

    void getListOfVenuesByLatLng(String location);
}
