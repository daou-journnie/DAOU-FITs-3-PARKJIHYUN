#include <stdio.h>

int main() {
    int sum = 10, score[] = {99, 80, 91, 78, 85};
    int *psum, score1, *pscore1, *pscore2;
    psum = &sum; // sum의 주소
    pscore1 = score;
    pscore2 = score+2;


    printf("변수 sum의 값 : %d\n", sum); // 10
    printf("포인터 변수 psum의 값: %d\n", psum); // sum의 주소값
    printf("포인터 변수 *psum의 값: %d\n", *psum); // 10
    printf("포인터 변수 *psum+1의 값: %d\n", *psum+1); // 11
    printf("포인터 변수 ++*psum의 값: %d\n", ++*psum); // 11
    printf("포인터 변수 *(psum+1)의 값: %d\n", *(psum+1)); // 11
    printf("포인터 변수 *++psum의 값: %d\n", *++psum); // ??
    printf("============================================\n");
    printf("&score : %d &score[0] : %d pscore1 : %d\n", &score, &score[0], pscore1);
    printf("포인터 변수 *pscore1의 값: %d\n", *pscore1); // score[0]의 값 = 99
    printf("포인터 변수 *pscore1+1의 값: %d\n", *pscore1+1); // 99+1=100
    printf("포인터 변수 ++*pscore1의 값: %d\n", ++*pscore1); // 1+99=100
    printf("포인터 변수 *(pscore1+1)의 값: %d\n", *(pscore1+1)); // score[1]
    printf("포인터 변수 *++pscore1의 값: %d\n", *++pscore1); // score[1] = 80
    printf("============================================\n");
    printf("&score : %d &score[2] : %d pscore2 : %d\n", &score, &score[2], pscore2);
    printf("포인터 변수 *pscore2의 값: %d\n", *pscore2); // score[2]의 값 = 91
    printf("포인터 변수 *pscore2+1의 값: %d\n", *pscore2+1); // 91+1=92
    printf("포인터 변수 ++*pscore2의 값: %d\n", ++*pscore2); // 1+91=92
    printf("포인터 변수 *(pscore2+1)의 값: %d\n", *(pscore2+1)); // score[3]
    printf("포인터 변수 *++pscore2의 값: %d\n", *++pscore2); // score[3] = 78





}
