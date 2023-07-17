# [Platinum I] 절댓값 수열 - 1494 

[문제 링크](https://www.acmicpc.net/problem/1494) 

### 성능 요약

메모리: 11484 KB, 시간: 80 ms

### 분류

많은 조건 분기, 수학, 정수론

### 문제 설명

<p>길이가 무한한 수열 S는 다음과 같이 정의된다.</p>

<ul>
	<li>S<sub>0</sub> = first;</li>
	<li>S<sub>1</sub> = second;</li>
	<li>S<sub>i</sub> = |S<sub>i-2</sub> - S<sub>i-1</sub>| (i>=2)</li>
</ul>

<p>first와 second와 질문의 개수가 주어졌을 때, 질문에 답하는 프로그램을 작성하시오.</p>

### 입력 

 <p>첫째 줄에 first와 second와 질문의 개수 N이 주어진다. 둘째 줄부터 N개의 줄에 질문이 주어진다. 질문이라는 것은 정수 i로 주어지며, S<sub>i</sub>를 출력하면 되는 것이다. N은 50보다 작거나 같은 자연수이고, 이 외의 모든 수는 10<sup>18</sup>보다 작거나 같은 음이 아닌 정수이다.</p>

### 출력 

 <p>첫째 줄에 각 질문에 대한 S<sub>i</sub>를 출력하면 된다.</p>

