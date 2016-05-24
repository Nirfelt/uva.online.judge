//10943 - How do you add?
import java.util.*;

public class Main {

    public static void main(String[] args) throws java.io.IOException {
        Main main = new Main();
        main.start();
    }

    void start() throws java.io.IOException {
        Scanner input = new Scanner(System.in);
        Integer[][] G = new Integer[102][102];
        while(input.hasNextLine()) {
            String[] tmp = input.nextLine().split(" ");
            int n = Integer.parseInt(tmp[0]);
            if(n == 0) return;
            int k = Integer.parseInt(tmp[1]);
            for(int i = 0; i < 102; i++) for(int c = 0; c < 102; c++) G[i][c] = -1;//Empty matrix
            System.out.println(adds(G, 0, n, k));

        }
    }

    int adds(Integer[][] G, int s, int j, int k){
        j %= 1000000;
        if(j < 0) return 0;//Endstatements
        if(s == k && j == 0) return 1;
        if(s == k && j != 0) return 0;
        if(G[s][j] != -1) return G[s][j];
        int sum = 0;
        for(int c = 0; c <= j; c++) sum += adds(G, s + 1, j - c, k) % 1000000;//Add upp row
        return G[s][j] = sum % 1000000;//Get final answer
    }
}
