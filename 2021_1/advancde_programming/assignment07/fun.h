// 
#ifndef FUNC_H
#define FUNC_H

#include <string> // for string
using namespace std;

class roman_numerals
{
private:
    string str;
    unsigned int num;
    string sub_num_to_str(unsigned int);
    string num_to_str(unsigned int);

public:
    roman_numerals(string a = "I") : str(a) {}
    roman_numerals(unsigned int a_n) : num(a_n) {}

    roman_numerals &operator+=(roman_numerals const &);
    roman_numerals operator+(roman_numerals const &) const;
    roman_numerals &operator-=(roman_numerals const &);
    roman_numerals operator-(roman_numerals const &) const;

    roman_numerals &operator*=(roman_numerals const &);
    roman_numerals operator*(roman_numerals const &) const;
    roman_numerals &operator/=(roman_numerals const &);
    roman_numerals operator/(roman_numerals const &) const;

    bool operator<(roman_numerals const &) const;
    bool operator<=(roman_numerals const &) const;

    int getNum() { return num; }
    string getStr() { return str; }
    void setvalue(string, unsigned int);
    friend ostream &operator<<(ostream &, roman_numerals const &);
    friend istream &operator>>(istream &, roman_numerals &);
};

#endif