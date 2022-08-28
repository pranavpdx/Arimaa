package arimaa;import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

/*
 * This class defines the Rabbit subclass of the Animal superclass.
 * Authors: Gabi Garcia and Pranav Sharma
 * Date:5/9/2019
 */

public class Rabbit extends Animal {
	
	public Rabbit(TeamType team, int row, int col) {
		super("Rabbit", team, team == TeamType.Gold ? "ArimaaRabbitGold.png": "ArimaaSilverRabbit.png", 1, row, col );
		
	} 
}