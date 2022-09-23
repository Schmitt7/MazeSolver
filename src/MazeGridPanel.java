import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import javax.swing.JPanel;

public class MazeGridPanel extends JPanel{
	private int rows;
	private int cols;
	private Cell[][] maze;



	// extra credit
	public void genDFSMaze() {
		boolean[][] visited;
		Stack<Cell> stack  = new Stack<Cell>();
		Cell start = maze[0][0];
		stack.push(start);
	}

	//homework
	public void solveMaze() {
		Stack<Cell> stack  = new Stack<Cell>();
		Cell start = maze[0][0];
		start.setBackground(Color.GREEN);
		Cell finish = maze[rows-1][cols-1];
		finish.setBackground(Color.RED);
		stack.push(start);



		while(!stack.empty()){
			// If the stack is not empty then run code below

			Cell current = stack.peek();
			// Returns what is on top of the stack

			if (current.getBackground() == Color.RED){

				break;
				// If trail gets to red exit then program ends
			}
			if(!current.northWall && !visited(current.row - 1, current.col)){
				// Not at north wall or a visited square

				stack.push(maze[current.row - 1][current.col]);
				// Move north

				current.setBackground(Color.CYAN);
				// Sets path to Cyan
			}

			else if(!current.eastWall && !visited(current.row, current.col + 1)){
				// Not at east wall or visited square

				stack.push(maze[current.row][current.col + 1]);
				// Move east

				current.setBackground(Color.CYAN);

			}

			else if(!current.southWall && !visited(current.row + 1, current.col)){
				// Not at south wall or a visited square

				stack.push(maze[current.row + 1][current.col]);
				// Move south

				current.setBackground(Color.CYAN);

			}


			else if(!current.westWall && !visited(current.row, current.col - 1)){
				// Not at west wall or visited square

				stack.push(maze[current.row][current.col - 1]);
				// Move south

				current.setBackground(Color.CYAN);

			}


			else {
				current.setBackground(Color.YELLOW);
				// Sets backup color to yellow

				stack.pop();
				// Back up and pop off stack when hitting a dead end
			}
		}
		start.setBackground(Color.GREEN);

	}


	

	


	public boolean visited(int row, int col) {
		Cell c = maze[row][col];
		Color status = c.getBackground();
		if(status.equals(Color.WHITE)  || status.equals(Color.RED)  ) {
			return false;
		}


		return true;


	}


	public void genNWMaze() {
		
		for(int row = 0; row  < rows; row++) {
			for(int col = 0; col < cols; col++) {

				if(row == 0 && col ==0) {
					continue;
				}

				else if(row ==0) {
					maze[row][col].westWall = false;
					maze[row][col-1].eastWall = false;
				} else if(col == 0) {
					maze[row][col].northWall = false;
					maze[row-1][col].southWall = false;
				}else {
					boolean north = Math.random()  < 0.5;
					if(north ) {
						maze[row][col].northWall = false;
						maze[row-1][col].southWall = false;
					} else {  // remove west
						maze[row][col].westWall = false;
						maze[row][col-1].eastWall = false;
					}
					maze[row][col].repaint();
				}
			}
		}
		this.repaint();
		
	}

	public MazeGridPanel(int rows, int cols) {
		this.setPreferredSize(new Dimension(800,800));
		this.rows = rows;
		this.cols = cols;
		this.setLayout(new GridLayout(rows,cols));
		this.maze =  new Cell[rows][cols];
		for(int row = 0 ; row  < rows ; row++) {
			for(int col = 0; col < cols; col++) {

				maze[row][col] = new Cell(row,col);

				this.add(maze[row][col]);
			}

		}


		this.genNWMaze();
		this.solveMaze();
		
	}




}
