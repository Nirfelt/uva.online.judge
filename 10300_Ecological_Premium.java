//10300 - Ecological premium
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws java.io.IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int test = Integer.parseInt(br.readLine());

        for(int i = 0; i < test; i++){
            int farmers = Integer.parseInt(br.readLine());//Get number of farmers
            int sum = 0;
            for(int v = 0; v < farmers; v++){
                String[] n = br.readLine().split(" ");
                float[] values = new float[3];
                for(int b = 0; b < 3; b++) values[b] = Float.parseFloat(n[b]);//Get farmer values
                float premium = ((values[0]/values[1])*values[2])*values[1];//Calc sum
                sum += Math.round(premium);
            }
            System.out.println(String.format("%s", sum));
        }
    }
}
