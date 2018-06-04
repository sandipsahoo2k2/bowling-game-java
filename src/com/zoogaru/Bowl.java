package com.zoogaru;

/**
 * Created by sandeep on 03/06/18.
 */
public class Bowl {
    private String name ;
    private int pinFalls ;
    private String score ;
    private int frameId ;

    public Bowl(String name, int pinFalls) {
        this.name = name;
        this.pinFalls = pinFalls;
        frameId = -1 ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPinFalls() {
        return pinFalls;
    }
    public void setPinFalls(int pinfalls) {
        this.pinFalls = pinFalls;
    }

    public int getFrameId() {
        return frameId;
    }
    public void setFrameId(int frame) {
        this.frameId = frame;
    }

    public void setScore(String finalStr) {
        if(finalStr.equals("10"))
        {
            finalStr = "X";
        }
        this.score = finalStr;
    }

    public String getScore() {
        return score;
    }

    @Override
    public String toString()
    {
        String finalStr = "";
        if(score.equals("F"))
        {
            finalStr = "0";
        }
        else if(score.equals("X"))
        {
            finalStr = "10";
        }
        return name + "\t" + score;
    }
}
