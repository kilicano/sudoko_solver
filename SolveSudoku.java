/* Project: Operations Research Assignment -- Solving Sudokus, Second Year Project I, EBS2002, Academic Year 2018/2019
 * Authors: Rick, Kilian (6170477) & Schuijren, Jordy Evelyn Daniel (6168152)
 * 
 * Necessary additional Classes: Cell.java, Sudoku.java, Queue.java (Programming course)
 * 
 * Counter Output:
 * 
 *            SimpleLogicRule     Generalization    Branching    reduced size of list (SimpleLogic)
 * Sudoku 1:      1                    1                0                  408
 * Sudoku 2:      6                    1                0                  411
 * Sudoku 3:      16                   2                0                  420
 * Sudoku 4:      11                   3                1                  434
 * Sudoku 5:      10                   4                2                  434
 * Sudoku 6:      16                   7                5                  441
 * */

import java.io.*;

public class SolveSudoku{
  public static void main(String[] args){
    try{
    Sudoku sudo = new Sudoku(args[0]); 
    sudo.solve();
    sudo.print();
  }
    catch (FileNotFoundException e){
      
      e.printStackTrace();
    }
  }
}