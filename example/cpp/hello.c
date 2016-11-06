#include<stdio.h>
#include<string.h> // head file of c, not c++
#include<string> // head file of c++
#include "hello.h"

using namespace std;

CDLL void hi() {
	printf("hi!");
}

CDLL string sayHello() {
	string stringHi = "hello";
    return stringHi;
}

CDLL void sayHelloTo(char* who) {
	char hi[256] = "hello, ";
	char* word = strcat(hi, who);
	printf(word);
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

