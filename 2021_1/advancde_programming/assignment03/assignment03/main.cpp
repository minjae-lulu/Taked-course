// 
// using cin.eof() recieve value
// 1. Daily highs: output is 24's highest val. using for, do while
// 2. Sliding window 24h av: for -12 +11 average, i divide when start and end reigon. and calculate sum
// and also think zeroCnt for correct average
// 3. removing mean: i remove largest values (max val = 0) so don't consider 0 value for correct mean.
#include <iostream>
#include <cmath> // for nan
#include <iomanip> // for aligned
#include "array.h"
using namespace std;

int main() {
    double arr[1000];
    int i = 0, j, k, nodata = 0;
    size_t n;

    cout << fixed;
    cout.precision(3);

    while (true) {
        cin >> arr[i++];
        if (cin.eof()) {
            break;
        }
        if (!cin) {
            arr[i] = NAN;
            cin.clear();
            while (cin.get() != '\n');
            nodata++;
        }
    }
    n = i;
    double dailyHigh = arr[0];


    cout << "Daily highs" << endl;
    cout << "===========" << endl;
    for (i = 24; i < n; i += 24) { // for think 24 hour's largest val
        k = 0;
        do {
            dailyHigh = arr[i - 24 + k++];
        } while (dailyHigh == 0);
        for (int j = i - 24; j < i; j++) {
            if (dailyHigh < arr[j] && arr[j] != 0)
                dailyHigh = arr[j];
        }
        cout << setw(12) << dailyHigh << endl;
    }

    k = 0;
    do {
        dailyHigh = arr[i - 24 + k++];
    } while (dailyHigh == 0);
    for (j = i - 24; j < i; j++) {
        if (dailyHigh < arr[j] && arr[j] != 0)
            dailyHigh = arr[j];
    }
    cout << setw(12) << dailyHigh << endl << endl;

    cout << "Sliding window 24-hour averages" << endl;
    cout << "===============================" << endl;
    for (i = 0; i < n; i++) {
        if (arr[i] == 0) {
            cout << setw(12) << "nan";
        }
        else {
            cout << setw(12) << arr[i];
        }
        double sum = 0;
        int zeroCnt = 0;
        int start = i - 12;
        j = i - 12;
        if (j < 0) { // thinkg for -12 temp val (front vals don't have enough vals so ignore)
            start = 0;
            for (j = 0; j <= i; j++) {
                sum += arr[j];
                if (arr[j] == 0)
                    zeroCnt++;
            }
                
        }
        else {
            for (j; j <= i; j++) {
                sum += arr[j];
                if (arr[j] == 0)
                    zeroCnt++;
            }
        }
        if (i + 12 > n) { // think for +11 val (end vals don't have enough vals so ignore)
            for (k = i + 1; k < n; k++) {
                sum += arr[k];
                if (arr[k] == 0)
                    zeroCnt++;
            }
                
        }
        else {
            for (k = i + 1; k < i + 12; k++) {
                sum += arr[k];
                if (arr[k] == 0)
                    zeroCnt++;
            }
        }

        cout << setw(12) << sum / (k - start - zeroCnt) << endl;
    }
    cout << endl << "Overall mean: " << mean(arr, n) << endl;
    *maximum_value(arr, n) = 0; // except one largest val
    cout << "Mean after removing the largest value: " << mean(arr, n) << endl;
    *maximum_value(arr, n) = 0; // except one more largest val
    cout << "Mean after removing the 2 largest values: " << mean(arr, n) << endl;

    return 0;
}