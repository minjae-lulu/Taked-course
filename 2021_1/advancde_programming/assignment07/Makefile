SANFLAGS = -fsanitize=address
CXXFLAGS += -Wall -std=c++17 -pedantic $(SANFLAGS)
LDFLAGS += $(SANFLAGS)
main: main.o	fun.o
	$(CXX)	-o	$@	$^	$(LDFLAGS)
