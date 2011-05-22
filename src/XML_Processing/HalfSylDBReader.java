/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package XML_Processing;

import Units.HalfSyl;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author thaodv_bkit
 */
public class HalfSylDBReader {
    ArrayList<HalfSyl> allHalfSyls = new ArrayList<HalfSyl>();

    /**
     * @return the allHalfSyls
     */
    public ArrayList<HalfSyl> getAllHalfSyls() {
        return allHalfSyls;
    }
    ArrayList<String> listLines;
    HalfSyl halfSyl;

    public HalfSylDBReader() {
       listLines = new FileInput("halfSylInfosDB2.txt").getListLines();
       readEachLine();
    }
//UnitName bDeleted wTranPoint dwDipLen UnitType bTone bLeftTone
//bRightTone dwLowFEnergy dwHighFEnergy cLeftUnitName cRightUnitName
    private void readEachLine() {
        for (int i = 0; i < listLines.size(); i++) {
            halfSyl = new HalfSyl();
            StringTokenizer stk = new StringTokenizer(listLines.get(i));
            halfSyl.setHalfSylIndex(i);
            halfSyl.setHalfSylName(stk.nextToken());
            halfSyl.setbDeleted(StrToInt(stk.nextToken().trim()));
            halfSyl.setwTranPoint(StrToInt(stk.nextToken().trim()));
            halfSyl.setDwDipLen(StrToInt(stk.nextToken().trim()));
            halfSyl.setUnitType(StrToInt(stk.nextToken().trim()));
            halfSyl.setTone(StrToInt(stk.nextToken().trim()));
            halfSyl.setLeftTone(StrToInt(stk.nextToken().trim()));
            halfSyl.setRightTone(StrToInt(stk.nextToken().trim()));
            halfSyl.setLowEnergy(StrToInt(stk.nextToken().trim()));
            halfSyl.setHighEnergy(StrToInt(stk.nextToken().trim()));
            halfSyl.setLeftUnitName(stk.nextToken());
            halfSyl.setRightUnitName(stk.nextToken());
            getAllHalfSyls().add(halfSyl);
        }
    }

    /*
     * @param str
     * @return
     */
    public int StrToInt(String str) {
        return Integer.parseInt(str);
    }
    public static void main(String[] args) {
        new HalfSylDBReader();
        System.out.println("kek");
    }
    

}
