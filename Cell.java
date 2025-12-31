import java.util.*;

public class Cell{
  public int row;
  public int column;
  public ArrayList<Integer> candidateList;
  public boolean solved;

  public Cell(int row, int column){
    this.row = row;
    this.column = column;
    this.candidateList = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9));
    this.solved = false;
  }

  public int getRow(){
    return this.row;}
  
  public int getColumn(){
    return this.column;}
  
  
  /*dividing the row and column number by 3, we make sure that cells in the same block get the same number, 
  e.g cell in row 1, column 1 gets number 0,0. By some calculations we make sure that cells in the same block get allocated
  to the same one digit "block number" */
  
  public int getBlock(){
    return ((int)(this.row/3))*2+((int)(this.column/3))*3; }
  
      
  public ArrayList<Integer> getCandidateList(){
    return this.candidateList;}
  
  public void setCandidateList(ArrayList<Integer> arrayList){
    this.candidateList = new ArrayList<Integer>(arrayList);
  }
  public void solvedNow(){
    this.solved = true;}
  
  public boolean columnValid(Sudoku sudo, int value){
    for(int i=0; i<9; i++){
      if(sudo.matrix[i][this.column].candidateList.get(0) == value && sudo.matrix[i][this.column].solved){
        return false;}
    }
    return true;
  }
  
  public boolean rowValid(Sudoku sudo, int value){
    for(int j=0; j<9; j++){
      if(sudo.matrix[this.row][j].candidateList.get(0) == value && sudo.matrix[this.row][j].solved){
        return false;}
    }
    return true;
  }
  
  public boolean blockValid(Sudoku sudo, int value){
    for(int k=0; k<9; k++){
      for(int l=0; l<9; l++){
        if(sudo.matrix[k][l].candidateList.get(0) == value && sudo.matrix[k][l].solved && sudo.matrix[k][l].getBlock() == this.getBlock()){
        return false;}
    }
  }
   return true;
  }
  
  public boolean isValid(Sudoku sudo, int value){
    if(rowValid(sudo,value) && columnValid(sudo,value) && blockValid(sudo,value)){
      return true;
    }
    return false;}
  

        
      
} 
 