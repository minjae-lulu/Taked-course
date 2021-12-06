// Check basic graph functions

#include "graph.h"
#include <vector>

int main(){
  cout << "----------------------- Starting test1.cpp ----------------------- \n";
  
  Graph G;
  
  cout << endl << "inserting the vertices" << endl;
  Graph::Vertex v[5];
  v[0]=G.insertVertex("A");
  v[1]=G.insertVertex("B");
  v[2]=G.insertVertex("C");
  v[3]=G.insertVertex("D");
  v[4]=G.insertVertex("E");
  
  string ids[5]={"A", "B", "C", "D", "E"};
  for(int i=0; i<5; i++)
    if (*v[i]==ids[i])
      cout << "SUCCESS: v[i]=" << ids[i] << endl;
    else
      cout << "ERROR: v[i]=" << *v[i] << " instead of " << ids[i] << endl;

  if (G.size()==5)
    cout << "\nSUCCESS: size=5" << endl;
  else
    cout << "\nERROR: size is " << G.size() << " instead of " << 5 << endl;

  cout << "\ninserting the edges\n";
  G.insertEdge(10,v[0],v[1]);
  G.insertEdge( 5,v[0],v[2]);
  G.insertEdge( 2,v[1],v[2]);
  G.insertEdge( 1,v[1],v[3]);
  G.insertEdge( 3,v[2],v[1]);
  G.insertEdge( 9,v[2],v[3]);
  G.insertEdge( 2,v[2],v[4]);
  G.insertEdge( 4,v[3],v[4]);
  G.insertEdge( 6,v[4],v[3]);
  G.insertEdge( 7,v[4],v[0]);
  
  int I[5][5]={
    0, 1, 1, 0, 0,
    0, 0, 1, 1, 0,
    0, 1, 0, 1, 1,
    0, 0, 0, 0, 1,
    1, 0, 0, 1, 0  
  };
  
  for (int i=0; i<5; i++)
  	for( int j=0; j<5;j++)
  	  if (I[i][j]==v[i].isAdjacentTo(v[j]))
  	    cout << "SUCCESS: v[" << i << "].isAdjacentTo(v[" << j << "]) returns a correct result" << endl;
  	  else
  	    cout << "ERROR: v[" << i << "].isAdjacentTo(v[" << j << "]) returns a wrong result" << endl;

  cout << endl << "checks setAux() and getAux()" << endl;
  v[2].setAux(17);
  if (v[2].getAux()==17)
    cout << "SUCCESS" << endl;
  else
    cout << "ERROR" << endl;

  cout << endl;
  double sum[5]={ 15, 3, 14, 4, 13 };
  for (int i=0; i<5;i++){
    double t=0;
    Graph::EdgeList L=v[i].outgoingEdgeList();
    Graph::EdgeItor it;
    for (it=L.begin(); it!=L.end(); it++)
      t+=**it;
    if (t==sum[i])
      cout << "SUCCESS: sum of the weights of outgoing edges of v[" << i << "]=" << sum[i] << endl;
    else
      cout << "ERROR: sum of the weights of outgoing edges of v[" << i << "]!=" << sum[i] << endl;
  }

  cout << "\nRunning Dijkstra's algorithm from v[0] to v[3]\n";
  vector<string> path=Dijkstra(G,v[0],v[3]);
  cout << "desired result: \"A C B D \"" << endl;
  cout << "   your result: \"";
  for (int i=0;i<path.size();i++)
    cout << path[i] << " ";
  cout << "\"" << endl;
  vector<string> sol={"A", "C", "B", "D"};
  if (sol.size()!=path.size())
    cout << "ERROR" << endl;
  else {
    bool b=true;
    for(int i=0; i<sol.size(); i++)
      if (sol[i]!=path[i])
        b=false;
    if (b==true)
      cout << "SUCCESS" << endl;
    else
      cout << "ERROR";  
  }
   
  cout << endl << "----------------------- Completed test1.cpp ----------------------- " << endl;
  
  return EXIT_SUCCESS;
}

