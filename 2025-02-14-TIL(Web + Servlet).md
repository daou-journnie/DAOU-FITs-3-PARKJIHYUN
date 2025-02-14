### Internet

- LAN(Local Area Network)을 연결한 물리적인 플랫폼
    
    여러 개의 LAN을 서로 연결해서 구성된 광역 네트워크(WAN)의 일부로, 전 세계를 연결하는 인터넷의 기반을 형성함
    
- 이 network 위에 service가 실행됨
    - **email, web, FTP 등** 다양한 서비스들이 이 플랫폼에서 운영됨
    - 각각의 서비스는 프로토콜과 서버 프로그램에 의해 제공됨

---

### IP Address

> 데이터 통신을 하기 위해 논리적 주소인 IP Address를 사용
> 
> - NIC(Network Interface Card)에 부여되어, 네트워크에 참여하는 컴퓨터를 식별 가능하게 함
> - **컴퓨터를 구분하기 위한 32bit 숫자** (IPv4의 경우)
>     - 예: 192.168.0.1
> - **숫자 대신 문자를 이용 → DNS(Domain Name System)**
>     - 사용자가 이해하기 쉬운 도메인 이름을 IP 주소로 변환해줌
- **포트번호**
    - 하나의 컴퓨터 내에서 여러 서비스를 구분하기 위해 사용되는 번호
    - IP 주소와 포트번호의 조합으로 통신의 종착점을 명확히 함
- **물리적인 주소 - MAC Address**
    - NIC에 할당된 고유 식별자
    - 네트워크 통신에서 논리 주소(IP Address) → 물리 주소(MAC Address)로 변환하는 ARP(Address Resolution Protocol) 과정을 통해 사용됨
- 데이터 통신을 하려면 **IP + Port**를 알아야 함
    - 이 정보들을 통해 어떤 컴퓨터의 어떤 서비스로 데이터를 전송할지 결정

---

### Protocol

- 데이터 통신을 위해 서로 지켜야 할 **약속(규칙)**
    - 통신에 참여하는 모든 장치가 동일한 규칙을 따르기 때문에 서로 이해하고 데이터를 주고받을 수 있음
- **HTTP Protocol을 이용한 web service (CS구조 - Client/Server)**
    - **Web Client**:
        - 주로 web browser가 이에 해당됨
        - — HTTP request →
            - Get
            - Post
    - **Web Server**:
        - HTTP 요청을 받아서 처리한 후 HTTP response를 반환함
        - **Stateless Protocol(무상태 프로토콜)**
            - 서버가 각 요청 사이의 상태를 유지하지 않음 (매 요청이 독립적임)

---

### Web service

![image.png](attachment:0c2eec06-21a5-4f5c-a18c-b3a3669858fa:image.png)

!https://velog.velcdn.com/images/leesomyoung/post/256fdf5f-b225-4e8c-9267-2748e0cc7ede/image.png

- **Static Web**
    - 클라이언트의 요청을 받았을 때, web server(apache, NginX 등)가 가지고 있는 **resource** (프로그램이 아닌 정적 파일, 예: HTML, 이미지, CSS 파일 등)를 반환함
    - 서버에서 별도의 처리 없이 저장된 리소스를 그대로 제공함
- **Dynamic Web**
    - 클라이언트의 요청에 따라 서버에서 **프로그램 실행 결과**를 반환함
    - 요청에 따라 데이터베이스 조회, 연산 처리 등을 거친 후 결과를 만들어냄
    - **Web에서 실행되는 프로그램 실행 request**
        - 웹 서버는 단순히 요청을 받고 정적 리소스를 제공하는 역할을 넘어서, 동적인 처리가 필요한 경우 이를 직접 실행할 수 없음
    - **그래서 다른 프로세스에 위임 - program(WAS(Web Application Server))에 request**
        - 웹 서버는 WAS나 다른 백엔드 프로세스에 요청을 넘겨서 실제 애플리케이션 로직을 처리하게 함
        - 이와 같은 구조로 클라이언트의 요청을 분산 처리하며, 보다 복잡한 동적 웹 서비스를 제공함

