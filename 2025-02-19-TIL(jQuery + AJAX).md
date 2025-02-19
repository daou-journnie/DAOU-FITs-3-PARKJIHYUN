# jQuery
## **JavaScript**

- **함수형 언어**: 함수를 1급 객체로 다루며, 고차 함수 사용
- **객체지향 언어**: 클래스 기반이 아닌 프로토타입 기반의 객체지향 패러다임
- **Prototype 기반**: `prototype`을 통한 상속 구조 → 기존 객체지향 언어와 다름


> - DOM 조작이 복잡하고 코드량이 많아짐
> - 이벤트 처리 및 애니메이션 구현이 까다로움
> - AJAX 사용 시 비동기 처리 및 콜백 지옥 문제 발생

## **HTML**

### HTML 요소(Element)

- 웹 페이지를 구성하는 기본 단위

### 블록 요소(Block-level Elements)

- 한 줄을 차지하는 요소 (`<div>`, `<p>`, `<h1>`)
- 예시 이미지

### 인라인 요소(Inline Elements)

- 내용만큼 크기를 차지 (`<span>`, `<a>` 등)
- 예시 이미지

### 속성(Attribute)

- 요소에 추가 정보를 부여 (예: `id`, `class`, `src` 등)

---

## **jQuery**

> JavaScript의 복잡한 부분을 단순화해주는 **라이브러리**
> 
> - **로직이 규정된 구조 → 유지보수 용이**
- **DOM 조작이 쉬움** → `$()` 선택자를 활용한 간결한 코드
- **이벤트 처리 간단** → `.click()`, `.hover()` 같은 메서드 제공
- **CSS 스타일 조작 간단** → `.css()`를 이용해 쉽게 변경
- **AJAX 요청 직관적** → `.ajax()`, `.get()`, `.post()` 등으로 쉽게 비동기 통신

---

**CDN 방식으로 라이브러리 포함**

```html
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>

```

### 선택자 (Selector)

HTML 요소 선택 방법

| 선택자 | 설명 | 예제 |
| --- | --- | --- |
| `$("태그명")` | 특정 태그 선택 | `$("h1")` |
| `$("#아이디")` | 특정 ID 선택 | `$("#myId")` |
| `$(".클래스")` | 특정 클래스 선택 | `$(".myClass")` |
| `$("*")` | 모든 요소 선택 | `$(" * ")` |
| `$("부모 > 자식")` | 바로 직계 자식 요소 선택 | `$("ul > li")` |
| `$("부모 자손")` | 부모 내부의 모든 후손 선택 | `$("div p")` |
| `$("형제 + 다음형제")` | 특정 요소의 바로 다음 형제 선택 | `$("#a + li")` |
| `$("형제 ~ 나머지형제")` | 특정 요소 이후의 모든 형제 선택 | `$("#a ~ li")` |
| `$("[속성=값]")` | 특정 속성을 가진 요소 선택 | `$("[type=checkbox]")` |

---

### 메서드 (Method)

선택한 요소에 원하는 작업 수행

| 메서드 | 설명 | 예제 |
| --- | --- | --- |
| `.css()` | 스타일 변경 | `$("h1").css("color", "red")` |
| `.text()` | 텍스트 가져오거나 변경 | `$("h1").text("안녕!")` |
| `.val()` | 입력값 가져오거나 변경 | `$("#input").val("새 값")` |
| `.attr()` | 속성 값 가져오거나 변경 | `$("img").attr("src", "new.jpg")` |
| `.append()` | 요소 마지막에 추가 | `$("ul").append("<li>추가됨</li>")` |
| `.prepend()` | 요소 처음에 추가 | `$("ul").prepend("<li>첫번째</li>")` |
| `.remove()` | 요소 삭제 | `$("#myId").remove()` |
| `.each()` | 반복 실행 | `$("li").each(function(i, el){ console.log($(el).text()); })` |

---

### 이벤트 처리 (Event Handling)

클릭, 입력 등의 사용자 동작을 감지

