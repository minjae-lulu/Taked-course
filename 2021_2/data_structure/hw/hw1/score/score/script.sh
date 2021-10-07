make clean
rm log?.txt
make
./test1 > log1.txt
./test2 > log2.txt
./test3 > log3.txt
./test4 
for i in {1..5}
	do
		./test5 instance"$i".txt solution"$i".txt
	done
./score
