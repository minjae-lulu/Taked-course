all: test1 test2 test3 graph.o

test1 : test1.o graph.h graph.o
	g++ -o test1 test1.o graph.o

test1.o : test1.cpp graph.h graph.o
	g++ -c test1.cpp

test2 : test2.o graph.h graph.o
	g++ -o test2 test2.o graph.o

test2.o : test2.cpp graph.h graph.o
	g++ -c test2.cpp
		
test3 : test3.o graph.h graph.o
	g++ -o test3 test3.o graph.o

test3.o : test3.cpp graph.h graph.o
	g++ -c test3.cpp
	
graph.o : graph.cpp graph.h
	g++ -c graph.cpp	
	
clean :
	rm test?.o test? graph.o
