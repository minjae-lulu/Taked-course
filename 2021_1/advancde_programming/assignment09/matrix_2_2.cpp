//  this is matrix_2_2.cpp file
#include "matrix_2_2.h"
#include <iomanip> // for layout printing (setw)
using namespace std;


ostream &operator<<(ostream &out, matrix_2_2<int> const &o){ // for int printing
    out << "[" << setw(8) << o.x[0][0] << setw(8)  << o.x[0][1] << " ]" << '\n';
    out << "[" << setw(8) << o.x[1][0] << setw(8)  << o.x[1][1] << " ]";
    return out;
}

ostream &operator<<(ostream &out, matrix_2_2<short> const &o){ // for short printing
    out << "[" << setw(8) << o.x[0][0] << setw(8)  << o.x[0][1] << " ]" << '\n';
    out << "[" << setw(8) << o.x[1][0] << setw(8)  << o.x[1][1] << " ]";
    return out;
}

ostream &operator<<(ostream &out, matrix_2_2<long> const &o){ // for long printing
    out << "[" << setw(8) << o.x[0][0] << setw(8)  << o.x[0][1] << " ]" << '\n';
    out << "[" << setw(8) << o.x[1][0] << setw(8)  << o.x[1][1] << " ]";
    return out;
}

ostream &operator<<(ostream &out, matrix_2_2<double> const &o){ // for double printing
    out << fixed; 
    out.precision(1); // floating-point numbers, 1 digit
    out << "[" << setw(8) << o.x[0][0] << setw(8) << o.x[0][1] << " ]" << '\n';
    out << "[" << setw(8) << o.x[1][0] << setw(8) << o.x[1][1] << " ]";
    return out;
}


ostream &operator<<(ostream &out, matrix_2_2<float> const &o){ // for float printing
    out << fixed;
    out.precision(1);
    out << "[" << setw(8) << o.x[0][0] << setw(8) << o.x[0][1] << " ]" << '\n';
    out << "[" << setw(8) << o.x[1][0] << setw(8) << o.x[1][1] << " ]";
    return out;
}

template <typename T> // for m1[0][1] = -1.5;
T *matrix_2_2<T>::operator[](size_t i){
   return x[i];
}

template <typename T> // by using get_value make = operator
matrix_2_2<T> &matrix_2_2<T>::operator=(matrix_2_2<T> const &other){
    T new_x[2][2];
    new_x[0][0] = other.get_value(0,0); // in order to access to private variable so using get_value fun
    new_x[0][1] = other.get_value(0,1);
    new_x[1][0] = other.get_value(1,0);
    new_x[1][1] = other.get_value(1,1);
    x[0][0] = new_x[0][0];
    x[0][1] = new_x[0][1];
    x[1][0] = new_x[1][0];
    x[1][1] = new_x[1][1];
    return *this;
}

template <typename T> // by using get_value make += operator
matrix_2_2<T> &matrix_2_2<T>::operator+=(matrix_2_2<T> const &other)
{
    T new_x[2][2];
    new_x[0][0] = this->x[0][0] + other.get_value(0, 0);
    new_x[0][1] = this->x[0][1] + other.get_value(0, 1);
    new_x[1][0] = this->x[1][0] + other.get_value(1, 0);
    new_x[1][1] = this->x[1][1] + other.get_value(1, 1);
    x[0][0] = new_x[0][0];
    x[0][1] = new_x[0][1];
    x[1][0] = new_x[1][0];
    x[1][1] = new_x[1][1];
    return *this;
}

template <typename T>
matrix_2_2<T> matrix_2_2<T>::operator+(matrix_2_2<T> const &other) const{
    matrix_2_2 r = *this;
    return r += other;
}

template <typename T> // by using get_value make *= operator
matrix_2_2<T> &matrix_2_2<T>::operator*=(matrix_2_2<T> const &other){
   T new_x[2][2];
   new_x[0][0] = (this->x[0][0] * other.get_value(0,0)) + (this->x[0][1] * other.get_value(1,0));
   new_x[0][1] = (this->x[0][0] * other.get_value(0,1)) + (this->x[0][1] * other.get_value(1,1));
   new_x[1][0] = (this->x[1][0] * other.get_value(0,0)) + (this->x[1][1] * other.get_value(1,0));
   new_x[1][1] = (this->x[1][0] * other.get_value(0,1)) + (this->x[1][1] * other.get_value(1,1));
   x[0][0] = new_x[0][0];
   x[0][1] = new_x[0][1];
   x[1][0] = new_x[1][0];
   x[1][1] = new_x[1][1];
   return *this;
}

template <typename T> 
matrix_2_2<T> matrix_2_2<T>::operator*(matrix_2_2<T> const &other) const{
    matrix_2_2 r = *this;
    return r *= other;
}

template <typename T> // we only need scalar, so simpler than previous operator
matrix_2_2<T> &matrix_2_2<T>::operator/=(T const &other){
   T new_x[2][2];
   new_x[0][0] = this->x[0][0] / other;
   new_x[0][1] = this->x[0][1] / other;
   new_x[1][0] = this->x[1][0] / other;
   new_x[1][1] = this->x[1][1] / other;
   x[0][0] = new_x[0][0];
   x[0][1] = new_x[0][1];
   x[1][0] = new_x[1][0];
   x[1][1] = new_x[1][1];
   return *this;
}

template <typename T>
matrix_2_2<T> matrix_2_2<T>::operator/(T const &other) const{
    matrix_2_2 r = *this;
    return r /= other;
}


template class matrix_2_2<int>;
template class matrix_2_2<double>;
template class matrix_2_2<short>;
template class matrix_2_2<long>;
template class matrix_2_2<float>;