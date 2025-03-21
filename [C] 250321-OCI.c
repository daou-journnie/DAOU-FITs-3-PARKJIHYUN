// oracle_db.c
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <oci.h>

#define USERNAME "C##CPractice"
#define PASSWORD "1234"
#define DBNAME   "localhost:1521/xe"

// Oracle handles
OCIEnv* envhp;
OCIError* errhp;
OCISvcCtx* svchp;
OCIServer* srvhp;
OCISession* usrhp;
OCIStmt* stmt;
OCIDefine* def1 = NULL, *def2 = NULL, *def3 = NULL, *def4 = NULL;

void check_error(OCIError* errhp) {
    text errbuf[512];
    sb4 errcode = 0;
    OCIErrorGet((dvoid*)errhp, 1, NULL, &errcode, errbuf, sizeof(errbuf), OCI_HTYPE_ERROR);
    printf("[❌ OCI ERROR] %s\n", errbuf);
}

int connect_to_db() {
    if (OCIEnvCreate(&envhp, OCI_DEFAULT, NULL, NULL, NULL, NULL, 0, NULL) != OCI_SUCCESS) {
        printf("OCIEnvCreate failed\n");
        return -1;
    }

    OCIHandleAlloc(envhp, (dvoid**)&errhp, OCI_HTYPE_ERROR, 0, NULL);
    OCIHandleAlloc(envhp, (dvoid**)&srvhp, OCI_HTYPE_SERVER, 0, NULL);
    OCIServerAttach(srvhp, errhp, (text*)DBNAME, strlen(DBNAME), OCI_DEFAULT);

    OCIHandleAlloc(envhp, (dvoid**)&svchp, OCI_HTYPE_SVCCTX, 0, NULL);
    OCIAttrSet(svchp, OCI_HTYPE_SVCCTX, srvhp, 0, OCI_ATTR_SERVER, errhp);

    OCIHandleAlloc(envhp, (dvoid**)&usrhp, OCI_HTYPE_SESSION, 0, NULL);
    OCIAttrSet(usrhp, OCI_HTYPE_SESSION, (void*)USERNAME, strlen(USERNAME), OCI_ATTR_USERNAME, errhp);
    OCIAttrSet(usrhp, OCI_HTYPE_SESSION, (void*)PASSWORD, strlen(PASSWORD), OCI_ATTR_PASSWORD, errhp);

    if (OCISessionBegin(svchp, errhp, usrhp, OCI_CRED_RDBMS, OCI_DEFAULT) != OCI_SUCCESS) {
        check_error(errhp);
        return -1;
    }
    OCIAttrSet(svchp, OCI_HTYPE_SVCCTX, usrhp, 0, OCI_ATTR_SESSION, errhp);
    printf("\n✅ Connected to Oracle Database successfully!\n\n");
    return 0;
}

void insert_customer(int id, const char* name, const char* phone, const char* birth_date) {
    char* sql = "INSERT INTO test_customer (CUSTOMER_ID, NAME, PHONE, BIRTH_DATE) VALUES (:1, :2, :3, :4)";
    OCIHandleAlloc(envhp, (void**)&stmt, OCI_HTYPE_STMT, 0, NULL);
    OCIStmtPrepare(stmt, errhp, (text*)sql, strlen(sql), OCI_NTV_SYNTAX, OCI_DEFAULT);

    OCIBind* bnd1 = NULL, *bnd2 = NULL, *bnd3 = NULL, *bnd4 = NULL;
    OCIBindByPos(stmt, &bnd1, errhp, 1, &id, sizeof(id), SQLT_INT, NULL, NULL, NULL, 0, NULL, OCI_DEFAULT);
    OCIBindByPos(stmt, &bnd2, errhp, 2, (void*)name, strlen(name)+1, SQLT_STR, NULL, NULL, NULL, 0, NULL, OCI_DEFAULT);
    OCIBindByPos(stmt, &bnd3, errhp, 3, (void*)phone, strlen(phone)+1, SQLT_STR, NULL, NULL, NULL, 0, NULL, OCI_DEFAULT);
    OCIBindByPos(stmt, &bnd4, errhp, 4, (void*)birth_date, strlen(birth_date)+1, SQLT_STR, NULL, NULL, NULL, 0, NULL, OCI_DEFAULT);

    if (OCIStmtExecute(svchp, stmt, errhp, 1, 0, NULL, NULL, OCI_COMMIT_ON_SUCCESS) == OCI_SUCCESS)
        printf("✅ 고객 추가 완료\n");
    else
        check_error(errhp);
}

