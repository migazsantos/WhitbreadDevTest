
package com.whitebread.whitbreaddevelopertest.models;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Item {

    @SerializedName("unreadCount")
    @Expose
    private int unreadCount;

    /**
     * 
     * @return
     *     The unreadCount
     */
    public int getUnreadCount() {
        return unreadCount;
    }

    /**
     * 
     * @param unreadCount
     *     The unreadCount
     */
    public void setUnreadCount(int unreadCount) {
        this.unreadCount = unreadCount;
    }

}
