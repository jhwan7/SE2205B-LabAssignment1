import java.io.*;

public class Assignment1{

  public int[][] denseMatrixMult(int[][] A, int[][] B, int size)
  {
    class mergeMatrix {
      int[][] mergeMatrix(int[][] a, int[][] b, int[][] c, int[][] d) {
        int size = 2 * a.length;
        int[][] C = initMatrix(size);

        C = sum(C, a, 0, 0, 0, 0, size);
        for (int x = 0; x < size/2; x++) {
          for (int y=0;y<size/2;y++)
            C[x][y+size/2] = b[x][y];
        }
        for (int x= 0; x < size/2; x++) {
          for (int y=0;y<size/2;y++)
            C[x+size/2][y] = c[x][y];
        }
        for (int x = 0; x < size/2; x++) {
          for (int y=0;y<size/2;y++)
            C[x+size/2][y+size/2] = d[x][y];
        }
        return C;
      }
    }
    if(size <= 2){


      int M_0 = (A[0][0]+A[1][1])*(B[0][0]+B[1][1]);
      int M_1 = (A[1][0]+A[1][1])*B[0][0];
      int M_2 = A[0][0]*(B[0][1]-B[1][1]);
      int M_3 = A[1][1]*(B[1][0]-B[0][0]);
      int M_4 = (A[0][0]+A[0][1])*B[1][1];
      int M_5 = (A[1][0]-A[0][0])*(B[0][0]+B[0][1]);
      int M_6 = (A[0][1]-A[1][1])*(B[1][0]+B[1][1]);

      int[][] C = new int[2][2];


      C[0][0] = M_0+M_3-M_4+M_6;
      C[0][1] = M_2+M_4;
      C[1][0] = M_1+M_3;
      C[1][1] = M_0-M_1+M_2+M_5;

      return C;
    }
    else{

      int[][]IdMatrix = initMatrix(size/2);

      int[][]a_0 = sum(A,IdMatrix,0,0,0,0,size/2);
      int[][]a_1 = sum(A,IdMatrix,0,size/2,0,0,size/2);
      int[][]a_2 = sum(A,IdMatrix,size/2,0,0,0,size/2);
      int[][]a_3 = sum(A,IdMatrix,size/2,size/2,0,0,size/2);
      int[][]b_0 = sum(B,IdMatrix,0,0,0,0,size/2); ;
      int[][]b_1 = sum(B,IdMatrix,0,size/2,0,0,size/2);
      int[][]b_2 = sum(B,IdMatrix,size/2,0,0,0,size/2);
      int[][]b_3 = sum(B,IdMatrix,size/2,size/2,0,0,size/2);


      int[][]M_0 = denseMatrixMult(
              sum(a_0,a_3,0,0,0,0,size/2),
              sum(b_0,b_3,0,0,0,0,size/2), size/2);
      int[][]M_1 = denseMatrixMult(
              sum(a_2, a_3, 0,0,0,0,size/2),
              b_0,size/2);
      int[][]M_2 = denseMatrixMult(a_0,
              sub(b_1,b_3,0,0,0,0,size/2),size/2);
      int[][]M_3 = denseMatrixMult(a_3,
              sub(b_2,b_0,0,0,0,0,size/2),size/2);
      int[][]M_4 = denseMatrixMult(
              sum(a_0,a_1,0,0,0,0,size/2),
              b_3,size/2);
      int[][]M_5 = denseMatrixMult(
              sub(a_2,a_0,0,0,0,0,size/2),
              sum(b_0,b_1,0,0,0,0,size/2),size/2);
      int[][]M_6 = denseMatrixMult(
              sub(a_1,a_3,0,0,0,0,size/2),
              sum(b_2,b_3,0,0,0,0,size/2),size/2);



      int[][]C_0 = sub(sum(sum(M_0,M_3,0,0,0,0,size/2),M_6,0,0,0,0,size/2), M_4, 0,0,0,0,size/2);
      int[][]C_1 = sum(M_2,M_4,0,0,0,0,size/2);
      int[][]C_2 = sum(M_1,M_3,0,0,0,0,size/2);
      int[][]C_3 = sub(sum(sum(M_0,M_2,0,0,0,0,size/2),M_5, 0,0,0,0,size/2),M_1, 0,0,0,0,size/2);

      mergeMatrix mrg = new mergeMatrix();

      return mrg.mergeMatrix(C_0,C_1,C_2,C_3);
    }
  }

  public int[][] sum(int[][] A, int[][] B, int x1, int y1, int x2, int y2, int n) {
    int[][] Sum = initMatrix(n);

    if (n<=A.length&&n<=B.length) {
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          Sum[i][j] = A[i+x1][j+y1] + B[i+x2][j+y2];
        }
      }
    }
    else if(n>=B.length&&n<=A.length) {
      for (int i = 0; i < B.length; i++) {
        for (int j = 0; j < B.length; j++) {
          Sum[i][j] = A[i+x1][j+y1] + B[i+x2][j+y2];
        }
      }
    }
    else{
      for (int i = 0; i < A.length; i++) {
        for (int j = 0; j < A.length; j++) {
          Sum[i][j] = A[i+x1][j+y1] + B[i+x2][j+y2];
        }
      }
    }
    return Sum;
  }

  public int[][] sub(int[][] A, int[][] B, int x1, int y1, int x2, int y2, int n)
  {
    int [][] Sub = initMatrix(n);

    if (n<=A.length&&n<=B.length) {
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          Sub[i][j] = A[i+x1][j+y1] - B[i+x2][j+y2];
        }
      }
    }
    else if(n>=B.length&&n<=A.length) {
      for (int i = 0; i < B.length; i++) {
        for (int j = 0; j < B.length; j++) {
          Sub[i][j] = A[i+x1][j+y1] - B[i+x2][j+y2];
        }
      }
    }
    else{
      for (int i = 0; i < A.length; i++) {
        for (int j = 0; j < A.length; j++) {
          Sub[i][j] = A[i+x1][j+y1] - B[i+x2][j+y2];
        }
      }
    }
    return Sub;
  }


  public int[][] initMatrix(int n)
  {
    return new int[n][n];
  }

  public void printMatrix(int n, int[][] A)
  {
    for(int x=0;x<n;x++){
      for(int y=0;y<n;y++){
        System.out.print(A[x][y]);
        System.out.print(' ');
      }
      System.out.println();
    }
  }

  public int[][] readMatrix(String filename, int n) throws Exception {
    int[][] A = new int[n][n];
    File file = new File(filename);
    BufferedReader in = new BufferedReader(new FileReader(file));
    int i = 0;
    while (i < n) {
      String[] temp = in.readLine().split(" ");
      for (int j = 0; j < n; j++) {
        A[i][j] = Integer.parseInt(temp[j]);
      }
      i++;
    }
    return A;
  }
}