void update_customer_name(int id, const char* new_name) {
    char* sql = "UPDATE test_customer SET NAME = :1 WHERE CUSTOMER_ID = :2";
    OCIHandleAlloc(envhp, (void**)&stmt, OCI_HTYPE_STMT, 0, NULL);
    OCIStmtPrepare(stmt, errhp, (text*)sql, strlen(sql), OCI_NTV_SYNTAX, OCI_DEFAULT);

    OCIBind* bnd1 = NULL, *bnd2 = NULL;
    OCIBindByPos(stmt, &bnd1, errhp, 1, (void*)new_name, strlen(new_name)+1, SQLT_STR, NULL, NULL, NULL, 0, NULL, OCI_DEFAULT);
    OCIBindByPos(stmt, &bnd2, errhp, 2, &id, sizeof(id), SQLT_INT, NULL, NULL, NULL, 0, NULL, OCI_DEFAULT);

    if (OCIStmtExecute(svchp, stmt, errhp, 1, 0, NULL, NULL, OCI_COMMIT_ON_SUCCESS) == OCI_SUCCESS)
        printf("✅ 고객 이름 수정 완료\n");
    else
        check_error(errhp);
}

void delete_customer(int id) {
    char* sql = "DELETE FROM test_customer WHERE CUSTOMER_ID = :1";
    OCIHandleAlloc(envhp, (void**)&stmt, OCI_HTYPE_STMT, 0, NULL);
    OCIStmtPrepare(stmt, errhp, (text*)sql, strlen(sql), OCI_NTV_SYNTAX, OCI_DEFAULT);

    OCIBind* bnd1 = NULL;
    OCIBindByPos(stmt, &bnd1, errhp, 1, &id, sizeof(id), SQLT_INT, NULL, NULL, NULL, 0, NULL, OCI_DEFAULT);

    if (OCIStmtExecute(svchp, stmt, errhp, 1, 0, NULL, NULL, OCI_COMMIT_ON_SUCCESS) == OCI_SUCCESS)
        printf("✅ 고객 삭제 완료\n");
    else
        check_error(errhp);
}

void select_customers() {
    char* sql = "SELECT * FROM test_customer";
    OCIHandleAlloc(envhp, (void**)&stmt, OCI_HTYPE_STMT, 0, NULL);
    OCIStmtPrepare(stmt, errhp, (text*)sql, strlen(sql), OCI_NTV_SYNTAX, OCI_DEFAULT);
    OCIStmtExecute(svchp, stmt, errhp, 0, 0, NULL, NULL, OCI_DEFAULT);

    int id; char name[50], phone[50], birth[20];
    OCIDefineByPos(stmt, &def1, errhp, 1, &id, sizeof(id), SQLT_INT, NULL, NULL, NULL, OCI_DEFAULT);
    OCIDefineByPos(stmt, &def2, errhp, 2, name, sizeof(name), SQLT_STR, NULL, NULL, NULL, OCI_DEFAULT);
    OCIDefineByPos(stmt, &def3, errhp, 3, phone, sizeof(phone), SQLT_STR, NULL, NULL, NULL, OCI_DEFAULT);
    OCIDefineByPos(stmt, &def4, errhp, 4, birth, sizeof(birth), SQLT_STR, NULL, NULL, NULL, OCI_DEFAULT);

    printf("\n[고객 테이블 조회]\n");
    printf("---------------------------------------------------------\n");
    printf("| ID |     NAME     |     PHONE     |    BIRTH DATE     |\n");
    printf("---------------------------------------------------------\n");

    while (OCIStmtFetch2(stmt, errhp, 1, OCI_DEFAULT, 0, OCI_DEFAULT) == OCI_SUCCESS) {
        printf("| %2d | %-12s | %-12s | %-17s |\n", id, name, phone, birth);
    }
    printf("---------------------------------------------------------\n\n");
}

void close_connection() {
    OCISessionEnd(svchp, errhp, usrhp, OCI_DEFAULT);
    OCIServerDetach(srvhp, errhp, OCI_DEFAULT);
    OCIHandleFree(usrhp, OCI_HTYPE_SESSION);
    OCIHandleFree(svchp, OCI_HTYPE_SVCCTX);
    OCIHandleFree(srvhp, OCI_HTYPE_SERVER);
    OCIHandleFree(errhp, OCI_HTYPE_ERROR);
    OCIHandleFree(envhp, OCI_HTYPE_ENV);
}

int main() {
    if (connect_to_db() != 0) return -1;

    // insert_customer(2, "Park", "010-1234-5678", "1998-01-01");
    // update_customer_name(1, "박천사");
    delete_customer(2);
    select_customers();

    close_connection();
    return 0;
}
