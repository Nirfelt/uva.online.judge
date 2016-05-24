//10878 - Decode the tape
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws java.io.IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String output = "";
        int[] mapping = {1,2,4,8,16,32,64,128};//Binary mapping
        br.readLine();
        while(true){
        String input = br.readLine();//Get input row
        if(input.endsWith("_")) break;
            int sum = 0;
            int index = 0;
            for (int i = 9; i >= 0; i--) {
                if(input.charAt(i) == 'o') sum += mapping[index];//Get sum of row
                if(input.charAt(i) != '.') index++;
            }
            output += Character.toString((char)sum);//Translate row to binary symbol, add to output
        }
        System.out.print(output);
    }
}
