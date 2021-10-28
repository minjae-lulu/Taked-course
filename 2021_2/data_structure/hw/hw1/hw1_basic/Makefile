all: test1 test2 test3 test4 test5

test5 : test5.o
	g++ -o test5 test5.o

test5.o : test5.cpp circlelist.h
	g++ -c test5.cpp
	
test4 : test4.o
	g++ -o test4 test4.o

test4.o : test4.cpp circlelist.h
	g++ -c test4.cpp

test3 : test3.o
	g++ -o test3 test3.o

test3.o : test3.cpp circlelist.h
	g++ -c test3.cpp

test2 : test2.o
	g++ -o test2 test2.o

test2.o : test2.cpp circlelist.h
	g++ -c test2.cpp
	
test1 : test1.o
	g++ -o test1 test1.o

test1.o : test1.cpp circlelist.h
	g++ -c test1.cpp

clean :
	rm test1 test1.o test2 test2.o test3 test3.o test4 test4.o test5 test5.o

