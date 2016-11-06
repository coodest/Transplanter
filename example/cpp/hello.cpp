#include<stdio.h>
#include<string.h> // head file of c, not c++
#include<string> // head file of c++
#include<vector>
#include<iostream>
#include "hello.h"

using namespace std;

CDLL void hi() {
	printf("hi!");
}

CDLL string sayHello() {
	string stringHi = "hello";
    return stringHi;
}

CDLL void sayHelloTo(string who) {
	string hi = "hello, ";
	hi.append(who);
	cout<<hi<<endl;
}

CDLL void figureShow(int a, float b) {
    printf("%d\n", a);
	printf("%f\n", b);
}

class Mathe {
    public:
        int add(int a, int b){
            return a+b;
        }
};

CDLL void add(){
    Mathe m;
    int result = m.add(1,1);
    printf("%d",result);
}

CDLL int num(){
    int a = 10;
    vector<int> v;
    v.push_back(a);
    return v[0];
}

