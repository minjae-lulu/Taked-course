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

Board::Board(string filename){}

Board::~Board(){
    for(int i=0; i<m; i++){
        delete [] M[i];
    }
    delete [] M;
}

int Board::getm() const { return m; }
int Board::getn() const { return n; }

int Board::get(Square s) const{
    int x = s.geti();
    int y = s.getj();
    return M[x][y];
}

void Board::set(Square s, int v){
    int x = s.geti();
    int y = s.getj();
    M[x][y] = v;
}

bool Board::inRange(const Square &s) const{
    int x = s.geti();
    int y = s.getj();
    return 0<=x && x< this->getm() && 0<= y && y<this->getn();
}

ostream& operator<<(ostream & out, const Board & B){

}

// class Board {
// public:
//   Board(int u, int v);  // creates an empty mxn board (i.e. M[i][j]=0 for all i,j)
//   Board(string filename);  // creates board from input file
//   ~Board();             // Destructor
//   int getm() const;                 // returns m
//   int getn() const;                 // returns n
//   int get(Square s) const;          // gets M[s.x][s.y]
//   void set(Square s,int v);         // sets M[s.x][s.y]=v
//   bool inRange(const Square &s) const;       // checks whether s is a square in B
//   int nEmpty() const;                  // number of empty squares
//   Square emptySquare() const;           // returns an empty square of B
//   friend ostream& operator<<(ostream& out, const Board& B);
// private:
//   int m;        // number of rows
//   int n;        // number of columns 
//   int** M;      // matrix recording the m*n squares of the board
// };

// ostream& operator<<(ostream & out, const Board & B);  // prints board B

// bool knightTour(Board & B);
//   // Returns true if there is a knight tour, and false otherwise.
//   // If there is a knight tour, board B is updated and contains
//   // this knight tour