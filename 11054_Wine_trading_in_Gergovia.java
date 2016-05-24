import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws java.io.IOException {
        Main main = new Main();
        main.start();
    }

    void start() throws java.io.IOException {
        Integer[] trades;
        while(true){
            int pop = Integer.parseInt(br.readLine());//Get total population
            if(pop == 0) break;//Break
            else{//Read all input
                trades = new Integer[pop];
                String[] tmp = br.readLine().split(" ");
                for(int i = 0; i < tmp.length; i++) trades[i] = Integer.parseInt(tmp[i]);
                System.out.println(getMinTravels(trades));
            }
        }
    }

    long getMinTravels(Integer[] trades){
        long travels = 0;
        long demand = 0;
        long supply = 0;
        for(int i = 0; i < trades.length; i++){
            if(trades[i] > 0) demand += trades[i];//Get demand
            if(trades[i] < 0) supply += trades[i] * (-1);//Get supply
            long tmp = demand - supply;
            if(tmp >= 0) travels += tmp;//Add to total travels
            else travels += tmp * (-1);
        }
        return travels;
    }
}