# Servlet Project

<aside>
✨

servlet

```java
[Web Client]
  └─ [HTTP Request: MyHello 실행 요청 (GET 또는 POST)]
         │
         ▼
      [Tomcat (Web Server + WAS 역할)]
         ├─ HTTP Request 수신 및 문자열 → 객체 변환
         └─ 요청 전달 → [Servlet Container]
                   │
                   ▼
             [서블릿 객체 확인]
                   ├─ 존재하지 않으면:
                   │      ├─ 서블릿 클래스 로딩
                   │      └─ 서블릿 객체 생성 후 init() 호출 (초기화)
                   └─ 존재하면 바로 사용
                   │
                   ▼
             [쓰레드 생성 및 요청 처리]
                   └─ 생성된 쓰레드에서 service() 호출
                           ├─ GET 방식이면 → doGet() 호출
                           └─ POST 방식이면 → doPost() 호출
                                       │
                                       ▼
                         [요청 처리 완료 → 결과 생성]
                                       │
                                       ▼
         [Tomcat] ← HTTP Response 전송 ← [Servlet Container]
                                       │
                                       ▼
                              [클라이언트에게 응답 전달]

```

- web client [Get|Post] request : MyHello를 실행해주세요
    - Tomcat
        - webserver HttpRequest : String → 객체 (→ service에 전달)
            - WAS
                - servlet container
                    - 객체 있니?
                        - 없음
                            - 서블릿 클래스 로딩
                            - 객체 생성 후 `init()` 호출 초기화
                        - MyHello 있음
                            
                            그대로 사용
                            
                    - thread 생성
                        - thread invoker
                        
                        `service()` 호출
                        
                        - Get 방식이면 → `doGet()`
                        - Post 방식이면 → `doPost()`
        - Client에게 response
        - client를 위해 만든 thread는 날아가고
        MyServlet 객체는 남아있음
            
            ⇒ 다른 사용자가 서블릿을 호출하면 재사용 - thread만 새로 생성
            
    

---

</aside>

Park님, 서블릿 관련 내용을 전반적으로 잘 정리해주셨어요. 아래에 제가 검토한 후 보완한 내용을 들여쓰기를 활용한 불릿 포인트 형식으로 정리해 보았습니다:

---

## Servlet 인터페이스를 구현해서 클래스 생성

- **웹에서 실행할 수 있는 Java 프로그램 작성**
    - **서블릿(Servlet) 구현**: 웹 요청에 대응하는 Java 클래스 작성

### **일반 클래스를 서블릿으로 만들어보기**

**1. HttpServlet 상속**

`extends HttpServlet`로 서블릿 클래스 생성

**2. 필요한 메서드 오버라이드**

- `init()`
    - 서블릿 초기화 작업 (최초 한 번 호출)
- `service()`
    - 각 요청마다 호출되는 메서드 (쓰레드에 의해 실행)
    - 내부에서 요청 방식(GET, POST 등)에 따라 `doGet()` 또는 `doPost()` 호출
- `doGet()`
    - GET 방식의 요청을 처리
- `doPost()`
    - POST 방식의 요청을 처리

**3. URL 매핑**

`@WebServlet("/url-mapping")`

- 이 어노테이션을 통해 서블릿과 특정 URL을 연결

### 서블릿 실행

`http://localhost:8080/HelloServletProject_war_exploded/myservlet`

- **세부 구성 요소**:
    - **protocol**:
        - `http`
    - **서버 IP**:
        - `localhost` (서버가 로컬 컴퓨터에 있을 경우)
    - **웹서버의 포트번호**:
        - `8080`
    - **프로젝트의 context**:
        - `/HelloServletProject_war_exploded`
            - WAR 파일을 exploded 방식으로 배포할 경우 해당 프로젝트의 컨텍스트명이 사용됨
    - **서블릿의 URL 매핑**:
        - `@WebServlet("/myservlet")`와 같이 설정된 매핑 값
