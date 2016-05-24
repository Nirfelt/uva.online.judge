//10245 - The closest pair problem
import java.awt.geom.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws java.io.IOException {
        Main main = new Main();
        main.start();
    }

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    void start() throws java.io.IOException {
        while(true){
            int coordsNum = Integer.parseInt(br.readLine());
            if(coordsNum == 0) break;
            Point2D.Double[] coords = new Point2D.Double[coordsNum];
            for(int i = 0; i < coordsNum; i++){//Read points
                String[] tmp = br.readLine().split(" ");
                coords[i] = new Point2D.Double(Double.parseDouble(tmp[0]), Double.parseDouble(tmp[1]));
            }
            HeapSort hs = new HeapSort();
            hs.sort(coords);//Sort coords on x-coord
            double dist = closestPairDist(coords);
            if(dist < 10000.0000) System.out.printf("%.4f\n", dist);
            else System.out.println("INFINITY");
        }
    }

    double closestPairDist(Point2D.Double[] coords){
        if(coords.length <= 3){//If number of points < 3 bruteforce closest pair
            List<Point2D.Double> list = new ArrayList<>();
            for(int i = 0; i < coords.length; i++) list.add(coords[i]);
            return bruteForce(list, Integer.MAX_VALUE);
        }
        double dist = Double.MAX_VALUE;
        for(int i = 1; i < coords.length; i++){//Sweepline to find candidates
            List<Point2D.Double> list = new ArrayList<>();
            list.add(coords[i]);//Add candidates to list
            for(int c = i-1; c >= 0; c--){
                if(coords[i].x-coords[c].x >= dist) break;
                double a = coords[i].y-coords[c].y;
                if(a<0) a=a*-1;
                if(a<dist) list.add(coords[c]);
            }
            dist = bruteForce(list, dist);//Bruteforce candidates, max 7.
        }
        return dist;
    }

    double bruteForce(List<Point2D.Double> list, double dist){
        for(int i = 0; i < list.size(); i++) {
            for (int c = i + 1; c < list.size(); c++) {
                double tmp = getDist(list.get(i), list.get(c));
                if (tmp < dist) dist = tmp;
            }
        }
        return dist;
    }

    double getDist(Point2D.Double p1, Point2D.Double p2){
        double a = p1.x-p2.x;
        double b = p1.y-p2.y;
        if(a<0) a=a*-1;
        if(b<0) b=b*-1;
        return Math.sqrt((a*a)+(b*b));
    }
}

class HeapSort {
    int s;

    Point2D.Double[] sort(Point2D.Double[] G) {
        G = heapify(G, G.length);
        for (int i = s; i > 0; i--) {
            swap(G, 0, i);
            s--;
            maxheap(G, 0);
        }
        return G;
    }

    Point2D.Double[] heapify(Point2D.Double[] G, int size) {
        s = size - 1;
        for (int i = s / 2; i >= 0; i--) maxheap(G, i);
        return G;
    }

    Point2D.Double[] maxheap(Point2D.Double[] G, int i) {
        int left = 2 * i;
        int right = 2 * i + 1;
        int max = i;
        if (left <= s && G[left].getX() > G[i].getX()) max = left;
        if (right <= s && G[right].getX() > G[max].getX()) max = right;
        if (max != i) {
            swap(G, i, max);
            maxheap(G, max);
        }
        return G;
    }

    Point2D.Double[] swap(Point2D.Double[] G, int i, int c){
        Point2D.Double tmp = G[i];
        G[i] = G[c];
        G[c] = tmp;
        return G;
    }
}
