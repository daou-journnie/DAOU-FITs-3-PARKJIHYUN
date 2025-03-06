#include <stdio.h>

int main()
{
    int age;
    char gender;
    double height;

    printf("성별은? (남자라면 M, 여자라면 F): ");
    scanf_s("%c", &gender, 1);
    //gender = getchar();
    //putchar(gender);
    // getchar(); // 개행 문자 제거

    printf("나이는? ");
    scanf_s("%d", &age);

    printf("키는? ");
    scanf_s("%lf", &height);

    printf("\n============\n");
    printf("성별: %c\n", gender);
    printf("나이: %3d\n", age);
    printf("키: %.1lfcm\n", height);

    return 0;
}
