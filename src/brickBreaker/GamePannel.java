package brickBreaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePannel extends JPanel implements ActionListener,KeyListener {
	private int score=0;
	private boolean play = false;
	private int totalBrick = 30;
	private Timer timer;
	private int delay =5;
	private int ballposx= 100;
	private int ballposy= 500;
	private int balldirx= -1;
	private int balldiry= -2;
	private int playerx = 350;
	private MapGenerator map;
	
	public GamePannel() {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(true);
		timer = new Timer(delay,this);
		timer.start();
		map = new MapGenerator(3,7);
		
	}
	
	
	public void paint(Graphics g) {
		//canvas color
		g.setColor(Color.black);
		g.fillRect(1, 1, 692, 592);
		//border color

		g.setColor(Color.green);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(0, 3, 3, 592);
		g.fillRect(683, 3, 3, 592);
		//paddle
		g.setColor(Color.red);
		g.fillRect(playerx, 550, 100, 8);
		//ball
		g.setColor(Color.YELLOW);
		g.fillOval(ballposx, ballposy, 20, 20);
		//bricks
		map.draw((Graphics2D)g);
		//score
		g.setColor(Color.PINK);
		g.setFont(new Font("serif",Font.BOLD,20));
		g.drawString("Score:- "+score, 550, 30);
		//game over
		if(ballposy>584) {
			play=false;
			balldirx=0;
			balldiry=0;
			g.setColor(Color.CYAN);
			g.setFont(new Font("serif",Font.BOLD,20));
			g.drawString("GAME OVER!  , SCORE-: "+score,200, 300);
			g.setColor(Color.CYAN);
			g.setFont(new Font("serif",Font.BOLD,20));
			g.drawString("Press Enter to Restart!",240, 330);
			
		}
		
		
		 if(score==105) {
				play=false;
				balldirx=0;
				balldiry=0;
				g.setColor(Color.CYAN);
				g.setFont(new Font("serif",Font.BOLD,20));
				g.drawString("YOU WON!  , SCORE-: "+score,200, 300);
				g.setColor(Color.CYAN);
				g.setFont(new Font("serif",Font.BOLD,20));
				g.drawString("Press Enter to Restart!",240, 330);
				
			}
		
		
		
	
	}
	
	public void moveLeft() {
		play=true;
		playerx -=20;
	}
	public void moveRight() {
		play=true;
		playerx+=20;
	}


	


	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			if(playerx<=0) {
				playerx=0;
			}else
				moveLeft();
		}if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			if(playerx>=600) {
				playerx=600;
			}
			else {
				moveRight();
		}
			
		
		}
			if(e.getKeyCode()==KeyEvent.VK_ENTER) {
				if(!play) {
					
				     score=0;
				     totalBrick=30;
					 ballposx= 100;
					 ballposy= 500;
					 balldirx= -1;
					 balldiry=-2;
					 playerx=320;
					 map=new MapGenerator(3,7);
					
					}
		repaint();
		
	}
		}


	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(play) {
			if(ballposx<=0) {
				balldirx=-balldirx;
			}if(ballposy<=0) {
				balldiry=-balldiry;
			}if(ballposx>=663) {
				balldirx=-balldirx;
			}
			Rectangle ballrect = new Rectangle(ballposx,ballposy,20,20);
			Rectangle paddlerect = new Rectangle(playerx, 550, 100, 8);
			if(ballrect.intersects(paddlerect)) {
				balldiry=-balldiry;
			}
			
			A:for(int i=0;i<map.map.length;i++) {
				for(int j=0;j<map.map[0].length;j++) {
					if(map.map[i][j]>0) {
					int width = map.width;
					int height = map.height;
					int brickposx = 70+j*width;
					int brickposy = 50+i*height;
					Rectangle brick = new Rectangle(brickposx,brickposy,width,height);
					if(ballrect.intersects(brick)) {
						map.setBrick(0, i, j);
						totalBrick--;
						score+=5;
						if(ballposx+19<=brickposx||ballposx+1>=brickposx+width) {
							balldirx =-balldiry;
						}else {
							balldiry=-balldiry;
						}
						break A;
					}
				}
				}
			}
			
			
			
			
			
			
			
			
			
			
			
			
			ballposx+=balldirx;
			ballposy+=balldiry;
		}
		repaint();
	}
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {}

	
	
	
	
	
	

}
