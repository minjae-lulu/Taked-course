// 20161190 leeminjae
// 1. define funtion mean that can ignore nan value so i can calculate mean value correctly
// 2. Define function maximum value to find max value in array. temp value is negative ans 
// Nan value is consider 0, so too consider arr[i] != 0. and return max value
#include <cstddef> 

double mean(double const* arr, size_t n) {
    double s = 0;
    double val = 0;
    int zeroCnt = 0;
    for (size_t i = 0; i < n; i++) {
        s += arr[i];
        if (arr[i] == 0)
            zeroCnt++;
    }
    val = s / (n - zeroCnt);
    return val;
}

double* maximum_value(double* arr, size_t n) {
    double* max_v = NULL;
    max_v = &arr[0];
    for (size_t i = 1; i < n; i++) {
        if (*max_v < arr[i] && arr[i] != 0) {
            max_v = &arr[i];
        }
    }
    return max_v;
}