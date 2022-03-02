// 
// .cpp file has functions. and have #include "func.h" 
#include "func.h"
#include <iostream>
#include <cmath>
#include <algorithm>
#include <cstring>
#include <string>
using namespace std;

shape_type determine_shape_type(char a)
{ // for switch case, return shape's shape_type
    if (a == 's')
        return shape_type::square;
    if (a == 'r')
        return shape_type::rectangle;
    if (a == 'c')
        return shape_type::circle;
}

size_t read_in_shapes(shape *output_array)
{ // read in shapes and store information
    size_t n = 0;
    do
    {
        cin >> output_array[n].name >> output_array[n].x >> output_array[n].y;
        output_array[n].type = determine_shape_type(output_array[n].name);
        switch (output_array[n].type)
        {
        case shape_type::square:
            cin >> output_array[n].value.s_l;
            break;
        case shape_type::rectangle:
            cin >> output_array[n].value.r_xy.r_x >> output_array[n].value.r_xy.r_y;
            break;
        case shape_type::circle:
            cin >> output_array[n].value.c_l;
            break;
        }

        n++;
    } while (!cin.eof() && cin); // if input end, then stop
    return n;
}

double dist_p(double x1, double y1, double x2, double y2) // distance of point with point
{
    return sqrt(pow(x1 - x2, 2) + pow(y1 - y2, 2));
}
double dist(shape p1, shape p2) // distance of shape and shape
{
    return sqrt(pow(p1.x - p2.x, 2) + pow(p1.y - p2.y, 2));
}

bool overlap(shape const &s1, shape const &s2) // compare two shapes if overlap. if overlap return true no overlap return false. consider all case.
{
    if (s1.type == shape_type::circle && s2.type == shape_type::circle)
    {
        if (dist(s1, s2) > s1.value.c_l + s2.value.c_l)
            return false;
        else
            return true;
    }
    else if (s1.type == shape_type::rectangle && s2.type == shape_type::rectangle)
    {
        if (s1.x > s2.x + s2.value.r_xy.r_x || s1.x + s1.value.r_xy.r_x < s2.x || s1.y + s1.value.r_xy.r_y < s2.y || s1.y > s2.y + s2.value.r_xy.r_y)
        {
            return false;
        }
        else
            return true;
    }
    else if (s1.type == shape_type::square && s2.type == shape_type::square)
    {
        if (s1.x > s2.x + s2.value.s_l || s1.x + s1.value.s_l < s2.x || s1.y + s1.value.s_l < s2.y || s1.y > s2.y + s2.value.s_l)
        {
            return false;
        }
        else
            return true;
    }
    else if (s1.type == shape_type::circle && s2.type == shape_type::rectangle)
    { // find minimum value circle's center and rectangle's each vertices
        double arr[4] = {dist_p(s1.x, s1.y, s2.x, s2.y), dist_p(s1.x, s1.y, s2.x + s2.value.r_xy.r_x, s2.y), dist_p(s1.x, s1.y, s2.x, s2.y + s2.value.r_xy.r_y), dist_p(s1.x, s1.y, s2.x + s2.value.r_xy.r_x, s2.y + s2.value.r_xy.r_y)};
        sort(arr, arr + 4);
        double c = arr[0];
        if (c > s1.value.c_l)
            return false;
        else
            return true;
    }
    else if (s1.type == shape_type::rectangle && s2.type == shape_type::circle)
    { // find minimum value circle's center and rectangle's each vertices
        double arr[4] = {dist_p(s1.x, s1.y, s2.x, s2.y), dist_p(s1.x + s1.value.r_xy.r_x, s1.y, s2.x, s2.y), dist_p(s1.x, s1.y + s1.value.r_xy.r_y, s2.x, s2.y), dist_p(s1.x + s1.value.r_xy.r_x, s1.y + s2.value.r_xy.r_y, s2.x, s2.y)};
        sort(arr, arr + 4);
        double c = arr[0];
        if (c > s2.value.c_l)
            return false;
        else
            return true;
    }
    else if (s1.type == shape_type::circle && s2.type == shape_type::square)
    { // find minimum value circle's center and square's each vertices
        double arr[4] = {dist_p(s1.x, s1.y, s2.x, s2.y), dist_p(s1.x, s1.y, s2.x + s2.value.s_l, s2.y), dist_p(s1.x, s1.y, s2.x, s2.y + s2.value.s_l), dist_p(s1.x, s1.y, s2.x + s2.value.s_l, s2.y + s2.value.s_l)};
        sort(arr, arr + 4);
        double c = arr[0];
        if (c > s1.value.c_l)
            return false;
        else
            return true;
    }
    else if (s1.type == shape_type::square && s2.type == shape_type::circle)
    { // find minimum value circle's center and square's each vertices
        double arr[4] = {dist_p(s1.x, s1.y, s2.x, s2.y), dist_p(s1.x + s1.value.s_l, s1.y, s2.x, s2.y), dist_p(s1.x, s1.y + s1.value.s_l, s2.x, s2.y), dist_p(s1.x + s1.value.s_l, s1.y + s1.value.s_l, s2.x, s2.y)};
        sort(arr, arr + 4);
        double c = arr[0];
        if (c > s2.value.c_l)
            return false;
        else
            return true;
    }
    else if (s1.type == shape_type::rectangle && s2.type == shape_type::square)
    {
        if (s1.x > s2.x + s2.value.s_l || s1.x + s1.value.r_xy.r_x < s2.x || s1.y + s1.value.r_xy.r_y < s2.y || s1.y > s2.y + s2.value.s_l)
            return false;
        else
            return true;
    }
    else if (s1.type == shape_type::square && s2.type == shape_type::rectangle)
    {
        if (s1.x > s2.x + s2.value.r_xy.r_x || s1.x + s1.value.s_l < s2.x || s1.y + s1.value.s_l < s2.y || s1.y > s2.y + s2.value.r_xy.r_y)
            return false;
        else
            return true;
    }
}

