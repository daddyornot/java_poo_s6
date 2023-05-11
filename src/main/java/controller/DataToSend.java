package controller;

import java.io.Serializable;

import nutsAndBolts.PieceSquareColor;

/**
 * @author francoise.perrin
 * <p>
 * Objet à destination de la View
 * créé par le Controller
 * à partir des données retournées par le Model
 */
public class DataToSend<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public T toMovePieceIndex = null;
    public T targetSquareIndex = null;
    public PieceSquareColor pieceSquareColor  = null;


    public DataToSend(
            T toMovePieceIndex,
            T targetSquareIndex,
            PieceSquareColor pieceSquareColor) {
        super();
        this.toMovePieceIndex = toMovePieceIndex;
        this.targetSquareIndex = targetSquareIndex;
        this.pieceSquareColor = pieceSquareColor;
    }


    @Override
    public String toString() {
        return "DataAfterMove [toMovePieceIndex=" + toMovePieceIndex
                + ", targetSquareIndex=" + targetSquareIndex
                + ", pieceSquareColor=" + pieceSquareColor + "]";
    }
}
