package org.wfd.model;

import java.io.Serializable;


// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.02F98068-A038-E6C4-12D5-6E6ABAD9547B]
// </editor-fold> 
public class House implements Serializable {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.E4EA6E9A-5DBC-29F7-FD08-04C76D45F815]
    // </editor-fold> 
    private int id;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.CBAD9B1B-AA68-22DB-598F-EE1AD72331DC]
    // </editor-fold> 
    private String name;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.84AEBB12-48EA-4BDC-90AA-3A683C73D6C3]
    // </editor-fold> 
    public House () {
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.A72A48DB-509F-DAA6-8F43-29DE443F66FF]
    // </editor-fold> 
    public String getName () {
        return name;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.2C31245B-152B-FE06-61F9-8A29D674544E]
    // </editor-fold> 
    public void setName (String val) {
        this.name = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.87531A1F-9A8D-1597-B704-9913CE6EF11D]
    // </editor-fold> 
    public int getId () {
        return id;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.A66F23FE-90F9-FEBB-EF41-9D15D285CF67]
    // </editor-fold> 
    public void setId (int val) {
        this.id = val;
    }

}