bool overlap(shape const *shapes, size_t num)
{ // return true if the state of cordinate system if overaping. no overap then return false.
    int sum = 0;
    for (int i = 0; i < num - 1; i++)
    {
        for (int j = i + 1; j < num; j++)
        {
            sum += overlap(shapes[i], shapes[j]);
        }
    }
    if (sum > 0)
        return true;
    else
        return false;
}

void expand(shape const *shapes, size_t num, shape *outputarray, double factor)
{ // make bigger and smaller outputarray's shape's length aoubt factor
    for (int i = 0; i < num; i++)
    {

        if (shapes[i].name == 's')
            outputarray[i].value.s_l = shapes[i].value.s_l * factor;
        if (shapes[i].name == 'r')
            outputarray[i].value.r_xy.r_x = shapes[i].value.r_xy.r_x * factor;
        outputarray[i].value.r_xy.r_y = shapes[i].value.r_xy.r_y * factor;
        if (shapes[i].name == 'c')
            outputarray[i].value.c_l = shapes[i].value.c_l * factor;
    }
}

double ideal_expansion_factor(shape const *shapes, size_t num)
{ // calculate ideal expansion factor
    double start = 0;
    double end = 100;
    double midpoint;

    shape result_shape[10];
    for (int i = 0; i < num; i++)
    {
        result_shape[i].x = shapes[i].x;
        result_shape[i].y = shapes[i].y;
        result_shape[i].name = shapes[i].name;
        result_shape[i].type = shapes[i].type;
    }

    for (int i = 0; i < 40; i++)
    { // using biscecting
        midpoint = (start + end) / 2;
        expand(shapes, num, result_shape, midpoint);
        if (overlap(result_shape, num))
            end = midpoint;
        else
            start = midpoint;
    }
    return midpoint;
}

void print_result(shape *shapes, size_t n, double factor)
{ // print_result
    for (size_t i = 0; i < n; i++)
    {
        cout << shapes[i].name << " " << shapes[i].x << " " << shapes[i].y << " ";
        switch (shapes[i].type)
        {
        case shape_type::square:
            cout << shapes[i].value.s_l * factor << '\n';
            break;
        case shape_type::rectangle:
            cout << shapes[i].value.r_xy.r_x * factor << " " << shapes[i].value.r_xy.r_y * factor << '\n';
            break;
        case shape_type::circle:
            cout << shapes[i].value.c_l * factor << '\n';
            break;
        }
    }
}
