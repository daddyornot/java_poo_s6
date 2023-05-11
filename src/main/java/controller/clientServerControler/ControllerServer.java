package controller.clientServerControler;

import controller.DataToSend;
import controller.InputViewData;
import controller.Mediator;
import controller.OutputModelData;
import gui.View;
import model.BoardGame;
import model.Coord;
import model.ModelConfig;
import nutsAndBolts.PieceSquareColor;
import tools.communication.CommunicationChannel;

import java.io.IOException;

public class ControllerServer implements BoardGame<Integer>, Mediator, Runnable {
    private BoardGame<Coord> model;
    private int toMovePieceIndex;
    private CommunicationChannel srvChannel;

    public ControllerServer(CommunicationChannel srvChannel) throws IOException {
        this.model = null;
        this.srvChannel = srvChannel;
        this.srvChannel.openConnection();

        Thread t = new Thread(this);
        t.start();

    }

    @Override
    public void setView(View view) {
    }

    public void setModel(BoardGame<Coord> model) {
        this.model = model;

    }


    /////////////////////////////////////////////////////////////////
    //
    // Controller vu comme un Substitut du model
    // il invoque les méthodes du model
    // après actions de l'utilisateur sur la vue
    //
    //////////////////////////////////////////////////////////////////

    /**
     * Invoque méthode moveCapturePromote() du model (après transformation des coordonnées)
     * Si déplacement effectif sur model, invoque méthode actionOnGui() de la view
     * pour rafraichir affichage en fonction des données retournées par le model
     */
    @Override
    public OutputModelData<Integer> moveCapturePromote(Integer toMovePieceIndex, Integer targetSquareIndex) {

        OutputModelData<Integer> outputControllerData = null;

        OutputModelData<Coord> outputModelData = null;
        InputViewData<Integer> inputViewData = null;

        Coord toMovePieceCoord = this.transformIndexToCoord(toMovePieceIndex);
        Coord targetSquareCoord = this.transformIndexToCoord(targetSquareIndex);

        if (this.model != null) {
            outputModelData = this.model.moveCapturePromote(toMovePieceCoord, targetSquareCoord);

//            if (outputModelData.isMoveDone && this.view != null) {
            if (outputModelData.isMoveDone) {

                inputViewData = new InputViewData<Integer>(
                        toMovePieceIndex,
                        targetSquareIndex,
                        transformCoordToIndex(outputModelData.capturedPieceCoord),
                        transformCoordToIndex(outputModelData.promotedPieceCoord),
                        outputModelData.promotedPieceColor);

//                this.view.actionOnGui(inputViewData);

                // COMMUNICATION SERVEUR -> CLIENT
                this.srvChannel.writeMessage(inputViewData);
            }
        }

        // Inutile de reconstituer un objetOutputModelData<Integer>, aucun client ne le récupère en mode local
        return outputControllerData;
    }

    @Override
    public PieceSquareColor getCurrentGamerColor() {
        return model.getCurrentGamerColor();
    }

    /**
     * @param squareIndex
     * @param length
     * @return les coordonnées métier calculées à  partir de l'index du SquareGUI sous la PieceGUI
     */
    private Coord transformIndexToCoord(int squareIndex) {
        Coord coord = null;
        int length = ModelConfig.LENGTH;
        char col = (char) ((squareIndex) % length + 'a');
        int ligne = length - (squareIndex) / length;
        coord = new Coord(col, ligne);
        return coord;
    }

    private int transformCoordToIndex(Coord coord) {
        int squareIndex = -1;
        int length = ModelConfig.LENGTH;
        if (coord != null) {
            squareIndex = (length - coord.getLigne()) * length + (coord.getColonne() - 'a');
        }
        return squareIndex;
    }


    @Override
    public void run() {
        while (true) {
            Object message = this.srvChannel.readMessage();
            if (message != null) {
                System.out.println("server receives: " + message.toString());
                if (this.getCurrentGamerColor() == ((DataToSend<Integer>) message).pieceSquareColor) {
                    this.moveCapturePromote(((DataToSend<Integer>) message).toMovePieceIndex,
                                            ((DataToSend<Integer>) message).targetSquareIndex);
                }
            }
        }
    }
}
