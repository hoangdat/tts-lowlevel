/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package UnitSelection;

/**
 *
 * @author thaodv-bkit
 */
public class DistanceVector {

    //vector khoang cach
    final double W1 =  0.5;//trong so
    final double W2 =  0.5;//trong so
    final double W3 =  0.5;//trong so
    final double W4 =  0.5;//trong so
    final double W5 =  0.5;//trong so 
    private double posInPhrsDis;//su khac biet vi tri cua am tiet trong phrase
    private double leftPhCtxDis; //su khac biet am cuoi cua am tiet ben trai
    private double rightPhCtxDis;//su khac biet am dau cua am tiet ben phai
    private double leftToneDis;//su khac biet thanh dieu cua am tiet ben trai
    private double rightToneDis;//su khac biet thanh dieu cua am tiet ben phai
    private double contextualDistance;
    //neu giong nhau thi su khac biet la 0, khac nhau thi su khac biet la 1

    public DistanceVector(){

    }

   

    /**
     * @return the contextualDistance
     */
    public double getContextualDistance() {
        return contextualDistance;
    }

    /**
     * @param contextualDistance the contextualDistance to set
     */
    public void setContextualDistance() {
        this.contextualDistance = W1*getPosInPhrsDis() + W2*getLeftPhCtxDis() + W3*getRightPhCtxDis() + W4*getLeftToneDis() + W5*getRightToneDis();
    }

    /**
     * @return the posInPhrsDis
     */
    public double getPosInPhrsDis() {
        return posInPhrsDis;
    }

    /**
     * @param posInPhrsDis the posInPhrsDis to set
     */
    public void setPosInPhrsDis(double posInPhrsDis) {
        this.posInPhrsDis = posInPhrsDis;
    }

    /**
     * @return the leftPhCtxDis
     */
    public double getLeftPhCtxDis() {
        return leftPhCtxDis;
    }

    /**
     * @param leftPhCtxDis the leftPhCtxDis to set
     */
    public void setLeftPhCtxDis(double leftPhCtxDis) {
        this.leftPhCtxDis = leftPhCtxDis;
    }

    /**
     * @return the rightPhCtxDis
     */
    public double getRightPhCtxDis() {
        return rightPhCtxDis;
    }

    /**
     * @param rightPhCtxDis the rightPhCtxDis to set
     */
    public void setRightPhCtxDis(double rightPhCtxDis) {
        this.rightPhCtxDis = rightPhCtxDis;
    }

    /**
     * @return the leftToneDis
     */
    public double getLeftToneDis() {
        return leftToneDis;
    }

    /**
     * @param leftToneDis the leftToneDis to set
     */
    public void setLeftToneDis(double leftToneDis) {
        this.leftToneDis = leftToneDis;
    }

    /**
     * @return the rightToneDis
     */
    public double getRightToneDis() {
        return rightToneDis;
    }

    /**
     * @param rightToneDis the rightToneDis to set
     */
    public void setRightToneDis(double rightToneDis) {
        this.rightToneDis = rightToneDis;
    }

}
