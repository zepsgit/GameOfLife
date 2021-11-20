package life;
import java.awt.Color;
import java.util.HashMap;
import bigGrid.BigGrid.Boundary;
import bigGrid.SparseBigGrid.Coordinate;
import bigGrid.SparseBigGrid;
import java.util.ArrayList;
import java.lang.Math;
/**
 * @author Zepeng Chen
 * @studentId 202094147
 * @emai zepengc@mun.ca
 *              -----------Declaration---------------
 * This file was prepared by Zepeng Chen. It was completed with the help of Erfan
 * --------------------------------------------------------------------
 */

public class Life implements LifeLike{
	private ArrayList<Color> allColors ; 
	private SparseBigGrid<Color> grid ;
	
	public Life() {
		this.grid = new SparseBigGrid<Color>(Color.WHITE);
		this.allColors = new ArrayList<Color>();
		this.allColors.add(getDefaultColor());
		this.allColors.add(Color.BLACK);
	}
		
	@Override
	public void reset() {
		// TODO Auto-generated method stub
		grid.setDefaultValue(getDefaultColor());
	}
	
	
	protected Color nextColor(int x, int y, ArrayList<Color> allColors) {
		int currentColorIdx = allColors.indexOf(grid.getCellValue(x, y));
		int nextColorIdx = (currentColorIdx >= allColors.size() - 1 ) ? 0 : currentColorIdx + 1 ;
		return allColors.get(nextColorIdx); 
		
	}
	
	
	@Override
	public void toggle(int x, int y) {
		
		// TODO Auto-generated method stub
		grid.setCellValue(x, y, nextColor(x, y, allColors));
	
	}
	
	
	
	public int countColor(int x, int y, Color clr) {
		int count=0;
			for(int i=x-1; i<=x+1; i++) {
				for(int j=y+1; j>=y-1; j--) {
					if(i==x && j==y) continue;
					if(grid.getCellValue(i, j)==clr) count++;
				}
			}
			return count;
	}

	protected void nextStep(int x, int y) {
		int neighborNum = countColor(x, y, Color.BLACK);
		Color cellColor = this.grid.getCellValue(x, y);
		if(cellColor.equals(Color.WHITE)) {
			
			if(neighborNum == 3) {
				toggle(x, y);	
			}
		
			
		}else {
			
			if(neighborNum == 3 || neighborNum == 2) {
				//do nothing.
			}else {
				
				toggle(x, y);	
			}
			
			
		}
	}
	
	@Override
	public void step() {
		/*
		 * The following Conway's rule excerpted from wikipedia
		 * 
		 * Any live cell with fewer than two live neighbours dies, as if by underpopulation.
		   Any live cell with two or three live neighbours lives on to the next generation.
		   Any live cell with more than three live neighbours dies, as if by overpopulation.
           Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
		 * 
		 * I define white color as dead state, black as live.
		 * */
		// TODO Auto-generated method stub
		Boundary boundary = grid.getBoundary();
		for(int x = boundary.left() ; x <= boundary.right() ; x ++) {
			for(int y = boundary.bottom() ; y <= boundary.top() ; y ++) {
				nextStep(x, y);
			}
		}
		
	}
	@Override
	public void setColor(int x, int y, Color color) {
		// TODO Auto-generated method stub
		grid.setCellValue(x, y, color);
	}
	@Override
	public Boundary getBoundary() {
		// TODO Auto-generated method stub
		return grid.getBoundary();
		
	}
	@Override
	public Color getDefaultColor() {
		// TODO Auto-generated method stub
		return Color.WHITE;
	}
	
	public ArrayList<Color> getPalette(){
		return allColors; 
	}
	
	@Override
	public Color getColor(int x,int y) {
		// TODO Auto-generated method stub
		return this.grid.getCellValue(x, y);
	}

}
