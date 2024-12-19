import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        List<String> result = new ArrayList<>();

        while(true){
            String line = bf.readLine();
            if(line==null){
                return;
            }
            if(parse(line)){
                System.out.println("valid");
            }
            else{
                System.out.println("invalid");
            }
        }
    }
    public static boolean parse(String line){
        Deque<String> context  = new ArrayDeque<>();
        for(int i=0;i<line.length();i++){
//            System.out.println(i);
            char curChar = line.charAt(i);
            if((int)curChar<32||(int)curChar>127){
                return false;
            }
            if(curChar=='<'){
                int length = calTagLength(line,i);
                if(length==-1){
                    return false;
                }
                String tag = line.substring(i,i+length);
                if(tag.charAt(1)=='/'){
                    if(context.isEmpty()){
                        return false;
                    }
                    String openBefore = context.pop();
                    String beforeContent = openBefore.substring(1,openBefore.length()-1);
                    String tagContent = tag.substring(2,tag.length()-1);
                    if(!beforeContent.equals(tagContent)){
                        return false;
                    }
                    i+=(length-1);
                    continue;
                } else if (tag.charAt(tag.length()-2)=='/') {
                    i+=(length-1);
                    continue;
                }else{
                    i+=(length-1);
                    context.push(tag);
                }
            }
            else if(curChar=='&'){
                int length = calEncodeLength(line,i);
                if(length==-1){
                    return false;
                }
                if(line.charAt(i+1)=='x'&&length%2==0){
                    return false;
                }
                i+=(length-1);
            }
            else if(curChar=='>'){
                return false;
            }
//            System.out.println(context);
        }
        if(!context.isEmpty()){
            return false;
        }
        return true;
    }

    public static int calEncodeLength(String string,int start){
        if(string.length()-1>=start+3) {
            String encode4 = string.substring(start, start + 4);
            if (encode4.equals("&lt;") || encode4.equals("&gt;")) {
                return 4;
            }
        }
        if(string.length()-1>=start+4){
            String encode5 = string.substring(start,start+5);
            if(encode5.equals("&amp;")){
                return 5;
            }
        }
        if(string.length()-1>=start+3){
            if(string.charAt(start+1)!='x'){
                return -1;
            }
            if(!isRightHexChr(string.charAt(start+2))){
                return -1;
            }
            for(int i=start+3;i<string.length();i++){
                if(!isRightHexChr(string.charAt(i))){
                    if(string.charAt(i)==';'){
                        return i-start+1;
                    }
                    return -1;
                }
            }
        }
        return -1;
    }

    public static boolean isRightHexChr(char chr){
        int chrNum = (int)chr;
        if(chrNum>='0'&&chrNum<='9'){
            return true;
        }
        else if((chrNum>='a'&&chrNum<='f')||(chrNum>='A'&&chrNum<='F')){
            return true;
        }
        return false;
    }


    /*
    태그가 숫자와 소문자로 이루어져 있는지 검사
    태그를 제대로 닫았는지 검사
    제대로 된 태그가 아닐 경우 -1
     */
    public static int calTagLength(String string,int start){
        if(start+1>string.length()-1){
            return -1;
        }
        if(string.charAt(start+1)=='/'){
            return calCloseTagLength(string,start);
        }
        else{
            return calOpenTagLength(string,start);
        }
    }

    public static int calOpenTagLength(String line,int start){
        if(line.length()-1<start+2){
            return -1;
        }
        if(!isRightTagChar(line.charAt(start+1))){
            return -1;
        }
        for(int i=start+2;i<line.length();i++){
            if(!isRightTagChar(line.charAt(i))){
                if(line.charAt(i)=='>'){
                    return i-start+1;
                }
                else if(line.charAt(i)=='/'){
                    if(i+1<line.length()&&line.charAt(i+1)=='>'){
                        return i+1-start+1;
                    }
                }
                return -1;
            }
        }
        return -1;
    }

    public static int calCloseTagLength(String line,int start){
        if(line.length()-1<start+3){
            return -1;
        }
        if(!isRightTagChar(line.charAt(start+2))){
            return -1;
        }
        for(int i=start+3;i<line.length();i++){
            if(!isRightTagChar(line.charAt(i))){
                if(line.charAt(i)=='>'){
                    return i-start+1;
                }
                return -1;
            }
        }
        return -1;
    }

    public static boolean isRightTagChar(char chr){
        int charNum = (int)chr;
        if((charNum>=(int)'a'&&charNum<=(int)'z')||(charNum>=(int)'0'&&charNum<=(int)'9')){
            return true;
        }
        return false;
    }

}
