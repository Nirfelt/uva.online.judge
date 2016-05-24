//185 - Romman numerals
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
            String input = br.readLine();
            if(input.equals("#")) break;
            String[] tmp = input.split("=");
            String[] tmp2 = tmp[0].split("\\+");
            String a = tmp2[0];
            String b = tmp2[1];
            String c = tmp[1];
            if(sum(a, b, c)) System.out.print("Correct ");//Check if correct ruman sum
            else System.out.print("Incorrect ");
            int arab = arabEv(a, b, c);//Get possible arabic evaluations
            if(arab == 0) System.out.println("impossible");
            else if(arab == 1) System.out.println("valid");
            else System.out.println("ambiguous");
        }
    }

    boolean sum(String a, String b, String c){
        if(romeNbr(a) + romeNbr(b) == romeNbr(c)) return true;//Check roman sum
        return false;
    }

    int romeNbr(String input){//Translate roman numbers
        char[] tmp = input.toCharArray();
        int[] numbers = new int[tmp.length];
        for(int i = 0; i < tmp.length; i++){
            if(tmp[i] == 'I') numbers[i] = 1;
            if(tmp[i] == 'V') numbers[i] = 5;
            if(tmp[i] == 'X') numbers[i] = 10;
            if(tmp[i] == 'L') numbers[i] = 50;
            if(tmp[i] == 'C') numbers[i] = 100;
            if(tmp[i] == 'D') numbers[i] = 500;
            if(tmp[i] == 'M') numbers[i] = 1000;
        }
        int sum = numbers[numbers.length-1];
        if(numbers.length == 1) return sum;
        for(int i = numbers.length-2; i >= 0; i--){
            if(numbers[i] < numbers[i+1]) sum -= numbers[i];
            else sum += numbers[i];
        }
        return sum;
    }

    int arabEv(String a, String b, String c){//Preset to dfs
        String str = "1234567890";
        char[] num = str.toCharArray();
        boolean[] visited = new boolean[str.length()];
        char[] branch = new char[str.length()];
        return dfs(num, str.length(), branch, -1, checkInput(a, b, c)-1, visited, a, b, c);
    }

    //dfs to check all possible arabic evaluations
    int dfs(char[] num, int size, char[] branch, int pos, int posMax, boolean[] visited, String a, String b, String c)
    {
        int count = 0;
        if (pos >= posMax)//Depth = number of unique symbols
        {
            if(checkArab(a, b, c, branch)) count++;//Check if valid solution
            return count;
        }
        for (int i = 0; i < size; i++)
        {
            if (!visited[i])
            {
                pos++;
                branch[pos] = num[i];
                visited[i] = true;
                count += dfs(num, size, branch, pos, posMax, visited, a, b, c);//Recursively generate permutations
                if(count >= 2) return count;//If possible evaluation >= 2 abort dfs
                visited[i] = false;
                pos--;
            }
        }
        return count;
    }

    boolean checkArab(String a, String b, String c, char[] branch){//Translate and check current permutation
        String chars = "IVXLCDM";
        String tmp = a + " " + b + " " + c;
        int count = 0;
        int n = checkInput(a, b, c);
        for(int i = 0; i < chars.length(); i++){
            if(tmp.indexOf(chars.charAt(i)) >= 0){
                String t = "" + chars.charAt(i);
                String num = "" + branch[count];
                tmp = tmp.replaceAll(t, num);
                count++;
            }
        }
        String[] test = tmp.split(" ");
        if(test[0].charAt(0) == '0' || test[1].charAt(0) == '0' || test[2].charAt(0) == '0') return false;//False if number starts with 0
        if(Integer.parseInt(test[0])+Integer.parseInt(test[1]) == Integer.parseInt(test[2])) return true;
        return false;
    }

    int checkInput(String a, String b, String c){//Count unique symbols
        String tmp = a+b+c;
        String chars = "IVXLCDM";
        int count = 0;
        for(int i = 0; i < chars.length(); i++) if(tmp.indexOf(chars.charAt(i)) >= 0) count++;
        return count;
    }
}
