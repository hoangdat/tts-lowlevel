/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package UnitSelection;

import java.util.ArrayList;
import sun.dc.pr.PathDasher;

/**
 *
 * @author thaodv_bkit
 */
public class Path implements Comparable<Path>{
    private float distance;
    ArrayList<Integer> indexesOfPreCandUnitsInPath = new ArrayList<Integer>();//chi so cua cac don vi ung vien phia truoc LP hien tai
    private int indexOfCurrentCandUnitInPath;//chi so cua don vi ung vien hien tai

    public Path(){

    }
    public Path(float d, int index){
       distance = d;
       indexOfCurrentCandUnitInPath = index;
    }


    public int compareTo(Path o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * @return the distance
     */
    public float getDistance() {
        return distance;
    }

    /**
     * @param distance the distance to set
     */
    public void setDistance(float distance) {
        this.distance = distance;
    }

    /**
     * @return the indexOfCurrentCandUnitInPath
     */
    public int getIndexOfCurrentCandUnitInPath() {
        return indexOfCurrentCandUnitInPath;
    }

    /**
     * @param indexOfCurrentCandUnitInPath the indexOfCurrentCandUnitInPath to set
     */
    public void setIndexOfCurrentCandUnitInPath(int indexOfCurrentCandUnitInPath) {
        this.indexOfCurrentCandUnitInPath = indexOfCurrentCandUnitInPath;
    }

}
