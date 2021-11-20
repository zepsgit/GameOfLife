package life;
import java.awt.Color;
import java.util.HashMap;
import bigGrid.BigGrid.Boundary;
import bigGrid.SparseBigGrid.Coordinate;
import bigGrid.SparseBigGrid;
import java.util.ArrayList;
import java.lang.Math;

import java.awt.Color;

import bigGrid.BigGrid.Boundary;
import bigGrid.DenseBigGrid;
/**
 * @author Zepeng Chen
 * @studentId 202094147
 * @emai zepengc@mun.ca
 *              -----------Declaration---------------
 * This file was prepared by Zepeng Chen. It was completed with the help of Erfan
 * --------------------------------------------------------------------
 */

public class LifeAndDeath implements LifeLike{
	ArrayList<Color> allColors ; 
	SparseBigGrid<Color> grid ;
	int minBoundary = 10 ; 
	/**
	 * every time, when we click the grid, this class will be invoked.
	 * so we can create an ArrayList for storing the color for toggling.
	 */
	public LifeAndDeath() {
		//this.grid = new SparseBigGrid<Color>(Color.WHITE);
		this.grid = new SparseBigGrid<Color>(getDefaultColor());
		this.allColors = new ArrayList<Color>();
		this.allColors.add(getDefaultColor());
		this.allColors.add(Color.GREEN);
		this.allColors.add(Color.BLACK);
		this.allColors.add(Color.RED);
	}
		
	@Override
	public void reset() {
		// TODO Auto-generated method stub
		grid.setDefaultValue(getDefaultColor());
	}
	
	
	public void toggle(int x, int y) {
		// TODO Auto-generated method stub
		Color value=grid.getCellValue(x, y);
		if(value==Color.WHITE) {
			grid.setCellValue(x, y, Color.GREEN);
		}
		if(value==Color.GREEN) {
			grid.setCellValue(x, y, Color.BLACK);
		}
		if(value==Color.BLACK) {
			grid.setCellValue(x, y, Color.RED);
		}
		if(value==Color.RED) {
			grid.setCellValue(x, y, Color.WHITE);
		}
	}
	
	private HashMap<String, Coordinate> NeighborsCoordinate(int x, int y) {
		HashMap<String, Coordinate> neighborsCoordinate = new HashMap<String, Coordinate>();
		neighborsCoordinate.put("north", new Coordinate(x, y+1));
		neighborsCoordinate.put("northEast", new Coordinate(x+1, y+1));
		neighborsCoordinate.put("east", new Coordinate(x+1, y));
		neighborsCoordinate.put("southEast", new Coordinate(x+1, y-1));
		neighborsCoordinate.put("south", new Coordinate(x, y-1));
		neighborsCoordinate.put("southWest", new Coordinate(x-1, y-1));
		neighborsCoordinate.put("west", new Coordinate(x-1, y));
		neighborsCoordinate.put("northWest", new Coordinate(x-1, y+1));
		
		return neighborsCoordinate ;
	}
	//we can do loop in the same function, 
	//but it is more readable to separate it with several subroutine.
	private int liveNeighborsNum(HashMap<String, Coordinate> hm, Color col) {
		int livesNum = 0 ;
		for(Coordinate i : hm.values()) {
			if(grid.getCellValue(i.x(), i.y()).equals(col)) 
				livesNum++ ;
		}
		return livesNum;
	}
	
	
	private void nextStep(int x, int y) {
		
		Color cellColor = this.grid.getCellValue(x, y);
		HashMap<String, Coordinate> neighbors = this.NeighborsCoordinate(x, y);
		
		int greenNum = liveNeighborsNum(neighbors, Color.GREEN );
		int blackNum = liveNeighborsNum(neighbors, Color.BLACK );
		int redNum = liveNeighborsNum(neighbors, Color.RED );
		
		
		if(cellColor.equals(Color.WHITE)) {
			
			
			
			if(greenNum >= 3) {
				this.grid.setCellValue(x, y, Color.GREEN);
				
			}else {
				this.grid.setCellValue(x, y, Color.WHITE);
				
			}
				
			
			
		}else if (cellColor.equals(Color.GREEN)) {
			
			
			
			
			if(blackNum == 1) {
				this.grid.setCellValue(x, y, Color.WHITE);
				
			}else if(blackNum != 1 && greenNum >= 6) {
				this.grid.setCellValue(x, y, Color.WHITE);
				
			}else if(blackNum > 1 && greenNum < 6) {
				this.grid.setCellValue(x, y, Color.BLACK);
				
			}else {
				this.grid.setCellValue(x, y, Color.GREEN);
				
			}
			
			
			
			
		}else if (cellColor.equals(Color.BLACK)) {
			
			
			
			
			if(redNum >=2) {
				this.grid.setCellValue(x, y, Color.RED);
				
			}else if(redNum < 2 && greenNum == 0) {
				this.grid.setCellValue(x, y, Color.WHITE);

			}else if(redNum < 2 && blackNum ==0) {
				this.grid.setCellValue(x, y, Color.WHITE);

			}else {
				this.grid.setCellValue(x, y, Color.BLACK);
				
			}
			
			
			
			
		}else if (cellColor.equals(Color.RED)) {
			
			
			
			if(blackNum == 0 && greenNum == 0) {
				this.grid.setCellValue(x, y, Color.WHITE);

			}else {
				this.grid.setCellValue(x, y, Color.RED);

			}
		}

	}
	
	@Override
	public void step() {
		// TODO Auto-generated method stub
		Boundary boundary = grid.getBoundary();
		for(int x = boundary.left() ; x <= boundary.right() ; x ++) {
			for(int y = boundary.bottom() ; y <= boundary.top() ; y ++) {
				this.nextStep(x, y);
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
