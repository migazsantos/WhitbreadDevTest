
package com.whitebread.whitbreaddevelopertest.models;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class SearchRequestObject {

    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("notifications")
    @Expose
    private List<Notification> notifications = new ArrayList<Notification>();
    @SerializedName("response")
    @Expose
    private SearchResponse searchResponse;

    /**
     * 
     * @return
     *     The meta
     */
    public Meta getMeta() {
        return meta;
    }

    /**
     * 
     * @param meta
     *     The meta
     */
    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    /**
     * 
     * @return
     *     The notifications
     */
    public List<Notification> getNotifications() {
        return notifications;
    }

    /**
     * 
     * @param notifications
     *     The notifications
     */
    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    /**
     * 
     * @return
     *     The searchResponse
     */
    public SearchResponse getSearchResponse() {
        return searchResponse;
    }

    /**
     * 
     * @param searchResponse
     *     The searchResponse
     */
    public void setSearchResponse(SearchResponse searchResponse) {
        this.searchResponse = searchResponse;
    }

}
