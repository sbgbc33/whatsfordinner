package org.wfd.model;

import java.io.Serializable;


// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.4612F468-D5DB-0798-B001-C78560D9CE91]
// </editor-fold> 
public class MealLog implements Serializable {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.C0AFA547-1B50-21A0-32CA-BED35C791ACE]
    // </editor-fold> 
    private Meal mMeal;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.0CF8FA70-1BA8-0D59-39DD-E0C35475D91D]
    // </editor-fold> 
    private House mHouse;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.543DC241-D66A-B1CC-C366-FF65ECC28C9A]
    // </editor-fold> 
    private java.util.Date logDate;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.12ED62F4-37D7-FBBB-BB6F-24EA23BA1112]
    // </editor-fold> 
    private BLDType bldType;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.CE886DA9-B5FB-CA40-3237-336DB2362168]
    // </editor-fold> 
    public MealLog () {
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.7FC48F59-E761-C051-CD66-54F3E77C2060]
    // </editor-fold> 
    public BLDType getBldType () {
        return bldType;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.35F72F1A-DEB6-583C-4A66-7EC8A04AFB97]
    // </editor-fold> 
    public java.util.Date getLogDate () {
        return logDate;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.EE5E77CE-325A-5A2C-7659-AE80E655B9F9]
    // </editor-fold> 
    public void setLogDate (java.util.Date val) {
        this.logDate = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.DBEF4BF9-234C-1159-5DF8-D2131C8FC1FA]
    // </editor-fold> 
    public void setBldType (BLDType val) {
        this.bldType = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.59828B25-C981-B78B-8CA1-4224D19B3DD7]
    // </editor-fold> 
    public House getHouse () {
        return mHouse;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.509C2F6C-C9F9-21CC-1270-FF341D8708CA]
    // </editor-fold> 
    public void setHouse (House val) {
        this.mHouse = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.3FF25A3C-D73C-78B5-ADAF-E5D7618F6473]
    // </editor-fold> 
    public Meal getMeal () {
        return mMeal;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.0C17EBB2-AAAF-34D7-3F29-68BAD3EDCACA]
    // </editor-fold> 
    public void setMeal (Meal val) {
        this.mMeal = val;
    }

}

