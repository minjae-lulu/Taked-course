#ifndef CIRCLELIST_H
#define CIRCLELIST_H
#include <iostream>
using namespace std;

template <typename E>
class CircleList;

template <typename E>
class CNode {
private:
    E elem;
    CNode<E>* next;

    friend class CircleList<E>;
};

template <typename E>
class CircleList {

public:
    CircleList(); // default constructor
    ~CircleList();
    bool empty() const; // returns true if list is empty
    const E& back() const; // element at the cursor
    const E& front() const; // element after the cursor
    void advance(); // moves cursor forward
    void add(const E& e); // adds a node after the cursor
    void remove(); // removes the node after cursor

private:
    CNode<E>* cursor;
};

template <typename E>
CircleList<E>::CircleList()
    : cursor(NULL){}

template <typename E>
CircleList<E>::~CircleList()
    { while(!empty()) remove(); }

template <typename E>
bool CircleList<E>::empty() const
    { return cursor == NULL; }

template <typename E>
const E& CircleList<E>::back() const
    { return cursor->elem; }

template <typename E>
const E& CircleList<E>::front() const
    { return cursor->next->elem; }

template <typename E>
void CircleList<E>::advance()
    { cursor = cursor->next; }



// operate

#endif