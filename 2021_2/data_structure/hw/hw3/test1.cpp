// Checking basic functionality (not I/Os, not vertexcover)

#include "tree.h"

typedef typename Tree<int>::Position IntPos;
typedef typename Tree<string>::Position StringPos;

int main(){
  cout << "----------------------- Starting test1.cpp ----------------------- \n";
  
  cout << "Constructing empty tree of integers\n";
  Tree<int> T;
  if (T.empty())
    cout << "SUCCESS: T is empty\n";
  else
    cout << "ERROR: T is not empty\n";
  if (T.size()==0)
    cout << "SUCCESS: size is 0\n";
  else
    cout << "ERROR: size is not 0\n";

  cout << "adding 5 at the root\n";
  IntPos p0=T.addRoot(5);
  if (T.root().isRoot())
    cout << "SUCCESS: root is root\n";
  else
    cout << "ERROR: root is not root\n";
  if (T.root().isLeaf())
    cout << "SUCCESS: root is leaf\n";
  else
    cout << "ERROR: root is not leaf\n";
  if (*T.root()==5)
    cout << "SUCCESS: root is 5\n";
  else
    cout << "ERROR: root is not 5\n";
  if (T.root().children().empty())
    cout << "SUCCESS: empty list of children\n";
  else
    cout << "ERROR: non-empty list of children\n";
  try{
    T.root().parent();
    cout << "ERROR: parent() of root does not throw an exception\n";
  }
  catch(runtime_error){
    cout << "SUCCESS: parent() of root throws an exception\n";
  }
  try{
    T.addRoot(8);
    cout << "ERROR: adding root to non-empty tree does not throw exception\n";
  }
  catch(runtime_error){
    cout << "SUCCESS: adding root to non-empty tree throws an exception\n";
  }

  cout << endl << "Insert 3, 2, and 7 (in this order) at the root.\n";
  cout << "Then insert 4 and 8 at 2.\n";
  IntPos p1 = T.insertAt(p0,3);
  IntPos p2 = T.insertAt(p0,2);
  IntPos p3 = T.insertAt(p0,7);
  IntPos p4 = T.insertAt(p2,4);
  IntPos p5 = T.insertAt(p2,8);
  if (!T.root().isLeaf())
    cout << "SUCCESS: root is not leaf\n";
  else
    cout << "ERROR: root is leaf\n";
  if (T.root().children().size()==3)
    cout << "SUCCESS: root has 3 children\n";
  else
    cout << "ERROR: root does not have 3 children\n";
  if (*p1==3)
    cout << "SUCCESS: 3 is stored at the right position\n";
  else
    cout << "ERROR: 3 is not stored at the right position\n";
  if (*p2==2)
    cout << "SUCCESS: 2 is stored at the right position\n";
  else
    cout << "ERROR: 2 is not stored at the right position\n";
  if (*p3==7)
    cout << "SUCCESS: 7 is stored at the right position\n";
  else
    cout << "ERROR: 7 is not stored at the right position\n";
  if (*p4==4)
    cout << "SUCCESS: 4 is stored at the right position\n";
  else
    cout << "ERROR: 4 is not stored at the right position\n";
  if (*p5==8)
    cout << "SUCCESS: 8 is stored at the right position\n";
  else
    cout << "ERROR: 8 is not stored at the right position\n";
  if (p1.isLeaf())
    cout << "SUCCESS: 3 is a leaf\n";
  else
    cout << "ERROR: 3 is not a leaf\n";  
  if (!p2.isRoot())
    cout << "SUCCESS: 2 is not the root\n";
  else
    cout << "ERROR: 2 is the root\n"; 
  if (p2.isLeaf())
    cout << "ERROR: 2 is a leaf\n";
  else
    cout << "SUCCESS: 2 is not a leaf\n";  
  if (p4.isLeaf())
    cout << "SUCCESS: 4 is a leaf\n";
  else
    cout << "ERROR: 4 is not a leaf\n";  
  if (*p1.parent()==5)
    cout << "SUCCESS: parent of 3 is 5\n";
  else
    cout << "ERROR: parent of 3 is not 5\n";  
  if (*p2.parent()==5)
    cout << "SUCCESS: parent of 2 is 5\n";
  else
    cout << "ERROR: parent of 2 is not 5\n";  
  if (*p3.parent()==5)
    cout << "SUCCESS: parent of 7 is 5\n";
  else
    cout << "ERROR: parent of 7 is not 5\n";  
  if (*p4.parent()==2)
    cout << "SUCCESS: parent of 4 is 2\n";
  else
    cout << "ERROR: parent of 4 is not 2\n";  
  if (*p4.parent()==2)
    cout << "SUCCESS: parent of 8 is 2\n";
  else
    cout << "ERROR: parent of 8 is not 2\n";  
 
  Tree<int>::Iterator it;
  Tree<int>::PositionList ch=T.root().children();
  int child[3];
  int i=0;
  for(it=ch.begin(); it!=ch.end();it++)
    child[i++]=**it;
  if (child[0]==3 and child[1]==2 and child[2]==7){
    cout << "SUCCESS: children of root are 3, 2 and 7\n";
  }
  ch=p2.children(); i=0;
  for(it=ch.begin(); it!=ch.end();it++)
    child[i++]=**it;
  if (child[0]==4 and child[1]==8){
    cout << "SUCCESS: children of 2 are 4 and 8\n";
  }
  
  cout << "----------------------- Completed test1.cpp ----------------------- " << endl;
  return EXIT_SUCCESS;
}

