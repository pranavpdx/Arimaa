package arimaa;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

/*
 * This class is the superclass for all objects of type Animal.
 * Authors: Gabi Garcia and Pranav Sharma
 * Date: 5/9/2019
 */
public class Animal {
	private String name;
	private TeamType team;
	private int currentRow;
	private int currentCol;
	private Image myImage;
	private int strength;
	private boolean clicked = false;
	private boolean highlight = false;

	Animal(String name, TeamType team, String ImageName, int strength, int row, int colomn) {
		this.name = name;
		this.team = team;
		this.strength = strength;
		this.currentRow = row;
		this.currentCol = colomn;
		this.clicked = true;
		if (!ImageName.equals(null)) {
			try {
				myImage = ImageIO.read(new File(ImageName));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public String getName() {
		return name;
	}
	public int getX(double width) {
		return ((int) (Math.round(currentCol * width)) - 1);
	}
	public int getY(double height) {
		return ((int) (Math.round(currentRow * height)));
	}
	public TeamType getTeam() {
		return team;
	}

	public int getCurrentRow() {
		return currentRow;
	}

	public void setHighlighted(boolean flag) {
		this.highlight = flag;
	}

	public boolean getHighlighted() {
		return this.highlight;
	}

	public void setCurrentCoordinates(int currentRow, int currentCol) {
		this.currentRow = currentRow;
		this.currentCol = currentCol;
	}

	public int getCurrentCol() {
		return currentCol;
	}


	public Image getMyImage() {
		return myImage;
	}

	public int getStrength() {
		return strength;
	}
	public void setClicked(boolean b) {
		clicked = b;
	}
	public boolean getClicked() {
		return clicked;
	}

}
