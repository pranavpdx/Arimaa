package arimaa;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

/*
 * This class defines the Horse subclass of the Animal superclass.
 * Authors: Gabi Garcia and Pranav Sharma
 * Date:5/9/2019
 */

public class Horse extends Animal {
	
	public Horse(TeamType team, int row, int col) {
		super("Horse", team, team == TeamType.Gold ? "ArimaaHorseGold.png": "ArimaaHorseSilver.png", 4, row, col );
		
	} 
}