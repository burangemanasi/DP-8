//413. Arithmetic Slices - https://leetcode.com/problems/arithmetic-slices/description/

//Brute Force
//Time Complexity: O(n^2)
class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
        int n=nums.length;
        int count=0;
        for(int i=0; i<n; i++){
            for(int j=i+2; j<n; j++){
                if(nums[j] - nums[j-1] == nums[j-1] - nums[j-2]){
                    count++;
                } else{
                    break;
                }
            }
        }
        return count;
    }
}

//Optimal - DP
//Time Complexity: O(n)
//Space Complexity: O(n)
class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
        int n=nums.length;
        int count=0;
        int[] dp = new int[n];

        if(n < 2){
            return 0;
        }

        for(int i=2; i<n; i++){
            if(nums[i] - nums[i-1] == nums[i-1] - nums[i-2]){
                dp[i] = dp[i-1]+1;
            }
            count += dp[i];
        }
        return count;
    }
}

//Optimal - DP
//Time Complexity: O(n)
//Space Complexity: O(1)
class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
        int n=nums.length;
        int count=0;
        int curr=0, prev=0;

        if(n < 2){
            return 0;
        }

        for(int i=2; i<n; i++){
            if(nums[i] - nums[i-1] == nums[i-1] - nums[i-2]){
                curr = prev+1;
            } else{
                curr=0;
            }
            count += curr;
            prev=curr;
        }
        return count;
    }
}