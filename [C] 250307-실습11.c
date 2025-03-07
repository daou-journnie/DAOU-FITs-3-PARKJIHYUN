#include <stdio.h>

void calculator(int a, int b);
void timeTable(int n);


int main() {
    // 1.
    int a, b;
    printf("가감승제를 원하는 두 수를 입력하세요 : ");
    scanf("%d %d", &a, &b);
    calculator(a, b);

    // 2.
    int n;
    printf("출력을 원하는 단은? : ");
    scanf_s("%d", &n);
    timeTable(n);


}

void calculator(int a, int b) {
    printf("%d + %d = %d\n", a, b, a+b);
    printf("%d - %d = %d\n", a, b, a-b);
    printf("%d * %d = %d\n", a, b, a*b);
    printf("%d / %d = %d\n", a, b, a/b);
}

void timeTable(int n) {
    for (int i = 0; i <= 9; i++) {
        printf("%d x %d = %d\n", n, i, n*i);
    }

}
