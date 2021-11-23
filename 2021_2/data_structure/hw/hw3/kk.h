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
int Tree<E>::size() const
  { return _n; }

template <typename E>
bool Tree<E>::empty() const
  { return _n == 0; }

template <typename E>
Tree<E>::~Tree(){
  _n = 0;
  // ????
}

template <typename E>
typename Tree<E>::Position Tree<E>::root() const
  { return Tree<E>::Position(_root); }

template <typename E>
typename Tree<E>::Position Tree<E>::addRoot(const E &e){
  if (empty() == false)
    throw runtime_error("root is already exist");
  else{
    _root = new Node;
    _root->elt = e;
    _n = 1;
  }
}

template <typename E>
typename Tree<E>::Position Tree<E>::insertAt(const Position &p, const E &e) {
  // ????
  // Node *nn = new Node;
  // nn -> parent = p.v;
  // nn -> elt = e;
  // p.v -> children.push_back(Position(nn));
  // _n += 1;

  Node* v = p.v;
  v -> elt = e;
  v -> children = new PositionList;
  v -> children -> parent = v;
  _n++;
  
}

template <typename E>
typename Tree<E>::PositionList Tree<E>::positions() const {
  PositionList pl;
  preorder(root(), pl);
  return PositionList(pl);
}

template <typename E>
void Tree<E>::print() {
  // ???
  // Position _root = root();
  // cout << _root;
  // PositionList ch = _root.positions();
  // for (Iterator q = ch.begin(); q != ch.end(); ++q)
  // {
  //   cout << " ";
  //   cout << *q;
  // }
}

template <typename E>
Tree<E>::Tree(string filename) {
  // ????
  // ifstream input_file(filename);
  //   int num;
  //   input_file >> num;
  //   m = num;
  //   input_file >> num;
  //   n = num;
}

template <typename E>
void Tree<E>::preorder(Position p, PositionList &pl) const{
//   // ????
//   pl.push_back(p);
//   for{ // maybe using iterator
//     if (p.v -> children != NULL){
//       preorder(p.v-> children, pl)
//     }
//   }
}

template <typename E>
E &Tree<E>::Position::operator*() {
  return v->elt;
}

template <typename E>
typename Tree<E>::Position Tree<E>::Position::parent() const {
  if (v->parent == NULL)
    throw runtime_error("root has no parent.");
  else
    return Tree<E>::Position(v->parent);
}

template <typename E>
typename Tree<E>::PositionList Tree<E>::Position::children() const
  { return PositionList(v->children); }

template <typename E>
bool Tree<E>::Position::isRoot() const
  { return v->parent == NULL; }

template <typename E>
bool Tree<E>::Position::isLeaf() const
  { return v->children.empty(); }




int vertexCover(const Tree<int> & T){
  // for test3
}

#endif