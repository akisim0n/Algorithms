import java.util.*;

public class Main {
    public static void main(String[] args) {

        System.out.println(701 - 25);
    }


    public int minSubArrayLen(int target, int[] nums) {
        int left = 0, right = 0, sum = 0;
        int subLen = Integer.MAX_VALUE;

        while (right < nums.length) {
            sum += nums[right];

            while (sum >= target && left <= right) {
                int currentLen = right - left + 1;
                if (subLen > currentLen) {
                    subLen = currentLen;
                }
                sum -= nums[left];
                left++;
            }
            right++;
        }

        if (subLen == Integer.MAX_VALUE) {
            subLen = 0;
        }

        return subLen;
    }

    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1, retIndex = -1;
        int mid = nums.length / 2;

        while (left <= right) {
            if (nums[mid] == target) {
                retIndex = mid;
                break;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
            mid = (left + right) / 2;
        }

        return retIndex;
    }

/*    public int guessNumber(int n) {
        int left = 0, right = n, retIndex = -1;
        int mid = n / 2;

        while (left <= right) {
            int tmpVal = guess(mid);
            if (tmpVal == 0) {
                retIndex = mid;
                break;
            } else if (tmpVal == 1) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
            mid = left + (right - left) / 2;
        }

        return retIndex;
    }*/

