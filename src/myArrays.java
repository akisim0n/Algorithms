import java.util.*;

public class myArrays {
    public static void main(String[] args) {

        System.out.println(countAndSay(4));

    }

    public static int containsDuplicates(int[] nums) {
/*        HashSet<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            if (numSet.contains(num)) {
                numSet.remove(num);
            } else {
                numSet.add(num);
            }
        }
        return numSet.iterator().next();*/
        return 1;
    }

    public static int[] intersectionOfTwoArrays(int[] nums1, int[] nums2) {
        ArrayList<Integer> result = new ArrayList<>();
        HashMap<Integer, Integer> nums1Map = new HashMap<>();
        for (int nums : nums1) {
            if (nums1Map.containsKey(nums)) {
                nums1Map.put(nums, nums1Map.get(nums) + 1);
            } else {
                nums1Map.put(nums, 1);
            }
        }
        for (int nums : nums2) {
            if (nums1Map.containsKey(nums) && nums1Map.get(nums) != 0) {
                result.add(nums);
                nums1Map.put(nums, nums1Map.get(nums) - 1);
            }
        }
        return result.stream().mapToInt(i -> i).toArray();
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                int low = i + 1, high = nums.length - 1;
                int sum = -nums[i];
                while (low < high) {
                    if (nums[low] + nums[high] == sum) {
                        result.add(Arrays.asList(nums[i], nums[low], nums[high]));
                        while (low < high && nums[low] == nums[low + 1]) {
                            low++;
                        }
                        while (low < high && nums[high] == nums[high - 1]) {
                            high--;
                        }
                        low++;
                        high--;
                    } else if (nums[low] + nums[high] < sum) {
                        low++;
                    } else {
                        high--;
                    }
                }
            }
        }
        return result;
    }

    public static void setMatrixZeroes(int [][] matrix) {
/*        boolean [] columnZeroes = new boolean[matrix.length];
        boolean [] rowZeroes = new boolean[matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    columnZeroes[i] = true;
                    rowZeroes[j] = true;
                }
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (columnZeroes[i] || rowZeroes[j]) {
                    matrix[i][j] = 0;
                }
            }
        }*/
        boolean columnFlag = false;
        boolean rowFlag = false;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    if (i == 0) {
                        columnFlag = true;
                    }
                    if (j == 0) {
                        rowFlag = true;
                    }
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (columnFlag) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
        if (rowFlag) {
            for (int i = 0; i < matrix[0].length; i++) {
                matrix[0][i] = 0;
            }
        }
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> mapStr = new HashMap<>();
        for (String str : strs) {
            char[] charsArr = str.toCharArray();
            Arrays.sort(charsArr);
            String tempStr = String.valueOf(charsArr);
            if (!mapStr.containsKey(tempStr)) {
                mapStr.put(tempStr, new ArrayList<>(List.of(str)));
            }
            mapStr.get(tempStr).add(str);
        }
        return new ArrayList<>(mapStr.values());
    }

    public static int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int j = 0;
        int result = 0;
        int i = 0;
        while (j < s.length()) {
            char letter = s.charAt(i);
            if (map.containsKey(letter)) {
                i = Math.max(map.get(letter), i);
            }
            result = Math.max(result, j - i + 1);
            map.put(letter, j + 1);
            j++;
        }
        return result;
    }

    public static String countAndSay(int n) {
        if (n == 1) return "1";
        String result = "11";
        int index = 2;
        while (index < n) {
            int count = 1;
            String tempResult = "";
            char letter = result.charAt(0);
            for (int i = 0; i < result.length() - 1; i++) {
                if (letter != result.charAt(i + 1)) {
                    tempResult += Integer.toString(count) + letter;
                    letter = result.charAt(i + 1);
                    count = 0;
                }
                count++;
            }
            result = tempResult + count + letter;
            index++;
        }
        return result;
    }

}