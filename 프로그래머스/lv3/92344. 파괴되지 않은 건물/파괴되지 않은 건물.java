import java.util.*;

class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;

        int[][] map = new int[board.length+1][board[0].length+1];

        for(int i = 0 ; i < skill.length ; i++){
            if(skill[i][0] == 2){
                map[skill[i][1]][skill[i][2]] += skill[i][5];
                map[skill[i][3]+1][skill[i][4]+1] += skill[i][5];
                map[skill[i][1]][skill[i][4]+1] -= skill[i][5];
                map[skill[i][3]+1][skill[i][2]] -= skill[i][5];
            } else{
                map[skill[i][1]][skill[i][2]] -= skill[i][5];
                map[skill[i][3]+1][skill[i][4]+1] -= skill[i][5];
                map[skill[i][1]][skill[i][4]+1] += skill[i][5];
                map[skill[i][3]+1][skill[i][2]] += skill[i][5];
            }
            
            
        }
        
        for(int i = 0 ; i < board.length ; i++){
            for(int j = 1 ; j < board[i].length ; j++){
                map[i][j] += map[i][j-1];
            }
        }
        
        for(int i = 0 ; i < board[0].length ; i++){
            for(int j = 1 ; j < board.length ; j++){
                map[j][i] += map[j-1][i];
            }
        }

        for(int i = 0 ; i < board.length ; i++){
            for(int j = 0 ; j < board[i].length ; j++){
                board[i][j] += map[i][j];
                if(board[i][j] > 0)
                    answer++;
            }
        }
        return answer;
    }
}