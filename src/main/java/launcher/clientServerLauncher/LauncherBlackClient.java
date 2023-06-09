package launcher.clientServerLauncher;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import controller.Mediator;
import controller.clientServerControler.ControllerClient;
import gui.GuiConfig;
import gui.View;
import nutsAndBolts.PieceSquareColor;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tools.communication.ClientChannel;
import tools.communication.CommunicationChannel;
import tools.communication.CommunicationConfig;



/**
 * @author francoise.perrin.
 * Lance l'ex�cution d'un jeu de dames cot� CLIENT NOIR o� existent
 * une instance de view et une instance ControlerClient
 * Ce client peut fonctionner avec 1 serveur et 1 client BLANC
 * le server est munis d'une instance de model et d'une instance de ControlerServer
 */
public class LauncherBlackClient extends Application {

	private EventHandler<MouseEvent> controllerClient;
	private View view;
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LauncherBlackClient.launch();
	}

	@Override
	public void init () throws Exception {
		super.init();
		
		CommunicationConfig config = new CommunicationConfig("127.0.0.1", 5555, 200);
		BlockingQueue<Object> msgQueue = new ArrayBlockingQueue<Object>(20);
		CommunicationChannel cltChannel = new ClientChannel(config, msgQueue);
		
		this.controllerClient = new ControllerClient(cltChannel, PieceSquareColor.BLACK);
		
		this.view = new View(this.controllerClient);

		((Mediator) this.controllerClient).setView(this.view);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setScene(new Scene(this.view,  GuiConfig.HEIGHT, GuiConfig.HEIGHT));
		primaryStage.setTitle("Jeu de dames - Client noir");
		primaryStage.setX(750);
		primaryStage.setY(150);
		primaryStage.show();
	}
}
