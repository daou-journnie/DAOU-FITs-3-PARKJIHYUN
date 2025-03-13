void dp1();

int main() {
    dp1();

}

void dp1() {
    int arr[5][5];

    int M, N;
    printf("행과 열의 수를 입력하세요 ");
    scanf("%d %d", &M, &N);

    for (int m = 0; m < M; m++) {
        for (int n = 0; n  < N; n++) {
            *(*(arr+m)+n) = 10*m + n;
            printf("%2d ",*(*(arr+m)+n));
        }
        printf("\n");
    }

    int row;
    printf("삭제하려는 행의 인덱스 번호를 입력하세요 ");
    scanf("%d", &row);

    for (int m = 0; m < M; m++) {
        if (m == row) {continue;}
        for (int n = 0; n  < N; n++) {
            printf("%2d ",*(*(arr+m)+n));
        }
        printf("\n");
    }

}
