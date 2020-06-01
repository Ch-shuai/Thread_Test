package test;

//给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
//
// 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
//
//
//
// 示例:
//
// 给定 nums = [2, 7, 11, 15], target = 9
//
//因为 nums[0] + nums[1] = 2 + 7 = 9
//所以返回 [0, 1]
//
// Related Topics 数组 哈希表

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

class TwoSum {

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		System.out.println("startTime" + startTime);
		Solution2 solution2 = new TwoSum().new Solution2();
		int[] nums = {9, 12, 4465, 15489, 1654184189, 13218418, 15618, 123189, 1564615, 1564624};
		int target = 1564624;
		int[] ints = solution2.twoSum(nums, target);
		System.out.println("ints[0]" + ints[0] + "ints[1]" + ints[1]);
		long endTime = System.currentTimeMillis();
		System.out.println("endTime" + endTime);

		System.out.println("startTime-endTime" + (startTime - endTime));
	}

	//leetcode submit region end(Prohibit modification and deletion)
	class Solution2 {
		public int[] twoSum(int[] nums, int target) {
			int[] indexs = new int[2];

			// 建立k-v ，一一对应的哈希表
			HashMap<Integer, Integer> hash = new HashMap<Integer, Integer>();
			for (int i = 0; i < nums.length; i++) {
				if (hash.containsKey(nums[i])) {
					indexs[0] = i;
					indexs[1] = hash.get(nums[i]);
					return indexs;
				}
				// 将数据存入 key为补数 ，value为下标
				hash.put(target - nums[i], i);
			}
			// // 双重循环 循环极限为(n^2-n)/2
			// for(int i = 0; i < nums.length; i++){
			//     for(int j = nums.length - 1; j > i; j --){
			//         if(nums[i]+nums[j] == target){
			//            indexs[0] = i;
			//            indexs[1] = j;
			//            return indexs;
			//         }
			//     }
			// }
			return indexs;
		}
	}
}
