SANFLAGS = -fsanitize=address
CXXFLAGS += -Wall -std=c++17 -pedantic $(SANFLAGS)
LDFLAGS += $(SANFLAGS)
main: main.o	book.o	person.o	library.o
	$(CXX)	-o	$@	$^	$(LDFLAGS)
