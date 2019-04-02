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
<<<<<<< HEAD
=======
while [ 1 -ne 0 ]
do
	sleep 10
done
>>>>>>> 8f03e05d4a9192f1847b17fa58ac385e9b09dc31
