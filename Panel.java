package arimaa;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Panel extends JPanel {
	boolean[][] highlightedCells = new boolean[8][8];

	int length = 8;
	// creates variables for the class
	Animal[][] cells = new Animal[length][length];
	double width;
	double height;
	ArrayList<Animal> animals = new ArrayList<Animal>();
	Animal LastPiece = null;
	Animal pushPiece = null;
	int pushDirection = 0;
	int pullDirection = 0;
	final int FORWARD = 1;
	final int BACKWARD = 2;
	final int LEFT = 3;
	final int RIGHT = 4;
	boolean redcheck = true;

	// sets all grid squares to null
	public Panel() {
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[0].length; j++) {
				cells[i][j] = null;
			}
		}

	}
	// prints all animals, mainly used just to check code
	public void printAnimals() {
		for (int i = 0; i < animals.size(); i++) {
			System.out.println(animals.get(i).getName());
		}
	}
	// returns if a cell is highlighted
	public boolean isHighlightedCell(int row, int col) {
		return highlightedCells[row][col];

	}
	// resets the panel
	public void resetPanel() {
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells.length; j++) {
				cells[i][j] = null;
			}
		}
		int size = animals.size();
		int index = 0;
		for( int x=0; x< size; x++)
		{
			animals.remove(index);
			
		}
				
	}
	// meant to get height of each cell
	public double cellHeight() {
		return height / length;
	}
	// meant to get height of each cell
	public int getLength() {
		return length;
	}
	// checks if cell is empty
	public boolean IsEmptyCell(int row, int col) {
		return cells[row][col] == null;
	}
	// gets team abed off of col and row
	public TeamType getTeam(int row, int col) {
		if (cells[row][col] == null) {
			return null;
		}
		return cells[row][col].getTeam();
	}
	// sets indicator to highlight the moves of an animals based off of row and col
	public void HighlightMoves(int row, int col) {
		this.clearHighlighted();

		cells[row][col].setHighlighted(true);
		LastPiece = cells[row][col];
		this.repaint();

	}
	// meant to set up default location of players, meant to test code easier
	public void DrawDefault() {
		addAnimal(new Elephant(TeamType.Gold, 1, 1));
		addAnimal(new Camel(TeamType.Gold, 1, 1));
		addAnimal(new Horse(TeamType.Gold, 1, 1));
		addAnimal(new Horse(TeamType.Gold, 1, 1));
		addAnimal(new Dog(TeamType.Gold, 1, 1));
		addAnimal(new Dog(TeamType.Gold, 1, 1));
		addAnimal(new Cat(TeamType.Gold, 1, 1));
		addAnimal(new Cat(TeamType.Gold, 1, 1));
		addAnimal(new Rabbit(TeamType.Gold, 1, 1));
		addAnimal(new Rabbit(TeamType.Gold, 1, 1));
		addAnimal(new Rabbit(TeamType.Gold, 1, 1));
		addAnimal(new Rabbit(TeamType.Gold, 1, 1));
		addAnimal(new Rabbit(TeamType.Gold, 1, 1));
		addAnimal(new Rabbit(TeamType.Gold, 1, 1));
		addAnimal(new Rabbit(TeamType.Gold, 1, 1));
		addAnimal(new Rabbit(TeamType.Gold, 1, 1));
		int index = 0;
		for (int i = 7; i > 5; i--) {
			for (int j = 0; j < length; j++) {

				Animal a = animals.get(index++);
				a.setCurrentCoordinates(i, j);
				UpdateAnimal(a, false);
			}
		}

		addAnimal(new Elephant(TeamType.Silver, 1, 1));
		addAnimal(new Camel(TeamType.Silver, 1, 1));
		addAnimal(new Horse(TeamType.Silver, 1, 1));
		addAnimal(new Horse(TeamType.Silver, 1, 1));
		addAnimal(new Dog(TeamType.Silver, 1, 1));
		addAnimal(new Dog(TeamType.Silver, 1, 1));
		addAnimal(new Cat(TeamType.Silver, 1, 1));
		addAnimal(new Cat(TeamType.Silver, 1, 1));
		addAnimal(new Rabbit(TeamType.Silver, 1, 1));
		addAnimal(new Rabbit(TeamType.Silver, 1, 1));
		addAnimal(new Rabbit(TeamType.Silver, 1, 1));
		addAnimal(new Rabbit(TeamType.Silver, 1, 1));
		addAnimal(new Rabbit(TeamType.Silver, 1, 1));
		addAnimal(new Rabbit(TeamType.Silver, 1, 1));
		addAnimal(new Rabbit(TeamType.Silver, 1, 1));
		addAnimal(new Rabbit(TeamType.Silver, 1, 1));
		for (int i1 = 0; i1 < 2; i1++) {
			for (int j1 = 0; j1 < length; j1++) {

				Animal a = animals.get(index++);
				a.setCurrentCoordinates(i1, j1);
				UpdateAnimal(a, false);
			}
		}
		this.repaint();
	}
	// adds animals when button is clicked
	public void addAnimal(Animal a) {
		animals.add(a);
		cells[a.getCurrentRow()][a.getCurrentCol()] = a;
		this.repaint();
	}
	// updates animals location
	public void UpdateAnimal(Animal a, boolean refresh) {
		cells[a.getCurrentRow()][a.getCurrentCol()] = a;
		if (refresh) {
			this.repaint();
		}
	}
	// paints all animals
	public void paintAnimals(Graphics g) {
		for (int i = 0; i < animals.size(); i++) {
			
			paintAnimal(animals.get(i), g);
		}
		
	}
	// moves a piece
	public void MovePiece(int row, int col) {
		cells[LastPiece.getCurrentRow()][LastPiece.getCurrentCol()] = null;
		cells[row][col] = LastPiece;
		LastPiece.setCurrentCoordinates(row, col);
		clearHighlighted();
		this.repaint();

	}

	// pushes a piece 
	public void pushPiece(int row, int col) {
	
		if (pushDirection == FORWARD && (row - 1 > -1)) {
			pushPiece = cells[row][col];
			cells[LastPiece.getCurrentRow()][LastPiece.getCurrentCol()] = null;
			cells[row][col] = LastPiece;
			LastPiece.setCurrentCoordinates(row, col);
			cells[row - 1][col] = pushPiece;
			pushPiece.setCurrentCoordinates(row - 1, col);
		} else if (pushDirection == BACKWARD && (row + 1 < 8)) {
			pushPiece = cells[row][col];
			cells[LastPiece.getCurrentRow()][LastPiece.getCurrentCol()] = null;
			cells[row][col] = LastPiece;
			LastPiece.setCurrentCoordinates(row, col);
			cells[row + 1][col] = pushPiece;
			pushPiece.setCurrentCoordinates(row + 1, col);
		} else if (pushDirection == LEFT && (col - 1 > -1)) {
			pushPiece = cells[row][col];
			cells[LastPiece.getCurrentRow()][LastPiece.getCurrentCol()] = null;
			cells[row][col] = LastPiece;
			LastPiece.setCurrentCoordinates(row, col);
			cells[row][col - 1] = pushPiece;
			pushPiece.setCurrentCoordinates(row, col - 1);
		} else if (pushDirection == RIGHT && (col + 1 < 8)) {
			pushPiece = cells[row][col];
			cells[LastPiece.getCurrentRow()][LastPiece.getCurrentCol()] = null;
			cells[row][col] = LastPiece;
			LastPiece.setCurrentCoordinates(row, col);
			cells[row][col + 1] = pushPiece;
			pushPiece.setCurrentCoordinates(row, col + 1);
		}
		clearHighlighted();
		repaint();

	}
	// pulls a piece
	public void pullPiece(int row, int col) {
		if (pullDirection == FORWARD && (row - 1 > -1) && cells[row-1][col] == null) {
			Animal otherPiece = cells[LastPiece.getCurrentRow() + 1][LastPiece.getCurrentCol()];
			cells[otherPiece.getCurrentRow()][otherPiece.getCurrentCol()] = null;
			otherPiece.setCurrentCoordinates(LastPiece.getCurrentRow(), LastPiece.getCurrentCol());
			cells[LastPiece.getCurrentRow()][LastPiece.getCurrentCol()] = otherPiece;
			LastPiece.setCurrentCoordinates(LastPiece.getCurrentRow() - 1, LastPiece.getCurrentCol());
			cells[LastPiece.getCurrentRow()][LastPiece.getCurrentCol()] = LastPiece;
		} else if (pullDirection == BACKWARD && (row + 1 < 8) && cells[row+1][col] == null) {
			Animal otherPiece = cells[LastPiece.getCurrentRow() - 1][LastPiece.getCurrentCol()];
			cells[otherPiece.getCurrentRow()][otherPiece.getCurrentCol()] = null;
			otherPiece.setCurrentCoordinates(LastPiece.getCurrentRow(), LastPiece.getCurrentCol());
			cells[LastPiece.getCurrentRow()][LastPiece.getCurrentCol()] = otherPiece;
			LastPiece.setCurrentCoordinates(LastPiece.getCurrentRow() + 1, LastPiece.getCurrentCol());
			cells[LastPiece.getCurrentRow()][LastPiece.getCurrentCol()] = LastPiece;
		} else if (pullDirection == LEFT && (col - 1 > -1) && cells[row][col-1] == null) {
			Animal otherPiece = cells[LastPiece.getCurrentRow()][LastPiece.getCurrentCol() + 1];
			cells[otherPiece.getCurrentRow()][otherPiece.getCurrentCol()] = null;
			otherPiece.setCurrentCoordinates(LastPiece.getCurrentRow(), LastPiece.getCurrentCol());
			cells[LastPiece.getCurrentRow()][LastPiece.getCurrentCol()] = otherPiece;
			LastPiece.setCurrentCoordinates(LastPiece.getCurrentRow(), LastPiece.getCurrentCol() - 1);
			cells[LastPiece.getCurrentRow()][LastPiece.getCurrentCol()] = LastPiece;
		} else if (pullDirection == RIGHT && (col + 1 < 8) && cells[row][col + 1] == null) {
			Animal otherPiece = cells[LastPiece.getCurrentRow()][LastPiece.getCurrentCol() - 1];
			cells[otherPiece.getCurrentRow()][otherPiece.getCurrentCol()] = null;
			otherPiece.setCurrentCoordinates(LastPiece.getCurrentRow(), LastPiece.getCurrentCol());
			cells[LastPiece.getCurrentRow()][LastPiece.getCurrentCol()] = otherPiece;
			LastPiece.setCurrentCoordinates(LastPiece.getCurrentRow(), LastPiece.getCurrentCol() + 1);
			cells[LastPiece.getCurrentRow()][LastPiece.getCurrentCol()] = LastPiece;
		}
		clearHighlighted();
		repaint();
	}
	// paints a single animals
	private void paintAnimal(Animal a, Graphics g) {
		g.drawImage(a.getMyImage(), a.getX(width), a.getY(height), (int) width, (int) height, null);

	}

	// paints panel with the grid squares, red squares and animals
	public void paintComponent(Graphics g) {
		//
		super.paintComponent(g);
		g.setColor(Color.RED);
		g.fillRect((int) (Math.round(2 * width)) + 1, (int) (Math.round(2 * height)) + 1, (int) width, (int) height);
		g.fillRect((int) (Math.round(2 * width)) + 1, (int) (Math.round(5 * height)) + 1, (int) width, (int) height);
		g.fillRect((int) (Math.round(5 * width)) + 1, (int) (Math.round(2 * height)) + 1, (int) width, (int) height);
		g.fillRect((int) (Math.round(5 * width)) + 1, (int) (Math.round(5 * height)) + 1, (int) width, (int) height);

		width = (double) this.getWidth() / (double) length;
		height = (double) this.getHeight() / (double) length;
		g.setColor(Color.BLACK);

		for (int i = 0; i < animals.size(); i++) {
			// highlights an animals available options
			if (animals.get(i).getHighlighted()) {
				int j = animals.get(i).getCurrentRow();
				int j2 = animals.get(i).getCurrentCol();
				animals.get(i).setHighlighted(false);
				// forward
				g.setColor(Color.GREEN);
				if ((j - 1) > -1) {
					if (cells[j - 1][j2] == null) {
						highlightForward(j, j2, g);
					} else if ((cells[j][j2].getStrength() > cells[j - 1][j2].getStrength()
							&& cells[j][j2].getTeam() != cells[j - 1][j2].getTeam() && j - 2 > -1
							&& cells[j - 2][j2] == null)) {
						pushDirection = FORWARD;
					
						highlightForward(j, j2, g);
					}
				}
				// back
				if ((j + 1) < 8) {
					if (cells[j + 1][j2] == null) {
						highlightBack(j, j2, g);
					} else if ((cells[j][j2].getStrength() > cells[j + 1][j2].getStrength()
							&& cells[j][j2].getTeam() != cells[j + 1][j2].getTeam()) && j + 2 < 8
							&& cells[j + 2][j2] == null) {
						pushDirection = BACKWARD;
				
						highlightBack(j, j2, g);
					}

				}
				// left
				if ((j2 - 1) > -1) {
					if (cells[j][j2 - 1] == null) {
						highlightLeft(j, j2, g);
					} else if ((cells[j][j2].getStrength() > cells[j][j2 - 1].getStrength()
							&& cells[j][j2].getTeam() != cells[j][j2 - 1].getTeam()) && j2 - 2 > -1
							&& cells[j][j2 - 2] == null) {
						pushDirection = LEFT;
					
						highlightLeft(j, j2, g);
					}
				}
				// right
				if ((j2 + 1) < 8) {
					if (cells[j][j2 + 1] == null) {
						highlightRight(j, j2, g);
					} else if ((cells[j][j2].getStrength() > cells[j][j2 + 1].getStrength()
							&& cells[j][j2].getTeam() != cells[j][j2 + 1].getTeam() && j2 + 2 < 8
							&& cells[j][j2 + 2] == null)) {
						pushDirection = RIGHT;
					
						highlightRight(j, j2, g);
					}
				}
			}

		}

		paintAnimals(g);
		g.setColor(Color.BLACK);

		// draws lines to separate columns
		if (redcheck == true) {
			g.setColor(Color.RED);
			g.fillRect((int) (Math.round(2 * width)) + 1, (int) (Math.round(2 * height)) + 1, (int) width,
					(int) height);
			g.fillRect((int) (Math.round(2 * width)) + 1, (int) (Math.round(5 * height)) + 1, (int) width,
					(int) height);
			g.fillRect((int) (Math.round(5 * width)) + 1, (int) (Math.round(2 * height)) + 1, (int) width,
					(int) height);
			g.fillRect((int) (Math.round(5 * width)) + 1, (int) (Math.round(5 * height)) + 1, (int) width,
					(int) height);
			redcheck = false;
		}
		g.setColor(Color.BLACK);
		for (int x = 0; x < length + 1; x++) {
			g.drawLine((int) Math.round(x * width), 0, (int) Math.round(x * width), this.getHeight());
			g.drawLine(0, (int) Math.round(x * height), this.getWidth(), (int) Math.round(x * height));
		}
		// draws lines to separate rows

	}
	// clears highLighted list
	public void clearHighlighted() {
		for (int i = 0; i < highlightedCells.length; i++) {
			for (int j = 0; j < highlightedCells[0].length; j++) {
				highlightedCells[i][j] = false;
			}
		}

	}
	//highlights forward grid
	public void highlightForward(int j, int j2, Graphics g) {
		if (!cells[j][j2].getName().equals("Rabbit") || cells[j][j2].getTeam().equals(TeamType.Gold)) {
			g.fillRect((int) (Math.round(j2 * width)) + 1, (int) (Math.round((j - 1) * height)) + 1, (int) width - 1,
					(int) height);
			this.highlightedCells[j - 1][j2] = true;
		}
	}
	//highlights backward grid
	public void highlightBack(int j, int j2, Graphics g) {
		if (!cells[j][j2].getName().equals("Rabbit") || cells[j][j2].getTeam().equals(TeamType.Silver)) {
			g.fillRect((int) (Math.round(j2 * width)) + 1, (int) (Math.round((j + 1) * height)) + 1, (int) width - 1,
					(int) height);
			highlightedCells[j + 1][j2] = true;
		}
	}
	//highlights Left grid
	public void highlightLeft(int j, int j2, Graphics g) {
		g.fillRect((int) (Math.round((j2 - 1) * width)) + 1, (int) (Math.round(j * height)) + 1, (int) width - 1,
				(int) height);
		highlightedCells[j][j2 - 1] = true;
	}
	//highlights Right grid
	public void highlightRight(int j, int j2, Graphics g) {
		g.fillRect((int) (Math.round((j2 + 1) * width)) + 1, (int) (Math.round(j * height)) + 1, (int) width - 1,
				(int) height);
		highlightedCells[j][j2 + 1] = true;
	}
	// checks if teammate is in any of the 4 directions
	public boolean checkForTeammate(int row, int col, TeamType team) {
		if (col + 1 < 8 && cells[row][col + 1] != null) {
			if (cells[row][col + 1].getTeam() == team) {
				return true;
			}
		}
		if (col - 1 > -1 && cells[row][col - 1] != null) {
			if (cells[row][col - 1].getTeam() == team) {
				return true;
			}

		}
		if (row - 1 > -1 && cells[row - 1][col] != null) {
			if (cells[row - 1][col].getTeam() == team) {
				return true;
			}

		}
		if (row + 1 < 8 && cells[row + 1][col] != null) {
			if (cells[row + 1][col].getTeam() == team) {
				return true;
			}

		}

		return false;
	}
	// removes animals from a list when it dies
	public void removeAnimal(int row, int col) {
		for (int i = 0; i < animals.size(); i++) {
			if (animals.get(i) == cells[row][col]) {
				animals.remove(i);
				cells[row][col] = null;
			}
		}
		repaint();

	}

	// kills anything in the red squares if allowed
	public void redSquareCheck() {
		if (cells[2][2] != null) {
			if (checkForTeammate(2, 2, cells[2][2].getTeam()) == false) {
				removeAnimal(2, 2);
			}
		}
		if (cells[2][5] != null) {
			if (checkForTeammate(2, 5, cells[2][5].getTeam()) == false) {
				removeAnimal(2, 5);
			}
		}
		if (cells[5][2] != null) {
			if (checkForTeammate(5, 2, cells[5][2].getTeam()) == false) {
				removeAnimal(5, 2);
			}
		}
		if (cells[5][5] != null) {
			if (checkForTeammate(5, 5, cells[5][5].getTeam()) == false) {
				removeAnimal(5, 5);
			}
		}

	}
	// returns opposite team
	public TeamType oppositeTeam(TeamType team) {
		if (team == TeamType.Gold) {
			return TeamType.Silver;
		} else {
			return TeamType.Gold;
		}
	}
	// checks for stronger players in all 4 directions
	public boolean checkForLargerPlayer(int row, int col, TeamType team) {
		if (col + 1 < 8 && cells[row][col + 1] != null) {
			if (cells[row][col + 1].getTeam() != team
					&& cells[row][col + 1].getStrength() > cells[row][col].getStrength()) {
				return true;
			}
		}
		if (col - 1 > -1 && cells[row][col - 1] != null) {
			if (cells[row][col - 1].getTeam() != team
					&& cells[row][col - 1].getStrength() > cells[row][col].getStrength()) {
				return true;
			}

		}
		if (row - 1 > -1 && cells[row - 1][col] != null) {
			if (cells[row - 1][col].getTeam() != team
					&& cells[row - 1][col].getStrength() > cells[row][col].getStrength()) {
				return true;
			}

		}
		if (row + 1 < 8 && cells[row + 1][col] != null) {
			if (cells[row + 1][col].getTeam() != team
					&& cells[row + 1][col].getStrength() > cells[row][col].getStrength()) {
				return true;
			}

		}

		return false;
	}
	// checks if a user has won
	public int checkWin() {
		for (int i = 0; i < animals.size(); i++) {
			if (animals.get(i).getName().equals("Rabbit")) {
				if (animals.get(i).getTeam() == TeamType.Gold && animals.get(i).getCurrentRow() == 0) {
					return 1;
				} else if (animals.get(i).getTeam() == TeamType.Silver && animals.get(i).getCurrentRow() == 7) {
					return 2;
				}
			}
		}
		return 0;
	}
	// checks if a team still has rabbits and can keep playing
	public int checkRabbitCount() {
		int goldCount = 0;
		int silverCount = 0;

		for (int i = 0; i < animals.size(); i++) {
			if (animals.get(i).getTeam() == TeamType.Gold) {
				if (animals.get(i).getName().equals("Rabbit")) {
					goldCount++;
				}
			}
			if (animals.get(i).getTeam() == TeamType.Silver) {
				if (animals.get(i).getName().equals("Rabbit")) {
					silverCount++;
				}
			}

		}
		if (goldCount == 0) {
			return 2;
		} else if (silverCount == 0) {
			return 1;
		}
		return 0;
	}

	//checks if an animal is frozen
	public boolean isPlayerFrozen(Animal a) {

		int j = a.getCurrentRow();
		int j2 = a.getCurrentCol();
		if (checkForLargerPlayer(j, j2, a.getTeam())
				&& checkForTeammate(a.getCurrentRow(), a.getCurrentRow(), a.getTeam()) == false) {
			return true;
		}
		if (!cells[j][j2].getName().equals("Rabbit") || cells[j][j2].getTeam().equals(TeamType.Gold)) {
			if ((j - 1) > -1) {
				if (cells[j - 1][j2] == null) {
					return false;
				}
			}
		}
		// back
		if ((j + 1) < 8) {
			if (!cells[j][j2].getName().equals("Rabbit") || cells[j][j2].getTeam().equals(TeamType.Silver)) {
				if (cells[j + 1][j2] == null) {
					return false;
				}

			}
		}
		// left
		if ((j2 - 1) > -1) {
			if (cells[j][j2 - 1] == null) {
				return false;
			}
		}
		// right
		if ((j2 + 1) < 8) {
			if (cells[j][j2 + 1] == null) {
				return false;
			}
		}

		return true;

	}
	// checks if an entire team is frozen
	public boolean isTeamFrozen(TeamType team) {
		int numPlayers = 0;
		int frozenPlayers = 0;
		for (int i = 0; i < animals.size(); i++) {
			if (animals.get(i).getTeam() == team) {
				numPlayers++;
				if (isPlayerFrozen(animals.get(i)) == true) {
					frozenPlayers++;
				}

			}
		}
		if (numPlayers == frozenPlayers) {
			return true;
		} else {
			return false;
		}
	}
	//checks if an animal is in any of the 4 directions, also testing switch statements hehe
	private boolean GetAnimal(DirectionEnum direction, TeamType type) {
		Animal current = LastPiece;
		int row = LastPiece.getCurrentRow();
		int col = LastPiece.getCurrentCol();
		Animal animal = null;
		try {
			switch (direction) {
			case Left:
				animal = cells[row][col - 1];
				break;

			case Right:
				animal = cells[row][col + 1];
				break;

			case Top:
				animal = cells[row - 1][col];
				break;

			case Bottom:
				animal = cells[row + 1][col];
				break;

			default:
				animal = null;
				break;

			}
		} catch (Exception e) {
			return false;
		}

		if (animal != null && animal.getTeam() != type) {
			if (current.getStrength() <= animal.getStrength()) {
				return false;
			}
		} else {
			return false;
		}

		return true;
	}
	
	// checks for a wekaer piece in 4 directions
	public boolean checkForWeakerPiece(TeamType team) {
		if (!GetAnimal(DirectionEnum.Left, team)) {
			if (!GetAnimal(DirectionEnum.Right, team)) {
				if (!GetAnimal(DirectionEnum.Top, team)) {
					if (!GetAnimal(DirectionEnum.Bottom, team)) {
						return false;
					}
				}
			}
		}

		return true;
	}
	// stores all the directions weaker pieces exist
	public DirectionEnum[] GetWeakerPieceDirections(TeamType team) {
		DirectionEnum[] directions = new DirectionEnum[4];
		int position = 0;
		if(GetAnimal(DirectionEnum.Left, team))
		{
			directions[position++] = DirectionEnum.Left;
		}
		
		if(GetAnimal(DirectionEnum.Right, team))
		{
			directions[position++] = DirectionEnum.Right;
		}
		
		if(GetAnimal(DirectionEnum.Top, team))
		{
			directions[position++] = DirectionEnum.Top;
		}
		
		if(GetAnimal(DirectionEnum.Bottom, team))
		{
			directions[position] = DirectionEnum.Bottom;
		}
		
		return directions;
	}
	
	// checks if the list of weaker pieces directions has a certain direction
	private boolean Contains(DirectionEnum direction, DirectionEnum[] directions)
	{
		for (int i = 0; i < directions.length; i++) {
			if( directions[i] == direction)
			{
				return true;
			}
			
		}
		
		return false;
	}
	// prints all the directions the weaker pieces exist
	private void printDirections(DirectionEnum[] directions)
	{
		for (int i = 0; i < directions.length; i++) {
			
		}	
	}
	// checks if user can pull a piece
	public boolean CanPullPiece(int row, int col, TeamType team) {
		
		DirectionEnum[] directions = GetWeakerPieceDirections(team);
		printDirections(directions);
		
		if (row - 1 > -1) {
			if (LastPiece == cells[row - 1][col] && Contains(DirectionEnum.Top, directions)) {
				pullDirection = BACKWARD;
				return true;
			}
		}
		if (row + 1 < 8) {
			if (LastPiece == cells[row + 1][col] && Contains(DirectionEnum.Bottom, directions)) {
				pullDirection = FORWARD;
				return true;
			}
		}
		if (col +1 <8) {
			if (LastPiece == cells[row][col + 1] && Contains(DirectionEnum.Right, directions)){
				pullDirection = LEFT;
				return true;
			}
		}
		if (col -1 < -1) {
			if (LastPiece == cells[row][col - 1] && Contains(DirectionEnum.Left, directions)) {
				pullDirection = RIGHT;
				return true;
			}
		}
		
		
		return false;

	}

}
