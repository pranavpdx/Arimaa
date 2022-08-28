package arimaa;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

/*
 * This class defines the Camel subclass of the Animal superclass.
 * Authors: Gabi Garcia and Pranav Sharma
 * Date:5/9/2019
 */

public class Camel extends Animal {
	
	//Camel constructor
	public Camel(TeamType team, int row, int col) {
		super("Camel", team, team == TeamType.Gold ? "ArimaaCamelGold.png": "ArimaaCamelSilver.png", 5, row, col );
		
	} 
}
