#include "knight.h"
using namespace std;

Square::Square(int a, int b) : i(a),j(b) {}

int Square::geti() const { return i; }
int Square::getj() const { return j; }

void Square::seti(int a) { i = a; }
void Square::setj(int b) { j = b; }


bool operator==(const Square &s, const Square &r){
    return s.geti() == r.geti() && s.getj() == r.getj();
}

Square move(Square s, int d){
    switch(d)
    {
        case 1:
            s.seti(s.geti()+2);
            s.setj(s.getj()+1);
            return s;
        case 2:
            s.seti(s.geti()+1);
            s.setj(s.getj()+2);
            return s;
        
        case 3:
            s.seti(s.geti()-1);
            s.setj(s.getj()+2);
            return s;
        case 4:
            s.seti(s.geti()-2);
            s.setj(s.getj()+1);
            return s;
        case 5:
            s.seti(s.geti()-2);
            s.setj(s.getj()-1);
            return s;
        case 6:
            s.seti(s.geti()-1);
            s.setj(s.getj()-2);
            return s;
        case 7:
            s.seti(s.geti()+1);
            s.setj(s.getj()-2);
            return s;
        case 8:
            s.seti(s.geti()+2);
            s.setj(s.getj()-1);
            return s;
        default:
            return s;
    }
}
Square moveBack(Square s, int d){
    switch(d)
    {
        case 1:
            s.seti(s.geti()-2);
            s.setj(s.getj()-1);
            return s;
        case 2:
            s.seti(s.geti()-1);
            s.setj(s.getj()-2);
            return s;
        
        case 3:
            s.seti(s.geti()+1);
            s.setj(s.getj()-2);
            return s;
        case 4:
            s.seti(s.geti()+2);
            s.setj(s.getj()-1);
            return s;
        case 5:
            s.seti(s.geti()+2);
            s.setj(s.getj()+1);
            return s;
        case 6:
            s.seti(s.geti()+1);
            s.setj(s.getj()+2);
            return s;
        case 7:
            s.seti(s.geti()-1);
            s.setj(s.getj()+2);
            return s;
        case 8:
            s.seti(s.geti()-2);
            s.setj(s.getj()+1);
            return s;
        default:
            return s;
    }
}


Board::Board(int u, int v){ // row = u, column = v
    m = u;
    n = v;
    M = new int *[u];
    for (int y=0; y<u; y++){
        M[y] = new int[v];
    }
    for(int y=0; y<u; y++){
        for(int x=0; x<v; x++){
            M[y][x] = 0;
        }
    }
}

Board::Board(string filename){
    ifstream input_file(filename);
    int num;
    input_file >> num;
    m = num;
    input_file >> num;
    n = num;

    M = new int *[m];
    for (int y=0; y<m; y++){
        M[y] = new int[n];
    }
    for(int y=0; y<m; y++){
        for(int x=0; x<n; x++){
            M[y][x] = 0;
        }
    }

    for(int i=0; i<m; i++){
        for(int j=0; j<n; j++){
            input_file >> num;
            M[i][j] = num;
        }
    }
}

Board::~Board(){
    for(int i=0; i<m; i++){
        delete [] M[i];
    }
    delete [] M;
}

int Board::getm() const { return m; }
int Board::getn() const { return n; }

int Board::get(Square s) const{
    if(!inRange(s))
        throw runtime_error("can't get because of wrong range(get error)");
    int x = s.geti();
    int y = s.getj();
    return M[x][y];
}

void Board::set(Square s, int v){
    if(!inRange(s))
        throw runtime_error("can't set because of wrong range(set error)");
    int x = s.geti();
    int y = s.getj();
    M[x][y] = v;
}

bool Board::inRange(const Square &s) const{
    int x = s.geti();
    int y = s.getj();
    return (0<=x && x< m) && (0<= y && y < n);
}

int Board::nEmpty() const{
    int res=0;
    for(int i=0; i<m; i++){
        for(int j=0; j<n; j++){
            if(M[i][j] == 0)    res++;
        }
    }
    return res;
}

Square Board::emptySquare() const{
    Square s(0,0);
    for(int i=0; i<m; i++){
        for(int j=0; j<n; j++){
            if(M[i][j] == 0){
                s.seti(i);
                s.setj(j);
                return s;
            }
        }
    }
    throw runtime_error("there are no empty square");
    return s;
}

ostream& operator<<(ostream & out, const Board & B){
    int m = B.getm();
    int n = B.getn();
    cout << m << '\n';
    cout << n << '\n';
    for(int i=0; i<m; i++){
        for(int j=0; j<n-1; j++){
            cout << B.M[i][j] <<" ";
        }
        cout << B.M[i][n-1] << '\n';
    }
    return out;
}

bool knightTour(Board & B){
    ArrayStack<int> S;
    Square pos = B.emptySquare();
    S.push(0);
    B.set(pos,1);
    while(true){
        int d = S.top();
        if(d<8){
            S.pop();
            d++;
            S.push(d);
            Square s = move(pos,d); // s != pos
            if(B.inRange(s)){
                if(B.get(s) == 0){ // advance
                    pos = move(pos,d); // s == d
                    S.push(0);
                    B.set(s, S.size());
                    if(B.nEmpty() == 0){

                        for(int i=1; i<=8; i++){
                            if(B.inRange(move(pos,i))){
                                if(B.get(move(pos,i))==1) return true; // backtracking end
                            }
                        }

                    }
                }
            }
        }
        else{ // moving back
            S.pop();
            if(S.empty())   return false; // no solution
            B.set(pos,0);
            pos = moveBack(pos,S.top());
        }
    }
}