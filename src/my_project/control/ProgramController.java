package my_project.control;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.abitur.datenstrukturen.Queue;
import KAGO_framework.model.abitur.datenstrukturen.Stack;
import KAGO_framework.model.abitur.datenstrukturen.List;
import my_project.model.CurrentMarker;
import my_project.model.ListTriangle;
import my_project.model.QueueBall;
import my_project.model.StackSquare;
import my_project.view.InputReceiver;

import java.awt.event.MouseEvent;
//import java.util.Stack;

/**
 * Ein Objekt der Klasse ProgramController dient dazu das Programm zu steuern. Die updateProgram - Methode wird
 * mit jeder Frame im laufenden Programm aufgerufen.
 */
public class ProgramController {

    //Attribute

    // Referenzen
    private ViewController viewController;  // diese Referenz soll auf ein Objekt der Klasse viewController zeigen. Über dieses Objekt wird das Fenster gesteuert.
    private Queue<QueueBall> ballQueue;
    private QueueBall lastBallinQueue;
    private Stack<StackSquare> squareStack;
    private StackSquare squareBeforeInStack;
    private List<ListTriangle> triangleList;
    private ListTriangle currentTriangle;
    private CurrentMarker currentMarker;

    /**
     * Konstruktor
     * Dieser legt das Objekt der Klasse ProgramController an, das den Programmfluss steuert.
     * Damit der ProgramController auf das Fenster zugreifen kann, benötigt er eine Referenz auf das Objekt
     * der Klasse viewController. Diese wird als Parameter übergeben.
     * @param viewController das viewController-Objekt des Programms
     */
    public ProgramController(ViewController viewController){
        this.viewController = viewController;
    }

    /**
     * Diese Methode wird genau ein mal nach Programmstart aufgerufen.
     * Sie erstellt die leeren Datenstrukturen, zu Beginn nur eine Queue
     */
    public void startProgram() {
        // Für Benutzerinteraktion
        new InputReceiver(this,viewController); // darf anonym sein, weil kein Zugriff nötig ist
        // Für die Queue:
        ballQueue = new Queue<>();
        lastBallinQueue = null; // die letzte Kugel muss für die Animation gemerkt werden
        squareStack = new Stack<>();
        squareBeforeInStack = null;
        triangleList = new List<>();
        currentTriangle = null;
        currentMarker = new CurrentMarker(-40,200,viewController);
    }

    public void addBallToQueue(){
        QueueBall newQueueBall = new QueueBall(650,50,lastBallinQueue,viewController);
        ballQueue.enqueue(newQueueBall);
        lastBallinQueue = newQueueBall;
    }

    public void deleteBallFromQueue(){
        if(!ballQueue.isEmpty()){
            if(ballQueue.front().tryToDelete()) ballQueue.dequeue();
        }
    }

    public void addSquare(){
        StackSquare newStackSquare = new StackSquare(400,100, squareBeforeInStack, viewController);
        squareStack.push(newStackSquare);
        squareBeforeInStack = newStackSquare;

    }

    public void deleteSquare(){
        if(!squareStack.isEmpty()){
            if(squareStack.top().isDeletable()){
                squareStack.pop();
            }
        }
    }

    public void addTriangle(){
        if(currentTriangle == null) {
            ListTriangle newTriangleList = new ListTriangle(40, 300, viewController);
            triangleList.append(newTriangleList);
            currentTriangle = newTriangleList;
            currentMarker.setX(currentTriangle.getX()+2);
            currentMarker.setY(currentTriangle.getY()-100);
            currentMarker.setA(0);
        }else{
            ListTriangle newTriangleList = new ListTriangle(currentTriangle.getX()+40,300,viewController);
            triangleList.append(newTriangleList);
            currentTriangle = newTriangleList;
            currentMarker.setX(currentTriangle.getX()+2);
            currentMarker.setY(currentTriangle.getY()-100);
            currentMarker.setA(0);
        }
    }

    public void deleteTriangle(){
        if(!triangleList.isEmpty()){
            viewController.removeDrawable(currentTriangle);
            triangleList.remove();
            currentMarker.setX(currentMarker.getX()-40);
            currentTriangle = getPreviousTriangle();
        }
    }

    public ListTriangle getPreviousTriangle(){
        ListTriangle temp;
        triangleList.toFirst();
        while(triangleList.hasAccess()){
            temp = triangleList.getContent();
            triangleList.next();
            if(triangleList.hasAccess()){
                if(triangleList.getContent().equals(currentTriangle))
                return temp;
            }
        }
        return null;
    }

    public void moveCurrent(){
        triangleList.next();
        currentMarker.reposition(triangleList.getContent());
    }

    /**
     * Aufruf bei Mausklick
     * @param e das Objekt enthält alle Informationen zum Klick
     */
    public void mouseClicked(MouseEvent e){

    }

    /**
     * Aufruf mit jeder Frame
     * @param dt Zeit seit letzter Frame
     */
    public void updateProgram(double dt){

    }
}
