package slet;

import java.util.ArrayList;

/**
 *
 * @author reget.kalamees
 */
public class Proov {
    private String nimi;
    private String hind;
    private ArrayList<pLaps> mingiList=new ArrayList<pLaps>();

    /**
     * @return the nimi
     */
    public String getNimi() {
        return nimi;
    }

    /**
     * @param nimi the nimi to set
     */
    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    /**
     * @return the hind
     */
    public String getHind() {
        return hind;
    }

    /**
     * @param hind the hind to set
     */
    public void setHind(String hind) {
        this.hind = hind;
    }

    /**
     * @return the mingiList
     */
    public ArrayList getMingiList() {
        return mingiList;
    }

    /**
     * @param mingiList the mingiList to set
     */
    public void setMingiList(ArrayList mingiList) {
        this.mingiList = mingiList;
    }
    
    
}
