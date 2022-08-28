package arimaa;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

/*
 * This class defines the Dog subclass of the Animal superclass.
 * Authors: Gabi Garcia and Pranav Sharma
 * Date:5/9/2019
 */

public class Dog extends Animal {
	
	public Dog(TeamType team, int row, int col) {
		super("Dog", team, team == TeamType.Gold ? "ArimaaDogGold.png": "ArimaaDogSilver.png", 3, row, col );
		
	} 
}