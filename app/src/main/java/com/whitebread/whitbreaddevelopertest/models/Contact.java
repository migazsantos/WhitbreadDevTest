
package com.whitebread.whitbreaddevelopertest.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Contact {

    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("formattedPhone")
    @Expose
    private String formattedPhone;
    @SerializedName("facebook")
    @Expose
    private String facebook;
    @SerializedName("facebookUsername")
    @Expose
    private String facebookUsername;
    @SerializedName("facebookName")
    @Expose
    private String facebookName;

    /**
     *
     * @return
     * The phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     *
     * @param phone
     * The phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     *
     * @return
     * The formattedPhone
     */
    public String getFormattedPhone() {
        return formattedPhone;
    }

    /**
     *
     * @param formattedPhone
     * The formattedPhone
     */
    public void setFormattedPhone(String formattedPhone) {
        this.formattedPhone = formattedPhone;
    }

    /**
     *
     * @return
     * The facebook
     */
    public String getFacebook() {
        return facebook;
    }

    /**
     *
     * @param facebook
     * The facebook
     */
    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    /**
     *
     * @return
     * The facebookUsername
     */
    public String getFacebookUsername() {
        return facebookUsername;
    }

    /**
     *
     * @param facebookUsername
     * The facebookUsername
     */
    public void setFacebookUsername(String facebookUsername) {
        this.facebookUsername = facebookUsername;
    }

    /**
     *
     * @return
     * The facebookName
     */
    public String getFacebookName() {
        return facebookName;
    }

    /**
     *
     * @param facebookName
     * The facebookName
     */
    public void setFacebookName(String facebookName) {
        this.facebookName = facebookName;
    }


}
