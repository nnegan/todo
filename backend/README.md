# kakaoix-todo 과제

## 과제내용
간단한 To-do 앱을 웹 기술을 사용하여 개발한다.

## API명세
추가 POST
```
http://127.0.0.1:18080/backend/v1/todo/add
```
json
```
{
	"boardContents" : "999999999999999999"
	,"boardStatus":"N",
,"boardRelation": [
			{          
            "relationBoardNumber": 1
    		 },
    		 {          
            "relationBoardNumber": 2
    		 }
       ]	
}
```
결과
```
{
    "returnCode": "SUCCESS",
    "message": "성공",
    "data": {
        "boardNumber": 6,
        "boardRelation": null,
        "boardContents": "vvvvv",
        "boardStatus": "N",
        "createdAt": "20200419",
        "updatedAt": "20200419"
    }
}
```
조회 GET
```
http://127.0.0.1:18080/backend/v1/todo/list?page=2&pageSize=10
```
결과
```
{
    "returnCode": null,
    "message": null,
    "data": null,
    "totalCount": 3,
    "page": 1,
    "pageSize": 10,
    "dataList": [
        {
            "boardNumber": 4,
            "boardRelation": [],
            "boardContents": "vvvvv",
            "boardStatus": "N",
            "createdAt": "20200419",
            "updatedAt": "20200419"
        },
        {
            "boardNumber": 5,
            "boardRelation": [],
            "boardContents": "vvvvv",
            "boardStatus": "N",
            "createdAt": "20200419",
            "updatedAt": "20200419"
        },
        {
            "boardNumber": 6,
            "boardRelation": [],
            "boardContents": "vvvvv",
            "boardStatus": "N",
            "createdAt": "20200419",
            "updatedAt": "20200419"
        }
    ]
}
```
수정 POST
```
http://127.0.0.1:18080/backend/v1/todo/update
```
json
```
{
	"boardNumber" :3
	,"boardContents" : "테스트"
    ,"boardRelation": [
			{          
            "relationBoardNumber": 1
    		 },
    		 {          
            "relationBoardNumber": 2
    		 }
       ]	
}
```
결과
```
{
    "returnCode": "SUCCESS",
    "message": "성공",
    "data": {
        "boardNumber": 3,
        "boardRelation": [
            {
                "seq": null,
                "relationBoardNumber": 1
            },
            {
                "seq": null,
                "relationBoardNumber": 2
            }
        ],
        "boardContents": "테스트",
        "boardStatus": null,
        "createdAt": null,
        "updatedAt": null
    }
}
```
삭제 PUT
```
http://127.0.0.1:18080/backend/v1/todo/delete/2
```
결과
```
{
    "returnCode": "SUCCESS",
    "message": "성공",
    "data": {
        "boardNumber": 2,
        "boardRelation": [],
        "boardContents": "111",
        "boardStatus": null,
        "createdAt": "20200418",
        "updatedAt": "20200418"
    }
}
```
상세 GET
```
http://127.0.0.1:18080/backend/v1/todo/detail/2
```
결과
```
{
    "returnCode": "SUCCESS",
    "message": "성공",
    "data": {
        "boardNumber": 2,
        "boardRelation": [],
        "boardContents": "111",
        "boardStatus": null,
        "createdAt": "20200418",
        "updatedAt": "20200418"
    }
}
```
## 기능 요구 사항
- [ ] 프론트단 구성을 못함.
- [x] 사용자는 문자열로 된 todo 항목을 추가 할 수 있다.
- [x] todo는 다른 todo들을 참조할 수 있다.
- [x] 사용자는 todo 목록을 조회할 수 있다.
    - [x] 작성 일자, 최종 수정 일자, 내용, 참조하고 있는 todo들의 id가 표시되어야 한다. (예시 참고)
    - [x] 페이지 네이션 구현 하면 가산점
- [x] 사용자는 todo를 수정할 수 있다.
- [x] 사용자는 todo를 삭제할 수 있다.
- [x] 사용자는 todo를 완료 또는 미완료로 상태변경을 할 수 있다.
    - [ ] 참조하고 있는 todo들이 모두 완료 상태가 아니라면 todo를 완료할 수 없다.
## 필수사항
- [x] 웹 기술을 이용할 것
- [x] 서버는 REST API 로 구현할 것.
- [x] DB는 로컬에서 실행될 수 있는 DBMS로 선정(H2, sqlite 등)
- [x] 페이지네이션 기능 구현
- [x] 검색 기능 (text, 완료 여부, 날짜, 등)~

## 우대사항 (가산점)
### [백엔드 지원]
- [x] 스프링 부트 사용
- [x] JPA 사용

### [공통]
- [ ] 유닛테스트 작성
- [ ] 백업 및 복원 기능을 구현
      - 파일 다운로드, 업로드 방식 혹은 드롭박스, 구글드라이브 등 클라우드 백업을 이용
- [ ] 검색 결과를 필터 및 정렬하는 기능 구현