package com.fyt.lcrep;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LeetCodeTest {

    /**
     * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。
     * 斐波那契数列的定义如下：
     * F(0) = 0,   F(1) = 1
     * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
     * @param n
     * @return
     */
    public int fib(int n) {
        if (n < 2){
            return n;
        }
        int f0 = 0;
        int f1 = 1;
        for (int i = 2; i <= n ; i++){
            int tmp = (f1 + f0)%1000000007;
            f0 = f1;
            f1 = tmp;
        }
        return f1;
    }

    @Test
    public void fibTest(){
        long startTime = System.currentTimeMillis();
        System.out.println("fib : "+ fib(7));
        System.out.println("ls: "+(System.currentTimeMillis() - startTime));
    }

    /**
     * 剑指 Offer 10- II. 青蛙跳台阶问题
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。
     * 求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
     *
     * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
     * @param n
     * @return
     */
    public int numWays(int n) {
        if (n < 2)return 1;
        int f0 = 1;
        int f1 = 1;
        for (int i = 2; i <= n ; i++){
            int tmp = (f1 + f0)%1000000007;
            f0 = f1;
            f1 = tmp;
        }
        return f1;
    }

    @Test
    public void numWaysTest(){
        long startTime = System.currentTimeMillis();
        System.out.println("numWays : "+ numWays(7));
        System.out.println("ls: "+(System.currentTimeMillis() - startTime));
    }

    /**
     * 剑指 Offer 11. 旋转数组的最小数字
     * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
     * 输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。
     * 例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。  
     *
     * 示例 1：
     *
     * 输入：[3,4,5,1,2]
     * 输出：1
     *
     * @param numbers
     * @return
     */
    public int minArray(int[] numbers) {
        if (numbers[0] == numbers[numbers.length/2]){
            for(int i = 1; i < numbers.length; i++){
                if(numbers[0] > numbers[i]){
                    return numbers[i];
                }
            }
        }else if (numbers[0] < numbers[numbers.length/2]){
            for (int i = numbers.length/2; i< numbers.length; i++){
                if(numbers[0] > numbers[i]){
                    return numbers[i];
                }
            }
        }else {
            for (int i = numbers.length/2; i>0; i--){
                if(numbers[i-1] > numbers[i]){
                    return numbers[i];
                }
            }
        }
        return numbers[0];
    }


    /**
     * 剑指 Offer 12. 矩阵中的路径
     * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
     * 路径可以从矩阵中的任意一格开始，每一步可以在矩阵中向左、右、上、下移动一格。
     * 如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。
     * 例如，在下面的3×4的矩阵中包含一条字符串“bfce”的路径（路径中的字母用加粗标出）。
     *
     * [["a","b","c","e"],
     * ["s","f","c","s"],
     * ["a","d","e","e"]]
     *
     * 但矩阵中不包含字符串“abfb”的路径，
     * 因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入这个格子。
     *
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        char[] words = word.toCharArray();
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(dfs(board, words, i, j, 0)) return true;
            }
        }
        return false;
    }
    boolean dfs(char[][] board, char[] word, int i, int j, int k) {
        if(i >= board.length || i < 0 || j >= board[0].length || j < 0 || board[i][j] != word[k]) return false;
        if(k == word.length - 1) return true;
        board[i][j] = '#';
        boolean res = dfs(board, word, i + 1, j, k + 1) || dfs(board, word, i - 1, j, k + 1) ||
                dfs(board, word, i, j + 1, k + 1) || dfs(board, word, i , j - 1, k + 1);
        board[i][j] = word[k];
        return res;
    }

    @Test
    public void existTest(){

        // ABCESEEEFS
        //[["A","B","C","E"],
        // ["S","F","E","S"],
        // ["A","D","E","E"]]
        char[][] cs = new char[][]{{'A','B','C','E'},{'S','F','E','S'},{'A','D','E','E'}};
        char[][] cs1 = new char[][]{{'a','b'},{'c','d'}};
        long startTime = System.currentTimeMillis();
        System.out.println("exist : "+ exist(cs,"ABCESEEEFS"));
        System.out.println("ls: "+(System.currentTimeMillis() - startTime));
    }

    /**
     * 剑指 Offer 13. 机器人的运动范围
     * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。
     * 一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），
     * 也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，
     * 因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
     *
     * 示例 1：
     * 输入：m = 2, n = 3, k = 1
     * 输出：3
     *
     * @param m
     * @param n
     * @param k
     * @return
     */
    public int movingCount(int m, int n, int k) {
        boolean[][] visited = new boolean[m][n];
        return dfs(0,0,m,n,k,visited);
    }

    public int dfs(int i, int j,int m, int n, int k,boolean[][] visited){
        if (i<0 || j<0 || i> m-1 || j> n-1 || visited[i][j] || (i/10 + i%10 + j/10+j%10) > k){
            return 0;
        }
        visited[i][j] = true;
        return 1+dfs(i-1,j,m,n,k,visited)
                + dfs(i+1,j,m,n,k,visited)
                + dfs(i,j-1,m,n,k,visited)
                + dfs(i,j+1,m,n,k,visited);
    }

    @Test
    public void movingCountTest(){
        long startTime = System.currentTimeMillis();
        System.out.println("movingCount : "+ movingCount(3,1,0));
        System.out.println("ls: "+(System.currentTimeMillis() - startTime));
    }

    public double myPow(double x, int n) {
        if (x == 1.0){
            return 1.0;
        }
        double res = 1.0;
        if(n > 0){
            for (int i=0; i< n;i++){
                res = res*x;
            }
        }else {
            for (int i=0; i< -n;i++){
                res = res*(1/x);
            }
        }
        return res;
    }

    @Test
    public void myPowTest(){
        long startTime = System.currentTimeMillis();
        long a = 8;
        System.out.println("myPow : "+ (a >> 1));
        System.out.println("ls: "+(System.currentTimeMillis() - startTime));
    }

}
