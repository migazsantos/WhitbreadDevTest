package com.whitebread.whitbreaddevelopertest.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.whitebread.whitbreaddevelopertest.R;
import com.whitebread.whitbreaddevelopertest.adapters.VenuesListAdapter;
import com.whitebread.whitbreaddevelopertest.models.Venue;
import com.whitebread.whitbreaddevelopertest.presenters.MainMvpPresenter;
import com.whitebread.whitbreaddevelopertest.presenters.MainPresenter;
import com.whitebread.whitbreaddevelopertest.utils.SpaceItemDecoration;

import java.util.List;

public class MainActivityView extends AppCompatActivity implements View.OnClickListener, MainMvpView {

    private final int LIST_ITEMS_SPACE = 30;

    private MainMvpPresenter mPresenter;

    private EditText mEditTextSearchName;
    private EditText mEditTextSearchWhere;
    private Button mSearchButton;
    private ImageView mSearchedVenueImage;
    private TextView mSearchedVenueName;
    private TextView mSearchedVenueCategory;
    private TextView mSearchedVenueRating;
    private View mSearchedVenueProgressSection;
    private View mSearchedVenueSection;
    private View mSearchedVenueProgressImage;
    private View mOtherVenuesSection;
    private View mOtherVenuesListProgress;
    private TextView mListLabel;
    private View mListNoResultsLabel;
    private View mResultsSection;
    private View mNoResultsLabel;

    private VenuesListAdapter mListAdapter;
    private RecyclerView mVenuesList;

    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new MainPresenter(this);


        setContentView(R.layout.activity_main);
        mEditTextSearchName = (EditText) findViewById(R.id.main_activity_search_place_name);
        mEditTextSearchWhere = (EditText) findViewById(R.id.main_activity_search_place_where);
        mSearchButton = (Button) findViewById(R.id.main_search_button);
        mSearchButton.setOnClickListener(this);
        mResultsSection =  findViewById(R .id.main_activity_results_label);
        mNoResultsLabel = findViewById(R .id.main_activity_no_results_label);

        mSearchedVenueProgressSection =  findViewById(R .id.main_activity_searched_venue_progress);
        mSearchedVenueSection =  findViewById(R .id.main_activity_searched_venue_section);

        final View searchedVenueView =  findViewById(R.id.main_activity_searched_venue);
        mSearchedVenueImage =(ImageView) searchedVenueView.findViewById(R .id.venue_item_image);
        mSearchedVenueName = (TextView) searchedVenueView.findViewById(R .id.venue_item_name);
        mSearchedVenueCategory = (TextView) searchedVenueView.findViewById(R .id.venue_item_category);
        mSearchedVenueRating = (TextView) searchedVenueView.findViewById(R .id.venue_item_rating);
        mSearchedVenueProgressImage =  searchedVenueView.findViewById(R .id.venue_item_progress_image);

        mOtherVenuesSection = findViewById(R.id.main_activity_other_venues_section);
        mOtherVenuesListProgress = findViewById(R.id.main_activity_other_venues_progress);
        mListLabel = (TextView) findViewById(R.id.main_activity_list_label);
        mListNoResultsLabel = findViewById(R.id.main_activity_list_no_results_label);

