# 일정 관리 앱 - 2 만들기 과제 (숙련)

스프링 부트에서 CRUD API를 구현하고, Postman으로 테스트 가능한 일정 관리 시스템입니다.  
일정 CRUD와 유저 CRUD가 구현되어 연관관계를 갖고 있으며, 회원가입 및 로그인(인증)이 가능합니다.

---

## 목차
- [프로젝트 개요](#프로젝트-개요)
- [기술 스택](#기술-스택)
- [패키지 구조](#패키지-구조)
- [ERD](#ERD)
- [API 명세서](#API-명세서)
- [lv1 ~ lv4 요약](#lv1--lv4-요약)
- [클래스별 역할 및 기능](#클래스별-역할-및-기능)

---

## 프로젝트 개요

[**프로젝트 목적**]
- JPA를 활용한 데이터베이스 관리  ->  CRUD 에 대한 구조 이해 
- Cookie/Session을 활용한 인증/인가  ->  보안 개념과 인증 매커니즘 이해

[**주요 학습 목표**]
- CRUD API 구현
- Postman 활용한 테스트
- MySQL / JPA 활용(데이터 CRUD)
- Spring MVC 어노테이션(@RequestMapping, @GetMapping, @PostMapping) 활용
- @RestController, @Service, @Repository 기능 활용
- 연관관계 설정
- Servlet Filter 이해 및 활용
- Cookie / Session 이해 및 활용

---

## 기술 스택
- Language: Java 17
- Framework : Spring Boot 3.5.4
- Database : MySQL 8.0.43
- ORM : JPA(Hibernate)
- IDE : IntelliJ IDEA
- API Test Tool : Postman


---

## 패키지 구조
아래 사진은 프로젝트 패키지 구조 입니다.
![패키지 구조](./docs/images/package-structure.png)

---

## ERD
아래 사진은 프로젝트의 ERD 입니다. 
![ERD](./docs/images/erd.png)

---

## API 명세서
자세한 내용은 아래 파일로 확인가능합니다. (pdf 파일 첨부)
[API 명세서 (PDF)](./docs/api/API-schedule.pdf)

---

## lv1 ~ lv4 요약

[**lv1. 일정 CRUD**]
- 일정 CRUD 구현
- 필드 : `작성 유저명`,`할일 제목`,`할일 내용`,`작성일`,`수정일`
- `작성일` / `수정일` 필드는 `JPA Auditing` 활용


[**lv2. 유저 CRUD**]
- 유저 CRUD 구현
- 필드 : `유저명`,`이메일`,`작성일`,`수정일`
- `작성일` / `수정일` 필드는 `JPA Auditing` 활용
- 연관관계 구현
- 일정은 `작성 유저명` 필드 대신 `유저 고유 식별자` 필드 가짐


[**lv3. 회원가입**]
- 유저에 `비밀번호` 필드 추가 (암호화 X)


[**lv4. 로그인(인증)**]
- Cookie / Session 활용해 로그인 기능 구현 -> `Filter` 활용
- `Filter` 활용해 인증 처리
- `@Configuration` 활용해 필터 등록
- 로그인 기능 = `이메일` / `비밀번호` 활용
- `회원가입` / `로그인 요청`  ->  인증 처리에서 제외
- 로그인 시 `이메일` / `비밀번호` 불일치 시 HTTP Status code 401 반환


---

## 패키지별 역할 및 기능

[**auth**]
- `controller` / `dto` / `service` 패키지로 구성
- 회원가입 , 로그인 관리
- 로그인 시 `이메일` , `비밀번호` 검증 포함


[**common**]
- `Filter` 활용하기 위한 패키지
- `FilterConfig` , `LoginFilter` 클래스로 구성
- `LoginFilter` : 로그인 되었는지 검사 후, 보호해야 할 URL로의 접근 막는 필터 클래스
- `FilterConfig` : 필터를 스프링에 등록 및 설정하는 클래스


[**controller**]
- 클라이언트 요청을 받아 Service 계층에 전달/응답 반환
- API 제공 (CRUD)
    - `POST   /signup`                          : 회원가입
    - `POST   /login`                           : 로그인(세션 발급, 화이트리스트)

    - `GET    /users`                           : 유저 전체 조회
    - `GET    /users/{userId}`                  : 유저 단건 조회
    - `PUT    /users/{userId}`                  : 유저 수정(이메일)
    - `DELETE /users/{userId}`                  : 유저 삭제

    - `POST   /users/{userId}/schedules`                    : 일정 생성
    - `GET    /users/{userId}/schedules`                    : 일정 전체 조회
    - `GET    /users/{userId}/schedules/{scheduleId}`       : 일정 단건 조회
    - `PUT    /users/{userId}/schedules/{scheduleId}`       : 일정 수정
    - `DELETE /users/{userId}/schedules/{scheduleId}`       : 일정 삭제


[**dto**]
- 요청/응답 데이터 전달 객체


[**entity**]
- DB 테이블과 매핑
- JPA Entity로 정의


[**repository**]
- DB 접근 계층
- Spring Data JPA 사용 (CRUD 메서드 제공)


---