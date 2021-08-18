//20161190 leeminjae this is matrix_2_2.h file
#ifndef MATRIX_2_2_h
#define MATRIX_2_2_h
#include <iostream>
#include <iomanip>
using namespace std;


template <typename T>
class matrix_2_2
{
private:
    T x[2][2]; // make two demension array
public:
    
    matrix_2_2(T a) : x{{a, 0}, {0, a}} {} // initilize by diagonal matrix

    T get_value(int i, int j) const { return x[i][j]; } // get private value

    friend ostream &operator<<(ostream &, matrix_2_2<int> const &);
    friend ostream &operator<<(ostream &, matrix_2_2<short> const &);
    friend ostream &operator<<(ostream &, matrix_2_2<long> const &);
    friend ostream &operator<<(ostream &, matrix_2_2<double> const &);
    friend ostream &operator<<(ostream &, matrix_2_2<float> const &);

    T *operator[](size_t); 

    matrix_2_2<T> &operator=(matrix_2_2<T> const &);

    matrix_2_2<T> &operator+=(matrix_2_2<T> const &); // for operate + and +=
    matrix_2_2<T> operator+(matrix_2_2<T> const &) const;

    matrix_2_2<T> &operator*=(matrix_2_2<T> const &); // for operate * and *=
    matrix_2_2<T> operator*(matrix_2_2<T> const &)const;
    
    matrix_2_2<T> &operator/=(T const &); // for operate / /= (it is only for scalar so don't need matrix_2_2<T >)
    matrix_2_2<T> operator/(T const &)const;

};

extern template class matrix_2_2<int>;
extern template class matrix_2_2<double>;
extern template class matrix_2_2<short>;
extern template class matrix_2_2<long>;
extern template class matrix_2_2<float>;


#endif