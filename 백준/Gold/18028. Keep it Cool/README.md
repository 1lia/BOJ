# [Gold III] Keep it Cool - 18028 

[문제 링크](https://www.acmicpc.net/problem/18028) 

### 성능 요약

메모리: 11496 KB, 시간: 80 ms

### 분류

브루트포스 알고리즘, 그리디 알고리즘

### 문제 설명

<p>As the workload of the semester is ramping up you get the task of refilling the fridge in the lab with soda. The fridge has s slots, each with a capacity for d bottles of soda, and you have n new soda bottles to add to the fridge. The sodas currently in the fridge are all nice and cold, but the new ones are not and need to be cooled in the fridge for a while until they are ready to drink.</p>

<p>You can only refill the fridge from the front, so in an ideal world, you would first take out all the sodas currently in the fridge, then put in the n new ones, and then put the old and cold sodas in front of the new ones. But in an ideal world you would also not have two exams and a homework deadline coming. You are simply way too busy to do all this work.</p>

<p>Instead, you are going to just put the new bottles in the front of the fridge and hope for the best. However, you can still to be clever about which slots to put the new sodas in. Each time a student comes for a soda, they will take one from the front of a uniformly random non-empty slot in the fridge. You decide to add the new bottles to the fridge so as to maximize the probability that all the next m students getting a soda from the fridge will get a cold one.</p>

### 입력 

 <p>The first line of input contains four integers n, m, s and d (1 ≤ n, m, s, d ≤ 100), the number of new soda bottles, number of students to optimize for, number of slots in the fridge, and capacity of each slot, respectively. Then follows a line containing s integers c<sub>1</sub>, . . . , c<sub>s</sub> (0 ≤ c<sub>i</sub> ≤ d for each i), where c<sub>i</sub> is the number of soda bottles currently in slot i of the fridge.</p>

<p>You may assume that there is free space for all the n new bottles in the fridge.</p>

### 출력 

 <p>If there is a chance that all the next m students will get a cold bottle, then output s integers describing a refill scheme for the n soda bottles that maximizes the probability of this happening. The i<sup>th</sup> of these s integers indicates how many of the new bottles are placed in the front of slot i in the fridge. If there are multiple optimal refill schemes, output any one of them. Otherwise, if it is impossible for all the next m students to get a cold soda, output “impossible” instead.</p>

