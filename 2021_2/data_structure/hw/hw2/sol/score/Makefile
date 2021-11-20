all: knight.o test1 test2 test3 solve check score

score : score.cpp
	g++ -o score score.cpp

check : check.cpp
	g++ -o check check.cpp

solve : solve.o knight.o
	g++ -o solve solve.o knight.o -O3

solve.o : solve.cpp knight.h
	g++ -c solve.cpp -O3

test3 : test3.o knight.o
	g++ -o test3 test3.o knight.o

test3.o : test3.cpp knight.h
	g++ -c test3.cpp

test2 : test2.o knight.o
	g++ -o test2 test2.o knight.o

test2.o : test2.cpp knight.h
	g++ -c test2.cpp

test1 : test1.o knight.o
	g++ -o test1 test1.o knight.o

test1.o : test1.cpp knight.h
	g++ -c test1.cpp

knight.o : knight.cpp knight.h
	g++ -c knight.cpp -O3
	
clean :
	rm test1 test1.o test2 test2.o test3 test3.o solve solve.o knight.o check score
