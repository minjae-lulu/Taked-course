SANFLAGS = -fsanitize=address
CXXFLAGS += -Wall -std=c++17 -pedantic $(SANFLAGS)
LDFLAGS += $(SANFLAGS)

main: main.o	functions.o
	$(CXX)	-o	$@	$^	$(LDFLAGS)
check:	check.o	functions.o
	$(CXX)	-o	$@	$^	$(LDFLAGS)

all: main check