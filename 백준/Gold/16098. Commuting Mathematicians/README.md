# [Gold II] Commuting Mathematicians - 16098 

[문제 링크](https://www.acmicpc.net/problem/16098) 

### 성능 요약

메모리: 163516 KB, 시간: 676 ms

### 분류

데이크스트라, 그래프 이론, 최단 경로

### 제출 일자

2025년 8월 21일 19:11:28

### 문제 설명

<p>The Association of Commuting Mathematicians (ACM) has a long-standing tradition of debating the latest developments in science, technology, engineering, and mathematics, during subway trips. While on a subway trip, all ACM members strictly abide by the association’s first ground rule: “Don’t walk and talk.” Thus, the debate is interrupted whenever the trip requires a transfer from one subway line to another. ACM members also fit the stereotype of the absent-minded professor, so they always forget the current topic when they are transferring, and open an unrelated new discussion when taking their seats on the new line. Debates are consequently hardly ever conclusive.</p>

<p>ACM members have discussed the problem on the subway, and agreed that they would need a fast and simple way to get around the city using the subway, while minimizing both the travel time and the number of line transfers. They were about to find an optimal solution for this task, but unfortunately they had to transfer and started talking about radio networks and groundhogs instead... It is thus up to you to solve their annoying problem.</p>

<p>The subway network is a graph consisting of stations and subway lines. A station is identified by a nonnegative integer number, e.g., 42. A subway line is a sequence of stops, each stop being a station, with a travel time between each pair of adjacent stations. For instance, in Example 1 of Figure 4, there are three lines: the blue line, the green line, and the orange line. Subway cars follow the line in both directions: travel times in either direction are the same, and the waiting time when transferring from one line to another is neglected. The stops of a line always correspond to different stations, except that lines may be cyclic: in this case, the last stop is the same station as the first stop, and the subway cars follow the line in both directions.</p>

<p>Knowing the details of the subway network, you must find the best travel route for the ACM members from a departure station to a destination station. The first objective of the algorithm is to minimize the travel time. In addition, among all routes having the minimal travel time, the algorithm should also minimize the number of transfers from one line to another. Note that it is never possible (or useful) to transfer from one line to itself.</p>

### 입력 

 <p>The input consists of several test cases. The first line contains an integer indicating the number of test cases. Each test case follows. The first line of a test case consists of two positive integers 0 < N ≤ 1000 and 0 < L ≤ 50 separated by a single space: N indicates the number of stations and L indicates the number of subway lines. This is followed by L lines describing each subway line, with each line consisting of the following integers separated by single spaces: the first integer 1 < K<sub>i</sub> ≤ N + 1 indicates the number of stops on the line, and the next 2 · K<sub>i</sub> − 1 integers S<sub>i,1</sub>, T<sub>i,1↔2</sub>, S<sub>i,2</sub>, T<sub>i,2↔3</sub>, . . . , S<sub>i,K<sub>i−1</sub></sub>, T<sub>i,(K<sub>i−1</sub>)↔K<sub>i</sub></sub>, S<sub>i,K<sub>i</sub></sub> specify the stops and the travel time between the stops. Specifically, for 1 ≤ j ≤ K<sub>i</sub>, the integer 0 ≤ S<sub>i,j</sub> < N describes the station at the i-th stop and, for 1 ≤ j < K<sub>i</sub>, the integer 0 < T<sub>i,j↔(j+1)</sub> ≤ 60 describes the travel time in minutes between the stops j and j + 1 on this line. Cyclic lines may only form a single cycle, i.e., the first station of one line is also the last station of that line. All non-extremal stations have to be different, meaning that S<sub>p</sub> ≠ S<sub>q</sub> for any 1 ≤ p < q ≤ K<sub>i</sub> except possibly when p = 1 and q = K<sub>i</sub>. The test case ends with a line that consists of two integers 0 ≤ F < N and 0 ≤ D < N separated by a single space: F indicates the departure station and D indicates the destination station. We always have F ≠ D, and a guarantee that there is a path between the stations F and D.</p>

### 출력 

 <p>For each test case in the input, your program should produce one line consisting of two integers separated by a single space. The first integer should be the smallest number of minutes of a path that goes from the departure station to the destination station. The second integer should be the smallest number of transfers of a path that goes from the departure station to the destination station in the smallest number of minutes. There should be no blank lines in your output.</p>

