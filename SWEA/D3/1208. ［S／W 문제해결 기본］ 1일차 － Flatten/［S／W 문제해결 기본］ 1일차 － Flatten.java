/////////////////////////////////////////////////////////////////////////////////////////////
// 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
// 아래 표준 입출력 예제 필요시 참고하세요.
// 표준 입력 예제
// int a;
// double b;
// char g;
// String var;
// long AB;
// a = sc.nextInt();                           // int 변수 1개 입력받는 예제
// b = sc.nextDouble();                        // double 변수 1개 입력받는 예제
// g = sc.nextByte();                          // char 변수 1개 입력받는 예제
// var = sc.next();                            // 문자열 1개 입력받는 예제
// AB = sc.nextLong();                         // long 변수 1개 입력받는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
// 표준 출력 예제
// int a = 0;                            
// double b = 1.0;               
// char g = 'b';
// String var = "ABCDEFG";
// long AB = 12345678901234567L;
//System.out.println(a);                       // int 변수 1개 출력하는 예제
//System.out.println(b); 		       						 // double 변수 1개 출력하는 예제
//System.out.println(g);		       						 // char 변수 1개 출력하는 예제
//System.out.println(var);		       				   // 문자열 1개 출력하는 예제
//System.out.println(AB);		       				     // long 변수 1개 출력하는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
import java.io.*;
import java.util.*;


/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int t = 10;
		for(int i=0;i<t;i++) {
			int dumps = Integer.parseInt(bf.readLine());
			int[] heights = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			System.out.println("#"+(i+1)+" "+flatten(dumps,heights));
		}
	}
	
	public static int flatten(int dumps, int[] heigths) {
		for(int i=0;i<dumps;i++) {
			int maxLoc = calMaxHeightLoc(heigths);
			int minLoc = calMinHeightLoc(heigths);
			if(heigths[maxLoc]==heigths[minLoc]) {
				return 0;
			}
			if(heigths[maxLoc]-heigths[minLoc]==1) {
				return 1;
			}
			heigths[maxLoc]-=1;
			heigths[minLoc]+=1;
		}
		return heigths[calMaxHeightLoc(heigths)]-heigths[calMinHeightLoc(heigths)];
		
	}
	
	public static int calMaxHeightLoc(int[] heigths) {
		int loc = 0;
		for(int i=1;i<heigths.length;i++) {
			if(heigths[i]>heigths[loc]) {
				loc = i;
			}
		}
		return loc;
	}
	
	public static int calMinHeightLoc(int[] heights) {
		int loc = 0;
		for(int i=1;i<heights.length;i++) {
			if(heights[i]<heights[loc]) {
				loc = i;
			}
		}
		return loc;
	}
}