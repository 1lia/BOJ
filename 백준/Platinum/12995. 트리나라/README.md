# [Platinum II] 트리나라 - 12995 

[문제 링크](https://www.acmicpc.net/problem/12995) 

### 성능 요약

메모리: 11492 KB, 시간: 72 ms

### 분류

다이나믹 프로그래밍, 트리에서의 다이나믹 프로그래밍, 트리

### 제출 일자

2024년 10월 24일 14:05:41

### 문제 설명

<p>트리나라는 N개의 도시로 이루어져 있고, 각각의 도시는 1번부터 N번까지 번호가 매겨져 있다. 트리나라의 도로 체계는 트리를 이룬다. 즉, 트리나라에는 N-1개의 양방향도로가 있다. 또, 모두 연결되어 있기 때문에, 임의의 두 도시 사이를 항상 오갈 수 있다.</p>

<p>스타트링크의 직원 K명은 트리나라로 이사를 가려고 한다. 모든 직원은 서로 다른 도시로 이사를 가야한다. 즉, 이사할 도시 K개를 선택해야 한다. 이사할 도시에는 중요한 조건이 하나 있는데, 모든 직원이 사는 도시는 연결되어 있어야 한다는 점이다. 예를 들어, 임의의 두 직원 사는 도시가 i와 j라면, i와 j를 연결하는 경로상에 있는 도시에도 직원이 살고 있어야 한다는 점이다.</p>

<p>트리나라의 트리 구조가 주어졌을 때, 이사할 도시 K개를 고르는 방법의 수를 구하는 프로그램을 작성하시오.</p>

### 입력 

 <p>첫째 줄에 도시의 수 N과 스타트링크 직원의 수 K가 주어진다. (2 ≤ N ≤ 50, 1 ≤ K ≤ N)</p>

<p>둘째 줄부터 N-1개의 줄에는 도로 정보가 주어진다.</p>

### 출력 

 <p>첫째 줄에 도시 K개를 선택하는 방법의 수를 1,000,000,007로 나눈 나머지를 출력한다. </p>