    public boolean searchMatrix(int[][] matrix, int target) {
        int left = 0, right = matrix.length - 1, matrixNum = -1;
        boolean found = false;

        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] >= target && matrix[i][matrix[i].length - 1] <= target) {
                matrixNum = i;
                break;
            }
        }

        int mid = matrix[matrixNum].length / 2;

        while (left <= right) {
            int tmpVal = matrix[matrixNum][mid];
            if (tmpVal == target) {
                found = true;
                break;
            } else if (tmpVal < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
            mid = left + (right - left) / 2;
        }
        return found;
    }

    public int search1(int[] nums, int target) {
        int left = 0, right = nums.length - 1, pivotIndex = -1, targetIndex = -1, mid = nums.length / 2;

        while (left <= right) {
            if (nums[mid] < nums[right]) {
                right = mid;
            } else if (nums[mid] > nums[right]) {
                left = mid + 1;
            }

            if (nums[left] <= nums[right]) {
                break;
            }

            mid = left + (right - left) / 2;
        }
        pivotIndex = left;

        if (pivotIndex == 0) {
            left = 0;
            right = nums.length - 1;
        } else if (nums[0] > target) {
            left = pivotIndex;
            right = nums.length - 1;
        } else {
            left = 0;
            right = pivotIndex;
        }

        while (left <= right) {
            mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                targetIndex = mid;
                break;
            }

            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return targetIndex;
    }

    public int search2(int[] nums, int target) {
        int left = 0, right = nums.length - 1, targetIndex = -1, mid = nums.length / 2;

        while (left <= right) {

            if (nums[mid] == target) {
                targetIndex = mid;
                break;
            }

            if (nums[mid] >= nums[left]) {
                if (nums[left] <= target && nums[mid] > target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] < target && nums[right] >= target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            mid = left + (right - left) / 2;
        }

        return targetIndex;
    }

    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1, mid;

        while (left <= right) {
            mid = left + (right - left) / 2;

            if (nums[left] <= nums[right]) {
                break;
            }

            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else if (nums[mid] < nums[right]) {
                right = mid;
            }
        }

        return nums[left];
    }

    public boolean search3(int[] nums, int target) {
        int left = 0, right = nums.length - 1, mid;

        while (left <= right) {
            mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return true;
            }

            if (nums[mid] > nums[left]) {
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else if (nums[mid] < nums[left]) {
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                
            }
        }

        return false;
    }

    class Solution {
        public List<List<String>> groupAnagrams(String[] strs) {
            HashMap<String, List<String>> mapStr = new HashMap<>();
            for (String str : strs) {
                char[] charsArr = str.toCharArray();
                Arrays.sort(charsArr);
                String tempStr = String.valueOf(charsArr);
                if (!mapStr.containsKey(tempStr)) {
                    mapStr.put(tempStr, new ArrayList<>(List.of(str)));
                } else {
                    mapStr.get(tempStr).add(str);
                }
            }
            return new ArrayList<>(mapStr.values());
        }
    }


    public List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums.length < 4) return new ArrayList<>();

        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();
        HashSet<String> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > target) continue;

            for (int j = i + 1; j < nums.length; j++) {

                int left = j + 1, right = nums.length - 1;
                long tempVal = nums[i] + nums[j];

                if (tempVal > target) continue;

                while (left < right) {
                    long candidates = tempVal + nums[left] + nums[right];

                    if (candidates < target) {
                        left++;
                    } else if (candidates > target) {
                        right--;
                    } else {
                        StringBuilder sb = new StringBuilder();
                        sb.append(nums[i]);
                        sb.append(nums[j]);
                        sb.append(nums[left]);
                        sb.append(nums[right]);
                        String tempResultString = sb.toString();
                        if (!set.contains(tempResultString)) {
                            set.add(tempResultString);
                            result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        }
                        right--;
                        left++;
                    }
                }

            }
        }
        return result;
    }


    public List<List<String>> groupAnagrams1(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            char[] charsArr = str.toCharArray();
            Arrays.sort(charsArr);
            String tempStr = new String(charsArr);
            if (!map.containsKey(tempStr)) {
                map.put(tempStr, new ArrayList<>());
            }
            map.get(tempStr).add(str);
        }

        return new ArrayList<>(map.values());
    }

    public boolean isAnagram(String s, String t) {
        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char cS = s.charAt(i);
            char cT = t.charAt(i);
            map.put(cS, map.getOrDefault(cS, 0) + 1);
            map.put(cT, map.getOrDefault(cT, 0) - 1);
        }

        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() != 0) {
                return false;
            }
        }
        return true;
    }

    public static List<Integer> findAnagrams(String s, String p) {
        int first=0,second=0, anagLen = p.length();

        if (s.length() < anagLen) return new ArrayList<>();

        HashMap<Character, Integer> templateMap = new HashMap<>();
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < p.length(); i++) {
            char strChar = p.charAt(i);
            templateMap.put(strChar, templateMap.getOrDefault(strChar, 0) + 1);
        }

        HashMap<Character, Integer> map = new HashMap<>();
        while (s.length() - first >= anagLen) {
            int diff = second - first;
            if (diff < anagLen) {
                char sChar = s.charAt(second);
                map.put(sChar, map.getOrDefault(sChar, 0) + 1);
                second++;
            } else {
                if (map.equals(templateMap)) {
                    result.add(first);
                }
                char sChar = s.charAt(first);
                map.put(sChar, map.get(sChar) - 1);
                if (map.get(sChar) == 0) {
                    map.remove(sChar);
                }
                first++;
            }
        }

        return result;
    }

    public static boolean isValid(String s) {

        if (s.length() < 2) return false;

        HashMap<Character, Character> map = new HashMap<>();
        map.put(']', '[');
        map.put('}', '{');
        map.put(')', '(');
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (map.containsKey(ch)) {
                if (stack.isEmpty() || stack.removeFirst() != map.get(ch)) {
                    return false;
                }
            } else {
                stack.push(ch);
            }
        }

        return stack.isEmpty();
    }

    public int numIslands(char[][] grid) {
        int result = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                char ch = grid[i][j];
                if (ch == '1') {
                    result++;
                    dfs(grid, i, j);
                }
            }
        }

        return result;
    }

    public void dfs(char[][] grid, int i, int j) {
        if ((i < 0 || i == grid.length) || (j < 0 || j == grid[0].length) || grid[i][j] == '0') return;

        grid[i][j] = '0';
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
    }

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

        ArrayList<int[]> result = new ArrayList<>();
        int index = 0;
        for (int i = 0; i < intervals.length; i++) {
            int[] interval = intervals[i];
            index = i + 1;
            while (interval[1] >= intervals[index][0]) {
                index++;
                interval[1] = Math.max(interval[1], intervals[index][1]);
            }
            result.add(interval);
            i = index + 1;
        }

        return result.toArray(new int[result.size()][]);
    }

    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> map = new HashMap<>();
        List<String> result = new ArrayList<>();

        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        PriorityQueue<String> wordsList = new PriorityQueue<>((o1, o2) -> map.get(o2) - map.get(o1) == 0 ? o1.compareTo(o2) : map.get(o2) - map.get(o1));

        wordsList.addAll(map.keySet());

        for (int i = 0; i < k; i++) {
            result.add(wordsList.poll());
        }



        return result;
    }

    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        List<Integer> result = new ArrayList<>();

        for (Integer num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Integer> wordsList = new PriorityQueue<>((o1, o2) -> map.get(o2) - map.get(o1));

        wordsList.addAll(map.keySet());

        int[] intRes = new int[k];

        for (int i = k - 1; i >= 0; i--) {
            intRes[i] = wordsList.poll();
        }

        return intRes;
    }

    public static String addBinary(String a, String b) {
        Deque<Character> stack = new ArrayDeque<>();
        int indexA = a.length() - 1;
        int indexB = b.length() - 1;
        int extraValue = 0;

        while (indexA >= 0 && indexB >= 0) {
            char chA = a.charAt(indexA);
            char chB = b.charAt(indexB);

            if (chA == '0' && chB == '0') {
                if (extraValue != 0) {
                    stack.push('1');
                    extraValue = 0;
                } else {
                    stack.push('0');
                }
            } else if (chA == '1' && chB == '1') {
                if (extraValue != 0) {
                    stack.push('1');
                } else {
                    stack.push('0');
                    extraValue = 1;
                }
            } else {
                stack.push('1');
            }
            indexA--;
            indexB--;
        }

        while (indexA >= 0) {
            char chA = a.charAt(indexA);

            if (extraValue > 0) {
                if (chA == '1') {
                    stack.push('0');
                } else {
                    stack.push('1');
                    extraValue = 0;
                }
            } else {
                stack.push(chA);
            }

            indexA--;
        }

        while (indexB >= 0) {
            char chB = b.charAt(indexB);

            if (extraValue > 0) {
                if (chB == '1') {
                    stack.push('0');
                } else {
                    stack.push('1');
                    extraValue--;
                }
            } else {
                stack.push(chB);
            }

            indexB--;
        }

        if (extraValue > 0) {
            stack.push('1');
        }

        StringBuilder sb = new StringBuilder();

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    public static String multiply(String num1, String num2) {
        int extraValue = 0;
        int num1Index = num1.length() - 1;
        int num2Index = num2.length() - 1;
        StringBuilder sb = new StringBuilder();

        while (num1Index > -1 || num2Index > -1 || extraValue > 0) {
            int sum = 1;
            if (num1Index > -1) {
                int val1 = num1.charAt(num1Index) - '0';
                sum = sum * val1;
                num1Index--;
            }

            if (num2Index > -1) {
                int val2 = num2.charAt(num2Index) - '0';
                sum = sum * val2;
                num2Index--;
            }

            sum += extraValue;

            extraValue = sum / 10;

            sb.append(sum % 10);
        }

        return sb.reverse().toString();

    }

    public static boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (wordDfs(board, i, j, 0, word)) return true;
            }
        }
        return false;
    }

    public static boolean wordDfs(char[][] board, int index1, int index2, int wordIndex, String word) {

        boolean result;

        if (index1 < 0 || index1 >= board.length || index2 < 0 || index2 >= board[0].length || board[index1][index2] != word.charAt(wordIndex)) {
            return false;
        }

        wordIndex++;

        if (wordIndex == word.length()) {
            return true;
        }

        char used = board[index1][index2];
        board[index1][index2] = '#';

        result = wordDfs(board, index1 + 1, index2, wordIndex, word);
        result = result || wordDfs(board, index1 - 1, index2, wordIndex, word);
        result = result || wordDfs(board, index1, index2 + 1, wordIndex, word);
        result = result || wordDfs(board, index1, index2 - 1, wordIndex, word);

        board[index1][index2] = used;

        return result;
    }


    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();

        int n = matrix.length;
        int m = matrix[0].length;

        int left = 0, right = m - 1, top = 0, bottom = n - 1;

        while(left <= right && top <= bottom) {
            for(int i = left; i <= right; i++) {
                result.add(matrix[top][i]);
            }
            top++;

            for(int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            right--;

            if(top <= bottom) {
                for(int i = right; i >= left; i--) {
                    result.add(matrix[bottom][i]);
                }
                bottom--;
            }

            if(left <= right) {
                for(int i = bottom; i >= top; i--) {
                    result.add(matrix[i][left]);
                }
                left++;
            }
        }

        return result;
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);

        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for (int endIndex = 1; endIndex <= s.length(); endIndex++) {
            for (int startIndex = 0; startIndex < endIndex; startIndex++) {

                if (dp[startIndex] && set.contains(s.substring(startIndex, endIndex))) {
                    dp[endIndex] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    public static String reverseWords(String s) {
        s = s.trim();
        StringBuilder sb = new StringBuilder();

        int index1 = s.length() - 1;
        while (index1 >= 0) {
            char ch = s.charAt(index1);
            if (ch != ' ') {
                int index2 = index1 - 1;
                while (index2 >= 0 && ch != ' ') {
                    ch = s.charAt(index2);
                    index2--;
                }
                if (index2 == - 1) {
                    sb.append(' ');
                }
                sb.append(s.substring(index2 + 1 , index1 + 1));
                index1 = index2;
            } else {
                index1--;
            }
        }

        return sb.toString().trim();
    }

    public static int compareVersion(String version1, String version2) {
        int len1 = version1.length();
        int len2 = version2.length();

        int index1 = 0, index2 = 0;

        while (index1 < len1 && index2 < len2) {
            int rev1 = 0;
            while (index1 < len1 && version1.charAt(index1) != '.') {
                rev1 = rev1 * 10 + version1.charAt(index1) - '0';
                index1++;
            }

            int rev2 = 0;
            while (index2 < len2 && version2.charAt(index2) != '.') {
                rev2 = rev2 * 10 + version2.charAt(index2) - '0';
                index2++;
            }

            if (rev1 != rev2) {
                return rev1 > rev2 ? 1 : -1;
            }
            index1++;
            index2++;
        }
        return 0;
    }


}
