//10340 - All in all
import java.util.*;

public class Main {

    public static void main(String[] args) throws java.io.IOException {
        Main main = new Main();
        main.start();
    }

    void start() throws java.io.IOException {
        Scanner input = new Scanner(System.in);
        while(input.hasNextLine()) {
            String[] tmp = input.nextLine().split(" ");
            if(checkStrings(tmp[0], tmp[1])) System.out.println("Yes");
            else System.out.println("No");
        }
    }

    boolean checkStrings(String s, String toTest){
        int c = 0;
        for(int i = 0; i < toTest.length(); i++){
            if(c < s.length()) if(s.charAt(c) == toTest.charAt(i)) c++;//Match symbols
            else return true;//If all symbols found in correct order return true
        }
        if(c == s.length()) return true;//Check if length is correct
        return false;
    }
}
