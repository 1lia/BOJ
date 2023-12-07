# [Diamond IV] Game on a Tree - 17962 

[문제 링크](https://www.acmicpc.net/problem/17962) 

### 성능 요약

메모리: 38604 KB, 시간: 256 ms

### 분류

다이나믹 프로그래밍, 트리에서의 다이나믹 프로그래밍, 게임 이론, 트리

### 제출 일자

2023년 12월 7일 21:47:01

### 문제 설명

<p>Alice and Bob play a game on a tree. Initially, all nodes are white.</p>

<p>Alice is the first to move. She chooses any node and put a chip on it. The node becomes black. After that players take turns. In each turn, a player moves the chip from the current position to an ancestor or descendant node, as long as the node is not black. This node also becomes black. The player who cannot move the chip looses.</p>

<p>Who wins the game?</p>

<p>An <em>ancestor</em> of a node v in a rooted tree is any node on the path between v and the root of the tree.</p>

<p>A <em>descendant</em> of a node v in a rooted tree is any node w such that node v is located on the path between w and the root of the tree.</p>

<p>We consider that the root of the tree is 1.</p>

### 입력 

 <p>The first line contains one integer n (1 ≤ n ≤ 100 000) — the number of nodes.</p>

<p>Each of the next n − 1 lines contains two integers u and v (1 ≤ u, v ≤ n) — the edges of the tree. It is guaranteed that they form a tree.</p>

### 출력 

 <p>In a single line, print “Alice” (without quotes), if Alice wins. Otherwise, print “Bob”.</p>

