# MangaView
[마나모아 앱](https://github.com/junheah/MangaViewAndroid) 에서 사용된 파서

라이브러리가 아닌 stand-alone 다운로더가 필요하면 [java-only 브랜치로](https://github.com/junheah/MangaView/tree/java-only)

## License: ##
[MIT License](LICENSE)

## Usage: ##
baseURL = "https://site.com" 형태

### 검색:
검색 모드
```
0: 제목
1: 작가 이름
2: 태그
3: 첫자음 (인덱스)
4: 발행 (인덱스)
5: 
6: 종합 (복수 태그)
```

기본적인 검색
```java
Search search = new Search("검색어", 모드);
//결과 불러오기
search.fetch(baseURL);
//결과 반환
Arraylist<Titles> result = search.getResult();
```
모든 결과 불러오기
```java
Search search = new Search("검색어", 모드);
search.fetch(baseURL);
Arraylist<Titles> result = new ArrayList<>();
while(!search.isLast()){
  //마지막 페이지일 경우 true 반환
  search.fetch();
  result.addAll(search.getResult());
}
```
종합 검색
```java
Search search = new Search("", 6);
// 종합 검색시 addQuery()를 통해 검색어를 설정해 주어야 한다
/* 0: 검색 방법 (1 = AND, 2 = OR)
 * 1: 첫글자 (0 = ㄱ, 1 = ㄴ, ...)
 * 2: 발행 (0 = 미분류, 1 = 주간, ...)
 * 3: 장르/태그
 */
search.addQuery(0, "2");
search.addQuery(1, "0");
search.addQuery(1, "1");
search.addQuery(2, "0");
search.addQuery(3, "개그");
search.fetch(baseURL);
```

### 만화 정보:
```java
String 제목 = title.getName();
String 작가 = title.getAuthor();
String 썸네일링크 = title.getThumb();
List<String> 태그 = title.getTags();
```
화 목록 불러오기
```java
title.fetchEps(baseURL);
ArrayList<Manga> episodes = title.getEps();
```

### 회차 정보:
```java
manga.fetch(baseURL);
//제목(객체) 불러오기
Title title = manga.getTitle();
//이미지 링크 불러오기
ArrayList<String> images = manga.getImgs();
//회차 리스트 불러오기
ArrayList<Manga> episodes = manga.getEps();
```

### 댓글:
화에서 댓글 불러오기
```java
ArrayList<Comment> 전체댓글 = manga.getComments();
ArrayList<Comment> 베스트댓글 = manga.getBestComments();
```
```java
String 유저이름 = comment.getUser();
String 아이콘 = comment.getIcon();
String 타임스탬프 = comment.getTimestamp();
String 내용 = comment.getContent();
int 인덴트 = comment.getIndent();
int 좋아요개수 = comment.getLikes();
```

### 디코딩:
만화 이미지 디코딩
```java
Manga 만화;
Decoder 디코더 = new Decoder(만화.getSeed(), 만화.getId());
Bitmap 출력 = 디코더.decode(입력);
```
