package com.example.android.quakereport;
public class Earthquake {
    private double magnitiude;
    private String place;
    private long dateandTime;
    private String url;

    public double getMagnitiude() {
        return magnitiude;
    }

    public String getPlace() {
        return place;
    }

    public long getDateandTime() {
        return dateandTime;
    }

    public String getUrl() {
        return url;
    }

    public Earthquake(double mMagnitiude, String mPlace, long mDateandTime, String url) {
        this.magnitiude = mMagnitiude;
        this.place = mPlace;
        this.dateandTime = mDateandTime;
        this.url = url;
    }
}
