# [Silver I] The Sierpinski Fractal - 6555 

[문제 링크](https://www.acmicpc.net/problem/6555) 

### 성능 요약

메모리: 66288 KB, 시간: 272 ms

### 분류

구현, 재귀

### 제출 일자

2024년 8월 27일 00:54:59

### 문제 설명

<p>Consider a regular triangular area, divide it into four equal triangles of half height and remove the one in the middle. Apply the same operation recursively to each of the three remaining triangles. If we repeated this procedure infinite times, we'd obtain something with an area of zero. The fractal that evolves this way is called the Sierpinski Triangle. Although its topological dimension is <em>2</em>, its Hausdorff-Besicovitch dimension is <em>log(3)/log(2)~1.58</em>, a fractional value (that's why it is called a fractal). By the way, the Hausdorff-Besicovitch dimension of the Norwegian coast is approximately <em>1.52</em>, its topological dimension being <em>1</em>.</p>

<p>For this problem, you are to outline the Sierpinski Triangle up to a certain recursion depth, using just ASCII characters. Since the drawing resolution is thus fixed, you'll need to grow the picture appropriately. Draw the smallest triangle (that is not divided any further) with two slashes, to backslashes and two underscores like this:</p>

<pre> /\
/__\
</pre>

<p>To see how to draw larger triangles, take a look at the sample output.</p>

### 입력 

 <p>The input contains several testcases. Each is specified by an integer <em>n</em>. Input is terminated by <em>n=0</em>. Otherwise <em>1<=n<=10</em> indicates the recursion depth.</p>

### 출력 

 <p>For each test case draw an outline of the Sierpinski Triangle with a side's total length of <em>2<sup>n</sup></em> characters. Align your output to the left, that is, print the bottom leftmost slash into the first column. The output must not contain any trailing blanks. Print an empty line after each test case.</p>

