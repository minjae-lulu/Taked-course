//20161190 leeminjae it is person header file
#ifndef PERSON_H
#define PERSON_H

#include <iostream>
#include <string>
#include "book.h"
#include <cstddef>
using namespace std;

class person {
private:
   string name;
   size_t list_of_books_size=0;  
   book **list_of_books = new book*[list_of_books_size];

public :
   person(string name) : name(name), list_of_books_size(0), list_of_books(nullptr) {}
   person(){}
   ~person();              //desturctor
   person(person const &); // copy constructor

   person &operator=(person const &); // assignment operator
   person &operator+=(book * ); // for add book to person

   bool operator[] (book *) const;  // check for if borrowed already

   string get_name(){return name;}
};

#endif