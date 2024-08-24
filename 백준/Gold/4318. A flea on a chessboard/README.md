# [Gold V] A flea on a chessboard - 4318 

[문제 링크](https://www.acmicpc.net/problem/4318) 

### 성능 요약

메모리: 18688 KB, 시간: 140 ms

### 분류

구현, 비둘기집 원리, 시뮬레이션

### 제출 일자

2024년 8월 24일 21:58:50

### 문제 설명

<p>An infinite chessboard is obtained by extending a finite chessboard to the right and up infinitely. Each square of the chessboard is either black or white with the side of <em>S</em> milimiters, 0 < <em>S</em> <= 1000. The leftmost bottom square of the chessboard is black. A flea is positioned on the chessboard at the point (<em>x</em>, <em>y</em>) (given in milimeters) and makes jumps by jumping <em>dx</em> milimeters to the right and <em>dy</em> milimiters up, 0 < <em>dx</em>, <em>dy</em>, that is, a flea at position (<em>x</em>, <em>y</em>) after one jump lands at position (<em>x+dx</em>, <em>y+dy</em>).</p>

<p>Given the starting position of the flea on the board your task is to find out after how many jumps the flea will reach a white square. If the flea lands on a boundary between two squares then it does not count as landing on the white square. Note that it is possible that the flea never reaches a white square.</p>

### 입력 

 <p>Each test case consists of one line of input containing five non-negative numbers separated by white space and giving integers <em>S</em>, <em>x</em>, <em>y</em>, <em>dx</em>, and <em>dy</em>. An input line containing five zeroes follows the last test case.</p>

### 출력 

 <p>For test case print one line of output in the format shown in the sample.</p>

