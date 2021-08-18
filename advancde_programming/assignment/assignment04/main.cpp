//20161190 leeminjae assignment04
#include <iostream>
#include <cstdlib>
#include <iomanip>
using namespace std;
#define SIZE 1000 // to fix array size

int score_without_realigning(char const * rna1, size_t n1, char const * rna2, size_t n2){
    int score = 0;
    size_t small_n = 0;
    if (n1 <= n2) // select lengh of the smaller array. to cal score
        small_n = n1;
    else
        small_n = n2;
    
    for(int i=0; i<small_n; i++){ // calculate score.
        if (( (rna1[i] +2 == rna2[i]) || (rna1[i] == rna2[i] + 2) ) == 1)
            score += 1;
        else
            score -=1;
    }
    return score;
}

int best_alignment(char const * rna1, size_t n1, char const * rna2, size_t n2, long * move){
    int best_score_first = INT_MIN; // initial score lowest value
    int best_score_second = INT_MIN;
    int best_score_total = INT_MIN;
    int test_score= INT_MIN;
    long move_first = 0;
    long move_second = 0;

    // first sequence shifted to the right (move is -)
    for(int i=0; i<=n2; i++){
        test_score = score_without_realigning(rna1,n1,rna2+i,n2-i);
        if(best_score_first < test_score){
            best_score_first = test_score;
            move_first = i; // when best score, cal move count
        }
    }
    // second sequence shifted to the right (move is +)
    for(int i=0; i<=n1; i++){
        test_score = score_without_realigning(rna1+i,n1-i,rna2,n2);
        if(best_score_second < test_score){
            best_score_second = test_score;
            move_second = i; // when best score, cal move count
        }
    }

    if(best_score_first <= best_score_second){ // select best score and move count
        best_score_total = best_score_second;
        *move = move_second;
    }
    else{
        best_score_total = best_score_first;
        *move = -move_first;
    }

    cout << "Best score: "<< best_score_total << endl;
    cout << "Best alignment: " << endl;

    if(*move <=0){ // when first squence move to right output form
        for(int i=0; i< *move *-2; i++){
            cout << " ";
        }
        for(int i=0; i< n1; i++){
        cout << static_cast<int>(rna1[i]) << " ";
        }
        cout << endl;
        for(int i=0; i< n2; i++){
            cout << static_cast<int>(rna2[i]) << " ";
        } 
    }
    else{ // when second squence move to right ouput form
        for(int i=0; i< n1; i++){
        cout << static_cast<int>(rna1[i]) << " ";
        }   
        cout << endl;
        for(int i=0; i< *move *2; i++){
            cout << " ";
        }
        for(int i=0; i< n2; i++){
        cout << static_cast<int>(rna2[i]) << " ";
        }
    }
    cout << endl;
    return best_score_total;
}


int main(){
    int arr1[SIZE]; // get first array
    size_t n1 = 0;
    for (int i=0; i<SIZE; i++){
        int num;
        cin >> num;
        if (num == -1) // end sequence
            break;
        if (!cin || num<-1 || num>3){ // bad input
            cout << "Bad input, Exit the program" << endl;
            return 1;
        }
        arr1[n1] = num; //store num
        n1++;
    }
    char rna1[n1]; // store value to rna form of char
    for (int i=0; i<n1; i++){
        rna1[i] = static_cast<char>(arr1[i]);
    }

    int arr2[SIZE]; // get second array
    size_t n2 = 0;
    for (int i=0; i<SIZE; i++){
        int num;
        cin >> num;
        if (num == -1)
            break;
        if (!cin || num<-1 || num>3){
            cout << "Bad input, Exit the program" << endl;
            return 1;
        }
        arr2[n2] = num;
        n2++;
    }
    char rna2[n2]; // store value to rna form of char
    for (int i=0; i<n2; i++){
        rna2[i] = static_cast<char>(arr2[i]);
    }

    long move = 0; //declare moving count
    best_alignment(rna1,n1,rna2,n2,&move); // cout all values.
    return 0;
}