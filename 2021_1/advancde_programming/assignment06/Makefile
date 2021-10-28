SANFLAGS = -fsanitize=address
CXXFLAGS += -Wall -std=c++17 -pedantic $(SANFLAGS)
LDFLAGS += $(SANFLAGS)

main: main.o	func.o
	$(CXX)	-o	$@	$^	$(LDFLAGS)