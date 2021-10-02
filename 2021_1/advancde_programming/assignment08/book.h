//20161190 leeminjae it is book header file
#ifndef BOOK_H
#define BOOK_H

#include <iostream>
#include <string>
#include <cstddef>
using namespace std;

class book {
private:
   string title;
   size_t borrowed_num;
public :
   book(string title, size_t borrowed_num=0) : title(title), borrowed_num(borrowed_num) {}
   book(){}
   ~book() {} //desturctor
   string get_title();
};

#endif