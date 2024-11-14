import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(bf.readLine());
		
		for(int i=1;i<=t;i++) {
			int[] input = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int[] arriveTime = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			if(doWait(input,arriveTime)) {
				System.out.println("#"+i+" "+"Impossible");
				continue;
			}
			System.out.println("#"+i+" "+"Possible");
		}

	}
	
	public static boolean doWait(int[] input, int[] arriveTime) {
		Arrays.sort(arriveTime);
		int n = input[0];
		int timeToComplete = input[1];
		int countOnce = input[2];
		
		for(int i=0;i<n;i++) {
			int breadCount = arriveTime[i]/timeToComplete*countOnce-i;
			if(breadCount==0) {
				return true;
			}
		}
		return false;
	}
	

}
