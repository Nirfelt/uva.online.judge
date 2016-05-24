//839 - Not so mobile
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    boolean ok;
    public static void main(String[] args) throws java.io.IOException{
        Main main = new Main();
        main.start();
    }

    void start() throws java.io.IOException{
        int mobiles = Integer.parseInt(br.readLine());
        for (int i = 0; i < mobiles; i++){
            br.readLine();
            ok = true;
            test();//Test if balanced
            if(ok) System.out.println("YES");
            else System.out.println("NO");
            if(i != mobiles - 1) System.out.println();
        }
    }

    int test() throws java.io.IOException{
        int wl, dl, wr, dr;
        String[] t = br.readLine().split(" ");//Get level in mobile
        wl = Integer.parseInt(t[0]);
        dl = Integer.parseInt(t[1]);
        wr = Integer.parseInt(t[2]);
        dr = Integer.parseInt(t[3]);
        if(wl == 0) wl = test();//Recursively test left side
        if(wr == 0) wr = test();//Recursively test right side
        if(wl*dl == wr*dr){
            return wl + wr;//If both sides are equal return total weight
        }else{
            ok = false;//If unbalanced set flags to false
            return 0;
        }
    }
}
