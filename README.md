# hunheroku
음취헌 음악감상동아리 사이트 - 음반리스트, 선곡표 만들기, 운영시간표 만들기, 개인시간표 입력

https://hunmusic.herokuapp.com/

## Stack
- JAVA
- JSP/JSTL
- MySQL
- Gson, Jsoup lib
- HTML/CSS/Javascript
- JQuery/Bootstrap
- Heroku

## 기능
### 메인 페이지
- Api

|Method|Path|Description|
|------|----|-----------|
|GET   |/   |main.jsp 건내줌|

- Carousel 구현

### 음반 리스트
- Api

|Method|Path|Description|
|------|----|-----------|
|GET   |/MusicList |페이지네이션 처리 후, musicList.jsp 건내줌|

- Modal
1. ▼Track 을 누르면, 트랙이 담긴 모달창이 뜸

### 곡 직접 추가
- Api

|Method|Path|Description|
|------|----|-----------|
|GET   |/addMusicDirectly|musicComplexForm.jsp(곡 직접 추가하기 Form) 건내줌|
|POST  |/addMusicDirectly|① Form에 담긴 값(바코드, 카테고리, 타이틀, 아티스트, 트랙, 음반사, CD수, 작곡가, 선호도) 저장 ② 알림창 띄우기|


### 곡 간편 추가
- Api

|Method|Path|Description|
|------|----|-----------|
|GET   |/addMusic|musicForm.jsp(곡 간편 추가하기 Form) 건내줌|
|POST  |/addMusic|① Form에 담긴 값(바코드, 카테고리, 선호도) 저장 ② 바코드 번호를 활용하여 음반 정보(타이틀, 아티스트, 트랙, 음반사, CD수) 크롤링 ③ 알림창 띄우기|

- 크롤링(Crwaling)
1. jsoup 라이브러리 사용
2. 교보문고 핫트랙스에서 음반 정보 크롤링
3. 해당 음반이 없다면, amazon에서 음반 정보 크롤링
4. amazon에서 받은 영어 이름은 정규표현식 처리(ex. Sohyun Park)

### 곡 검색
- Api

|Method|Path|Description|
|------|----|-----------|
|POST  |/search|Form에 담긴 단어로 DB에서 검색한 결과를 담아, searchResult.jsp 건내줌|

### 곡 수정 및 삭제
- Api

|Method|Path|Description|
|------|----|-----------|
|GET   |/updateMusic|updateMusicForm.jsp(해당 바코드의 곡을 수정하기 Form) 건내줌|
|POST  |/updateMusic|① 수정 사항 저장 ② 알림창 띄우기|
|POST  |/DeleteMusicServlet|① Form에서 받아온 바코드에 해당하는 음악 삭제 ② /MusicList로 리다이렉트|

### 한 주 선곡표 만들기
- Api

|Method|Path|Description|
|------|----|-----------|
|GET   |/chooseMusic|chooseMusic.jsp(빈 한 주 선곡표) 건내줌|
|POST  |/chooseMusic|① 음반리스트에서 15곡 랜덤으로 뽑기 ② 결과를 담아, chooseMusic.jsp 건내줌|

### 개인 시간표 생성 및 삭제
- Api

|Method|Path|Description|
|------|----|-----------|
|GET   |/operationTable|① 시간표 제출한 학생들의 이름을 담은 운영시간표 가져오기 ② 결과를 담아, operation.jsp 건내줌|
|POST  |/operationTable|학생이 입력한 이름과 시간표를 저장하기|
|POST  |/DeleteClassTimeServlet|① 해당 학생 id의 개인시간표 삭제 ② /operationTable로 리다이렉트|

- Javascript로 개인 시간표 입력 받기 구현
1. 학생이 불가능한 시간 칸을 클릭하면, toggleClass 이벤트를 호출
2. change 라는 class가 추가되어, 칸 색깔이 바뀜
3. change class를 가지고 있는 칸들(선택된 칸들)의 id 값을 담아 배열에 넣어줌
4. 객체에 학생 이름과 배열을 담아 서버로 보내기(ajax)
5. /operationTable 로 리다이렉트

- 서블렛에서 받은 데이터 처리하기
1. gson 라이브러리 사용
2. 자바스크립트로부터 받은 문자열을 json 객체로 변환하기
3. 학생 이름과 시간표 배열을 뽑아내 DB에 저장

### 운영시간표 생성 및 삭제
- Api

|Method|Path|Description|
|------|----|-----------|
|GET   |/MakingOperationTable|① 시간표 제출한 사람 명단과 운영시간표 가져오기 ② 결과를 담아, makingOperationTable.jsp 건내줌|
|POST  |/MakingOperationTable|① 학생들의 개인시간표로 한 학기 운영 시간표 만들기 ② /MakingOperationTable 로 리다이렉트|
|POST  |/DeleteOperationTableServlet|① 해당 id의 운영시간표 삭제하기 ② /MakingOperationTable 로 리다이렉트|

- 운영시간표 만들기 알고리즘
1. 조건: 한 학생 당 2~3타임 배정 받도록. 최대 주 2회 동아리 들리도록. 같은 요일에 여러 번 들리지 않도록.
2. 3타임 연속되는 학생 찾아서 운영시간표에 넣기
3. 2타임 연속되는 학생 찾아서 운영시간표에 넣기
4. 나머지 학생들 운영시간표의 빈 칸에 최대 2번 넣어주기
5. 운영시간표의 빈 칸에는 연속으로 두 번 오는 사람 중에 골라서 넣기
6. 한 타임도 배정을 받지 않은 학생들은 3타임 연속으로 배정돼있는 시간을 골라서(가운데 타임은 X) 넣어주기
7. 여전히 한 타임도 배정을 받지 않은 학생들은 2타임 연속으로 배정돼있는 시간을 골라서 넣어주기

### Front 관련
- 모바일에서도 사용할 수 있도록 반응형 웹으로 구현

## Herok 배포 및 기타 삽질 로그
제 블로그(코딩하는 엘모)를 참고해주세요. https://blog.naver.com/sgs03091/221633332556

## 추가하고 싶은 기능 및 개선할 점
- 단순 램덤이 아닌, 선호도를 반영하여 선곡표 만들기
- 시간표 알고리즘 개선
- 크롤링 중 간간히 등장하는 nullpointerexception 처리하기
- 크롤링 시간 단축하기
- 크롤링 중 단순 기다리세요 메시지가 아닌, 로딩 창 만들기
- 외부인/동아리원/회장 권한 나누기

## 소감
- 자바를 배운지 얼마 안 된 시점에서 만든 초창기작이라 부족한 점이 많다.
- 특히 if, else가 난무한 시간표 알고리즘 개선과 null 처리가 제일 시급하다.
- 눈덩이처럼 불어나는 Servlet 파일들과 여러 기능들을 추가하기 위해서는 Spring Framework를 배워야한다.
- ~~Spring MVC 까지는 배우고 이 프로젝트를 만들어볼걸 그랬다ㅠㅠ 그래도 저학년 후배들이 이어서 운영해야 하니까 스프링을 모르는 후배들에게는 다행일수도..~~
- 그렇지만 설계부터 백앤드, 프론트앤드, 배포까지 한 사이클을 다 돌아본 것에 의의가 있다고 생각한다.
- 강의 및 튜토리얼에서는 등장하지 않는 정형화되지 않은 상황들을 처리해봄으로써 구글링 스킬이나 프로그래밍적 사고가 발전한 것 같다.
- 이제 스프링 공부하자^^















