package org.wfd.model;

import java.io.Serializable;


// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.6498B47D-F3BD-06DD-7CAB-97D1FF62F5C5]
// </editor-fold> 
public class MealSuggestion implements Serializable {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.1FB99D38-BAD4-5854-3D06-DB88279F7F4D]
    // </editor-fold> 
    private int suggestionDate;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.16253CF3-5854-8651-746D-EC2155B8B01F]
    // </editor-fold> 
    private BLDType bldType;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.64FBF809-35C7-6600-5396-28B128267120]
    // </editor-fold> 
    private House mHouse;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.FF582133-B42D-6323-4805-6EE360CE96F4]
    // </editor-fold> 
    private Meal mMeal;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.DD0E1EB3-2DA1-0A23-E6E0-AF27E61E923E]
    // </editor-fold> 
    public MealSuggestion () {
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.FC592F2D-C70E-36FA-EF71-7B6080C654A3]
    // </editor-fold> 
    public BLDType getBldType () {
        return bldType;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.DD0D3B24-3073-5BFE-38A8-E5F9C85ADA53]
    // </editor-fold> 
    public void setBldType (BLDType val) {
        this.bldType = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.415014B7-CB35-11D8-C437-FC774390F349]
    // </editor-fold> 
    public House getHouse () {
        return mHouse;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.005C33A8-53C9-74BB-9621-1B18336006CB]
    // </editor-fold> 
    public void setHouse (House val) {
        this.mHouse = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.F85CCAA8-EBF1-5F5E-2054-B9775229A635]
    // </editor-fold> 
    public Meal getMeal () {
        return mMeal;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.4F8F8BFA-74D9-97E1-8E62-527EA818A7D6]
    // </editor-fold> 
    public void setMeal (Meal val) {
        this.mMeal = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.24C3A063-0E9B-DEF8-0C7B-5A9848ECEE80]
    // </editor-fold> 
    public int getSuggestionDate () {
        return suggestionDate;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.CFB788B6-C28E-3899-ABEC-3F5AB9C4E823]
    // </editor-fold> 
    public void setSuggestionDate (int val) {
        this.suggestionDate = val;
    }

}

