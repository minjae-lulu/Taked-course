//it has prototype function and struct.
#ifndef FUNC_H
#define FUNC_H

#include <cstddef> // for a definition of size_t
enum class shape_type{ square, rectangle, circle };

struct shape{ 
    char name;
    double x, y;
    shape_type type;
    union shape_value{
        double s_l;
        double c_l;
        struct r_xy{
            double r_x;
            double r_y;
        } r_xy;
    } value;
};

shape_type determine_shape_type(char );
size_t read_in_shapes(shape *);
double dist_p(double, double, double, double);
double dist(shape, shape);
bool overlap(shape const &, shape const &);
bool overlap(shape const *, size_t);
void expand(shape const *, size_t , shape *, double);
double ideal_expansion_factor(shape const *, size_t);
void print_result(shape *, size_t, double);

#endif