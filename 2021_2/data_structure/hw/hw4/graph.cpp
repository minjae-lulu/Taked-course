#include "graph.h"

#include <algorithm> // reverse함수를 쓰기위해
#include <queue>

static const int INF = 1234567890;

vector<string> Dijkstra(Graph &G, Graph::Vertex s, Graph::Vertex t){
  vector<string> route; // 결과값
  int idx = 0, n = G.size();
  vector<int> dist(n, INF); // 출발점으로부터 떨어진 거리를 저장하는 벡터(배열). 무한대 값으로 전체 초기화
  vector<bool> visited(n, false); // 이미 방문한곳이면 true
  vector<int> prevIdx(n, -1); // prevIdx[i] : i노드 이전에 방문한 노드의 번호 => 실제 경로를 저장하기 위함. -1로 전체 초기화
  vector<Graph::Vertex> vtx; // 그래프의 전체 Vertex를 벡터에 저장한다.(인덱스 활용하기 위함)
  Graph::VertexList vl = G.getVertexCollection();

  for(Graph::VertexItor itr = vl.begin(); itr != vl.end(); itr++){
    itr->setAux(idx++); // 새로 push할 vtx벡터 인덱스를 vertex의 aux에 저장한다.  인덱스 사용 : 단순히 노드를 번호로 매기기 위함
    vtx.push_back(*itr);
  }

  // 다익스트라 알고리즘 부분 (출발점에서 모든 노드까지의 최단거리 구하기)
  priority_queue<pair<double, int> > pq; // pair : {weight, idx}
  pq.push(make_pair(0, s.getAux())); // 출발점을 넣어준다. getAux() : 그래프상 노드의 번호라고 생각
  dist[s.getAux()] = 0;

  while(!pq.empty()){
    pair<double, int> p = pq.top();
    pq.pop();

    int curIdx = p.second;
    if(visited[curIdx])
      continue;
    visited[curIdx] = true; // 방문 체크

    Graph::EdgeList el = vtx[curIdx].outgoingEdgeList();
    for(Graph::EdgeItor itr = el.begin(); itr != el.end(); itr++){
      int nextIdx = itr->dest().getAux(); // 연결된 노드의 번호
      double nextDist = *(*itr); // 연결된 노드와의 거리(weight)

      if(dist[nextIdx] > dist[curIdx] + nextDist){ 
        /*
          (현재까지 구한 출발노드~연결된 노드까지의 거리) > (출발노드~현재 노드까지의 거리 + 현재노드~연결된 노드까지의 거리)
          즉 지금까지 구한 시작점과의 거리보다, 현재 노드를 거쳐서 가는 거리가 더 짧다면 값을 갱신한다.
        */
        dist[nextIdx] = dist[curIdx] + nextDist;
        pq.push(make_pair(-dist[nextIdx], nextIdx)); 
        /*
          -dist[nextIdx] : 음수를 쓰는 이유는 기본적으로 priority_queue는 가장 큰 값을 뽑아내는 최대힙이기 때문이다. 
          가장 가까운 거리의 노드를 먼저 뽑아내야 하기때문에, 음수를 취해주면 가장 작은 값이 가장 큰 값이 된다.
        */

        prevIdx[nextIdx] = curIdx; // 직전에 방문한 곳을 저장
      }
    }
  }


  // 실제 최단경로 뽑아내는 부분
  route.push_back(*t);
  for(int i = prevIdx[t.getAux()]; i != -1; i = prevIdx[i]){ // 도착점을 출발으로 잡고 역순으로 스택에 저장. 반복문 종료조건 : 출발점 이전 노드는 -1임
    route.push_back(*vtx[i]);
  }

  reverse(route.begin(), route.end()); // 역순으로 뒤집기

  return route;
}
