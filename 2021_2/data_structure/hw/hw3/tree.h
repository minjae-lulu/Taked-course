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
#define EXIT_SUCCESS 0
#include <stdexcept>
#include <vector>
#include <algorithm>

template <typename E>
E& Tree<E>::Position::operator*() // get element
  { return v->elt; }

template <typename E>
typename Tree<E>::Position Tree<E>::Position::parent() const // get parent
{
  if(isRoot())
    throw runtime_error("It is root node.");

  return Tree<E>::Position(v->parent);
}

template <typename E>
typename Tree<E>::PositionList Tree<E>::Position::children() const //get node's children
  { return v->children; }

template <typename E>
bool Tree<E>::Position::isRoot() const // root node?
  { return v->parent == NULL; }

template <typename E>
bool Tree<E>::Position::isLeaf() const // is it a leaf?
  { return v->children.empty(); }

template <typename E>
int Tree<E>::size() const // number of nodes
  { return _n; }

template <typename E>
bool Tree<E>::empty() const // is the tree empty?
  { return _n == 0; }

template <typename E>
typename Tree<E>::Position Tree<E>::root() const // get the root
  { return Tree<E>::Position(_root); }

template <typename E>
typename Tree<E>::Position Tree<E>::addRoot(const E & e) // add a root containing e to an empty tree
{
  if(!empty())
    throw runtime_error("Tree is empty.");

  Tree<E>::Node* node = new Tree<E>::Node();
  node->elt = e;

  Tree<E>::Position pos(node);
  _root = node;
  _n = 1;
  return pos;
}

template <typename E>
typename Tree<E>::Position Tree<E>::insertAt(const Position & p, const E & e) // insert a child of the node at position p 
{
  Tree<E>::Node* node = new Tree<E>::Node();
  node -> elt = e;
  node -> parent = p.v;

  Tree<E>::Position pos(node);
  p.v -> children.push_back(pos);
  _n++;
  return pos;
}


template <typename E>
typename Tree<E>::PositionList Tree<E>::positions() const // get positions of all the nodes in preorder
{
  Tree<E>::PositionList pl;
  preorder(_root, pl);
  return pl;
}

template <typename E>
void Tree<E>::print() // print elements in preorder in standard output
{
  // Tree<E>::PositionList pl = positions();
  // for(Iterator q = pl.begin(); q != pl.end(); q++)
  //   cout << q->v->elt << " ";
  // cout << endl;
}

template <typename E>
Tree<E>::Tree(string filename) // constructs a tree from a file
{
  // _n = 0;
  // _root = NULL;

  // ifstream is(filename);
  // if(!is.is_open())
  //   throw runtime_error("File is not exist.");

  // int size = 0;
  // is >> size;

  // struct Input // input structure
  // {
  //   int seq;
  //   E elt;
  //   int parent;

  //   bool operator<(const Input& b) // for sorting
  //   {
  //     if(this->parent == b.parent) // if same parent
  //       return this->seq < b.seq; // lager seq plaecs back

  //     return this->parent < b.parent; // lager parent plaecs back
  //   }
  // };

  // vector<Input> inputVct;
  // for(int i = 0; i < size; i++) // read file
  // {
  //   Input input;
  //   is >> input.seq >> input.elt >> input.parent;
  //   inputVct.push_back(input);
  // }

  // sort(inputVct.begin(), inputVct.end());

  // vector<Position> posVct(size);
  // for(int i = 0; i < size; i++) // add node
  // {
  //   if(i == 0)
  //   {
  //     Position pos = addRoot(inputVct[i].elt);
  //     posVct[inputVct[i].seq] = pos;
  //   }
  //   else
  //   {
  //     Position pos = insertAt(posVct[inputVct[i].parent], inputVct[i].elt);
  //     posVct[inputVct[i].seq] = pos;
  //   }
  // }

  // is.close();
}

template <typename E>
Tree<E>::~Tree() // Destructor
{
  Tree<E>::PositionList pl = positions();
  for(Iterator q = pl.begin(); q != pl.end(); q++)
    delete q->v;
}

template <typename E>
void Tree<E>::preorder(Position p, PositionList& pl) const // preorder utility
{
  // pl.push_back(p);

  // PositionList ch = p.children();
  // for(Iterator q = ch.begin(); q != ch.end(); q++)
  //   preorder(*q, pl);

  pl.push_back(o);
  PositionList ch = p.children();
  for(Iterator q = ch.begin(); q != ch.end(); q++)
    preorder(*q, pl);
}

int dp(const Tree<int>::Position& pos, const int flag)
{
  // int ret = 0;

  // if(flag == 0)
  // {
  //   Tree<int>::PositionList ch = pos.children();
  //   for(Tree<int>::Iterator q = ch.begin(); q != ch.end(); q++)
  //     ret += dp(*q, 1);
  // }
  // else
  // {
  //   Tree<int>::Position& p = const_cast<Tree<int>::Position&>(pos);
  //   ret += *p;
  //   Tree<int>::PositionList ch = pos.children();
  //   for(Tree<int>::Iterator q = ch.begin(); q != ch.end(); q++)
  //     ret += min(dp(*q, 0), dp(*q, 1));
  // }

  // return ret;
}

// int vertexCover(const Tree<int> & T)
// {
//   return min(dp(T.root(), 0), dp(T.root(), 1));
// }

#endif