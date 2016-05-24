//10608 - Friends
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int[][] matrix;
    int[] visited;

    public static void main(String[] args) throws java.io.IOException{
        Main main = new Main();
        main.start();
    }

    void start() throws java.io.IOException{
        int cases = Integer.parseInt(br.readLine());
        for(int i = 0; i < cases; i++){
            String[] t = br.readLine().split(" ");
            int p = Integer.parseInt(t[0]);
            int r = Integer.parseInt(t[1]);
            int biggestGroup = 0;
            visited = new int[p];
            createMatrix(p, r);//Get input and build matrix
            for(int y = 0; y < p; y++) visited[y] = 0;
            for(int y = 0; y < p; y++){
                int tmp = 0;
                if(visited[y] == 0) tmp = df(y, p);//Run dfs on all unvisited nodes
                if(tmp > biggestGroup) biggestGroup = tmp;//Save biggest group
            }
            System.out.println(biggestGroup);
        }
    }

    int df(int v, int p){//Find all nodes connected to v as group
        int c = 1;
        if(visited[v] == 0) visited[v] = 1;
        for(int i = 0; i < p; i++) if (matrix[v][i] == 1 && visited[i] == 0) c += df(i, p);
        return c;
    }

    void createMatrix(int p, int r) throws java.io.IOException{//Build ajacencymatrix
        matrix = new int[p][p];
        for(int o = 0; o < p; o++) for (int u = 0; u < p; u++) matrix[o][u] = 0;
        for(int c = 0; c < r; c++){//Get relations from input
            String[] edge = br.readLine().split(" ");
            matrix[Integer.parseInt(edge[0])-1][Integer.parseInt(edge[1])-1] = 1;
            matrix[Integer.parseInt(edge[1])-1][Integer.parseInt(edge[0])-1] = 1;
        }
    }
}
