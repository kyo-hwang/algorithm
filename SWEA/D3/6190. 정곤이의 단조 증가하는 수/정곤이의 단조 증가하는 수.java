import java.io.*;
import java.util.*;

public class Solution{
	public static void main(String args[]) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(bf.readLine());
		for(int i=1;i<=t;i++) {
			int n = Integer.parseInt(bf.readLine());
			int[] nums = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			System.out.println("#"+i+" "+calMaxIncrementingNumber(nums));
		}
		
	}
	
	public static int calMaxIncrementingNumber(int[] nums) {
		int maxNum = -1;
		for(int i=0;i<nums.length;i++) {
			for(int j=i+1;j<nums.length;j++) {
				int multiNum = nums[i]*nums[j];
				if(isIncrementing(multiNum)) {
					maxNum = Math.max(maxNum,multiNum);
				}
			}
		}
		return maxNum;
	}
	
	public static boolean isIncrementing(int multiNum) {
		String multiNumString = Integer.toString(multiNum);
		for(int k=1;k<multiNumString.length();k++) {
			if(multiNumString.charAt(k)<multiNumString.charAt(k-1)) {
				return false;
			}
		}
		return true;
	}

}
