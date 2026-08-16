class Solution {
    public boolean stoneGameIX(int[] stones) {
       int cnt0=0; 
       int cnt1=0; 
       int cnt2=0;

       for(int i=0 ;i<stones.length;i++){
        int x=stones[i]% 3;
        if(x==0){
            cnt0++;
        }
        else if(x==1){
            cnt1++;
        }
        else {
            cnt2++;
        }
       } 

       if(cnt0 % 2 == 0){
        return cnt1 >= 1 && cnt2 >= 1;
       }
        return ((cnt1 -cnt2)>2 || (cnt2 -cnt1)>2);
    }
}