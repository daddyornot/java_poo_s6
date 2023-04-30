package model;


import nutsAndBolts.PieceSquareColor;

public class PawnModel extends AbstractPieceModel implements Promotable {
	
	private int direction;

	public PawnModel(Coord coord, PieceSquareColor pieceColor) {
		super(coord, pieceColor);

		this.direction = PieceSquareColor.BLACK.equals(this.getPieceColor()) ? -1 : 1;

	}

	@Override
	public boolean isMoveOk(Coord targetCoord, boolean isPieceToCapture) {
		boolean ret = false;

		int colDistance = targetCoord.getColonne() - this.getColonne();
		int ligDistance = targetCoord.getLigne() - this.getLigne();
		int deltaLig = (int) Math.signum(ligDistance);
		
		// Cas d'un déplacement en diagonale
		if (Math.abs(colDistance) == Math.abs(ligDistance)){
			
			// sans prise
			if (!isPieceToCapture) {
				if (deltaLig == this.direction && Math.abs(colDistance) == 1) {
					ret = true;
				}
			}
			// avec prise
			else {
				if (Math.abs(colDistance) == 2) {
					ret = true;
				}
			}
		}

		return ret;
	}

	@Override
	public boolean isPromotable() {
		boolean ret = false;

		switch (this.getPieceColor()) {
			case BLACK:
				ret = (this.getLigne() == 1);
				break;
			case WHITE:
				ret = (this.getLigne() == Coord.MAX);
				break;
			default:
				throw new IllegalStateException("PawnModel is unexpected to have '" 
									+ this.getPieceColor() + "' as color");
		}
		return ret;
	}
	
	/**
	 * Dans notre contexte les PieceModel sont soit des PawnModel soit des QueenModel
	 * donc la promotion d'un PawnModel suppose sa destruction et la création d'une QueenModel
	 * Dans d'autre contexte, on aurait pu imaginer lui changer sa stratégie de déplacement 
	 * 
	 * @throws UnsupportedOperationException
	 */
	@Override
	public void promote() {
		throw new UnsupportedOperationException();
	}

	
}

