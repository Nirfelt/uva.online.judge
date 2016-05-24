//10307 - Killing aliens in borg maze
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    Integer[][] wG;
    int cNodes;

    public static void main(String[] args) throws java.io.IOException {
        Main main = new Main();
        main.start();
    }

    void start() throws java.io.IOException {
        int cases = Integer.parseInt(br.readLine());
        for (int i = 0; i < cases; i++) {//Get input and run test
            String[] t = br.readLine().split(" ");
            int p = Integer.parseInt(t[0]);
            int r = Integer.parseInt(t[1]);
            if(getEdges(p, r)){
                System.out.println(kruskal(cNodes));
            }else{
                System.out.println("0");
            }
        }
    }

    boolean getEdges(int p, int r) throws java.io.IOException {
        Character[] input = new Character[p*r];
        List<Integer> flag = new ArrayList<>();
        int c1 = 0;
        for (int i = 0; i < r; i++) {//Read input to array, save flags
            String row = br.readLine();
            for (int c = 0; c < p; c++) {
                if (c >= row.length()) input[c1] = '#';
                else {
                    input[c1] = row.charAt(c);
                    if(row.charAt(c) == 'A' || row.charAt(c) == 'S') flag.add(c1);
                }
                c1++;
            }
        }
        if(flag.size() <= 1) return false;
        Integer[] flags = new Integer[flag.size()];
        for(int i = 0; i < flag.size(); i++) flags[i] = flag.get(i);//Transfer flags
        int s = (flags.length*(flags.length-1))/2;
        wG = new Integer[s][3];
        for(int i = 0; i < s; i++) for(int c = 0; c < 3; c++) wG[i][c] = 0;
        int cTotal = 0;
        for(int i = 0; i < flags.length; i++){//Run bfs on flags
            Integer[] tmp = bfs(input, flags[i], p);//Add weights to flags
            for(int c = i + 1; c < flags.length; c++) {
                wG[cTotal][0] = tmp[flags[c]];
                wG[cTotal][1] = i;
                wG[cTotal][2] = c;
                cTotal++;
            }
        }
        cNodes = s;
        return true;
    }

    boolean checkNode(Character[] G, int i){
        if(i < 0 || i >= G.length) return false;
        if(G[i] == '#') return false;
        return true;
    }

    Integer[] bfs(Character[] G, int v, int p){
        Integer[] dist = new Integer[G.length];
        for(int i = 0; i < dist.length; i++){
            dist[i] = Integer.MAX_VALUE;
        }
        Queue<Integer> Q = new LinkedList<>();
        Q.add(v);
        dist[v] = 0;
        while(!Q.isEmpty()){
            v = Q.poll();
            if(checkNode(G, v-p) && dist[v-p] == Integer.MAX_VALUE){
                dist[v-p] = dist[v]+1;
                Q.add(v-p);
            }
            if(checkNode(G, v+p) && dist[v+p] == Integer.MAX_VALUE){
                dist[v+p] = dist[v]+1;
                Q.add(v+p);
            }
            if(checkNode(G, v-1) && dist[v-1] == Integer.MAX_VALUE){
                dist[v-1] = dist[v]+1;
                Q.add(v-1);
            }
            if(checkNode(G, v+1) && dist[v+1] == Integer.MAX_VALUE){
                dist[v+1] = dist[v]+1;
                Q.add(v+1);
            }
        }
        return dist;
    }

    int kruskal(int size){
        if(size == 1) return wG[0][0];
        wG = new HeapSort().sort(wG, size);
        UnionFind uf = new UnionFind(size);
        int tmp = 0;
        for(int i = 0; i < size; i++) if (uf.union(wG[i][1], wG[i][2])) tmp += wG[i][0];
        return tmp;
    }
}

class HeapSort {
    int s;

    Integer[][] sort(Integer[][] wG, int size) {
        wG = heapify(wG, size);
        for (int i = s; i > 0; i--) {
            swap(wG, 0, i);
            s--;
            maxheap(wG, 0);
        }
        return wG;
    }

    Integer[][] heapify(Integer[][] wG, int size) {
        s = size - 1;
        for (int i = s / 2; i >= 0; i--) maxheap(wG, i);
        return wG;
    }

    Integer[][] maxheap(Integer[][] wG, int i) {
        int left = 2 * i;
        int right = 2 * i + 1;
        int max = i;
        if (left <= s && wG[left][0] > wG[i][0]) max = left;
        if (right <= s && wG[right][0] > wG[max][0]) max = right;
        if (max != i) {
            swap(wG, i, max);
            maxheap(wG, max);
        }
        return wG;
    }

    Integer[][] swap(Integer[][] wG, int i, int c){
        int tmp;
        for(int p = 0; p < 3; p++){
            tmp = wG[i][p];
            wG[i][p] = wG[c][p];
            wG[c][p] = tmp;
        }
        return wG;
    }
}

class UnionFind {
    int[] parent;
    int[] rank;

    UnionFind(int c) {
        parent = new int[c];
        rank = new int[c];
        for (int i = 0; i < c; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    boolean union(int i, int c) {
        int tmpi = find(i);
        int tmpc = find(c);
        int iRank = rank[tmpi];
        int cRank = rank[tmpc];
        if(tmpi == tmpc) return false;
        if(iRank < cRank) {
            parent[tmpi] = tmpc;
        }
        else if(cRank < iRank) {
            parent[tmpc] = tmpi;
        }
        else {
            parent[tmpi] = tmpc;
            rank[tmpc]++;
        }
        return true;
    }

    int find(int i) {
        if (parent[i] == i) {
            return i;
        }
        else {
            int tmp = find(parent[i]);
            parent[i] = tmp;
            return tmp;
        }
    }
}
