
package matrixop;
import java.util.Arrays;
import java.util.Scanner;

public class MatrixOp 
{
    public static Scanner s = new Scanner(System.in);
    public static int r1, r2, c1, c2;                 //rows and columns
    public static int i=0, j=0;                      //loop variables
    public static int add[][], sub[][], mult[][], trans[][], meet[][], join[][];
    
    public static void main(String[] args) 
    {
        String ans = "";
       do {
           System.out.println("First matrix"); //getting first and second matrix from the user
       int[][] first= getMat();
       System.out.println("Second matrix");
       int[][] second= getMat();
       
       r1 = first.length;                       //rows and columns for the first matrix
       c1 = first[0].length;
       r2 = second.length;                       //rows and columns for the second
       c2 = second[0].length;
       
       System.out.println("First matrix");           //displaying first and second matrix 
       showMat(first, r1, c1);
       System.out.println("Second matrix");
       showMat(second, r2, c2);
       
       System.out.print("Select the operation: "
       + "\n 1 for Addition" + "\n 2 for Subtraction" + 
         "\n 3 for Multiplication" + "\n 4 for Transpose" + 
         "\n 5 for Meet" + "\n 6 for Join" + 
         "\n 7 for RowSum, ColumnSum, DiagonalSum" +
         "\n Enter-- \t");
       int operation = s.nextInt();
       
       switch (operation)
       {
           case 1: addMat(first, second);
                   break;
           case 2: subMat(first, second);
                   break;
           case 3: multMat(first, second);
                   break;
           case 4: transMat(first, r1, c1);                            
                   transMat(second, r2, c2);                                  
                   break;
           case 5: meetMat(first, second);
                   break;
           case 6: joinMat(first, second);
                   break;
           case 7: sumMat(first, r1, c1);
                   sumMat(second, r2, c2);                                           
                   break;
           default: System.out.println("Invalid operation");          
       }
       
       System.out.println("Do you want to continue? Enter yes/no : ");
       ans = s.next().toLowerCase().charAt(0)+"";
       } while((ans.trim()).equals("y"));
       
    }
    
    public static int[][] getMat()
    {
        System.out.print("Enter the number of rows: ");
        int rows = s.nextInt();
        System.out.print("Enter the number of columns: ");
        int columns = s.nextInt();
        System.out.print("Enter elements (" +rows*columns+ "): ");
        int arr[][]= new int[rows][columns];
        for(i=0; i<rows; i++)
        {
        for (j=0; j<columns; j++)
         {
           arr[i][j] = s.nextInt();
         }
        }
        return arr;
    }
    
    public static void showMat(int arr[][], int rows, int columns)
    {
        for(i=0; i<rows; i++)
        {
        for (j=0; j<columns; j++)
         {
          System.out.print("\t" +arr[i][j]+"\t");
         }
        System.out.print("\n");
        }
        
    }
    
    public static void addMat(int x[][], int y[][])
    {
      if ( r1==r2 && c1==c2 )                                   // matrices can only be added if number of rows and columns are same
      { 
          add = new int[r1][c1];
          for(i=0; i<r1; i++)
          {
              for(j=0; j<c1; j++)
              {
                  add[i][j] = x[i][j] + y[i][j];               //adding each corrosponding element
              }
          }
          System.out.println("Adding first and second matrix: ");
          showMat(add, r1, c1);
      }
          
      else
          System.out.println("--Addition not possible--");
    }
    
    public static void subMat(int x[][], int y[][])
    {
        
      if ( r1==r2 && c1==c2 )                          //matrices can only be subtracted if number of rows and columns are same
      { 
          sub = new int[r1][c1];
          for(i=0; i<r1; i++)
          {
              for(j=0; j<c1; j++)
              {
                  sub[i][j] = x[i][j] - y[i][j];      //subtracting each corrosponding element
              }
          }
          System.out.println("Subtracting second matrix from first: ");
          showMat(sub, r1, c1);
      }
          
      else
          System.out.println("--Subtraction not possible--");
    }
    
     public static void multMat(int x[][], int y[][])
    {
      if ( c1 == r2)                                          //multiplication only possible if its true
      { 
          mult = new int[r1][c2];
          for(i=0; i<r1; i++)
          {
              for(j=0; j<c2; j++)
              {
                  for (int k=0; k<c1; k++)                  
                  {
                     mult[i][j] += x[i][k]*y[k][j];         // to get each element of a row multipliesd 
                  }                                         //with the corrosponding element of the column and then 
              }                                             //adding the sum of prodicts to get one element
          }
          System.out.println("Multiplying first and second matrix: ");
          showMat(mult, r1, c2);                           // dimensions of resultant matrix
      }
          
      else
          System.out.println("--Multiplication not possible--");
    }
    
