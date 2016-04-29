package com.whitebread.whitbreaddevelopertest.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.whitebread.whitbreaddevelopertest.R;
import com.whitebread.whitbreaddevelopertest.api.RestAPI;
import com.whitebread.whitbreaddevelopertest.models.Venue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by miguel_santos on 28-04-2016.
 */
public class VenuesListAdapter extends RecyclerView.Adapter<VenuesListAdapter.ViewHolder> {

    private Context mContext;
    private IItemClickListener mItemClickListener;
    private List<Venue> mDataset;

    public VenuesListAdapter(Context context, List<Venue> dataset, IItemClickListener itemClickListener ) {
        mContext = context;
        mDataset = dataset;
        mItemClickListener = itemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.venue_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Venue venue = mDataset.get(position);

        holder.mItemName.setText(venue.getName());
        if (venue.getCategories() != null && venue.getCategories().size() > 0){
            holder.mItemCategory.setText(venue.getCategories().get(0).getName());
        }
        if (venue.getRating() > 0){
            holder.mItemRating.setText(String.valueOf(venue.getRating()));
        }
        if ( venue.getLocation() != null ) {
            holder.mItemDistance.setText(String.format(mContext.getString(R.string.list_item_distance), String.valueOf(venue.getLocation().getDistance())));
        }
        if (venue.getPhotos() != null && venue.getPhotos().getGroups() != null &&  venue.getPhotos().getGroups().get(0) != null
                &&  venue.getPhotos().getGroups().get(0).getItems() != null && venue.getPhotos().getGroups().get(0).getItems().get(0) != null){

            final String urlImage = venue.getPhotos().getGroups().get(0).getItems().get(0).getPrefix() + RestAPI.IMAGE_SIZE + venue.getPhotos().getGroups().get(0).getItems().get(0).getSuffix();

            Glide.with(mContext)
                    .load(urlImage)
                    .placeholder(android.R.drawable.progress_indeterminate_horizontal)
                    .error(R.mipmap.ic_fousquare)
                    .into(holder.mItemImage);
        }
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public final TextView mItemName;
        public final TextView mItemCategory;
        public final TextView mItemDistance;
        public final ImageView mItemImage;
        public final View mProgressImage;
        public final TextView mItemRating;

        public ViewHolder(View itemView) {
            super(itemView);
            mItemName = (TextView) itemView.findViewById(R.id.venue_item_name);
            mItemCategory = (TextView) itemView.findViewById(R.id.venue_item_category);
            mItemDistance = (TextView) itemView.findViewById(R.id.venue_item_distance);
            mItemImage = (ImageView) itemView.findViewById(R.id.venue_item_image);
            mProgressImage = itemView.findViewById(R.id.venue_item_progress_image);
            mItemRating = (TextView) itemView.findViewById(R.id.venue_item_rating);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mItemClickListener.onClick(mDataset.get(getAdapterPosition()));
        }
    }

    public void setDataset(final List<Venue> listVenue){
        mDataset = listVenue;
    }

    public interface IItemClickListener{
        void onClick(Venue venue);
    }
}
