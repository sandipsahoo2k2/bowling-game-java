package com.zoogaru ;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BowlingGame {

	public static void main(String[] args) {

		BufferedReader br = null;
		FileReader fr = null;
		if(args.length < 1)
		{
			System.out.println("Please pass a file name with path as input");
			return;
		}
		String FILENAME = args[0];

		try {
			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);

			PlayerManager frameMgr = new PlayerManager();
			Map<String, List<Bowl>> bowlByPlayers = new HashMap<>();
			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				String[] columnDetail = new String[2];
				columnDetail = sCurrentLine.split("\t");
				try
				{
					int pinFalls = 0;
					String score = columnDetail[1] ;
					if(score.equals("F"))
					{
						pinFalls = 0;
					}
					else if(score.equals("X"))
					{
						pinFalls = 10;
					}
					else {
						pinFalls = Integer.parseInt(columnDetail[1]);
					}
					Bowl b = new Bowl(columnDetail[0], pinFalls);
					b.setScore(score);
					//add the bowl to the frame
					frameMgr.addABowlToTheFrame(columnDetail[0], b);
				} catch (NumberFormatException e){}
			}
			System.out.println("\n" + FILENAME + " parsed and Bowling game Frames created.\n");
			//Print report
			System.out.println("\nPrinting Report card ... \n");
			frameMgr.printReport();

		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			try {
				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {
				ex.printStackTrace();
			}

		}
	}
}
