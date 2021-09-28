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
    //CircleList(const CircleList<E> & L);

    bool empty() const; // returns true if list is empty
    const E& back() const; // element at the cursor
    const E& front() const; // element after the cursor
    void advance(); // moves cursor forward
    void add(const E& e); // adds a node after the cursor
    void remove(); // removes the node after cursor

    int size() const;
    bool find(const E& e);
    void reverse();

    friend ostream& operator<<(ostream & out, const CircleList<E>& l);

private:
    CNode<E>* cursor;
    int l_size;
};

template <typename E>
CircleList<E>::CircleList()
    : cursor(NULL), l_size(0){}

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

template <typename E>
void CircleList<E>::add(const E&e){
    CNode<E>* v = new CNode<E>;
    l_size++;
    v -> elem = e;
    if(cursor == NULL){ // it mean empty list
        v -> next = v; // v point itself
        cursor = v; // cursor point v
    }
    else{
        v -> next = cursor -> next; //  make (cursor -> a) to (cursor -> v -> a)
        cursor -> next = v; // 
    }
}

template <typename E>
void CircleList<E>::remove(){
    CNode<E>* old = cursor->next;
    if(old == cursor){ //empty list
        cursor = NULL;
    }
    else{
        cursor -> next = old -> next;
    }

    if(l_size > 0)    l_size--;
    else            l_size = 0;

    delete old;
}

template <typename E>
int CircleList<E>::size() const
    { return l_size;}

// overode
// bool operator==(const CircleList<E>& x, const CircleList<E>& y){
//     return x.
// }

ostream& operator<<(ostream & out, const CircleList<E>& l) {
    return out << l.front();
}


// ostream& operator<<(ostream & out, const Vector2& v){  // prints a vector 
//   out << "(" << v.getx() << ", " << v.gety() << ")";
//   return out;
// }

//exception handle front(). back(), remove() when empty 

#endif
