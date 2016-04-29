package com.whitebread.whitbreaddevelopertest.views;

import com.whitebread.whitbreaddevelopertest.models.ExploreItem;
import com.whitebread.whitbreaddevelopertest.models.Venue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by miguel_santos on 27-04-2016.
 */
public interface MainMvpView  {

    void showVenuesList(List<Venue> venuesList);

    void showRecommendedVenuesList(List<Venue> venuesList);

    void showNoResults();

    void showListNoResults();

}
