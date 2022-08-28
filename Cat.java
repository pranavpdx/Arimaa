package arimaa;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

/*
 * This class defines the Cat subclass of the Animal superclass.
 * Authors: Gabi Garcia and Pranav Sharma
 * Date:5/9/2019
 */

public class Cat extends Animal {
	
	public Cat(TeamType team, int row, int col) {
		super("Cat", team, team == TeamType.Gold ? "ArimaaCatGold.png": "ArimaaCatSilver.png", 2, row, col );
		
	} 
}