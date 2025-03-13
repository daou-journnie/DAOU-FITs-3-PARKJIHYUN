#include <stdio.h>

void dp1();

int main() {
    dp2();

}

void getSum(int** arr) {
    int sum = 0;
    for (int m = 0; m < 3; m++) {
        for (int n = 0; n  < 3; n++) {
            sum += arr[m][n];
        }
    }
    printf("배열의 합은 %d", sum);


}

void getMax(int** arr) {
    int max = arr[0][0];
    for (int m = 0; m < 3; m++) {
        for (int n = 0; n  < 3; n++) {
            if (max < arr[m][n]) max = arr[m][n];
        }
    }
    printf("배열의 최대값은 %d", max);

}

void getMin(int** arr) {
    int min = arr[0][0];
    for (int m = 0; m < 3; m++) {
        for (int n = 0; n  < 3; n++) {
            if (min > arr[m][n]) min = arr[m][n];
        }
    }
    printf("배열의 최소값은 %d", min);
}

void getSqaredMatrix(int** arr) {
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j <3; j++) {
            printf("%d ",arr[i][j]);
        }
        printf("\n");
    }
}

typedef void (*operation)(int**);
void dp2() {
    int array[3][3] = {
        {0, 1, 2},
        {3, 4, 5},
        {6, 7, 8}
    };
    int* arr[3] = {array[0], array[1], array[2]};

    operation operations[4] = {getSum, getMax, getMin, getSqaredMatrix};
    int menu;
    printf("연산 방법을 선택하기\n(0: 합, 1: 최대값, 2: 최소값, 3: 제곱): ");
    scanf("%d", &menu);
    operations[menu](arr);

}


