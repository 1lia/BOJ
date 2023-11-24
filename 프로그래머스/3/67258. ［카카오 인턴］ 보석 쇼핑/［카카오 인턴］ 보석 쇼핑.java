import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        int cnt = 0;
        int max = 0;
        int start = 0;
        int length = 100000000;
        int N = gems.length;
        HashMap<String , Integer> map = new HashMap<>();
        Deque<Integer> q = new ArrayDeque<>();
        for(int i = 0 ; i < N ; i++){
            if(!map.containsKey(gems[i])){
                map.put(gems[i] , max++);
            }
        }
        int[] arr = new int[max];
        
        for(int i = 0 ; i < N ; i++){
            int num = map.get(gems[i]);
            q.offer(num);
            arr[num]++;
            if(arr[num] == 1){
                cnt++;
            }
            
            while(!q.isEmpty() && cnt == max && arr[q.peekFirst()] != 1){
                int t = q.pollFirst();
                arr[t]--;
            }
            if(cnt == max && q.size() < length){
                length = q.size();
                start = i - q.size() + 2;
            }
        }
        int[] answer = {start , start + length - 1};
        return answer;
    }
}