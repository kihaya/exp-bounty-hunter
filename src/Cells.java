
/**
 * @author kihaya
 * rep for cells to share situation.
 */
public class Cells {
	
   public final int CELL_X = 60;
   public final int CELL_Y = 40;
   private int[][] cells;
   
   Cells(){
	   cells = new int[CELL_X][CELL_Y];
	   for(int i=0;i<CELL_X;++i){
		   for(int j=0;j<CELL_Y;++j){
			   cells[i][j] = Consts.EMPTY;
		   }
	   }
   }
   
   public void setValue(int x,int y,int value){
	   cells[x][y] = value;
   }
   
   public int getValue(int x,int y){
	   return cells[x][y];
   }
}
