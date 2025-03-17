#include <stdio.h>

typedef struct student {
    char name[20];
    char sex;
    int stid;
    int sub1;
    int sub2;
    int sub3;
    double avg;
} ST;

int input(ST* st);

int main() {
    ST st1 = {"kdhong", 'm', 1508001, 0, 0, 0, 0.0};
    input(&st1);
    printf("main() 함수에서 출력된 세 과목의 점수는 %d %d %d입니다\n", st1.sub1, st1.sub2, st1.sub3);
}

int input(ST* st) {
    printf("%s 학생의 3과목 성적을 입력하세요(공란으로 구분) : ", st -> name );
    scanf("%d %d %d", &st -> sub1, &st -> sub2, &st -> sub3);
    printf("input() 함수에서 입력된 값은 %d %d %d입니다\n", st->sub1, st->sub2, st->sub3);
}
