// Check input file reading

#include "graph.h"
#include <vector>

int main(){
  cout << "----------------------- Starting test2.cpp ----------------------- \n";
  
  Graph G0;
  cout << "\nReading graph G0 from file instance1.in" << endl;
  Graph::Vertex s,t;
  G0.ReadFile("instances/instance1.in", s, t);
  
  int I[5][5]={
    0, 1, 1, 0, 0,
    0, 0, 1, 1, 0,
    0, 1, 0, 1, 1,
    0, 0, 0, 0, 1,
    1, 0, 0, 1, 0  
  };
  Graph::Vertex v[5];
  
  Graph::VertexItor vit;
  Graph::VertexList vL=G0.getVertexCollection();
  int i=0;
  for (vit=vL.begin(); vit!=vL.end(); vit++, i++)
    v[i]=*vit;   
  
  for (int i=0; i<5; i++)
  	for( int j=0; j<5;j++)
  	  if (I[i][j]==v[i].isAdjacentTo(v[j]))
  	    cout << "SUCCESS: v[" << i << "].isAdjacentTo(v[" << j << "]) returns a correct result" << endl;
  	  else
  	    cout << "ERROR: v[" << i << "].isAdjacentTo(v[" << j << "]) returns a wrong result" << endl;
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
  
 
  cout << endl << "----------------------- Completed test2.cpp ----------------------- " << endl;
  
  return EXIT_SUCCESS;
}

