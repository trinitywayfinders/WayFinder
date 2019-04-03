#!/bin/bash

rm log.txt
ionic serve>log.txt &
STR="window opened"
cat log.txt | grep "$STR"
while [ $? -ne 0 ]
do
	sleep 1
	cat log.txt | grep "$STR"
done
touch ./src/pages/signup/signup.ts
while [ 1 -ne 0 ]
do
	sleep 10
done
