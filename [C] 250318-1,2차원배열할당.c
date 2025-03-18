 #include <stdio.h>
#include <stdlib.h>

int main() {
    int n = 4;
    int* mal  =  (int*) malloc(n*sizeof(int));
    int* cal  =  (int*) calloc(n, sizeof(int));

    for (int i = 0; i < 4; i++) {
        printf("%d\n", *(mal+i));

    }

    for (int i = 0; i < 4; i++) {
        printf("%d\n", *(cal+i));

    }

    int r = 3, c=4;
    int** arr = (int **) calloc(r, sizeof(int*));
    for (int i = 0; i < r; i++) {
        arr[i] = (int*) calloc(c, sizeof(int));
    }

    for (int i = 0; i < r; i++) {
        for (int j = 0; j < c; j++) {
            arr[i][j] = i * c +j;
            printf("%d\t", arr[i][j]);
        }
        printf("\n");
    }

    for (int i = 0; i < r; i++) {
        free(arr[i]);
    }



    return 0;
}
