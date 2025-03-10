#include <stdio.h>

int compute_total_salary();
int compute_tax(int amount);

void display_tax_returns(int tot_salary, int tax_target, int tax);
int salary;
int main() {
  int exemption = 10000000;
  int total_salary, tax_target, tax;
  printf("월 급여를 입력하세요 : ");
  scanf("%d", &salary);
  total_salary = compute_total_salary();
  tax_target = total_salary - exemption;
  tax = compute_tax(tax_target);
  display_tax_returns(total_salary, tax_target, tax);
  return 0;
}

int compute_total_salary() {
  int bonus_rate = 300;
  return (salary * 12) + (int)(salary * bonus_rate / 100);
}

int compute_tax(int amount) {
  int tax_rate;
  if(salary >= 5000000) {
    tax_rate = 5;
  } else if(salary >= 2500000) {
    tax_rate = 3;
  } else {
    tax_rate = 2;
  }
  return (int)(amount * tax_rate / 100);
}

void display_tax_returns(int tot_salary, int tax_target, int tax) {
  printf("\n >>> 세금 내역서 <<<\n");
  printf("연봉 : %8d\n", tot_salary);
  printf("세금 과세 대상 : %8d\n", tax_target);
  printf("최종 세금 부과액 : %8d\n", tax);
}
