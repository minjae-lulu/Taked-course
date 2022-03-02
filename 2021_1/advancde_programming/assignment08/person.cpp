// it is person.cpp file
#include "person.h"
#include <iostream>
#include <cstddef>
#include "book.h"
#include <string>
using namespace std;

person::~person(){
    delete[] list_of_books;
}
person::person(person const &o)
    : name(o.name), list_of_books_size(o.list_of_books_size), list_of_books(new book *[o.list_of_books_size]){
    for (size_t i = 0; i < list_of_books_size; i++)
    {
        list_of_books[i] = o.list_of_books[i];
    }
}

person &person::operator=(person const &o)
{
    delete[] list_of_books;
    name = o.name;
    list_of_books_size = o.list_of_books_size;
    list_of_books = new book *[o.list_of_books_size];
    for (size_t i = 0; i < list_of_books_size; i++)
    {
        list_of_books[i] = o.list_of_books[i];
    }
    return *this;
}

person &person::operator+=(book *new_element)
{ // for add book to person
    book **new_elements = new book *[list_of_books_size + 1];
    for (size_t i = 0; i < list_of_books_size; i++)
        new_elements[i] = list_of_books[i];
    new_elements[list_of_books_size] = new_element;
    list_of_books_size++;
    delete[] list_of_books;
    list_of_books = new_elements;
    return *this;
}
bool person::operator[](book *needle) const
{ // check for if borrowed already
    for (size_t i = 0; i < list_of_books_size; i++)
        if (list_of_books[i] == needle)
            return true;
    return false;
}

