all: test1 test2 test3 test4

test4 : test4.o tree.h
	g++ -o test4 test4.o 

test4.o : test4.cpp tree.h
	g++ -c test4.cpp

test3 : test3.o tree.h
	g++ -o test3 test3.o 

test3.o : test3.cpp tree.h
	g++ -c test3.cpp
	
test2 : test2.o tree.h
	g++ -o test2 test2.o 

test2.o : test2.cpp tree.h
	g++ -c test2.cpp

test1 : test1.o tree.h
	g++ -o test1 test1.o 

test1.o : test1.cpp tree.h
	g++ -c test1.cpp
	
clean :
	rm test?.o test?
