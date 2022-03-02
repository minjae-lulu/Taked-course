//It loads several header files and executes main.cpp.
#include <iostream>
#include <string>
#include <cstddef>
#include "book.h"
#include "person.h"
#include "library.h"

static book books[] = {
    book("The Great Gatsby"),
    book("1984"),
    book("Lord of the Flies"),
    book("Dracula"),
};
static person people[] = {
    person("Joe"),
    person("Jane"),
    person("Fred"),
};
int main() {
    library l(books, sizeof books / sizeof books[0], people, sizeof people / sizeof people[0]);
    l.take_out(0, 0);
    l.return_all(0);
    l.take_out(0, 0);
    l.take_out(0, 1);
    library m = l;
    l.return_all(0);
    l.take_out(0, 2);
    m.take_out(1, 2);
    m.take_out(1, 0); //fail
    m.return_all(0);
    m.take_out(2, 2); //fail
    l.take_out(1, 1);
    l.return_all(0);
    m.return_all(2);
    m.take_out(2, 0);
    m.return_all(2);
    m.take_out(0, 2); //fail
    m.take_out(0, 1);
    m.take_out(2, 0);
    l.take_out(1, 2);
    l.take_out(2, 3);
    l.take_out(2, 0);
    using namespace std;
    cout << l << endl;
    cout << m << endl;
    return 0;
}