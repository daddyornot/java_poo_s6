package gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import nutsAndBolts.PieceSquareColor;


/**
 * @author francoise.perrin
 * 
 * Cette classe permet de donner une image aux pièces
 *
 */

public class PieceGui extends ImageView implements CheckersPieceGui {
	private PieceSquareColor color;

	public PieceGui(Image image, PieceSquareColor color) {
		this.color = color;
		this.setImage(image);
	}

	@Override
	public void promote(Image image) {

		this.setImage(image);

	}

	@Override
	public boolean hasSameColorAsGamer(PieceSquareColor gamerColor) {

		// TODO Atelier 2, utile pour Atelier 4

		return this.color == gamerColor; // A changer
	}

}