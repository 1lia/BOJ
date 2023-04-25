#include <iostream>
#include <vector>
#include <queue>
#include <array>

using namespace std;
typedef long long ll;

int N;
int indegree_max[300001];
int indegree_min[300001];
vector<int> g[300001];
vector<int> g2[300001];
ll dp[300001];
ll MOD = 1000000007;

ll div(int n) {
	if (n == 0)
		return 1;

	if (n == 1)
		return N;

	if (dp[n] != 0)
		return dp[n];

	ll t = div(n >> 1);

	if ((n & 1) == 0) {
		return dp[n] = (t * t) % MOD;
	}
	else {
		return dp[n] = (((t * t) % MOD )* N) % MOD;
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	int K, P , a, b;
	cin >> N >> K >> P;

	for (int i = 0; i < P; i++) {
		cin >> a >> b;
		g[b].push_back(a);
		g2[a].push_back(b);
		indegree_max[a]++;
		indegree_min[b]++;
	}

	priority_queue<int , vector<int>, greater<int>> q;
	priority_queue<int , vector<int>, greater<int>> q2;

	for (int i = 0; i < K; i++) {
		if (indegree_max[i] == 0) {
			q.push(i);
		}

		if (indegree_min[i] == 0) {
			q2.push(i);
		}
	}

	ll result = 0;
	ll cnt_max = N - K;
	ll cnt_min = K - 1;

	for (int i = 0; i < K; i++){
		int t = q.top();
		q.pop();
		for (int j : g[t]) {
			if (--indegree_max[j] == 0) {
				q.push(j);
			}
		}

		int t2 = q2.top();
		q2.pop();
		for (int j : g2[t2]) {
			if (--indegree_min[j] == 0) {
				q2.push(j);
			}
		}

		result += (cnt_max * div(t)) - (cnt_min * div(t2));
		result %= MOD;
		cnt_max++;
		cnt_min--;
	}
	cout << result;
}