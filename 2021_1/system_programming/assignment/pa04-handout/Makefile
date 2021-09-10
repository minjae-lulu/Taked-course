# Makefile for the Programming Assignment #4 Writing Simple Shell (Part 3)

TEAM = NOBODY
VERSION = 1
DRIVER = ./sdriver.pl
TSH = ./tsh
TSHREF = ./tshref
TSHARGS = "-p"
CC = gcc
CFLAGS = -Wall -O2
FILES = $(TSH) ./myspin ./mysplit ./mystop ./myint

all: $(FILES)
	-tar -cvf pa04_00000000.tar tsh.c


##################
# Regression tests
##################

# Run tests using the student's shell program
test11:
	$(DRIVER) -t trace11.txt -s $(TSH) -a $(TSHARGS)
test12:
	$(DRIVER) -t trace12.txt -s $(TSH) -a $(TSHARGS)
test13:
	$(DRIVER) -t trace13.txt -s $(TSH) -a $(TSHARGS)
test14:
	$(DRIVER) -t trace14.txt -s $(TSH) -a $(TSHARGS)
test15:
	$(DRIVER) -t trace15.txt -s $(TSH) -a $(TSHARGS)

# Run the tests using the reference shell program
rtest11:
	$(DRIVER) -t trace11.txt -s $(TSHREF) -a $(TSHARGS)
rtest12:
	$(DRIVER) -t trace12.txt -s $(TSHREF) -a $(TSHARGS)
rtest13:
	$(DRIVER) -t trace13.txt -s $(TSHREF) -a $(TSHARGS)
rtest14:
	$(DRIVER) -t trace14.txt -s $(TSHREF) -a $(TSHARGS)
rtest15:
	$(DRIVER) -t trace15.txt -s $(TSHREF) -a $(TSHARGS)

# clean up
clean:
	rm -f $(FILES) *.o *~ *.tar
