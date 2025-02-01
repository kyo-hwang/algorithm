import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main{
    public static class Student implements Comparable<Student>{
        int classId;
        int ability;
        public Student(int classId,int ability){
            this.classId = classId;
            this.ability = ability;
        }

        @Override
        public int compareTo(Student student){
            return this.ability - student.ability;
        }

        public String toString(){
            return "{classId : "+classId+", ability : "+ability+"}";
        }
    }

    public static int n;
    public static int m;
    public static int[] intervalClassStudentCount;
    public static List<Student> students;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        Integer[] input = Arrays.stream(bf.readLine().split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
        n = input[0];
        m = input[1];
        intervalClassStudentCount = new int[n];
        students = new ArrayList<>();
        for(int i=0;i<n;i++){
            input = Arrays.stream(bf.readLine().split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
            for(int j=0;j<m;j++){
                students.add(new Student(i,input[j]));
            }
        }
        students.sort(Student::compareTo);
        int left =0;
        int right=0;
        int countLeft = n;
        for(Student student:students){
            intervalClassStudentCount[student.classId]++;
            if(intervalClassStudentCount[student.classId]==1){
                countLeft--;
            }
            if(countLeft==0){
                break;
            }
            right++;
        }

        int result = Integer.MAX_VALUE;

        while(right<m*n){
            result = Math.min(students.get(right).ability-students.get(left).ability,result);
            if(intervalClassStudentCount[students.get(left).classId]-1>0){
                intervalClassStudentCount[students.get(left).classId]--;
                left++;
            }
            else{
                right++;
                if(right>=m*n){
                    break;
                }
                intervalClassStudentCount[students.get(right).classId]++;
            }
        }

        System.out.println(result);
    }

}
