package com.zoogaru;

import java.util.ArrayList;
import java.util.List;

import static com.zoogaru.Constants.MAX_BOWLS_PER_FRAME;
import static com.zoogaru.Constants.MAX_PINFALLS_PER_FRAME;

/**
 * Created by sandeep on 03/06/18.
 */
public class Frame {
    String name;
    int frameId ;
    int score;
    int totalScore;
    List<Bowl> bowlings = new ArrayList<>();
    public Frame(String name, int frameId)
    {
        this.name = name;
        this.frameId = frameId;
    }

    String getName()
    {
        return name;
    }

    int getFrameId()
    {
        return frameId;
    }

    void setScore(int score)
    {
        this.score = score;
    }

    int getScore()
    {
        return score;
    }

    void setTotalScore(int score)
    {
        this.totalScore = score;
    }

    int getTotalScore()
    {
        return totalScore;
    }

    public int getAllPartScore()
    {
        int pinfalls = 0;
        for(Bowl b : bowlings)
        {
            pinfalls += b.getPinFalls();
        }
        return pinfalls;
    }

    boolean addABowl(Bowl b)
    {
        if(bowlings.size() == 0)
        {
            return bowlings.add(b);
        }
        else if(getAward() == Award.STRIKE) {
            return false;
        }
        else if(bowlings.size() == MAX_BOWLS_PER_FRAME) {
            return false;
        }
        return bowlings.add(b);
    }

    boolean isFilled()
    {
        if((getAward() == Award.STRIKE) || (bowlings.size() == MAX_BOWLS_PER_FRAME))
        {
            return true;
        }
        return false;
    }

    Award getAward()
    {
        int score = 0;
        for(int i = 0 ; i < bowlings.size() ; i++) {
            score += bowlings.get(i).getPinFalls();
            if(i == 0 && score == MAX_PINFALLS_PER_FRAME)
            {
                bowlings.get(0).setScore("X");
                return Award.STRIKE;
            }
            else if(score == MAX_PINFALLS_PER_FRAME)
            {
                bowlings.get(1).setScore("/");
                return Award.SPARE;
            }
        }
        return Award.NONE;
    }

    @Override
    public String toString()
    {
        String pinFalls = "";
        if(getAward() == Award.STRIKE)
        {
            pinFalls += " \tX";
        }
        else if(getAward() == Award.SPARE)
        {
            pinFalls += bowlings.get(0).getPinFalls() + "\t/";
        }
        else
        {
            pinFalls += bowlings.get(0).getPinFalls() + "\t" + bowlings.get(1).getPinFalls() ;
        }
        return pinFalls;
    }
}