        mVenuesList = (RecyclerView) findViewById(R.id.main_venues_list);
        mVenuesList.setLayoutManager(new LinearLayoutManager(this));
        mVenuesList.addItemDecoration(new SpaceItemDecoration(LIST_ITEMS_SPACE));

    }




    /*
     * Display all the venues results on the list from search query
     */
    @Override
    public void showVenuesList(List<Venue> venuesList) {
        hideGeneralLoading();
        mSearchButton.setEnabled(true);
        mSearchedVenueSection.setVisibility(View.GONE);

        //may be necessary in case there had been an error or no items search before
        hideNoResultsView();
        if (venuesList.size() == 0){
            showNoResults();
        }else if (venuesList.size() == 1){
            //as there's only one possible result, let's show it as selected already and download the close recommendations
            mOnItemClickListener.onClick(venuesList.get(0));
        }else{
            mListAdapter = new VenuesListAdapter(this, venuesList, mOnItemClickListener);
            mVenuesList.setAdapter(mListAdapter);

            mListLabel.setText(getString(R.string.main_activity_venues_similar_label));
        }
    }


    @Override
    public void showRecommendedVenuesList(List<Venue> venuesList) {
        if (venuesList.size() == 0){
            showListNoResults();
        }else if (mListAdapter != null){
            mListAdapter.setDataset(venuesList);
            mListAdapter.notifyDataSetChanged();
        }else{
            mListAdapter = new VenuesListAdapter(this, venuesList, mOnItemClickListener);
            mVenuesList.setAdapter(mListAdapter);
        }

        hideListLoading();

    }

    /*
     * In case there are no results to show, display "No Results" message
     */
    @Override
    public void showNoResults() {
        mResultsSection.setVisibility(View.GONE);
        mNoResultsLabel.setVisibility(View.VISIBLE);
    }

    public void hideNoResultsView() {
        mResultsSection.setVisibility(View.VISIBLE);
        mNoResultsLabel.setVisibility(View.GONE);
    }

    @Override
    public void showListNoResults() {
        mListNoResultsLabel.setVisibility(View.VISIBLE);
        mOtherVenuesListProgress.setVisibility(View.GONE);
        mVenuesList.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        String query = mEditTextSearchName.getText().toString();
        String where = mEditTextSearchWhere.getText().toString();
        if ( !where.isEmpty() ){
            mSearchButton.setEnabled(false);
            showGeneralLoading();

            mPresenter.getListOfVenuesByLocAndQuery(where, query);

        }else{
            mEditTextSearchWhere.setError(getString(R.string.main_invalid_location_provided));
        }

    }

    /*
     * Loading used when SEARCH button is pressed
     */
    private void showGeneralLoading() {
        mSearchedVenueSection.setVisibility(View.GONE);
        mOtherVenuesSection.setVisibility(View.GONE);
        mSearchedVenueProgressSection.setVisibility(View.VISIBLE);
    }

    private void hideGeneralLoading(){
        mSearchedVenueSection.setVisibility(View.VISIBLE);
        mOtherVenuesSection.setVisibility(View.VISIBLE);
        mSearchedVenueProgressSection.setVisibility(View.GONE);
    }

    /*
     * Loading used when a list  is pressed
     */
    private void showListLoading() {
        mOtherVenuesListProgress.setVisibility(View.VISIBLE);
        mVenuesList.setVisibility(View.GONE);
    }


    private void hideListLoading() {
        mOtherVenuesListProgress.setVisibility(View.GONE);
        mVenuesList.setVisibility(View.VISIBLE);
    }

    /*
     * Sets a venue on the "searched" section
     */
    private void showSelectedVenue(Venue venue) {
        mSearchedVenueSection.setVisibility(View.VISIBLE);
        mSearchedVenueName.setText(venue.getName());
        if (venue.getCategories() != null && venue.getCategories().size() > 0){
            mSearchedVenueCategory.setText(venue.getCategories().get(0).getName());
        }
        if (venue.getRating() > 0){
            mSearchedVenueRating.setText(String.valueOf(venue.getRating()));
        }

    }


    private final VenuesListAdapter.IItemClickListener mOnItemClickListener = new VenuesListAdapter.IItemClickListener() {
        @Override
        public void onClick(Venue venue) {
            showSelectedVenue(venue);

            final String latLng = venue.getLocation().getLat() + "," + venue.getLocation().getLng();
            mPresenter.getListOfVenuesByLatLng(latLng);

            mListLabel.setText(getString(R.string.main_activity_venues_other_label));
            showListLoading();
        }
    };


}
