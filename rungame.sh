#!/bin/sh
  
cd src
javac com/zoogaru/BowlingGame.java

echo "File name arg: $1"

java com.zoogaru.BowlingGame $1

