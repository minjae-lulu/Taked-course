//
#include <iostream>
#include <cmath>
#include <algorithm>
#include <cstring>
#include <string>
#include "func.h"
using namespace std;


int main(){
    shape shapes[10];
    size_t num_shapes = read_in_shapes(shapes);

    double factor = ideal_expansion_factor(shapes, num_shapes);
    cout << "Ideal expansion factor " << factor << '\n';

    print_result(shapes, num_shapes, factor);

    return 0;
}