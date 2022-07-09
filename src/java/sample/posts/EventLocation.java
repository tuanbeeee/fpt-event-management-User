/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.posts;

public class EventLocation {

    private String locationID;
    private String locaitonName;

    public EventLocation() {
    }

    public EventLocation(String locationID, String locaitonName) {
        this.locationID = locationID;
        this.locaitonName = locaitonName;
    }

    public String getLocationID() {
        return locationID;
    }

    public void setLocationID(String locationID) {
        this.locationID = locationID;
    }

    public String getLocaitonName() {
        return locaitonName;
    }

    public void setLocaitonName(String locaitonName) {
        this.locaitonName = locaitonName;
    }

}
