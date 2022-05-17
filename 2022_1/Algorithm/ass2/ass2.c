#include <stdio.h>
// 배열과 배열의 길이를 받아서, 정답을 출력하는 함수입니다.sol_fun 함수는 mergesort를 한후, 그 중앙값을 k로잡아 sigma(arr[i]-k)를 구하는 방식입니다! 
// 절댓값을 구하는 함수 (get_absol), 수업시간에 배운 merge함수, mergesort함수, 정답을 구하는 sol_fun 함수를 작성하였습니다.!


int get_absol(int a, int b){
	if (a >= b)	return a-b; 
	else		return b-a;
}

void merge(int arr[], int l, int m, int r){
	int i, j, k;
	int n1 = m - l + 1;
	int n2 = r - m;
	int L[n1], R[n2];

	for (i = 0; i < n1; i++)
		L[i] = arr[l + i];
	for (j = 0; j < n2; j++)
		R[j] = arr[m + 1 + j];

	i = 0; 
	j = 0; 
	k = l; 

	while (i < n1 && j < n2){
		if (L[i] <= R[j]){
			arr[k] = L[i];
			i++;
		}
		else{
			arr[k] = R[j];
			j++;
		}
		k++;
	}
	while (i < n1){
		arr[k] = L[i];
		i++;
		k++;
	}
	while (j < n2){
		arr[k] = R[j];
		j++;
		k++;
	}
}

void mergesort(int arr[], int l, int r){
	if (l < r){
		int m = l + (r - l) / 2;
		mergesort(arr, l, m);
		mergesort(arr, m + 1, r);
		merge(arr, l, m, r);
	}
}


int sol_fun(int *arr, int n){ 		// I assume sol_fun need two parameter, array and element number in array
	int sum = 0;
	mergesort(arr, 0, n - 1);
	int k = arr[n / 2]; 			// find value mid value of array, this value is the value that sum of sugma(arr[]-k) make mininum. 
	for (int i = 0; i < n; i++){
		sum += get_absol(arr[i],k);	//calculate sum of sugma(arr[]-k)
	}
	return sum;
}

int main(){

	int A[] = {9,8,3,4,1};
	int B[] = {8, 45, 23, 5, 4, 12, 7, 55, 65, 433,567,456,24,76,245,567,2,6,9,456,23,45};
	//int B[] = {2,70,8,4,8,50,900,1};
	int num = sizeof(A) / sizeof(A[0]);
	
	printf("%d",sol_fun(A,num));
	printf("\n");


	return 0;
}
