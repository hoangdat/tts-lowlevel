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
public class Path implements Comparable<Path> {

    private float distance;
    private ArrayList<Integer> indexesOfPreCandUnitsInPath = new ArrayList<Integer>();//chi so cua cac don vi ung vien phia truoc LP hien tai
    private int indexOfCurrentCandUnitInPath;//chi so cua don vi ung vien hien tai

    public Path() {
    }

    public Path(float d, int index) {
        distance = d;
        indexOfCurrentCandUnitInPath = index;
        indexesOfPreCandUnitsInPath.add(index);
    }

    public int compareTo(Path o) {
        if (this.distance > o.distance) {
            return 1;
        }else if(this.distance == o.distance){
            return 0;
        }else return -1;
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

    /**
     * @return the indexesOfPreCandUnitsInPath
     */
    public ArrayList<Integer> getIndexesOfPreCandUnitsInPath() {
        return indexesOfPreCandUnitsInPath;
    }

    /**
     * @param indexesOfPreCandUnitsInPath the indexesOfPreCandUnitsInPath to set
     */
    public void setIndexesOfPreCandUnitsInPath(ArrayList<Integer> indexesOfPreCandUnitsInPath) {
        this.indexesOfPreCandUnitsInPath = indexesOfPreCandUnitsInPath;
    }
}
