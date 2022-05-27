import java.util.ArrayList;
import java.util.List;

public class BalancedWordsCounter {

    private String word;

    private boolean isStringOnlyAlphabet(String str)
    {
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if ((!(ch >= 'A' && ch <= 'Z'))
                    && (!(ch >= 'a' && ch <= 'z'))) {
                return false;
            }
        }
        return true;
    }

    private boolean isBalanced(String word){
        List<Character> listOfLetters = new ArrayList<Character>();
        boolean result = false;
        int counter;
        int referencialCount = 0;
        for(int i = 0; i < word.length(); i++){
            counter = 0;
            if(listOfLetters.contains(word.charAt(i)))
                continue;
            for(int j = i + 1; j < word.length(); j++){
                if(word.charAt(i) == word.charAt(j))
                    counter++;
            }
            if(i == 0) {
                referencialCount = counter;
                result = true;
            }
            else{
                if(counter == referencialCount)
                    result = true;
                else
                    return false;
            }
            listOfLetters.add(word.charAt(i));
        }

        return result;
    }

    public int count(String word) throws RuntimeException{
        int result = 0;
        if(word == null || !isStringOnlyAlphabet(word))
            throw new RuntimeException();
        for (int i = 1; i < word.length(); i++){
            for(int j = 0; j + i <= word.length(); j++){
                String subword = word.substring(j, j + i);
                if(isBalanced(subword))
                    result++;
            }
        }
        return result;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

}
