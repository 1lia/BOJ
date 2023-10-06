def solution(money):
        n = len(money)
        dp = [[0]*4 for _ in range(n)]
        
        dp[1][1] = money[1]
        dp[1][2] = money[0]
        
        for i in range(2, n):
            dp[i][0] = max(dp[i-1][1], dp[i-1][0])
            dp[i][1] = dp[i-1][0] + money[i]
            dp[i][2] = max(dp[i-1][3], dp[i-1][2])
            dp[i][3] = dp[i-1][2] + money[i]

        answer = max(dp[n-1][0], dp[n-1][1], dp[n-1][2])
        return answer