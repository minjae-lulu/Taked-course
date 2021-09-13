test : vector2.o main.o
	g++ -o test vector2.o main.o

vector2.o : vector2.h vector2.cpp
	g++ -c vector2.cpp

main.o : main.cpp vector2.h
	g++ -c main.cpp

clean :
	rm test vector2.o main.o

