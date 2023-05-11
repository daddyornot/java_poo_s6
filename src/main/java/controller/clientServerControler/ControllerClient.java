package controller.clientServerControler;

import controller.DataToSend;
import controller.InputViewData;
import controller.Mediator;
import gui.CheckersSquareGui;
import gui.View;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.BoardGame;
import model.Coord;
import nutsAndBolts.PieceSquareColor;
import tools.communication.CommunicationChannel;

import java.io.IOException;

public class ControllerClient implements EventHandler<MouseEvent>, Mediator, Runnable {

    private CommunicationChannel cltChannel;
    private PieceSquareColor pieceSquareColor;
    private View view;
    private int toMovePieceIndex;


    public ControllerClient(CommunicationChannel cltChannel, PieceSquareColor pieceSquareColor) throws IOException {
        this.view = null;
        this.cltChannel = cltChannel;
        this.pieceSquareColor = pieceSquareColor;
        this.cltChannel.openConnection();

        Thread t = new Thread(this);
        t.start();

    }

    private void setToMovePieceIndex(int toMovePieceIndex) {
        this.toMovePieceIndex = toMovePieceIndex;
    }

    public int getToMovePieceIndex() {
        return toMovePieceIndex;
    }

    @Override
    public void setView(View view) {
        this.view = view;
    }

    @Override
    public void setModel(BoardGame<Coord> model) {
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        try {
            if (mouseEvent.getSource() instanceof CheckersSquareGui)
                checkersSquareGuiHandle(mouseEvent);
            else
                checkersPieceGuiHandle(mouseEvent);
        } catch (Exception e) {
            // Try - Catch pour empêcher pgm de planter tant que les interfaces
            // CheckersSquareGui et CheckersPieceGui n'existent pas
            System.err.println(
                    e.getMessage() + "\n"
                            + "=> Cette erreur est \"normale\" tant que n'ont pas été implémentées\n"
                            + "   les interfaces CheckersSquareGui et CheckersPieceGui"
            );
        }
    }

    /**
     * @param mouseEvent Ecoute les événements sur les PieceGui
     */
    private void checkersPieceGuiHandle(MouseEvent mouseEvent) {

        // Recherche PieceGui sélectionnée
        ImageView selectedPiece = (ImageView) mouseEvent.getSource();

        // Recherche et fixe coordonnée de la pièce sélectionnée
        CheckersSquareGui parentSquare = (CheckersSquareGui) selectedPiece.getParent();
        this.setToMovePieceIndex(parentSquare.getSquareCoord());

        mouseEvent.consume();
    }

    /**
     * @param mouseEvent Ecoute les événements sur les SquareGui
     */
    private void checkersSquareGuiHandle(MouseEvent mouseEvent) {

        // Recherche SquareGUI sélectionné
        CheckersSquareGui square = (CheckersSquareGui) mouseEvent.getSource();
        int targetSquareIndex = square.getSquareCoord();

        // Le controller va invoquer la méthode moveCapturePromotion() du model
        // et si le model confirme que la pièce a bien été déplacée à cet endroit,
        // il invoquera une méthode de la view pour la rafraichir
//        this.moveCapturePromote(this.getToMovePieceIndex(), targetSquareIndex);

        // COMMUNICATION CLIENT -> SERVEUR
        DataToSend<Integer> dataToSend = new DataToSend<>(this.getToMovePieceIndex(), targetSquareIndex, this.pieceSquareColor);
        this.cltChannel.writeMessage(dataToSend);

        // il n'y a plus de pièce à déplacer
        this.setToMovePieceIndex(-1);

        // On évite que le parent ne récupère l'event
        mouseEvent.consume();
    }

    public void run() {
        while (true) {
            Object message = this.cltChannel.readMessage();
            if (message != null) {
                System.out.println("client receives: " + message.toString());
                PlatformHelper.run(() -> this.view.actionOnGui((InputViewData<Integer>) message));
            }
        }
    }
}
