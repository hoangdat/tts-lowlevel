/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DB_Processing;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thaodv-bkit
 */
public class HalfsylDBReader {

    int sizeOfDB;// 6675218
    int numByteToCopy;
    FileInputStream fis;
    byte buf[], data1[];

    public HalfsylDBReader(String halfSylName) throws FileNotFoundException, IOException {
        fis = new FileInputStream(halfSylName);
        sizeOfDB = fis.available();
        buf = this.ReadHalfSylDB();

        for (int i = 0; i < 1000; i++) {
            numByteToCopy = 28;
            data1 = new byte[numByteToCopy];
            System.arraycopy(buf, 2 + 50000 + 28 * i, data1, 0, numByteToCopy);
            System.out.println(new String(data1));

        }

//        for (int i = 49999; i < buf.length; i++) {
//            System.out.println((char)buf[i]);
//
//        }
    }
    /*
    for(int i = 0;i<*(WORD*)(m_cHeader);i++)
    {
    m_fAUnits.Seek(*(DWORD *)(m_cHeader + 8 * i + 4 + 2),CFile::begin);
    char cTemp[28];//8];
    m_fAUnits.Read(cTemp,27);//7);
    m_stSupInfo[i].bDeleted = cTemp[0];
    m_stSupInfo[i].wTranPoint = *(WORD *)(cTemp + 1);
    m_stSupInfo[i].dwDipLen = *(DWORD *)(cTemp + 3)-8;

    // Bo xung ngay 06 thang 12 nam 2005, lay cac thong tin ve context cua Unit
    m_stSupInfo[i].UnitType = *(BYTE *)(cTemp + 7);
    m_stSupInfo[i].bTone	= *(BYTE *)(cTemp + 8);
    m_stSupInfo[i].bLeftTone	= *(BYTE *)(cTemp + 9);
    m_stSupInfo[i].bRightTone	= *(BYTE *)(cTemp + 10);
    m_stSupInfo[i].dwLowFEnergy	= *(DWORD *)(cTemp + 11);
    m_stSupInfo[i].dwHighFEnergy= *(DWORD *)(cTemp + 15);
    memcpy(m_stSupInfo[i].cLeftUnitName,cTemp +19,4);
    m_stSupInfo[i].cLeftUnitName[4] = '\0';
    memcpy(m_stSupInfo[i].cRightUnitName,cTemp +23,4);
    m_stSupInfo[i].cRightUnitName[4] = '\0';

    // Ket thuc bo xung
    }
     */

    private byte[] ReadHalfSylDB() {
        byte buf[] = new byte[sizeOfDB];
        try {
            fis.read(buf);
        } catch (IOException ex) {
            Logger.getLogger(HalfsylDBReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return buf;
    }

    /*
     *
     */
    public static void main(String[] args) {

        try {
            HalfsylDBReader halfsylDBReader = new HalfsylDBReader("HalfSyl.dat");
        } catch (Exception ex) {
            Logger.getLogger(HalfsylDBReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
