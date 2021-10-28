UPDATE: You can get your final score by running the script "script.sh".
So you just need to type "./script" from the linux command line.
It produces 5 log files log1.txt .... log5.txt

--------------------------------------------------------------------------------------

These are 5 programs for checking assignment 1: 
- test1 checks the basic functions
- test2 looks for memory leaks
- test3 checks the additional functions 
- test4 checks the overloaded operators
- test5 checks a longer sequence of operations

In order to compile them, you need to:
- upload the files in the same directory as circlelist.h
- type make

To run the files, for test1, test2 and test3, type ./test1 ... and the results will
be printed on the terminal. If you do not get any "ERROR ..." message, your code 
no problem has been detected with your code.

For test4, the result is printed in a lot file "log4.txt" for technical reasons.
You can look at it under linux by typing "cat log4.txt". Same with test5.

For test5, you need to give as input an instance file (sequence of instructions)
and the corresponding solutin file. For instance, type:
./test5 instance1.txt solution1.txt
./test5 instance2.txt solution2.txt

In the final grading step, we will use other instance files for test5. 


