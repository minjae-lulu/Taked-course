//  -----------------------------DO NOT MODIFY THIS PART  ----------------------------------

#ifndef LINKEDTREE
#define LINKEDTREE

#include <iostream>
#include <fstream>
#include <list>

using namespace std;

template <typename E>             // base element type
class Tree {
public:                           // public types
  class Position;
  typedef list<Position> PositionList;     // List of node positions type
  typedef typename list<Position>::iterator Iterator;   // Iterator type
protected:                       
  struct Node{                    // Node structure
    E elt;
    Node *parent;
    PositionList children;
    Node() : elt(), parent(NULL), children() {}
  };
public:
  class Position {                       // a node position
  friend class Tree;
  public:
    Position(Node* _v = NULL) : v(_v) {} // constructor
    E& operator*();                      // get element
    Position parent() const;             // get parent
    PositionList children() const;       //get node's children
    bool isRoot() const;                 // root node?
    bool isLeaf() const;                 // is it a leaf?
  private:
    Node* v;
  };
public:                           // public functions
  Tree() : _root(NULL),_n(0) {};   // default constructor, constructs an empty tree
  int size() const;               // number of nodes
  bool empty() const;             // is the tree empty?
  Position root() const;          // get the root
  Position addRoot(const E & e);  // add a root containing e to an empty tree
  Position insertAt(const Position & p, const E & e); // insert a child of the node at position p 
  PositionList positions() const; // get positions of all the nodes in preorder
  void print();                   // print elements in preorder in standard output
  Tree(string filename);          // constructs a tree from a file
  ~Tree();                        // Destructor
protected:
  void preorder(Position p, PositionList& pl) const;          // preorder utility
private:
  Node * _root;   // pointer to the root
  int _n;          // number of nodes
};

int vertexCover(const Tree<int> & T);   // weight of a minimum vertex cover of T

// ------------------------------- TYPE YOUR CODE BELOW ----------------------------------------


template <typename E> 
typename Tree<E>::Position Tree<E>::Position::parent() const{
  if (isRoot())
    throw runtime_error("parent of the root\n");
  return Position(v->parent);
}

template <typename E> 
typename Tree<E>::PositionList Tree<E>::Position::children() const{
  return v->children;
}
template <typename E> 
bool Tree<E>::Position::isRoot() const{
  return v->parent==NULL;
}

template <typename E> 
bool Tree<E>::Position::isLeaf() const{
  return v->children.empty();
}

template <typename E> 
E& Tree<E>::Position::operator*(){
  return v->elt;
}

template <typename E>
int Tree<E>::size() const{
  return _n;
}

template <typename E>
bool Tree<E>::empty() const{
  return _n==0;
} 

template <typename E>
typename Tree<E>::Position Tree<E>::root() const{
  return Position(_root);
}             
   
template <typename E>
typename Tree<E>::Position Tree<E>::addRoot(const E & e){
  if(!empty())
    throw runtime_error("calling addroot on a non-empty tree");
  _root=new Node();
  _root->elt=e;
  _root->parent=NULL;
  _n=1;
  return Position(_root);
}

template <typename E>
typename Tree<E>::Position Tree<E>::insertAt(const Tree<E>::Position & p, const E & e){
  Tree<E>::Node* w=new Tree<E>::Node;
  w->elt=e;
  Position q(w);
  p.v->children.push_back(q);
  q.v->parent=p.v;
  _n++;
  return Position(q);
}

template <typename E>
void Tree<E>::preorder(Position p, Tree<E>::PositionList& pl) const {
  pl.push_back(p);
  PositionList ch=p.children();
  for(Iterator it=ch.begin(); it!=ch.end(); it++)
    preorder(*it,pl);
}

template <typename E>
typename Tree<E>::PositionList Tree<E>::positions() const{
  PositionList(pl);
  preorder(Position(_root),pl);
  return PositionList(pl);
}   

template <typename E>
void Tree<E>::print(){
  PositionList pl=positions();
  for(Iterator it=pl.begin(); it!=pl.end(); it++)
    cout << *(Position(*it))<< " ";
  cout << endl;
}

template <typename E>
Tree<E>::~Tree(){
  if(!empty()){
    PositionList pl=positions();
    typedef typename PositionList::reverse_iterator RIterator;  
    for(RIterator it=pl.rbegin(); it!=pl.rend(); it++)
      delete (*it).v;
  }
}

template <typename E>
Tree<E>::Tree(string filename){
  _root=NULL; _n=0;
  ifstream infile(filename);
  if (!infile.is_open())
    throw runtime_error("could not open input file");
  cout << "reading file " << filename << endl;
  int n;
  infile >> n;
  Position p[n];
  for(int i=0; i<n; i++){
    E e;
    int j;
    infile >> j;
    infile >> e;
    infile >> j;
    if (i==0)
      p[0]=addRoot(e);
    else
      p[i]=insertAt(p[j],e);
  }
}

void recVertexCover(Tree<int>::Position p, int & withRoot, int & withoutRoot){
  if (p.isLeaf()){
    withRoot=*p;
    withoutRoot=0;
    return;   
  }
  withRoot=*p;
  withoutRoot=0;
  Tree<int>::PositionList ch=p.children();
  for(Tree<int>::Iterator it=ch.begin(); it!=ch.end(); it++){
    int w,wo;
    recVertexCover(*it,w,wo);
    withRoot+=min(w,wo);
    withoutRoot+=w;
  }
}
int vertexCover(const Tree<int> & T){  
  int with,without; 
  recVertexCover(T.root(),with,without);
  return min(with,without);
}

#endif



