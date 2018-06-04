package com.zoogaru;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.zoogaru.Constants.MAX_FRAMES_PER_GAME;

/**
 * Created by sandeep on 03/06/18.
 */
public class PlayerManager {

    Map<String, List<Frame>> framesByPlayers = new HashMap<>();
    Map<String, List<Bowl>> bowlsByPlayers = new HashMap<>();

    public void addABowlToTheFrame(String name, Bowl b) {
        Frame f;
        List<Frame> savedFrames = framesByPlayers.get(name);
        if (savedFrames == null) {
            savedFrames = new ArrayList<Frame>();
            f = new Frame(name, 1);
            f.addABowl(b);
            savedFrames.add(f);
        } else {
            int index = savedFrames.size() - 1;
            f = savedFrames.get(index);
            if (f.isFilled()) {
                f = new Frame(name, savedFrames.size() + 1);
                f.addABowl(b);
                savedFrames.add(f);
            } else {
                f.addABowl(b);
            }
            //savedFrames.set(index, f);
        }
        b.setFrameId(f.getFrameId());

        framesByPlayers.put(name, savedFrames);

        List<Bowl> savedBowlings = bowlsByPlayers.get(name);
        if (savedBowlings == null) {
            savedBowlings = new ArrayList<Bowl>();
        }
        savedBowlings.add(b);
        bowlsByPlayers.put(name, savedBowlings);
    }

    void printReport() {
        System.out.println("Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10");
        for (Map.Entry<String, List<Frame>> entry : framesByPlayers.entrySet()) {
            String key = entry.getKey();
            System.out.println(key);
            System.out.print("Pinfalls");

            List<Frame> frames = entry.getValue();
            for (int i = 0; i < frames.size(); i++) {

                Frame f = frames.get(i);
                System.out.print("\t");
                System.out.print(f);
            }

            System.out.println();
            System.out.print("Score");

            List<Bowl> allBowls = bowlsByPlayers.get(key);
            frames = entry.getValue();
            int totalScore = 0;
            int bowlIndex = 0;
            for (int frameIdx = 0; frameIdx < MAX_FRAMES_PER_GAME; frameIdx++) {
                Bowl b = allBowls.get(bowlIndex);
                Frame f = frames.get(frameIdx);

                int extraScore = 0;
                if (f.getAward() == Award.STRIKE) {
                    bowlIndex++;
                    extraScore = allBowls.get(bowlIndex).getPinFalls() + allBowls.get(bowlIndex + 1).getPinFalls();
                } else if (f.getAward() == Award.SPARE) {
                    bowlIndex = bowlIndex + 2;
                    extraScore = allBowls.get(bowlIndex).getPinFalls();
                }
                else {
                    bowlIndex = bowlIndex + 2;
                }

                int frameScore = f.getAllPartScore() + extraScore;
                f.setScore(frameScore);

                totalScore += frameScore;
                f.setTotalScore(totalScore);

                //scoreIndex += f.bowlings.size() ;
                System.out.print("\t\t");
                System.out.print(totalScore);
            }
            System.out.println();
        }
    }

    public void print()
    {
        System.out.println("Creating frames....");
        for(Map.Entry<String, List<Frame>> entry : framesByPlayers.entrySet())
        {
            String key = entry.getKey();
            List<Frame> frames = entry.getValue();
            for(Frame f : frames) {
                System.out.println(f);
            }
        }
    }
}
