test : line.o main.o
	g++ -o test line.o main.o

line.o : line.h line.cpp
	g++ -c line.cpp

main.o : main.cpp line.h
	g++ -c main.cpp

clean :
	rm test line.o main.o

