class Solution {
    public int totalFruit(int[] fruits) {
        int low=0 , high=0 ,maxLen=-1;

        Map<Integer ,Integer > map=new HashMap<>();
        while(high < fruits.length){
            int ch=fruits[high];
            map.put(ch,map.getOrDefault(ch,0)+1);
            while (map.size() > 2){
                int left =fruits[low];
                map.put(left,map.get(left)-1);
                if(map.get(left)==0){
                    map.remove(left);
                }
                low++;
            }
                maxLen = Math.max(maxLen, high - low + 1);

            high++;
        }
        return maxLen;
    }
}