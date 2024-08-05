# [Gold II] Load Testing (Large) - 12574 

[문제 링크](https://www.acmicpc.net/problem/12574) 

### 성능 요약

메모리: 11520 KB, 시간: 64 ms

### 분류

애드 혹, 수학

### 제출 일자

2024년 8월 5일 17:35:12

### 문제 설명

<p>Now that you have won Code Jam and been hired by Google as a software engineer, you have been assigned to work on their wildly popular programming contest website. </p>

<p>Google is expecting a lot of participants (<strong>P</strong>) in Code Jam next year, and they want to make sure that the site can support that many people at the same time. During Code Jam 2010 you learned that the site could support at least <strong>L</strong> people at a time without any errors, but you also know that the site can't yet support <strong>P</strong> people.</p>

<p>To determine how many more machines you'll need, you want to know within a factor of <strong>C</strong>how many people the site can support. This means that there is an integer <strong>a</strong> such that you know the site can support <strong>a</strong> people, but you know the site can't support <strong>a</strong> * <strong>C</strong> people.</p>

<p>You can run a series of <em>load tests</em>, each of which will determine whether the site can support at least <strong>X</strong> people for some integer value of <strong>X</strong> that you choose. If you pick an optimal strategy, choosing what tests to run based on the results of previous tests, how many load tests do you need in the worst case?</p>

### 입력 

 <p>The first line of the input gives the number of test cases, <strong>T</strong>.  <strong>T</strong> lines follow, each of which contains space-separated integers <strong>L</strong>, <strong>P</strong> and <strong>C</strong> in that order.</p>

<h3>Limits</h3>

<ul>
	<li>1 ≤ <strong>T</strong> ≤ 1000.</li>
	<li>2 ≤ <strong>C</strong> ≤ 10.</li>
	<li><strong>L</strong>, <strong>P</strong> and <strong>C</strong> are all integers.</li>
	<li>1 ≤ <strong>L</strong> < <strong>P</strong> ≤ 10<sup>9</sup>.</li>
</ul>

### 출력 

 <p>For each test case, output one line containing "Case #x: y", where x is the case number (starting from 1) and y is the number of load tests you need to run in the worst case before knowing within a factor of <strong>C</strong> how many people the site can support.</p>

