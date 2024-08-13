# [Gold II] Bajka - 20196 

[문제 링크](https://www.acmicpc.net/problem/20196) 

### 성능 요약

메모리: 15908 KB, 시간: 208 ms

### 분류

다이나믹 프로그래밍

### 제출 일자

2024년 8월 13일 22:51:05

### 문제 설명

<p>Little Fabijan got bored with picture books, so he decided to read his first fairytale. Unfortunately, Fabijan often encounters a word that scares him. To overcome his fear, he will play a game he invented.</p>

<p>The scary word can be represented as an array of n lowercase letters. To start the game, Fabijan puts his finger on some position of the array and writes the letter from that position on a piece of paper. He then performs one of the following moves an arbitrary number of times:</p>

<ul>
	<li>He moves the finger to a position that is one place to the left or to the right of the current position, if that position exists. Also, Fabijan will then write the letter from the new position on the paper, after the last written letter.</li>
	<li>He moves the finger to any position with the same letter as the current one. Fabijan will not write anyhing on the paper in this case.</li>
</ul>

<p>It takes him |x − y| seconds to move the finger from position x to position y.</p>

<p>Fabijan will overcome his fear of the word if, at the end of the game, his favourite word is written on the paper. He wants to finish the fairytale as soon as possible, so he asks you to tell him the minimum number of seconds it will take him to overcome his fear of the given scary word.</p>

### 입력 

 <p>The first line contains integers n and m (1 ≤ n, m ≤ 300).</p>

<p>The second line contains n lowercase letters, the word that scares Fabijan.</p>

<p>The third line contains m lowercase letters, Fabijan’s favourite word.</p>

### 출력 

 <p>Print the shortest possible time in seconds Fabijan needs to write his favourite word on the paper, or −1 if that is not possible.</p>

