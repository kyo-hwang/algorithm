import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static String wordFinding;
    public static Map<Character, List<Integer>> wordCharacterMapping;
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        wordFinding = bf.readLine();
        wordCharacterMapping = new HashMap<>();
        for(int i=0;i<wordFinding.length();i++){
            if(wordCharacterMapping.containsKey(wordFinding.charAt(i))){
                wordCharacterMapping.get(wordFinding.charAt(i)).add(i);
            }else{
                List<Integer> characterLocs = new ArrayList<>();
                characterLocs.add(i);
                wordCharacterMapping.put(wordFinding.charAt(i),characterLocs);
            }
        }
        String text = bf.readLine();
        Deque<Character> textDeque = new ArrayDeque<>();
        for(int i=0;i<text.length();i++){
            textDeque.add(text.charAt(i));
        }

        Deque<Character> frontQue = new ArrayDeque<>();
        Deque<Character> backQue = new ArrayDeque<>();

        StringBuffer frontResult = new StringBuffer();
        StringBuffer backResult = new StringBuffer();
        boolean isFrontTurn = true;
        while(!textDeque.isEmpty()){
            if(isFrontTurn){
                frontResult.append(getFrontResult(frontQue,textDeque));
            }
            else{
                backResult.append(getBackResult(backQue,textDeque));
            }
            isFrontTurn = !isFrontTurn;
        }


        while(!backQue.isEmpty()){
            frontResult.append(getFrontResult(frontQue,backQue));
        }

        while(!frontQue.isEmpty()){
            frontResult.append(frontQue.pollLast());
        }
        frontResult.append(backResult.reverse());
        System.out.println(frontResult);
    }
    public static StringBuffer getBackResult(Deque<Character> backQue,Deque<Character> textDeque){
        StringBuffer result = new StringBuffer();
        while (!textDeque.isEmpty()){
            Character curChar = textDeque.pollLast();
            boolean isRightCharacter = false;
            List<Integer> locCandidates = wordCharacterMapping.get(curChar);
            if(locCandidates!=null){
                for(int locCandidate:locCandidates){
                    StringBuffer temp = new StringBuffer();
                    temp.append(curChar);
                    int backLength = wordFinding.length()-locCandidate-1;
                    if(backLength>backQue.size()){
                        continue;
                    }
                    for(int i=0;i<backLength;i++){
                        temp.append(backQue.pop());
                    }
                    if(temp.toString().equals(wordFinding.substring(locCandidate))){
                        for(int i=temp.length()-1;i>=0;i--){
                            backQue.offerFirst(temp.charAt(i));
                        }
                        if(backQue.size()>=wordFinding.length()&&curChar==wordFinding.charAt(0)){
                            StringBuffer removeCandidate = new StringBuffer();
                            for(int i=0;i<wordFinding.length();i++){
                                removeCandidate.append(backQue.pollFirst());
                            }
                            if(!removeCandidate.toString().equals(wordFinding)){
                                for(int i=wordFinding.length()-1;i>=0;i--){
                                    backQue.offerFirst(removeCandidate.charAt(i));
                                }
                            }else{
                                return result;
                            }
                        }
                        isRightCharacter = true;
                        break;
                    }else{
                        for(int i=temp.length()-1;i>=1;i--){
                            backQue.offerFirst(temp.charAt(i));
                        }
                    }
                }
            }
            if(!isRightCharacter){
                StringBuffer tempString = new StringBuffer();
                tempString.append(curChar);
                while (!backQue.isEmpty()){
                    tempString.append(backQue.pollFirst());
                }
                result.append(tempString.reverse());
            }
        }
        return result;
    }


    public static StringBuffer getFrontResult(Deque<Character> frontQue,Deque<Character> textDeque){
        StringBuffer result = new StringBuffer();
        while(!textDeque.isEmpty()){
            boolean isRightCharacter = false;
            Character curChar = textDeque.pollFirst();
            List<Integer> locCandidates = wordCharacterMapping.get(curChar);
            if(locCandidates!=null){
                for(int locCandidate:locCandidates){
                    StringBuffer temp =new StringBuffer();
                    temp.append(curChar);
                    if(frontQue.size()<locCandidate){
                        continue;
                    }
                    for(int i=0;i<locCandidate;i++){
                        temp.append(frontQue.pop());
                    }

                    temp.reverse();

                    if(temp.toString().equals(wordFinding.substring(0,locCandidate+1))){
                        for(int i=0;i<temp.length();i++){
                            frontQue.push(temp.charAt(i));
                        }

                        if(frontQue.size()>=wordFinding.length()&&wordFinding.charAt(wordFinding.length()-1)==curChar){
                            StringBuffer removeCandidate = new StringBuffer();
                            for(int i=0;i<wordFinding.length();i++){
                                removeCandidate.append(frontQue.pop());
                            }

                            removeCandidate = removeCandidate.reverse();
                            //찾으려는 문자열이 아니라면 원상복구
                            if(!removeCandidate.toString().equals(wordFinding)){
                                for(int i=0;i<wordFinding.length();i++){
                                    frontQue.push(removeCandidate.charAt(i));
                                }
                            }//찾으려는 문자열이라면 끝
                            else{
                                return result;
                            }
                        }
                        isRightCharacter = true;
                        break;
                    }else{
                        for(int i=0;i<temp.length()-1;i++){
                            frontQue.push(temp.charAt(i));
                        }
                    }
                }
            }
            if(!isRightCharacter){
                while (!frontQue.isEmpty()){
                    result.append(frontQue.pollLast());
                }
                result.append(curChar);
            }
        }
        return result;
    }
}
