#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <malloc.h>

int main() {
	int* p1 = malloc(sizeof(int));
	*p1 = 3;

	int* p2 = realloc(p1, sizeof(int) * 4);

	printf("p1: %p, %d\r\n", p1, *p1);
	printf("p2: %p, %d", p2, *p2);
	//free(p1); // Runtime Error
	free(p2);
	return 0;
}
