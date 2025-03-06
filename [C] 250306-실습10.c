#include <stdio.h>

int main()
{
    int menu, r;
	double pi = 3.14;

    printf("========================\n");
    printf("1. 원의 둘레 구하기\n");
    printf("2. 원의 넓이 구하기\n");
    printf("3. 구의 부피 구하기\n");
    printf("4. 그만두기\n");
    printf("========================\n");
            
    printf("원하는 내용은은? ");
    scanf_s("%d", &menu);

    printf(">> 반지름은? ");
    scanf_s("%d", &r);

    switch (menu) {
    case 1: printf(" >> 반지름이 %d인 원의 둘레는 %.2lf\n", r, 2 * r * pi); break;
    case 2: printf(" >> 반지름이 %d인 원의 넓이는 %.2lf\n", r, r * r * pi);  break;
    case 3: printf(" >> 반지름이 %d인 구의 부피는 %.2lf\n", r, r * r * r * pi * (4 / 3));  break;
    }

    printf(" >> 결과를 확인했으면 아무키나 누르세요.");

    //
    int n, sum = 0;
    printf("정수 n을 입력 : ");
    scanf_s("%d", &n);
    for (int i = 1; i <= n; i++) {
        if(i%2==0)sum += i;
    }
    printf("1부터 %d까지의 짝수의 합은 %d입니다.\n", n, sum);

}
