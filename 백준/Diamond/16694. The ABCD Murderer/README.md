# [Diamond IV] The ABCD Murderer - 16694 

[문제 링크](https://www.acmicpc.net/problem/16694) 

### 성능 요약

메모리: 63728 KB, 시간: 412 ms

### 분류

아호-코라식, 자료 구조, 다이나믹 프로그래밍, 그리디 알고리즘, 문자열, 트리, 트라이

### 제출 일자

2023년 11월 28일 17:47:36

### 문제 설명

<p>Oscar enjoys watching crime movies very much. He admires all those villains for how creative some of them can get. He would like to show off some of his creativity, too. Sadly, he’s quite inexperienced and he is unable to come up with his own kind of a trick. So he would like to get inspired by some old trick. He always loved watching criminals cutting out the letters from newspapers and putting them together to form a ransom text. However, Oscar ain’t a total rip off so he came up with his own variant of this trick. He feels that putting the text together letter by letter is simply too boring and time consuming. So he decided to create his ransom note by cutting out whole words.</p>

<p>Oscar bought several mainstream newspapers, therefore he has practically unlimited source of paper to cut the words from. He can cut out any particular word as many times as he wishes. However, he’s still limited by the set of words which appear in the newspapers. The trouble is that some words are just not used in the newspapers at all. To make his job a bit easier, he decided to erase all punctuation and all whitespace characters from the ransom note and ignore the case of characters. He also allows the cut-out words to overlap, as far as the overlapping parts contain the same text. Oscar now wonders how many words at minimum he has to cut out from the newspapers to put together the ransom note.</p>

### 입력 

 <p>The first input line contains an integer L (1 ≤ L ≤ 3 · 10<sup>5</sup>), the number of words found in the newspapers. The next line contains the text of the ransom note. The text is not empty and consists of lowercase English letters only. It is at most 3 · 10<sup>5</sup> characters long.</p>

<p>Each of the next L lines contains one word appearing in the newspapers, in lowercase English letters. None of these words is empty or longer than the text of the ransom note. All words appearing in the newspapers are listed in the input at least once. Sum of lengths of all words is not greater than 3 · 10<sup>5</sup>.</p>

### 출력 

 <p>Output the minimal number of words Oscar has to cut out from the newspapers to compose his ransom note. If it is not possible to compose the ransom note, print −1. Each word has to be counted as many times as it is physically cut out from a newspaper.</p>

