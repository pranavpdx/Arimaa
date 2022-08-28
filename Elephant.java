package arimaa;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

/*
 * This class defines the Elephant subclass of the Animal superclass.
 * Authors: Gabi Garcia and Pranav Sharma
 * Date:5/9/2019
 */

public class Elephant extends Animal {

	public Elephant(TeamType team, int row, int col) {
		super("Elephant", team, team == TeamType.Gold ? "ArimaaElephantGold.png": "ArimaaElephantSilver.png", 6, row,col );
		
	} 
}