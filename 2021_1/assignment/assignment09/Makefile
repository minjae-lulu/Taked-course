SANFLAGS = -fsanitize=address
CXXFLAGS += -Wall -std=c++17 -pedantic $(SANFLAGS)
LDFLAGS += $(SANFLAGS)
main: main.o matrix_2_2.o
	$(CXX) -o $@ $^ $(LDFLAGS)