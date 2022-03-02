// it is library header file
#ifndef LIBRARY_H
#define LIBRARY_H

#include <iostream>
#include <cstddef>
#include "book.h"
#include "person.h"
#include <string>
using namespace std;

class library : public book, person {
private:
    size_t b_num = 4; //initialize
    size_t p_num = 3;
    book *list_book = new book[b_num];
    person *list_person = new person[p_num];
    size_t n0 = 0, n1 =0, n2 =0, n3=0;
    string two_arr[3][4] = {""}; // make new 2demension arry

 public:
   library(book *b, size_t bnum, person *p, size_t pnum, size_t n0 = 0, size_t n1 = 0, size_t n2 = 0, size_t n3 = 0); // constructor
   ~library(); //desturctor
   library(library const &); // copy constructor
   library &operator=(library const &); // assignment operator
   
   void take_out(int , int );
   void return_all(int );
   string get_book(int , int );
   size_t get_num(int );

   string result_num(string);

   friend ostream &operator<<(ostream &, library &);
};

#endif