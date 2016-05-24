//116 - Unidirectional TSP
import java.util.*;

public class Main {

    Integer[] path;
    int n;
    int m;

    public static void main(String[] args) throws java.io.IOException {
        Main main = new Main();
        main.start();
    }

    void start() throws java.io.IOException {
        Scanner input = new Scanner(System.in);
        Integer[][] G;
        while(input.hasNextInt()) {//Scan all input and build matrix
            n = input.nextInt();
            m = input.nextInt();
            G = new Integer[n][m];
            path = new Integer[m];
            for(int i = 0; i < n; i++) {
                for (int c = 0; c < m; c++) {
                    G[i][c] = input.nextInt();
                }
            }
            int ans = solve(G);
            for(int i = 0; i < m; i++) {
                System.out.print(path[i]+1);
                if(i != m-1) System.out.print(" ");
            }
            System.out.println("\n" + ans);
        }
    }

    int solve(Integer[][] G){
        Integer[][] A = new Integer[n][m];
        path[0] = 0;
        for(int i = 0; i < n; i++) A[i][m - 1] = G[i][m - 1];//Build last row
        for(int c = m-2; c >= 0; c--) for(int i = 0; i < n; i++) A[i][c] = G[i][c] + A[getMinRow(A, i, c+1)][c+1];//Build summed paths backwards
        for(int i = 0; i < n; i++) if (A[i][0] < A[path[0]][0]) path[0] = i;//Get smallest element in first column
        for(int i = 1; i < m; i++) path[i] = getMinRow(A, path[i-1], i);//Build full path from first element
        return A[path[0]][0];//Return sum of smallest path
    }

    int getMinRow(Integer[][] A, int nPos, int mPos){//Choose smallest row weight, if same choose row with smallest row number
        int tmp = (n+nPos-1)%n;
        if(A[nPos][mPos] < A[tmp][mPos] || (A[nPos][mPos].equals(A[tmp][mPos]) && nPos < tmp)) tmp = nPos;
        if(A[(nPos+1)%n][mPos] < A[tmp][mPos] || (A[(nPos+1)%n][mPos].equals(A[tmp][mPos]) && (nPos+1)%n < tmp)) tmp = (nPos+1)%n;
        return tmp;
    }
}
