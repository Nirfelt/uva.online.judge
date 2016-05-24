//10078 - The art gallery
import java.awt.geom.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws java.io.IOException {
        Main main = new Main();
        main.start();
    }

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    void start() throws java.io.IOException {
        while(true){
            int corners = Integer.parseInt(br.readLine());//Number of corners
            if(corners == 0) break;
            Point2D.Float[] coords = new Point2D.Float[corners];
            for(int i = 0; i < corners; i++){//Fill list of points
                String[] tmp = br.readLine().split(" ");
                coords[i] = new Point2D.Float(Float.parseFloat(tmp[0]), Float.parseFloat(tmp[1]));
            }
            if(checkGallery(coords)) System.out.println("Yes");
            else System.out.println("No");
        }
    }

    boolean checkGallery(Point2D.Float[] coords){
        int turn = 0;
        Line2D.Float tmpL = new Line2D.Float();
        for(int i = 0; i < coords.length; i++){//Follow polygon edges
            tmpL.setLine(coords[i], coords[(i+1)%coords.length]);
            int tmp = tmpL.relativeCCW(coords[(i+2)%coords.length]);//Check turn
            if(turn == 0) turn = tmp;//Save turn
            if(turn < 0 && tmp > 0) return true;//Check correct turn
            if(turn > 0 && tmp < 0) return true;//Check correct turn
        }
        return false;
    }
}

