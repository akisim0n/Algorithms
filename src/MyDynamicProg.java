import java.util.Arrays;

public class MyDynamicProg {
    public static void main(String[] args) {
        System.out.println(houseRobber(new int[] {2,7,9,3,1}));
    }

    public static int climbStairs(int n) {
/*        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];*/
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int prevValue1 = 1;
        int prevValue2 = 2;
        int currentValue = 0;
        for (int i = 3; i <= n; i++) {
            currentValue = prevValue1 + prevValue2;
            prevValue1 = prevValue2;
            prevValue2 = currentValue;

        }
        return currentValue;
    }

    public static int bestTimeToBuyAndSellStock(int[] prices) {
/*        int profit = 0;
        int index = 1;
        int index2;
        while (index < prices.length) {
            if (prices[index] > prices[index - 1] ) {
                int lowPrice = prices[index - 1];
                int highPrice = 0;
                index2 = index;
                while (index2 < prices.length) {
                    if (prices[index2] > highPrice) {
                        highPrice = prices[index2];
                    }
                    index2++;
                }
                int diff = highPrice - lowPrice;
                if (profit < diff) profit = diff;
            }
            index++;
        }
        return profit;*/
        int minValue = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < minValue) {
                minValue = prices[i];
            } else if (maxProfit < prices[i] - minValue) {
                maxProfit = prices[i] - minValue;
            }
        }
        return maxProfit;
    }

    public static int maximumSubArray(int [] nums) {
        //Kaden's algorithm
/*        int sum = nums[0];
        int maxSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (sum < 0) {
                sum = nums[i];
            } else {
                sum += nums[i];
            }
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;*/

        int [] result = new int[nums.length];
        result[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            result[i] = Math.max(nums[i], result[i - 1] + nums[i]);
        }
        return Arrays.stream(result).max().getAsInt();

    }

    public static int houseRobber(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        for (int i = 1; i < nums.length; i++) {
            if (i == 1) {
                nums[i] = Math.max(nums[0], nums[1]);
            } else {
                nums[i] = Math.max(nums[i - 1], nums[i] + nums[i - 2]);
            }
        }
        return nums[nums.length - 1];
    }

    public static int numDecodings(String s) {

        if (s == null || s.isEmpty() || s.charAt(0) == '0') {
            return 0;
        }

        int[] dp = new int[s.length()];
        dp[0] = 1;
        for (int i = 1 ; i < s.length() - 1; i++) {
            int prevVal = dp[i-1];
            char ch = s.charAt(i);
            if (ch == '0') {
                dp[i] = prevVal;
                continue;
            }
            char prevCh = s.charAt(i-1);
            int num1 = (prevCh - '0') * 10 + ch - '0';
            if (num1 > 9 && num1 < 27 && prevCh != '0') {
                dp[i] = prevVal + 1;
            } else {
                dp[i] = prevVal;
            }
        }

        return dp[dp.length - 1];
    }

}
