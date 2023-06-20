class Solution {
    public String solution(String code) {
        StringBuilder sb = new StringBuilder();
        int mode = 1;
        for(int i = 0; i < code.length() ; i++){
            if(code.charAt(i) == '1'){
                mode ^= 1;
            } else if((i & 1) != mode){
                sb.append(code.charAt(i));
            }
        }
        if(sb.length() == 0)
            return "EMPTY";
        return sb.toString();
    }
}