#include "graph.h"

#include <algorithm> // for reverse function
#include <queue>

static const int INF = 2100000000; // distance value.

vector<string> Dijkstra(Graph &G, Graph::Vertex s, Graph::Vertex t)
{
  vector<string> route; // result vector
  int idx = 0, n = G.size();
  vector<int> dist(n, INF);       // distance vector by start point. initialize very big value.
  vector<bool> visited(n, false); // if already visted -> true. else -> false
  vector<int> prevIdx(n, -1);     // pre visited index (before i node) -> for save real path. initilize -1.
  vector<Graph::Vertex> vtx;      // graph's total vertex save to vtx. (for index)
  Graph::VertexList vl = G.getVertexCollection();

  for (Graph::VertexItor itr = vl.begin(); itr != vl.end(); itr++){
    itr->setAux(idx++); // first, vtx's index save to vertex's aux (vtx will push so already make this.)
    vtx.push_back(*itr);
  }

  // dijkstra algorithm.
  priority_queue<pair<double, int>> pq; // pair : {weight, idx}
  pq.push(make_pair(0, s.getAux()));    // push start point. getAux() : node index
  dist[s.getAux()] = 0;

  while (!pq.empty()){ // until priority queue empty
    pair<double, int> p = pq.top();
    pq.pop();

    int curIdx = p.second;
    if (visited[curIdx])
      continue;
    visited[curIdx] = true; // visit check

    Graph::EdgeList el = vtx[curIdx].outgoingEdgeList();
    for (Graph::EdgeItor itr = el.begin(); itr != el.end(); itr++){
      int nextIdx = itr->dest().getAux(); // connect node index
      double nextDist = *(*itr);          // connect node distnace

      if (dist[nextIdx] > dist[curIdx] + nextDist){
        // if total vale > add now node, refresth the value
        dist[nextIdx] = dist[curIdx] + nextDist;
        pq.push(make_pair(-dist[nextIdx], nextIdx));
        // pq is max value, but we need shortest dist. so make -dist -> shrotest value = max vale
        prevIdx[nextIdx] = curIdx; // save visited point 
      }
    }

    
  }

  // save shortest path
  route.push_back(*t);
  for (int i = prevIdx[t.getAux()]; i != -1; i = prevIdx[i]){ 
    // endpoint is start point and save at stack. until i == -1
    route.push_back(*vtx[i]);
  }

  reverse(route.begin(), route.end()); // reverse

  return route;
}
