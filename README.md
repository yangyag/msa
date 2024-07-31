# 주요 사항

## 1. Eureka 서버 실행
#### - Service Discovery
#### - http://localhost:8761 에서 서비스 등록상태 체크

## 2. API-Gateway 구현부
#### - Spring Cloud Gateway 적용한 라우팅 처리
#### - 각 모듈별 Filter 분리 적용
#### - Spring Webflux 사용
#### - WebClient Builder 설정을 통한 LoadBalancer 네이밍 사용
#### - GatewayFilterFactory 적용하여 JWT 토큰 인증

## 3. Auth Service 구현부
#### - JWT 토큰 발급
#### - JWT 토큰 검증
#### - 사용자 인증
#### - 사용자 등록,수정,삭제
#### - Spring Data JPA
#### * 아래 패턴 적용
##### - Command Pattern
##### - Factory Pattern
##### - CQRS Pattern 준수

## 4. Kubernetis
#### - minikukbe 에서 구동
#### - .k8s 폴더의 k8s-manifests.yaml 참조
#### - Front 영역은 Vue.js 로 구성 중

## 5. Category Service(카테고리) 구현부
#### - Spring Data JPA
#### * 아래 패턴 적용
##### - Command Pattern
##### - Factory Pattern
##### - CQRS Pattern 준수

## 6. Post Service(게시글) 구현부
#### - Spring Data JPA
#### - 구현 예정

## 7. Reply Service(댓글) 구현부
#### - Spring Data JPA
#### - 구현 예정