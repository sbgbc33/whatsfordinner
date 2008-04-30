package org.wfd.model;

import java.io.Serializable;


// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.6CC6179E-35F6-C400-9D30-F03D16C9B92D]
// </editor-fold> 
public class Meal implements Serializable {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.DAD64520-4957-3CC3-C21C-B319B6131287]
    // </editor-fold> 
    private int id;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.9DA53FE5-9388-90BF-4B5C-2E2A962642E1]
    // </editor-fold> 
    private String name;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.F93CC4A1-E9B1-6055-4B30-3E74FD78D462]
    // </editor-fold> 
    private int ethnicity;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.975F45C8-6B5A-11D2-0F7D-E40BED6FB3BC]
    // </editor-fold> 
    private BLDType bldType;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.12216F59-1EC1-0982-0D13-CC6ADB73FCD2]
    // </editor-fold> 
    public Meal () {
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.224D2D15-3581-8BFC-7985-04A006DD7BB2]
    // </editor-fold> 
    public BLDType getBldType () {
        return bldType;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.C0375BF8-622D-7F41-3B63-7F6803216C00]
    // </editor-fold> 
    public int getEthnicity () {
        return ethnicity;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.211A19F3-6F7E-884B-A0B0-D52B8A580C59]
    // </editor-fold> 
    public void setEthnicity (int val) {
        this.ethnicity = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.EEB56ADC-52BE-5D6E-348C-27BB967325B4]
    // </editor-fold> 
    public String getName () {
        return name;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.F60FC5F3-165C-F3FC-E415-96E4C921BBFF]
    // </editor-fold> 
    public void setName (String val) {
        this.name = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.0945932D-30E4-1D08-C6F0-CE03466E95C3]
    // </editor-fold> 
    public void setBldType (BLDType val) {
        this.bldType = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.AFE6C06F-EEBD-C15C-8915-F436573A0DB3]
    // </editor-fold> 
    public int getId () {
        return id;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.F85B9948-1B54-4B75-7D6F-33AE8B331D34]
    // </editor-fold> 
    public void setId (int val) {
        this.id = val;
    }

}

