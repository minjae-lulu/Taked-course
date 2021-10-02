SANFLAGS = -fsanitize=address
CXXFLAGS += -Wall -std=c++17 -pedantic $(SANFLAGS)
LDFLAGS += $(SANFLAGS)

main: main.o	array.o
	$(CXX)	-o	$@	$^	$(LDFLAGS)