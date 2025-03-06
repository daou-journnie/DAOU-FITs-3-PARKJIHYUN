#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>

int practice06()
{
    // 1. 성별, 나이, 키 입력
    char gender;
    int age;
    double height;

    printf("성별은? (남자라면 M, 여자라면 F): ");
    scanf_s("%c", &gender, 1);
    //gender = getchar();
    //putchar(gender);
    getchar(); // 개행 문자 제거

    printf("나이는? ");
    scanf_s("%d", &age);

    printf("키는? ");
    scanf_s("%lf", &height);

    printf("\n============\n");
    printf("성별: %c\n", gender);
    printf("나이: %3d\n", age);
    printf("키: %.1lfcm\n", height);

    // 2. 이름 입력
    char name[10]; // 문자열 : 한 글자 이상 문자열 "a\0" char name[10]
    printf("이름은? ");
    scanf_s("%s", name, (unsigned)sizeof(name));
    printf("입력한 이름: %s\n", name);

    // 3
    // 정수 두 개 입력
    int intA, intB;
    printf("정수 두 개 입력: ");
    scanf_s("%d %d",&intA, &intB);
    printf("%d %d",intA, intB);


    // 정수 세 개 입력
    int intC, intD, intE;
    printf("정수 세 개 입력: ");
    scanf_s("%d %d %d", &intC, &intD, &intE);
    printf("%d %d %d", intC, intD, intE);


    return 0;
}