     public static void transMat(int x[][], int rows, int columns)
    { 
          trans = new int[columns][rows];
          for(i=0; i<rows; i++)
          {
              for(j=0; j<columns; j++)
              {
                trans[j][i] = x[i][j]; 
              }
          }
          System.out.println("Transpose of first matrix: ");
          showMat(trans, columns, rows);
          
          int d=0;                  // a random variable used to know the nature of matrix, 1 for sym, 2 for skew sym and 3 for nothing
          for(i=0; i<rows; i++)
          {
              for(j=0; j<columns; j++)
              {
                  if (trans[j][i]==x[i][j])           //symmetric matrix
                  {
                    d = 1;
                    continue;  
                  }
                  else if (trans[j][i] == -x[i][j])   //skew symmetric matrix
                  {
                    d = 2;
                    continue;          
                  }
                  else                                //none
                  {
                    d = 3;
                    break;   
                  }
              }
          }
          
          switch (d)
          {
              case 1: System.out.println("The matrix is SYMMETRIC");
                      break;
              case 2: System.out.println("The matrix is SKEW-SYMMETRIC");
                      break;
              case 3: System.out.println("The matrix is NEITHER symmetric NOR skew-symmetric");
                      break;
              default: System.out.println("Can't find the nature of the matrix");
          }
    }
     
     public static void meetMat(int x[][], int y[][])
    {
      boolean is_boolean = false;                                     //to know if the matrix is boolean or not
      if ( r1==r2 && c1==c2 )
      { 
          meet = new int[r1][c1];
          for(i=0; i<r1; i++)
          {
              for(j=0; j<c1; j++)
              {
                  if( (x[i][j]==0 || x[i][j]==1) && (y[i][j]==0 || y[i][j]==1) )
                  {
                  is_boolean=true;                                  // meet only workson boolean matrices that is 0-1
                  meet[i][j] = x[i][j] * y[i][j];                   // meet is and operation
                  }
                  
                  else 
                  is_boolean=false;      
              }
          }
          
          if (is_boolean == true)
          {
          System.out.println("Meet of first and second matrix: ");
          showMat(meet, r1, c1);
          }
          else
          System.out.println("Can't perform the function, matrix not boolean");
      }
          
      else
          System.out.println("--Meet not possible--");
    }  

     public static void joinMat(int x[][], int y[][])
    {
      boolean is_boolean = false;                                     //to know if the matrix is boolean or not
      if ( r1==r2 && c1==c2 )
      { 
          join = new int[r1][c1];
          for(i=0; i<r1; i++)
          {
              for(j=0; j<c1; j++)
              {
                  if( (x[i][j]==0 || x[i][j]==1) && (y[i][j]==0 || y[i][j]==1) )
                  {
                  is_boolean=true;                                 //join only works on 0-1 matrix
                  join[i][j] = x[i][j] + y[i][j];                 //join is the or operation
                  }
                  
                  else 
                  is_boolean=false;      
              }
          }
          
          if (is_boolean == true)
          {
          System.out.println("Join of first and second matrix: ");
          showMat(join, r1, c1);
          }
          else
          System.out.println("Can't perform the function, matrix not boolean");
      }
          
      else
          System.out.println("--Join not possible--");
    }  
     
    public static void sumMat(int x[][], int rows, int columns)   //for rowsum,columnsum and diagonalsum
    {
        int ds = 0;                                             // diagonal sum
        int[][] rs = new int[rows][1];                         //row sum , the idea is to display the result in a matrix of 1 column
        int[][] cs = new int[1][columns];                      //column sum, the idea is to display result in a matrix of 1 row
       
        for(int[] row: rs)
        {
            Arrays.fill(row, 0);
        }
        for(int[] column: cs)
        {
            Arrays.fill(column, 0);
        }
          for(i=0; i<rows; i++)                                //getting ds
          {
              for(j=0; j<columns; j++)
              {
                if (i==j)
                    ds += x[i][j];                         
              }
          }
          
          for(i=0; i<rows; i++)                              //getting rs
          {
              for(j=0; j<columns; j++)
              {
                rs[i][0]+=x[i][j];
              }
          }
          
           for(j=0; j<columns; j++)                         // getting cs
          {
              for(i=0; i<rows; i++)
              {
                cs[0][j]+=x[i][j];
              }
          }
           
          System.out.println("The DIAGONAL SUM is: " +ds);
          System.out.println("The ROW SUM is: ");
          showMat(rs, rows, 1);
          System.out.println("The COLUMN SUM is: ");
          showMat(cs, 1, columns);
          
          
    }
   
    
     
     
     
     
}

