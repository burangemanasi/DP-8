//120. Traingle - https://leetcode.com/problems/triangle/description/

//DFS (Top Down approach)
//Time Complexity: O(2^n) Exponential Solution
//Time Limit exceeded
class Solution {
    int min;
    public int minimumTotal(List<List<Integer>> triangle) {
        this.min = Integer.MAX_VALUE;
        helper(triangle, 0, 0, 0); //row,col,sum initially is 0
        return min;
    }

    private void helper(List<List<Integer>> triangle, int r, int c, int sum){
        //base case
        if(r == triangle.size()){
            min = Math.min(min, sum);
            return;
        }
        //logic
        helper(triangle, r+1, c, sum + triangle.get(r).get(c));
        helper(triangle, r+1, c+1, sum + triangle.get(r).get(c));
    }
}


//DFS - Top Down approach (int based Recursion)
//Time Complexity: O(2^n) Exponential Solution
//Time Limit exceeded
class Solution {
    int min;
    public int minimumTotal(List<List<Integer>> triangle) {
        return helper(triangle, 0, 0, 0); //row,col,sum initially is 0
    }

    private int helper(List<List<Integer>> triangle, int r, int c, int sum){
        //base case
        if(r == triangle.size()){
            return sum;
        }
        //logic
        int left = helper(triangle, r+1, c, sum + triangle.get(r).get(c));
        int right = helper(triangle, r+1, c+1, sum + triangle.get(r).get(c));

        return Math.min(left, right);
    }
}

//Memoization with Bottom Top Approach
//DP
//Time Complexity: O(n)
class Solution {
    int[][] memo;
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        this.memo = new int[n][n];
        //filling with MAX_VALUE to differentiate between sum value
        //and un-visited node
        for(int i=0; i<n; i++){
            Arrays.fill(memo[i], Integer.MAX_VALUE);
        }
        return helper(triangle, 0, 0); //row,col,sum initially is 0
    }

    private int helper(List<List<Integer>> triangle, int r, int c){
        //base case
        if(r == triangle.size()){
            return 0;
        }
        //can't use 0 as unvisited can be 0, sum can also be 0
        //thus considering MAX_VALUE
        if(memo[r][c] != Integer.MAX_VALUE){
            return memo[r][c];
        }
        //logic
        int left = helper(triangle, r+1, c);
        int right = helper(triangle, r+1, c+1);

        memo[r][c] =  Math.min(left, right) + triangle.get(r).get(c);

        return memo[r][c];
    }
}

//Time Complexity: O(n)
//Space Complexity: O(1)
//Top Down Approach
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();

        for(int i=1; i<n; i++){ //triangle.get(i)
            for(int j=0; j<=i; j++){
                if(j == 0){
                    triangle.get(i).set(j, triangle.get(i).get(j) + triangle.get(i-1).get(0));
                } else if(j == i){
                    triangle.get(i).set(j, triangle.get(i).get(j) + triangle.get(i-1).get(j-1));
                } else {
                    triangle.get(i).set(j, triangle.get(i).get(j) +
                            (Math.min(triangle.get(i-1).get(j-1), triangle.get(i-1).get(j))));
                }
            }
        }

        int result=Integer.MAX_VALUE;
        for(int j=0; j<n; j++){
            result = Math.min(result, triangle.get(n-1).get(j));
        }
        return result;
    }
}

//Bottom-Up
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();

        int[][] dp = new int[n][n];

        for(int j=0; j<n; j++){
            dp[n-1][j] = triangle.get(n-1).get(j);
        }

        for(int i=n-2; i>=0; i--){
            for(int j=0; j<=i; j++){
                dp[i][j] = triangle.get(i).get(j) +
                        Math.min(dp[i+1][j], dp[i+1][j+1]); //next row-same column, next row-col+1
            }
        }

        //return root
        return dp[0][0];
    }
}
