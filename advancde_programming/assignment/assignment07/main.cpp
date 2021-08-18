//20161190 leeminjae
// I had an error that the linker did not terminate normally.
//There was no error before dividing one file into three, but when dividing the file into three, the linker does not work well.
//But the object file is well made, and the implementation works without a hitch.

#include <iostream>
#include "fun.h"
#include <string>
#include <algorithm>
#include <cctype> // for use toupper
using namespace std;

int main(){
    int rom[129]; // ascii has 128 case
    rom['I'] = 1;
    rom['V'] = 5;
    rom['X'] = 10;
    rom['L'] = 50;
    rom['C'] = 100;
    rom['D'] = 500;
    rom['M'] = 1000;
    roman_numerals a, b; // make two roman_numerals class
    cout << "Enter starting and ending numbers: ";
    cin >> a >> b;

    string a_changed = a.getStr();
    string b_changed = b.getStr();

    for(size_t i=0; i<a_changed.size(); i++){
        a_changed[i] = toupper(a_changed[i]);
    } for(size_t i=0; i<b_changed.size(); i++){
        b_changed[i] = toupper(b_changed[i]);
    }
    unsigned int a_num =0;
    unsigned int b_num =0;

    for (size_t i = 0; i < a_changed.size(); i++){
        if (rom[a_changed[i - 1]] < rom[a_changed[i]])
            a_num += rom[a_changed[i]] - rom[a_changed[i - 1]] * 2;
        else
            a_num += rom[a_changed[i]];
    } for (size_t i = 0; i < b_changed.size(); i++){
        if (rom[b_changed[i - 1]] < rom[b_changed[i]])
            b_num += rom[b_changed[i]] - rom[b_changed[i - 1]] * 2;
        else
            b_num += rom[b_changed[i]];
    }
    a.setvalue(a_changed, a_num);
    b.setvalue(b_changed, b_num);

    for (roman_numerals c = a; c <= b; c= c+1 ){
        cout << c << '\t' << c*c <<'\t' << c*(c+1)*(c*2+1)/6 - (a-1)*a*(a*2-1)/6 << '\n';
    }
    return 0;
}