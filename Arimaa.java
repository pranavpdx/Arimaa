package arimaa;

/*
 * The main class that runs the whole program and sets up frame pieces 
 * Authors: Gabi Garcia and Pranav Sharma
 * Date:5/9/2019
 */
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Arimaa implements ActionListener, MouseListener {
	// initializing variables
	JFrame frame = new JFrame("Arimaaaaaaa");
	Container east = new Container();
	Container east1 = new Container();
	double width = 850;
	double height = 750;
	JLabel pieces = new JLabel("Pieces");
	JButton endTurn = new JButton("End Turn");
	Panel panel = new Panel();;
	boolean tendTurn = false;
	int onAnimal = -1;
	JButton elephant;
	final int ielephant = 0;
	int elephantnum = 1;
	JButton camel;
	final int icamel = 1;
	int camelnum = 1;
	JButton h1, h2;
	JButton[] hlabel = { h1, h2 };
	final int ihorse = 2;
	int horsenum = 2;
	JButton d1, d2;
	JButton[] dlabel = { d1, d2 };
	final int idog = 3;
	int dognum = 2;
	JButton c1, c2;
	JButton[] clabel = { c1, c2 };
	final int icat = 4;
	int catnum = 2;
	JButton r1, r2, r3, r4, r5, r6, r7, r8;
	JButton[] rlabel = { r1, r2, r3, r4, r5, r6, r7, r8 };
	final int irabbit = 5;
	int rabbitnum = 8;
	int endgameCount = 16;
	TeamType turn = TeamType.Gold;
	int turnNum = 0;
	boolean startGame = false;
	boolean gameStart = false;
	ArrayList<JButton> disableButtons = new ArrayList<JButton>();
	final int teamGold = 0;
	final int teamSilver = 1;
	Animal LastPiece = null;
	boolean goldSetup = false;
	ToggleSwitch toggle = new ToggleSwitch();
	Container north = new Container();
	JLabel drag = new JLabel("Pull Piece?");

	// Set up appears when an instance of arimaa is created
	Arimaa() {
		endTurn.setEnabled(false);
		endTurn.addActionListener(this);
		frame.setSize((int) width, (int) height);
		frame.setLayout(new BorderLayout());
		east.setLayout(new BorderLayout());
		east.add(pieces, BorderLayout.NORTH);
		createEast("ArimaaElephantGold.png", "ArimaaCamelGold.png", "ArimaaHorseGold.png", "ArimaaDogGold.png",
				"ArimaaCatGold.png", "ArimaaRabbitGold.png");
		east.add(east1, BorderLayout.CENTER);

		frame.add(panel, BorderLayout.CENTER);
		panel.addMouseListener(this);
		panel.repaint();
		frame.add(east, BorderLayout.EAST);

		north.setLayout(new GridLayout(1, 2));
		north.add(endTurn);
		north.add(drag);
		north.add(toggle);
		frame.add(north, BorderLayout.NORTH);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// panel.DrawDefault();-- Uncomment these and set endTurn.enabled() to true to
		// play with pre-setup pieces
		// startGame = true;
	}

	// turns image to an image icont o be added to a button
	public ImageIcon addImage(String file) {
		ImageIcon elephant = new ImageIcon(file);
		Image img = elephant.getImage();
		Image newimg = img.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
		JButton b = new JButton();
		elephant = new ImageIcon(newimg);
		return elephant;
	}

	// starts game
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Arimaa();
	}

	// when buttons are clicked, it sets up a indicator so when user presses on the
	// panel it knows which animals to draw. Also instructs what to do each time
	// endTurn in clicked
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(elephant)) {
			onAnimal = ielephant;
		} else if (e.getSource().equals(camel)) {
			onAnimal = icamel;
		} else if (e.getSource().equals(hlabel[0]) || e.getSource().equals(hlabel[1])) {
			onAnimal = ihorse;
		} else if (e.getSource().equals(dlabel[0]) || e.getSource().equals(dlabel[1])) {
			onAnimal = idog;
		} else if (e.getSource().equals(clabel[0]) || e.getSource().equals(clabel[1])) {
			onAnimal = icat;
		} else if (e.getSource().equals(rlabel[0]) || e.getSource().equals(rlabel[1]) || e.getSource().equals(rlabel[2])
				|| e.getSource().equals(rlabel[3]) || e.getSource().equals(rlabel[4]) || e.getSource().equals(rlabel[5])
				|| e.getSource().equals(rlabel[6]) || e.getSource().equals(rlabel[7])) {
			onAnimal = irabbit;
		} else if (e.getSource().equals(endTurn)) {
			// the first time endTurn is clicked it changes the buttons on the side oanel to
			// silver and lest Silver player setup their turn
			if (goldSetup == true) {
				east.remove(east1);
				createEast("ArimaaElephantSilver.png", "ArimaaCamelSilver.png", "ArimaaHorseSilver.png",
						"ArimaaDogSilver.png", "ArimaaCatSilver.png", "ArimaaSilverRabbit.png");
				east.add(east1);
				frame.revalidate();
				frame.repaint();
				turn = TeamType.Silver;
				// resets variables that indicate how many of each animal is permitted
				elephantnum = 1;
				camelnum = 1;
				horsenum = 2;
				dognum = 2;
				catnum = 2;
				rabbitnum = 8;
				endgameCount = 16;
				endTurn.setEnabled(false);
				goldSetup = false;
				// second time the button is clicked it disables the side panel and the game
				// begins
			} else if (startGame == true) {
				disablePieces();
				gameStart = true;
				endTurn.setEnabled(false);
				startGame = false;
				turn = TeamType.Gold;
				// each time after the button is clicked it switches whos turn the program is on
				// as well as sets the turn COunt to 0
			} else if (gameStart == true) {
				if (turn == TeamType.Gold) {
					turn = TeamType.Silver;
				} else if (turn == TeamType.Silver) {
					turn = TeamType.Gold;

				}
				turnNum = 0;
				endTurn.setEnabled(false);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		// generates what column and row was clicked
		// NOTE: I spelled column wrong everywhere but please don't judge and just enjoy
		// this hard hard game :)
		int colomn = Math.min(panel.getLength() - 1, (int) (e.getX() / (panel.getWidth() / panel.length)));
		int row = Math.min(panel.getLength() - 1, (int) (e.getY() / (panel.getHeight() / panel.length)));
		// only runs if game has begun
		if (gameStart == true) {
			// only players whos turn it is can play
			if (turn == panel.getTeam(row, colomn) || panel.getTeam(row, colomn) == null
					|| (panel.isHighlightedCell(row, colomn))) {
				// only lets players play 4 turns
				if (turnNum < 4) {
					// checks if user is trying to push a piece
					if (!panel.IsEmptyCell(row, colomn) && panel.isHighlightedCell(row, colomn)) {
						turnNum++;
						panel.pushPiece(row, colomn);
						panel.redSquareCheck();
						if (turnNum > 0) {
							endTurn.setEnabled(true);
						}
						// highlights moves for users
					} else if (!panel.IsEmptyCell(row, colomn)) {
						// check if frozen here// check this code here - works!
						if (panel.checkForLargerPlayer(row, colomn, turn)
								&& panel.checkForTeammate(row, colomn, turn)) {
							panel.HighlightMoves(row, colomn);
						} else if (panel.checkForLargerPlayer(row, colomn, turn)) {

						} else {
							panel.HighlightMoves(row, colomn);

						}

					}

					if (panel.isHighlightedCell(row, colomn)) {
						// checks if user is trying to pull a piece
						if (toggle.isActivated() && panel.CanPullPiece(row, colomn, turn)) {
							panel.pullPiece(row, colomn);
							turnNum++;

						} else {// if not user is just trying to move

							panel.MovePiece(row, colomn);
							turnNum++;
						}

						if (turnNum > 0) {
							endTurn.setEnabled(true);
						}
					}
					// always checking if an animals is going to die in a red square
					panel.redSquareCheck();
				}
			}
			// checking if a user has won
			if (panel.checkWin() == 1 || panel.checkRabbitCount() == 1 || panel.isTeamFrozen(TeamType.Silver)) {
				printWinner("Gold Wins!");
				gameStart = false;
			} else if (panel.checkWin() == 2 || panel.checkRabbitCount() == 2 || panel.isTeamFrozen(TeamType.Gold)) {
				printWinner("Silver Wins!");
				gameStart = false;
			}
		}
	}

	// pops up the message that tells the winner
	private void printWinner(String winner) {
		JFrame frame = new JFrame();
		int answer = JOptionPane.showOptionDialog(frame, winner + " Play Again?", "Winner!", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, null, height);
		if (answer == JOptionPane.YES_OPTION) {
			resetGame();
		} else if (answer == JOptionPane.NO_OPTION) {

		}

	}

	// resets game
	private void resetGame() {
		east.remove(east1);
		createEast("ArimaaElephantGold.png", "ArimaaCamelGold.png", "ArimaaHorseGold.png", "ArimaaDogGold.png",
				"ArimaaCatGold.png", "ArimaaRabbitGold.png");
		east.add(east1);
		frame.revalidate();
		frame.repaint();
		panel.resetPanel();
		gameStart = false;
		startGame = false;
		goldSetup = false;
		endTurn.setEnabled(false);
		turnNum = 0;
		turn = TeamType.Gold;
		elephantnum = 1;
		camelnum = 1;
		horsenum = 2;
		dognum = 2;
		catnum = 2;
		rabbitnum = 8;
		endgameCount = 16;
		panel.repaint();

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		// calculates row and column
		double width = (double) panel.getWidth() / panel.length;
		double height = (double) panel.getHeight() / panel.length;
		int colomn = Math.min(panel.length - 1, (int) (e.getX() / width));
		int row = Math.min(panel.length - 1, (int) (e.getY() / height));
		// if game has not started yet
		if (gameStart == false) {
			// if gold is setting up it allows user to click of buttons and click on panel
			// to set up players
			if (turn == TeamType.Gold) {
				if (row > 5 && panel.IsEmptyCell(row, colomn)) {
					if (onAnimal == ielephant && elephantnum > 0) {
						Elephant Elephant = new Elephant(TeamType.Gold, row, colomn);
						panel.addAnimal(Elephant);
						endgameCount--;
						elephantnum--;
					} else if (onAnimal == icamel && camelnum > 0) {
						Camel Camel = new Camel(TeamType.Gold, row, colomn);
						panel.addAnimal(Camel);
						endgameCount--;
						camelnum--;
					} else if (onAnimal == ihorse && horsenum > 0) {
						Horse Horse = new Horse(TeamType.Gold, row, colomn);
						panel.addAnimal(Horse);
						endgameCount--;
						horsenum--;
					} else if (onAnimal == idog && dognum > 0) {
						Dog dog = new Dog(TeamType.Gold, row, colomn);
						panel.addAnimal(dog);
						panel.repaint();
						endgameCount--;
						dognum--;
					} else if (onAnimal == icat && catnum > 0) {
						Cat cat = new Cat(TeamType.Gold, row, colomn);
						panel.addAnimal(cat);
						endgameCount--;
						catnum--;
					} else if (onAnimal == irabbit && rabbitnum > 0) {
						Rabbit rabbit = new Rabbit(TeamType.Gold, row, colomn);
						panel.addAnimal(rabbit);
						endgameCount--;
						rabbitnum--;
					}
				}
				// once gold is done setting up silver may begin
				if (endgameCount == 0) {
					endTurn.setEnabled(true);
					goldSetup = true;
				}
				// if silver is setting up it allows user to click of buttons and click on panel
				// to set up players
			} else if (turn == TeamType.Silver) {
				if (row < 2 && panel.IsEmptyCell(row, colomn)) {
					if (onAnimal == ielephant && elephantnum > 0) {
						Elephant Elephant = new Elephant(TeamType.Silver, row, colomn);
						panel.addAnimal(Elephant);
						endgameCount--;
						elephantnum--;
					} else if (onAnimal == icamel && camelnum > 0) {
						Camel Camel = new Camel(TeamType.Silver, row, colomn);
						panel.addAnimal(Camel);
						endgameCount--;
						camelnum--;
					} else if (onAnimal == ihorse && horsenum > 0) {
						Horse Horse = new Horse(TeamType.Silver, row, colomn);
						panel.addAnimal(Horse);
						endgameCount--;
						horsenum--;
					} else if (onAnimal == idog && dognum > 0) {
						Dog dog = new Dog(TeamType.Silver, row, colomn);
						panel.addAnimal(dog);
						endgameCount--;
						dognum--;
					} else if (onAnimal == icat && catnum > 0) {
						Cat cat = new Cat(TeamType.Silver, row, colomn);
						panel.addAnimal(cat);
						endgameCount--;
						catnum--;
					} else if (onAnimal == irabbit && rabbitnum > 0) {
						Rabbit rabbit = new Rabbit(TeamType.Silver, row, colomn);
						panel.addAnimal(rabbit);
						endgameCount--;
						rabbitnum--;
					}
					if (endgameCount == 0) {
						endTurn.setEnabled(true);
						startGame = true;
					}
				}
			}
		}
	}
	// creates container with buttons
	public Container createEast(String elephant1, String camel1, String horse1, String dog1, String cat1,
			String rabbit1) {
		east1.removeAll();
		east1.setLayout(new GridLayout(8, 2));
		// elephant
		elephant = new JButton();
		elephant.addActionListener(this);
		elephant.setIcon(addImage(elephant1));
		east1.add(elephant);
		elephant.addActionListener(this);
		disableButtons.add(elephant);
		// camel
		camel = new JButton();
		camel.addActionListener(this);
		camel.setIcon(addImage(camel1));
		east1.add(camel);
		camel.addActionListener(this);
		disableButtons.add(camel);
		// horse
		for (int x = 0; x < hlabel.length; x++) {
			hlabel[x] = new JButton();
			hlabel[x].addActionListener(this);
			hlabel[x].setIcon(addImage(horse1));
			east1.add(hlabel[x]);
			disableButtons.add(hlabel[x]);
		}
		// dog
		for (int x = 0; x < dlabel.length; x++) {
			dlabel[x] = new JButton();
			dlabel[x].addActionListener(this);
			dlabel[x].setIcon(addImage(dog1));
			east1.add(dlabel[x]);
			disableButtons.add(dlabel[x]);
		}
		// cat
		for (int x = 0; x < clabel.length; x++) {
			clabel[x] = new JButton();
			clabel[x].addActionListener(this);
			clabel[x].setIcon(addImage(cat1));
			east1.add(clabel[x]);
			disableButtons.add(clabel[x]);

		}
		// rabbit
		for (int i = 0; i < rlabel.length; i++) {
			rlabel[i] = new JButton();
			rlabel[i].addActionListener(this);
			rlabel[i].setIcon(addImage(rabbit1));
			east1.add(rlabel[i]);
			disableButtons.add(rlabel[i]);
		}
		return east1;
	}
	// disables all side panel buttons
	public void disablePieces() {
		for (int i = 0; i < disableButtons.size(); i++) {
			disableButtons.get(i).setEnabled(false);
		}
	}

}
