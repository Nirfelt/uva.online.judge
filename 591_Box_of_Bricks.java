//591 - Box of bricks
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws java.io.IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int set = 0;
        while(true){
            set++;
            int cols = Integer.parseInt(br.readLine());
            if(cols == 0) return;
            int sum = 0;
            String[] n = br.readLine().split(" ");
            int[] values = new int[cols];
            for(int b = 0; b < n.length; b++){//Calc total bricks
                values[b] = Integer.parseInt(n[b]);
                sum += values[b];
            }
            int average = sum/cols;//Get average bricks in column
            int moves = 0;
            //Check and add all cols above average
            for(int h = 0; h < cols; h++) if(values[h] > average) moves += values[h] - average;
            System.out.println(String.format("Set #%s", set));
            System.out.println(String.format("The minimum number of moves is %s.", moves));
            System.out.println();
        }
    }
}
