package life;

import java.awt.Color;

import bigGrid.BigGrid.Boundary;
import bigGrid.DenseBigGrid;
/**
 * @author Zepeng Chen
 * @studentId 202094147
 * @emai zepengc@mun.ca
 *              -----------Declaration---------------
 * This file was prepared by Zepeng Chen. It was completed by me alone
 * --------------------------------------------------------------------
 * This file create different classes which implement the same interface.
 * Different classes have different functionalities of doing addtion, subtraction, etc.
 */
public class Gomoku implements LifeLike{

	Color WHITE=Color.WHITE;
	Color BLACK=Color.BLACK;
	Color GREEN=Color.GREEN;
	
	DenseBigGrid<Color> dg=new DenseBigGrid<Color>(GREEN);
	Boundary bound=dg.getBoundary();
	int top=bound.top();
	int bottom=bound.bottom();
	int left=bound.left();
	int right=bound.right();
	//count the same color Horizontally
	
	public boolean isFiveHorizontal(int x, int y) {
		int xx=x;
		int yy=y;
		int count=0;
		while(yy>bottom) {
			while(xx<right) {
				if(count<4) {
					if(getColor(xx,yy).equals(getColor(xx+1,yy))) {
						count++;
						xx++;
					}
					else {
						count=0;
						xx++;
					}
				}
				else {
					if(getColor(xx-1,yy).equals(GREEN)) return false;
					else return true;
					
				}
			}
			yy--;
		}
		return false;
	}
	
	int countV=0;
public boolean isFiveVertical(int x, int y) {
		
		while(x<right) {
			while(y>bottom) {
				
				if(countV==4) {
					return true;
				}
				Color currentClr=dg.getCellValue(x, y);
				Color nextClr=dg.getCellValue(x, y-1);
				if(nextClr==currentClr) {
					countV++;//encounter the same color, counter added and do recursion
					isFiveVertical(x,y-1);
				}
				else {
					countV=0;//color is not continuous, reset count and do recursion
					isFiveVertical(x,y-1);
					
				}
			}
			x++;
		}
		return false;
		
	}

int countD1=0;
public boolean isFiveDiagonal1(int x, int y) {
		
		while(x<right) {
			while(y>bottom) {
				
				if(countD1==4) {
					return true;
				}
				Color currentClr=dg.getCellValue(x, y);
				Color nextClr=dg.getCellValue(x+1, y-1);
				if(nextClr==currentClr) {
					countD1++;//encounter the same color, counter added and do recursion
					isFiveDiagonal1(x+1,y-1);
				}
				else {
					countD1=0;//color is not continuous, reset count and do recursion
					isFiveDiagonal1(x+1,y-1);
					
				}
			}
			x++;
		}
		return false;
		
	}

int countD2=0;
public boolean isFiveDiagonal2(int x, int y) {
		
		while(x>left) {
			while(y>bottom) {
				
				if(countD2==4) {
					return true;
				}
				Color currentClr=dg.getCellValue(x, y);
				Color nextClr=dg.getCellValue(x-1, y-1);
				if(nextClr==currentClr) {
					countD2++;//encounter the same color, counter added and do recursion
					isFiveDiagonal2(x-1,y-1);
				}
				else {
					countD2=0;//color is not continuous, reset count and do recursion
					isFiveDiagonal2(x-1,y-1);
					
				}
			}
			x--;
		}
		return false;
		
	}

	public boolean isFinalState() {
		if(isFiveHorizontal(left,top) ||
		   isFiveVertical(left,top) ||
		   isFiveDiagonal1(left,top)||
		   isFiveDiagonal2(right, top)) {
			return true;
		}
		else return false;
	}
	
	public void toggle(int x, int y) {
		// TODO Auto-generated method stub
		Color value=dg.getCellValue(x, y);
		boolean isFinalState=isFinalState();
		if(!isFinalState && value==GREEN) {
			dg.setCellValue(x, y, BLACK);
		}
	}

	public void setColor(int x, int y, Color color) {
		// TODO Auto-generated method stub
		dg.setCellValue(x, y, color);
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		dg.setDefaultValue(GREEN);
	}

	@Override
	public void step() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Boundary getBoundary() {
		// TODO Auto-generated method stub
		return dg.getBoundary();
	}

	@Override
	public Color getDefaultColor() {
		// TODO Auto-generated method stub
		return dg.getDefaultValue();
	}

	@Override
	public Color getColor(int x, int y) {
		// TODO Auto-generated method stub
		return dg.getCellValue(x, y);
	}

}
