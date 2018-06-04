#!/bin/sh
  
cd src
javac com/zoogaru/BowlingGame.java

java com.zoogaru.BowlingGame ../game.txt
java com.zoogaru.BowlingGame ../carlfile.txt

