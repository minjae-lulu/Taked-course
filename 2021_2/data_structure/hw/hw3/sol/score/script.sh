make clean
rm log?.txt
#rm *.sol
make
echo "test 1"
./test1 > log1.txt
echo "test 2"
./test2
echo "test 3"
timeout 60s ./test3 > log3.txt
echo "test 4"
timeout 60s ./test4 > log4.txt
./score