| 이벤트 | 설명 | 예제 |
| --- | --- | --- |
| `.click()` | 클릭 이벤트 | `$("#btn").click(function(){ alert("클릭됨!"); });` |
| `.hover()` | 마우스 오버 이벤트 | `$(".box").hover(function(){ $(this).css("background", "yellow"); });` |
| `.on()` | 이벤트 등록 | `$("h1").on("click", function(){ alert("제목 클릭!"); });` |

# AJAX 실습 정리

## AJAX 호출

AJAX는 페이지 전체를 새로 고치지 않고도 서버와 비동기 통신을 가능하게 합니다. jQuery의 `$.ajax()` 메서드를 사용하여, 아래와 같은 정보를 포함한 요청을 서버로 보냅니다.

1. **요청 준비**:
    - `$.ajax()` 호출 시 필요한 옵션을 포함한 객체 생성
2. **서버 요청**:
    - 지정한 URL로 GET 방식의 비동기 요청 전송
3. **응답 수신**:
    - 서버로부터 JSON 응답을 받으면 자동으로 객체로 매핑
4. **데이터 처리**:
    - `success` 콜백 함수에서 응답 객체를 활용하여 영화 목록 데이터를 추출
    - 각 영화 제목을 `<li>` 요소로 만들어 `<ol>`에 추가
5. **에러 처리**:
    - 요청 실패 시 `error` 콜백 함수에서 에러 메시지 출력

---

### HTML 구조

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <!-- jQuery 라이브러리 로드 -->
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <!-- AJAX 관련 스크립트 파일 연결 -->
    <script src="js/boxOffice.js"></script>
</head>
<body>
    <h1>영화 차트</h1>
    <br>
    <!-- 결과를 표시할 목록 -->
    <ol></ol>
    <!-- 버튼 클릭 시 myFunc() 실행 -->
    <input type="button" value="event" onclick="myFunc()">
</body>
</html>

```

### JavaScript (boxOffice.js)

```jsx
function myFunc() {
    // 버튼 클릭 시 AJAX 호출 실행

    $.ajax({
        async: true, // 비동기 호출 (default)
        url: "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json",
        type: "GET", // HTTP GET 방식
        dataType: "json", // 응답 데이터 형식 (JSON)
        data: {
            key: "---", // API 인증 키
            targetDt: "20250218" // 조회할 날짜 (YYYYMMDD 형식)
        },
        success: function(response) {
            // 호출 성공 시 실행
            alert("호출 성공!");
            console.log("요청 성공:", response);

            // 응답 객체에서 영화 차트 목록 추출
            let dailyBoxOfficeList = response.boxOfficeResult.dailyBoxOfficeList;
            console.log(dailyBoxOfficeList);

            // 영화 목록을 순회하면서 <ol> 요소에 <li>로 추가
            dailyBoxOfficeList.forEach((movie) =>  {
                console.log(movie.movieNm);
                $("ol").append(`<li>${movie.movieNm}</li>`);
            });
        },
        error: function(xhr, status, error) {
            // 호출 실패 시 실행
            alert("호출 실패!!");
            console.error("요청 실패:", status, error);
        }
    });
}

```

`success: function(response)`

- **async**
    - `true`: 비동기 요청 (기본값)
    - `false`: 동기 요청 (사용은 권장되지 않음)
- **url**
    - 호출할 API 주소를 지정
    - 예: `"http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json"`
- **type**
    - HTTP 메서드: GET 또는 POST
    - 여기서는 GET 방식 사용
- **dataType**
    - 서버 응답 데이터의 타입 (예: `"json"`, `"xml"`)
    - JSON 응답을 객체로 자동 매핑
- **data**
    - 서버로 전송할 파라미터를 객체 형태로 전달
    - 예: `{ key: "API_KEY", targetDt: "20250218" }`
- **success**
    - AJAX 호출이 성공했을 때 실행되는 콜백 함수
    - 응답 데이터는 매개변수로 전달받으며, 여기서 DOM 조작 등 후속 작업 수행
- **error**
    - AJAX 호출이 실패했을 때 실행되는 콜백 함수
    - 에러 정보와 상태를 확인할 수 있음 
