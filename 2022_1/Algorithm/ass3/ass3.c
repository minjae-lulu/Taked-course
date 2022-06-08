#include <stdio.h>
#include <math.h>
#define MAX 1000

// find distance of two point
float twop_dis(float x1, float y1, float x2, float y2){
	float res = 0.0;
	res = sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
	return res;
}

float min_distance(float points[MAX][2], int n){
	float distance = 100000000000000000000.0; // 1e20
	float dis_mat[n][n];
	float dis_mat_ori[n][n];

	for(int i=0; i<n; i++){
		for(int j=0; j<n; j++){
			if(i == j){
				dis_mat[i][j] = -1; // 음수로 초기화하고, 양수중 작은수만 선택할 계획
				dis_mat_ori[i][j] = -1;
			}	
			else{
				dis_mat[i][j] = twop_dis(points[i][0], points[i][1], points[j][0], points[j][1]);
				dis_mat_ori[i][j] = twop_dis(points[i][0], points[i][1], points[j][0], points[j][1]);
			}
		}
	}


	float min_dist_arr[n];
	int min_idx = 0;
	float min_dis_val = 0.0;
	
	
	// i = 시작점의 idx로 모든 점에서 각각 시작했을때의 최솟값을 찾는다.
	for(int i=0; i<n; i++){
		int change_idx=i;
		int cnt=0;
		int sequence_arr[n];
		while(cnt<n){
			// 현재 위치에서 가장 가까운점의 idx출력
			//printf("min_idx is %d \n",change_idx);
			
			float min_val = 100000000000000000000.0; // 1e20 매우 큰값으로 초기화한다. input값이 더 커지면, 더크게 잡아줘야 하지만 resonable하다고 말씀하심

			// 가장 가까운 점이 어디있는지 찾아내고, 그 점의 idex와 값을 저장한다.
			for(int j=0; j<n; j++){
				if(dis_mat[change_idx][j] < min_val && dis_mat[change_idx][j] > 0){
					min_val = dis_mat[change_idx][j];
					min_idx = j;
				}	
			}

			// 한번 지나온 점들은 포함하지 않기위해 값들을 -1로 변환한다. 특히, 세로축은 전부 -1로 만든다.
			dis_mat[change_idx][min_idx] = -1;
			for (int jj=0; jj<n; jj++){
				dis_mat[jj][change_idx] = -1;
			}
			
			// 각각 최소 거리를 구하는데 지나온 경로들을 배열에 저장한다. 
			sequence_arr[cnt] = change_idx;
			change_idx = min_idx;
			cnt++;
		}
		
		// 답을 구하면서 값이면한 행렬을 원래대로 copy 해준다.
		for(int a=0; a<n; a++){
			for(int b=0; b<n; b++){
				dis_mat[a][b] = dis_mat_ori[a][b];
			}
		}

	
		min_dis_val = 0;

		// 경로가 잘 저장되었는지 확인,
		// for(int i=0; i<n; i++)	printf("%d ", sequence_arr[i]);

		// 저장된 경로를 이용하여, 각 점들끼리의 거리가 저장된 행렬을 이용하여 최소 거리를 구한다.
		for(int i=0; i<n-1; i++){
			min_dis_val = min_dis_val + dis_mat_ori[sequence_arr[i]][sequence_arr[i+1]];
		}
		// printf("\n min_dis_val is %f \n",min_dis_val);

		// 각 시작점에서 출발했을때의 최소 선의 길이보다 distance가 작으면 갱신하며, 모든 시작점에서 선을 그리디로 이었을때 최솟값을 찾는다.
		if(distance > min_dis_val)	distance = min_dis_val; 
		min_dis_val = 0;
	}

	// 점들간 거리 저장 행렬 확인하는 출력함수
	// for(int i=0; i<n; i++){
    //         for(int j=0; j<n; j++){
    //             printf("%f ",dis_mat[i][j]);
    //         }
    //         printf("\n");
    // }
	return distance;
}

int main(){

	//float points[MAX][2]= {1,1, 2,2, 3,3, 7,8 , 8,1}; // -> 15.284659
	//float points[MAX][2]= {-1,3, 2,7, 4,5, 23,54, -3,7, 132,-6, 7,8, 12,43, 234,654, -34,43, 12,-5, 34,32}; // (12 points)-> 1018.660339
	//float points[MAX][2]= {-123,3, 2,743, 4,45, -233,54, -3,7, -132,-6, 7,-8, 412,143, 12234,654, -343,43, 12,-5, 34,32, -1,3, 2,7, 4,5, 23,54, -3,7, 132,-6, 7,8, 12,43, 234,654, -34,43, 12,-5, 34,32}; // (24 points)->  13955.259766
	//float points[MAX][2]= {1,2, 1,1, 2,1 ,5,5}; // => 7.00
	//float points[MAX][2]= {1,1, 2,2, 3,3, 7,8 , 8,1, 0,0, -1,-1}; // -> 18.113
	float points[MAX][2]= {1,1, 2,2, 3,3, 7,8 , 8,1, 0,0, -1,-1, 20,20, -10,20}; // -> 64.560
	int n = 9;
	
	printf("solution is : %f ",min_distance(points, n));
	return 0;
}
