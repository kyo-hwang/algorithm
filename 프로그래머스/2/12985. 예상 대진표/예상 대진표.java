import java.util.*;
import java.io.*;
class Solution
{
    public int solution(int n, int a, int b)
    {
        
        int answer = 0;
        
        while(a!=b){
            a = (a+1)/2;
            b = (b+1)/2;
            answer++;
        }
        
        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println(n);

        return answer;
    }
}