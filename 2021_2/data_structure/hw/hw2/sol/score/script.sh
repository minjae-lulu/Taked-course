make clean
rm log?.txt
rm *.sol
make
echo "test 1"
./test1 > log1.txt
echo "test 2"
./test2 > log2.txt
echo "test 3: solving negative instances"
timeout 60s ./test3 > log3.txt
echo "test 4: solving positive instances"
timelimit=(0 10 10 10 10 10 10 10 30 300)
for i in {1..9}
	do
		echo "solving instance $i"
		timeout ${timelimit[$i]}s ./solve instances/instance"$i".in > instance"$i".sol
		./check instances/instance"$i".in instance"$i".sol >> log4.txt
	done
./score
