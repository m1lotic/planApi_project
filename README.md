# planApi_project
일정관리 API 구현

# 사용 기술
- Spring Boot
- JDBC (orm, jpa 미사용)
- MySQL

<br>

# ERD


<br>

# 패키징 구조
```bash
todo-api-project
├── controller
│   └── ScheduleController.java
├── service
│   └── ScheduleService.java
├── repository
│   └── ScheduleRepository.java // interface
│   └── ScheduleRepositoryImpl.java
├── dto
│   ├── ScheduleRequestDto.java         // 생성/수정용
│   ├── ScheduleResponseDto.java        // 조회
│   └── PasswordCheckDto.java           // 수정/삭제 요청 시 비밀번호
├── entity
│   └── Schedule.java                
└── exception
    └── PasswordMismatchException.java
```
<br>

# API 명세서
