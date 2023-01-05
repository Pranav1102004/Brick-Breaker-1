package brickBreaker;

import java.awt.Color;
import java.awt.Graphics2D;

public class MapGenerator {
	public int[][] map;
	public int width;
	public int height;
	
	
	public MapGenerator(int row, int col) {
		map = new int[row][col];
		for(int i =0;i<row;i++) {
			for(int j =0;j<col;j++) {
				map[i][j] = 1; 
			}
			
		}
		width = 540/col;
		height=150/row;
	
	}
	public void setBrick(int value,int r,int c) {
		map[r][c]=value;
	}
	
	public void draw(Graphics2D g) {
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map[0].length;j++) {
				if(map[i][j]>0) {
					g.setColor(Color.BLUE);
					g.fillRect(j*width+70, i*height+50, width,height);
					g.setColor(Color.black);
					g.drawRect(j*width+70, i*height+50, width,height);
				}
			}
		}
		
	}

}
