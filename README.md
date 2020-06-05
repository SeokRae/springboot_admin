## SpringBoot Admin Project
- 간단한 상품, 고객, 파트너, 주문, 주문 상세 관리자 API

### SpringBoot
- JPA
- Lombok

### Refactoring Point
- Generic 활용
    - Controller CRUD Method
    - Service CRUD Method
- API
    - Header
    - Body
- enum 활용
    - 각 Entity 의 Status 처리

### Actions
- [x] User CRUD API
    - User 1:N OrderGroup
- [x] Item CRUD API
    - Partner 1:N Item 1:N OrderDetail 
- [x] Category CRUD API
    - Category 1:N Partner

- [x] OrderGroup CRUD API
    - User 1: N OrderGroup 1:N OrderDetail
- [x] OrderDetail CRUD API
    - Item 1:N OrderDetail N:1 OrderGroup
- [x] Partner CRUD API
    - Category 1:N Partner 1:N Item

