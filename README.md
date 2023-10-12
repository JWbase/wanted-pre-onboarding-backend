# wanted-pre-onboarding-backend

# 서비스 개요
    - 본 서비스는 기업의 채용을 위한 웹 서비스 입니다.
    - 회사는 채용공고를 생성하고, 이에 사용자는 지원합니다.


## 서비스 기술 스택
    - Java 11
    - SpringBoot 2.7.16
    - JPA
    - JUnit5
    - MySQL 8.0.21

## 요구사항 분석
    1. 채용공고를 등록합니다.
    2. 채용공고를 수정합니다.
    3. 채용공고를 삭제합니다.
    4-1. 채용공고 목록을 가져옵니다.
    4-2. 채용공고 검색 기능 구현
    5. 채용 상세 페이지를 가져옵니다.
    6. 사용자는 채용공고에 지원합니다.

## API Docs - localhost:8080

### 1. 채용공고 등록

    POST /api/jobs
    Content-Type: application/json

- RequestBody
```json
{
  "companyId": 1,
  "position": "백엔드 주니어 개발자",
  "compensation": 500000,
  "postingDetails": "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..",
  "technologyUsed": "Java"
}
```

- ResoponseBody
```json
{
  "id": 1,
  "companyName": "원티드랩",
  "country": "KOREA",
  "city": "SEOUL",
  "position": "백엔드 주니어 개발자",
  "compensation": 500000,
  "technologyUsed": "Java"
}
```

### 2. 채용공고 수정
    PUT /api/jobs/:id/edit
    Content-Type: application/json

- RequestBody
```json
{
  "position": "백엔드 주니어 개발자",
  "compensation": 2000000,
  "postingDetails": "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..",
  "technologyUsed": "Python"
}
```

### 3. 채용공고 삭제
    DELETE: /api/jobs/{jobPostingId}/delete

### 4-1. 채용공고 목록
GET /api/jobs

- ResoponseBody
```json
[
  {
    "id": 1,
    "companyName": "사무실1",
    "country": "KOREA",
    "city": "SEOUL",
    "position": "백엔드 주니어 개발자",
    "compensation": 1000000,
    "technologyUsed": "Python"
  },
  {
    "id": 2,
    "companyName": "사무실1",
    "country": "KOREA",
    "city": "SEOUL",
    "position": "백엔드 주니어 개발자",
    "compensation": 1000000,
    "technologyUsed": "Java"
  },
  {
    "id": 3,
    "companyName": "사무실2",
    "country": "KOREA",
    "city": "BUSAN",
    "position": "백엔드 주니어 개발자",
    "compensation": 1000000,
    "technologyUsed": "Go"
  }
]
```

### 4-2. 채용공고 검색
GET /api/jobs?search=Java

- ResoponseBody
```json
[
   {
    "id": 2,
    "companyName": "사무실1",
    "country": "KOREA",
    "city": "SEOUL",
    "position": "백엔드 주니어 개발자",
    "compensation": 1000000,
    "technologyUsed": "Java"
  }
]
```

### 5. 채용공고 상세 페이지
    GET /api/:id

- ResoponseBody
```json
{
  "id": 2,
  "companyName": "사무실1",
  "country": "KOREA",
  "city": "SEOUL",
  "position": "백엔드 주니어 개발자",
  "compensation": 1000000,
  "postingDetails": "사무실2에서 프론트 엔드 주니어 개발자를 채용합니다. 자격요건은..",
  "technologyUsed": "Java",
  "otherPostings": [1, 4, 5, 6, 7, 8]
}
```

### 6. 사용자가 채용공고 지원
    POST /api/apply

- RequestBody
```json
{
  "jobPostingId": 1,
  "userId": 1
}
```