//20161190 leeminjae it is library.cpp file
#include "library.h"
#include "book.h"
#include "person.h"
#include <iostream>
#include <string>
#include <cstddef>
using namespace std;


library::library(book *b, size_t bnum, person *p, size_t pnum, size_t n0 = 0, size_t n1 = 0, size_t n2 = 0, size_t n3 = 0)
    : b_num(bnum), p_num(pnum), list_book(new book[bnum]), list_person(new person[pnum]), n0(n0), n1(n1), n2(n2), n3(n3){
    for (size_t i = 0; i < bnum; i++)
        list_book[i] = b[i];
    for (size_t i = 0; i < pnum; i++)
        list_person[i] = p[i];
}

library::~library(){                       //desturctor
    delete[] list_book; //deallocate
    delete[] list_person;
}

library::library(library const &o) // copy constructir
    : b_num(o.b_num), p_num(o.p_num), list_book(new book[o.b_num]), list_person(new person[o.p_num]), n0(o.n0), n1(o.n1), n2(o.n2), n3(o.n3), two_arr(o.two_arr)
{
    for (size_t i = 0; i < b_num; i++)
        list_book[i] = o.list_book[i];
    for (size_t i = 0; i < p_num; i++)
        list_person[i] = o.list_person[i];
}

library &library::operator=(library const &o){
    delete[] list_book; //deallocate
    delete[] list_person;
    b_num = o.b_num;
    p_num = o.p_num;
    list_book = new book[o.b_num];
    list_person = new person[o.p_num];
    n0 = o.n0;
    n1 = o.n1;
    n2 = o.n2;
    n3 = o.n3;
    for (size_t i = 0; i < 3; i++){ // for l = m, 2d array copy
        for (size_t j = 0; j < 4; j++){
            two_arr[i][j] = o.two_arr[i][j];
        }
    }
    for (size_t i = 0; i < b_num; i++)
        list_book[i] = o.list_book[i];
    for (size_t i = 0; i < p_num; i++)
        list_person[i] = o.list_person[i];
    return *this;
}

void library::take_out(int a, int b){
    for (size_t i = 0; i < 3; i++){
        for (size_t j = 0; j < 4; j++){
            if (two_arr[i][j] == list_book[b].get_title()){ // if anyone take out book. then, can't borrow book so return.
                return;
            }
        }
    }
    if (b == 0)
        n0++;
    if (b == 1)
        n1++;
    if (b == 2)
        n2++;
    if (b == 3)
        n3++;
    if (two_arr[a][0].empty()){
        two_arr[a][0] = list_book[b].get_title();
    }
    else if (two_arr[a][1].empty()){
        two_arr[a][1] = list_book[b].get_title();
    }
    else if (two_arr[a][2].empty()){
        two_arr[a][2] = list_book[b].get_title();
    }
}

void library::return_all(int a){
    for (size_t i = 0; i < 4; i++){
        two_arr[a][i] = "";
    }
}

string library::get_book(int a, int b){
    return two_arr[a][b];
}

size_t library::get_num(int a){
    if (a == 0)
        return n0;
    else if (a == 1)
        return n1;
    else if (a == 2)
        return n2;
    else if (a == 3)
        return n3;
    return 0;
}

string library::result_num(string name){
    string result;
    if (name == "The Great Gatsby")
        result = to_string(n0);
    else if (name == "1984")
        result = to_string(n1);
    else if (name == "Lord of the Flies")
        result = to_string(n2);
    else if (name == "Dracula")
        result = to_string(n3);

    if (result == "0" || result == "1")
        return "";
    else
        return " (" + result + ")";
}

ostream &operator<<(ostream &out, library &o){
    for (size_t i = 0; i < o.p_num; i++)
    {
        out << o.list_person[i].get_name() << ": "; // person name

        if (o.get_book(i, 0) == "")
            cout << "";
        else
            out << o.get_book(i, 0) << o.result_num(o.get_book(i, 0));
        for (size_t j = 1; j < o.b_num; j++)
        {
            if (o.get_book(i, j) == "")
                cout << "";
            else
                out << ", " << o.get_book(i, j) << o.result_num(o.get_book(i, j));
        }
        out << '\n';
    }
    return out;
}