#ifndef CIRCLELIST
#define CIRCLELIST

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
  CircleList() : cursor(NULL){};     // default constructor
  CircleList(const CircleList<E> & L);  // copy constructor
  ~CircleList()
    { while(!empty()) remove();}     // destructor 
  bool empty() const                 // returns true if list is empty  
    { return cursor == NULL; }
  const E& back() const;             // element at the cursor
  const E& front() const;            // element after the cursor
  void advance();                    // moves cursor forward 
  void add(const E& e);              // adds node after cursor
  void remove();                     // removes node after cursor
  int size() const;                  // number of elements in the list
  void reverse();                    // reverse the list
  bool find(E e);                    // returns true if e is in the list, 
                                     // and places cursor at e  
  bool operator==(const CircleList<E> & L) const;
             // checks whether two lists record the same elements in the same order            
private:
  CNode<E>* cursor;                    
};

template <typename E>
CircleList<E>::CircleList(const CircleList<E>  & L){  // copy constructor
  if (L.cursor==NULL)
  	cursor=NULL;
  else{
    cursor = new CNode<E>;             // copy back element
    cursor->elem = L.back();
    CNode<E>* t=L.cursor;              // copy the other elements
    CNode<E>* c=cursor;
    while(t->next!=L.cursor)
    {
      t=t->next;
      CNode<E>* v=new CNode<E>;
      v->elem=t->elem;
      c->next=v;
      c=v; 
    }
    c->next=cursor;                   // closes the loop 
  }
}

template <typename E>
const E& CircleList<E>::back() const{             // element at the cursor
  if(empty())
    throw runtime_error("Accessing the back element of an empty list");
  return cursor->elem;
}

template <typename E>
const E& CircleList<E>::front() const{        // element after the cursor
  if(empty())
    throw runtime_error("Accessing the front element of an empty list");
  return cursor->next->elem;
}

template <typename E>
void CircleList<E>::advance(){                    // moves cursor forward 
  if(empty())
  	return;
  cursor = cursor->next;
} 


template <typename E>
void CircleList<E>::add(const E& e) {    // adds node after cursor
  CNode<E>* v = new CNode<E>;
  v->elem = e;
  if (cursor == NULL) {
    v->next = v;
    cursor = v;
  }
  else {
    v->next = cursor->next;
   cursor->next = v;
  }
}

template <typename E>
void CircleList<E>::remove() {         // removes node after cursor
 if(empty())
    throw runtime_error("Removing from an empty list");
  CNode<E>* old = cursor->next;
  if (old == cursor)
    cursor = NULL;
  else
    cursor->next = old->next;  
  delete old;                 // important: avoids memory leaks
}

template <typename E> 
int CircleList<E>::size() const{   // number of elements in the list
  CNode<E>* t=cursor;
  int result=0;
  if (t==NULL)
  	return 0;
  do{
    t=t->next;
    result++;
  }
  while(t!=cursor);
  return result;
}

  
template <typename E>
void CircleList<E>::reverse(){
  CircleList<E> R,T;                // temporary list
  while (!empty()) {
    E e = front(); 
    remove();
    R.add(e);
  }
  // Now R is the reverse of the input list
  while (!R.empty()) {
    E e = R.front(); 
    R.remove();
    T.add(e);
  }
  // Now T is equal to the input list
  while (!T.empty()) {
    E e = T.front(); 
    T.remove();
    add(e);
  }
} 

template <typename E>
bool CircleList<E>::find(E e){
  if(empty()){
  	return false;
  }
  CNode<E>* start=cursor;
  do{
    if(back()==e)
      return true;
    advance();
  }while(cursor!=start); 
  return false;
}
  
template <typename E>
bool CircleList<E>::operator==(const CircleList<E> &L) const{
  if(size()!=L.size())
    return false;              // if one is empty and the other is not, they are different
  if(empty())                  // now both are empty, so they are equal
    return true;
  CNode<E>* c=cursor;          // temporary curors on *this and L
  CNode<E>* t=L.cursor;
  do{                          
    if (c->elem!=t->elem)      // checks if elements are different
      return false;
    c=c->next;
    t=t->next;
  }
  while(c!=cursor and t!=L.cursor);
  if(c!=cursor or t!=L.cursor)
    return false;             // sizes are different
  return true;                // now the lists must be equal
}
  

template <typename E>
ostream& operator<<(ostream & out, CircleList<E>& cl){  // prints a vector 
  int n=cl.size();
  for(int i=0; i<n; i++){
    cl.advance();
    cout << cl.back();
    if (i!=n-1)
    	cout << " ";
  }
  return out;
}

#endif
