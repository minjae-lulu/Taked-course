//20161190 leeminjae
// i define funcion at fun.cpp file 
#include "fun.h"
#include <iostream>
#include <string>
#include <algorithm>

using namespace std;

void roman_numerals::setvalue(string a, unsigned int b){ // for setvalue
    str = a;
    num = b;
}

string roman_numerals::sub_num_to_str(unsigned int num){  // for change num to str function 
    string result;
    switch(num){
        case 0: return "";
        case 1: return "I";
        case 5: return "V";
        case 10: return "X";
        case 50: return "L";
        case 100: return "C";
        case 500: return "D";
        case 1000: return "M";

        case 4: return "IV";
        case 9: return "IX";
        case 40: return "XL";
        case 90: return "XC";
        case 400: return "CD";
        case 900: return "CM";
        
        default:
            if(num/10 == 0){ // think num is single digit natural number
                if(num>5){
                    num -=5;
                    result += sub_num_to_str(5);
                }
                if(num%3 == 1){ result += "I";}
                if(num%3 == 2){ result += "II";}
                if(num%3 == 0){ result += "III";}
            }
            else if (num/100 == 0){ // think num is double-digit natural number
                if(num>50) {
                    num -= 50;
                    result += sub_num_to_str(50);
                }
                if((num/10)%3 == 1){ result += "X";}
                if((num/10)%3 == 2){ result += "XX";}
                if((num/10)%3 == 0){ result += "XXX";}
            }
            else if (num/1000 == 0){ // think num is three-digit natural number
                if(num>500){
                    num -= 500;
                    result += sub_num_to_str(500);
                }
                    if((num/100)%3 ==1){result += "C";}
                    if((num/100)%3 ==2){result += "CC";}
                    if((num/100)%3 ==0){result += "CCC";}
            }
            else if (num/10000 == 0){ // think num is four-digit natural number
                for(size_t i = 1; i<=(num/1000); i++)
                    result += "M";
            }
    }
    return result;
}

string roman_numerals::num_to_str(unsigned int num){ // for number change to string  and neem sub_num_to_str
    string result;
    int ten = 10;
    while (num != 0){
        if(num%1000 == 499){ //exception case for 499
            result.insert(0,"ID");
            num -= num%1000;
            ten *= 10;
        }
        if(num%1000 == 999){ //exception case for 999
            result.insert(0,"IM");
            num -= num%1000;
            ten *= 10;
        }
        if(num%1000 == 490){ //exception case for 49x
            result.insert(0,"XD");
            num -= num%1000;
            ten *= 10;
        }
        if(num%1000 == 990){ //exception case for 99x
            result.insert(0,"XM");
            num -= num%1000;
            ten *= 10;
        }
        if(num%100 == 49){//exception case for 49
            result.insert(0,"IL");
            num -= num%100;
            ten *= 10;
        }
        if(num%100 == 99){//exception case for 99
            result.insert(0,"IC");
            num -= num%100;
            ten *= 10;
        }
        else{
        result.insert(0, sub_num_to_str(num % ten)); 
        num -= num % ten;
        ten *= 10;
        }
    }
    return result;
}

roman_numerals &roman_numerals::operator+=(roman_numerals const &other){
    unsigned new_num = this->num + other.num;
    string new_str = num_to_str(new_num);
    num = new_num;
    str = new_str;
    return *this;
}
roman_numerals roman_numerals::operator+(roman_numerals const &other) const {
    roman_numerals r = *this;
    return r += other;
}
roman_numerals &roman_numerals::operator-=(roman_numerals const &other){
    unsigned new_num = this->num - other.num;
    string new_str = num_to_str(new_num);
    num = new_num;
    str = new_str;
    return *this;
}
roman_numerals roman_numerals::operator-(roman_numerals const &other) const {
    roman_numerals r = *this;
    return r -= other;
}
roman_numerals &roman_numerals::operator*=(roman_numerals const &other){
    unsigned new_num = this->num * other.num;
    string new_str = num_to_str(new_num);
    num = new_num;
    str = new_str;
    return *this;
}
roman_numerals roman_numerals::operator*(roman_numerals const &other) const {
    roman_numerals r = *this;
    return r *= other;
}
roman_numerals &roman_numerals::operator/=(roman_numerals const &other){
    unsigned new_num = this->num / other.num;
    string new_str = num_to_str(new_num);
    num = new_num;
    str = new_str;
    return *this;
}
roman_numerals roman_numerals::operator/(roman_numerals const &other) const{
    roman_numerals r = *this;
    return r /= other;
}
bool roman_numerals::operator<(roman_numerals const &other) const {
    roman_numerals another = *this;
    return another.num < other.num;
}
bool roman_numerals::operator<=(roman_numerals const &other) const {
    roman_numerals another = *this;
    return another.num <= other.num;
}



ostream &operator<<(ostream &out, roman_numerals const &r) { // for output 
    return out << r.str;
}
istream &operator>>(istream &in, roman_numerals &r){ // for cin 
    in >> r.str;
    return in;
}