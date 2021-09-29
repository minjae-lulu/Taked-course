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

    friend class CircleList<E>; // allow circlelist acess to class cnode private member.
};

template <typename E>
class CircleList {

public:
    CircleList(); // default constructor
    CircleList(const CircleList<E> & L);// copy constructor
    ~CircleList();

    bool empty() const; // returns true if list is empty
    const E& back() const; // element at the cursor
    const E& front() const; // element after the cursor
    void advance(); // moves cursor forward
    void add(const E& e); // adds a node after the cursor
    void remove(); // removes the node after cursor

    int size() const;
    bool find(const E& e);
    void reverse();

    //friend ostream& operator<<(ostream & out, const CircleList<E>& l);

private:
    CNode<E>* cursor;
    int l_size;
};

template <typename E>
CircleList<E>::CircleList()
    : cursor(NULL), l_size(0){}

template <typename E>
CircleList<E>::CircleList(const CircleList<E> & L){
    cursor = L.cursor;
    l_size = L.l_size;
}

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

template <typename E>
bool CircleList<E>::find(const E& e) {
    for(int i=0; i< this->l_size; i++){
        if(this->front() == e)  return true;
        else this->advance();
    }
    return false;
}

template <typename E>
void CircleList<E>::reverse(){
    if(this->l_size == 0)   return;

    CircleList<E>* C = new CircleList<E>;
    E len = this->l_size;
    for(int i=0; i<len; i++){
        for(int j=0; j<len-i-1; j++){
            this->advance();
        }
        E temp = this->front();
        this->remove();
        C->add(temp);
    }
    for(int i=0; i<len; i++){
        E temp = C->front();
        C->remove();
        this->add(temp);
    }

    delete C;
}


template <typename E>
bool operator==(CircleList<E>& x, CircleList<E>& y){
    if(x.size() != y.size())    return false;
    else{
        for(int i=0; i<x.size(); i++){
            if(x.front() != y.front())  return false;
            else{
                x.advance();
                y.advance();
            }
        }
    }
    return true;
}

template <typename E>
ostream& operator<<(ostream & out, CircleList<E>& l) {
    if(l.size() <= 0)   return out;

    for(int i=0; i<l.size(); i++){
        out << l.front() << " ";
        l.advance();
    }
    return out;
}



//exception handle front(). back(), remove() when empty 

#endif


// question : at << operator and == operator we must use "const" CircleList<E>?
// 