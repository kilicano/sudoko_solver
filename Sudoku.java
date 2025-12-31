import java.util.*;
import java.io.*;

public class Sudoku{
  public Cell[][] matrix = new Cell[9][9];
  private Queue<Cell> hints = new Queue<Cell>();
  
  //counters to keep track of performance information
  //Output in SolveSudoku.java
  public int simpleLogicCounter = 0;
  public int generalizationCounter = 0;
  public int branchingCounter = 0;
  public int simpleLogicReduceCells = 0;
  
  public Sudoku(String filename)
    throws java.io.FileNotFoundException{
    File file = new File(filename);
    Scanner input = new Scanner (file);
    
   for(int j=0; j<9; j++){
     for(int k=0; k<9; k++){
        this.matrix[j][k] = new Cell(j,k);}}
    
    int n = input.nextInt();
    for(int i=0; i<n; i++){
      int row = input.nextInt();
      int column = input.nextInt();
      int value = input.nextInt();
      this.matrix[row][column].candidateList.clear();
      this.matrix[row][column].candidateList.add((Integer) value);
      this.matrix[row][column].solvedNow();
      this.hints.enqueue(this.matrix[row][column]);} 
       
    input.close();}
  
 //creates an empty Sudoku 
  public Sudoku(){
    for(int j=0; j<9; j++){
     for(int k=0; k<9; k++){
       this.matrix[j][k] = new Cell(j,k);}}}
    
  
  public void solveSimpleLogic(){
    simpleLogicCounter++;
      while(!this.hints.isEmpty()){
        Cell noCandidate = hints.dequeue();
        int column = noCandidate.getColumn();
        int row = noCandidate.getRow();
        int block = noCandidate.getBlock();
        int value = noCandidate.candidateList.get(0);
        
        //removes the value of the solved cell in the same row
        for(int i=0; i<9; i++){
          if(this.matrix[i][column].candidateList.contains(value) && !this.matrix[i][column].solved){
            this.matrix[i][column].candidateList.remove((Integer) value);
            simpleLogicReduceCells++;}}

        //removes the value of the solved cell in the same column      
        for(int j=0; j<9; j++){
          if(this.matrix[row][j].candidateList.contains(value) && !this.matrix[row][j].solved){
            this.matrix[row][j].candidateList.remove((Integer) value);
            simpleLogicReduceCells++;}}
        
        //removes the value of the solved cell in the same block and enqueues the new solved cells to the hint queue
        for(int k=0; k<9; k++){
          for(int l=0; l<9; l++){
            if(this.matrix[k][l].candidateList.contains(value)&& this.matrix[k][l].getBlock() == block &&  !this.matrix[k][l].solved){
              this.matrix[k][l].candidateList.remove((Integer) value);
              simpleLogicReduceCells++;}
             if(this.matrix[k][l].candidateList.size() ==1 && !this.matrix[k][l].solved){
                hints.enqueue(this.matrix[k][l]);
                this.matrix[k][l].solvedNow();}}}
      }
  }
 
    
    public void generalization(){
      generalizationCounter++;
      int counter = 0;
      int position = 0;
      
      //checks all rows whether there is an integer in the candidate list that only appears once, 
      //if yes, set this cell to this value and appplies simple logic method
      for(int i=0; i<9; i++){
        for(int k=1; k<10; k++){
          counter = 0;
          for(int j=0; j<9; j++){
            if(this.matrix[i][j].getCandidateList().contains((Integer) k) && !this.matrix[i][j].solved){
              counter++;
              position = j;              
            }}
            if(counter == 1){
              this.matrix[i][position].getCandidateList().clear();
              this.matrix[i][position].getCandidateList().add(k);
              hints.enqueue(this.matrix[i][position]);
              this.matrix[i][position].solvedNow();
              solveSimpleLogic();
            }
          }
      }
        
      
       //checks all columns whether there is an integer in the candidate list that only appears once, 
      //if yes, set this cell to this value and appplies simple logic method
      for(int j=0; j<9; j++){
        for(int k=1; k<10; k++){
          counter = 0;
          for(int i=0; i<9; i++){
            if(this.matrix[i][j].getCandidateList().contains(k) && !this.matrix[i][j].solved){
              counter++;
              position = i;
            }}
            if(counter == 1){
              this.matrix[position][j].getCandidateList().clear();
              this.matrix[position][j].getCandidateList().add(k);
              hints.enqueue(this.matrix[position][j]);
              this.matrix[position][j].solvedNow();
              solveSimpleLogic();
            }
          }
        }
      
       //checks all blocks whether there is an integer in the candidate list that only appears once, 
      //if yes, set this cell to this value and appplies simple logic method
      int column=0;
      int row=0;
      for(int k=1; k<10; k++){
        for(int l=0; l<11; l++){                 
          counter = 0;
          column = 0;
          row = 0;
          for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
              if(this.matrix[i][j].getCandidateList().contains(k) && this.matrix[i][j].getBlock() == l &&!this.matrix[i][j].solved){
                counter++;
                row = i;
                column = j;
              }}}
              if(counter == 1){
                this.matrix[row][column].getCandidateList().clear();
                this.matrix[row][column].getCandidateList().add(k);
                hints.enqueue(this.matrix[row][column]);
                this.matrix[row][column].solvedNow();
                solveSimpleLogic();
              }
            }
          }
        }
      
    
    //applied in SolveSudoku.java
    public void solve(){
      this.solveSimpleLogic();
      this.generalization();
      this.branching(this);
    }
    
    //applied in SolveSudoku.java
    public void print(){
      for(int i=0; i<9; i++) {
        for(int j=0; j<9; j++) {
          if(this.matrix[i][j].solved){
            System.out.print(this.matrix[i][j].getCandidateList().get(0) + "  ");
          }
          else{
            System.out.print("0  ");}
        }
        System.out.println();
      }
      System.out.println("The Sudoku has been solved by using the following approaches:");
      System.out.println("Simple Logic has been used " + simpleLogicCounter + " times and reduced the size of a list in a cell "+ simpleLogicReduceCells + " times.");
      System.out.println("Generalization has been used " + generalizationCounter + " times.");
      System.out.println("The Sudoku has beeen solved by branching "+ branchingCounter + " times.");
      
    
    }

  //copies a Sudoku, therefore, all properties of each cell and the hint queue 
  public Sudoku copy(Sudoku copy){
    for(int i=0; i<9; i++) {
      for(int j=0; j<9; j++) {
        copy.matrix[i][j].row = this.matrix[i][j].row;
        copy.matrix[i][j].column = this.matrix[i][j].column;
        copy.matrix[i][j].solved = this.matrix[i][j].solved;
        copy.matrix[i][j].candidateList = new ArrayList<Integer>(this.matrix[i][j].candidateList);
      }}
      Queue<Cell> temp = new Queue<Cell>();

      while(!this.hints.isEmpty()){
        temp.enqueue(this.hints.dequeue());
      }
      while(!temp.isEmpty()){
        Cell tempItem = temp.dequeue();
        copy.hints.enqueue(tempItem);
        this.hints.enqueue(tempItem);}
        
    return copy;
  }
  //for ennumeration to loop the method until the sudoku is solved
  public boolean sudokuSolved(){
    for(int i=0; i<9; i++){
      for(int j=0; j<9; j++){
        if(this.matrix[i][j].candidateList.size()!=1){
          return false;}
      }
    }
    return true;
  }
  
  public void branching(Sudoku sudo){
    Stack<Sudoku> sudoStack = new Stack<Sudoku>();
    sudo = this.copy(this);
    //finds an unsolved cell in the matrix, calls it candidateCell
    while(!sudo.sudokuSolved()){
      Cell candidateCell = null;
      for(int i=0; i<9; i++){
        for(int j=0; j<9; j++){
          if(this.matrix[i][j].candidateList.size() >1){
            candidateCell = sudo.matrix[i][j];
            break;
          }
          if(candidateCell != null){
            break;}
        }
      }
      //checks the candidate List of the unsolved cell and tries to solve the sudoku with help of the simpleMethod and generalization
      while(!candidateCell.candidateList.isEmpty()){
        Sudoku copy = new Sudoku();
        copy = sudo.copy(copy);
        sudoStack.push(copy);
        int value = candidateCell.candidateList.get(0);
        candidateCell.candidateList.clear();
        candidateCell.candidateList.add(value);
        hints.enqueue(candidateCell);
        candidateCell.solvedNow();
        solveSimpleLogic();
        generalization();
        if(this.sudokuSolved()){break;}
        // if the cell was not correct, the program starts back at the old sudoku with another one
        else{
          branchingCounter++;
          copy = sudoStack.pop();
          break;
        }  
      }
    }
  }
}