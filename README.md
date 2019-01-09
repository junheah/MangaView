# MangaShowMe-Crawler
망가쇼미 크롤러 by [junheah](https://github.com/junheah)

used in [MangaViewAndroid](https://github.com/junheah/MangaViewAndroid)

## License: ##
[MIT License](LICENSE)

## Usage: ##

### 검색:
검색 모드
```
0: 제목
1: 작가 이름
2: 태그
3: 첫자음 (인덱스)
4: 발행 (인덱스)
```

기본적인 검색
```java
Search search = new Search("검색어", 모드);
//결과 불러오기
search.fetch();
//결과 반환
Arraylist<Titles> result = search.getResult();
```
모든 결과 불러오기
```java
Search search = new Search("검색어", 모드);
search.fetch();
Arraylist<Titles> result = new ArrayList<>();
while(!search.isLast()){
  //마지막 페이지일 경우 true 반환
  search.fetch();
  result.addAll(search.getResult());
}
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
title.fetchEps();
ArrayList<Manga> episodes = title.getEps();
```

### 회차 정보:
```java
manga.fetch();
//제목(객체) 불러오기
Title title = manga.getTitle();
//이미지 링크 불러오기
ArrayList<String> images = manga.getImgs();
//회차 리스트 불러오기
ArrayList<Manga> episodes = manga.getEps();
```
