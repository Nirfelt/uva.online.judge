//920 - Sunny mountains
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
        int cases = Integer.parseInt(br.readLine());
        for (int i = 0; i < cases; i++) {
            int coordsNum = Integer.parseInt(br.readLine());
            Point2D.Float[] coords = new Point2D.Float[coordsNum];//replace point2D
            for (int c = 0; c < coordsNum; c++) {
                String[] tmp = br.readLine().split(" ");
                coords[c] = new Point2D.Float(Float.parseFloat(tmp[0]), Float.parseFloat(tmp[1]));
            }
            HeapSort hs = new HeapSort();
            hs.sort(coords, coordsNum);//Sort on x-coord
            int high = 0;//Highest peak
            int prev = 0;//Previous walley
            float dist = 0;//Total distans in sun
            for (int c = 0; c < coordsNum; c++) {
                if (coords[c].y > coords[high].y) {//Check if peak is high enough
                    if (c % 2 == 1) {
                        Line2D.Float l1 = new Line2D.Float();
                        l1.setLine(coords[c], coords[prev]);
                        Line2D.Float l2 = new Line2D.Float();
                        l2.setLine(coords[high].x, coords[high].y, coords[c].x, coords[high].y);
                        Point2D.Float P = getIntersectionPoint(l1, l2);//Get intersection from highest peak
                        dist += Math.sqrt((P.x-coords[c].x)*(P.x-coords[c].x)+(coords[c].y-coords[high].y)*(coords[c].y-coords[high].y));//Pythagoras for distance
                    }
                    high = c;
                }
                prev = c;
            }
            System.out.printf("%.2f\n", dist);
        }
    }

    public Point2D.Float getIntersectionPoint(Line2D.Float line1, Line2D.Float line2) {
        if (!line1.intersectsLine(line2)) return null;
        double px = line1.getX1(), py = line1.getY1(), rx = line1.getX2() - px, ry = line1.getY2() - py;
        double qx = line2.getX1(), qy = line2.getY1(), sx = line2.getX2() - qx, sy = line2.getY2() - qy;
        double det = sx * ry - sy * rx;
        if (det == 0) return null;
        else {
            double z = (sx * (qy - py) + sy * (px - qx)) / det;
            if (z == 0) return null;
            return new Point2D.Float((float) (px + z * rx), (float) (py + z * ry));
        }
    }
}

class HeapSort {
    int s;

    Point2D.Float[] sort(Point2D.Float[] G, int size) {
        G = heapify(G, size);
        for (int i = s; i > 0; i--) {
            swap(G, 0, i);
            s--;
            maxheap(G, 0);
        }
        return G;
    }

    Point2D.Float[] heapify(Point2D.Float[] G, int size) {
        s = size - 1;
        for (int i = s / 2; i >= 0; i--) maxheap(G, i);
        return G;
    }

    Point2D.Float[] maxheap(Point2D.Float[] G, int i) {
        int left = 2 * i;
        int right = 2 * i + 1;
        int max = i;
        if (left <= s && G[left].getX() < G[i].getX()) max = left;
        if (right <= s && G[right].getX() < G[max].getX()) max = right;
        if (max != i) {
            swap(G, i, max);
            maxheap(G, max);
        }
        return G;
    }

    Point2D.Float[] swap(Point2D.Float[] G, int i, int c){
        Point2D.Float tmp = G[i];
        G[i] = G[c];
        G[c] = tmp;
        return G;
    }
}
