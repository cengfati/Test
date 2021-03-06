package my_project.control;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.abitur.datenstrukturen.Queue;
import KAGO_framework.model.abitur.datenstrukturen.Stack;
import KAGO_framework.model.abitur.datenstrukturen.List;
import my_project.model.*;
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

    private CircleAr[][] circleArray;
    private RectangleField recField;
    private CircleAr circle;
    private CurrentRec currentRec;

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
        // Für Stack
        squareStack = new Stack<>();
        squareBeforeInStack = null;
        // Für List
        triangleList = new List<>();
        currentTriangle = null;
        currentMarker = new CurrentMarker(-40,200,viewController);
        //Für Array
        circleArray = new CircleAr[8][4];
        recField = new RectangleField(100,150,viewController);
        circle = new CircleAr(-50,-1000,viewController);
        currentRec = new CurrentRec(100, 150, viewController);
    }

    public void addRectangleToArray(){
        //TODO Nicht erzeugen, wenn außerhalb des Rechtecks
        if(circle.getReihe() >= 0 && circle.getReihe() <= 7 && circle.getSpalte() >= 0 && circle.getSpalte() <= 3){
            if(circleArray[circle.getReihe()][circle.getSpalte()] == null){
                CircleAr newCircle = new CircleAr(100 + circle.getReihe() * 40, 150 + circle.getSpalte() * 40, viewController);
                circleArray[circle.getReihe()][circle.getSpalte()] = newCircle;
            }
        }
    }

    public void moveRight(){
        currentRec.setX(currentRec.getX()+40);
        circle.setReihe(circle.getReihe()+1);
        //TODO hier muss noch irgendwie current gezeigt werde/ Farbe verändert
    }

    public void moveLeft(){
        currentRec.setX(currentRec.getX()-40);
        circle.setReihe(circle.getReihe()-1);
    }

    public void moveUp(){
        currentRec.setY(currentRec.getY()-40);
        circle.setSpalte(circle.getSpalte()-1);
    }

    public void moveDown(){
        currentRec.setY(currentRec.getY()+40);
        circle.setSpalte(circle.getSpalte()+1);
    }

    public void removeCircle(){
        viewController.removeDrawable(circleArray[circle.getReihe()][circle.getSpalte()]);
        circleArray[circle.getReihe()][circle.getSpalte()] = null;
    }

    public void changeColor(){
        if(circleArray[circle.getReihe()][circle.getSpalte()].getG() == 0) {
            circleArray[circle.getReihe()][circle.getSpalte()].setG(255);
        }else{
            circleArray[circle.getReihe()][circle.getSpalte()].setG(0);
        }
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
        }else{
            ListTriangle newTriangleList = new ListTriangle(currentTriangle.getX()+40,300,viewController);
            triangleList.append(newTriangleList);
            currentTriangle = newTriangleList;
            currentMarker.setX(currentTriangle.getX()+2);
            currentMarker.setY(currentTriangle.getY()-100);
        }
    }

    public void deleteTriangle(){
        if(!triangleList.isEmpty()){
            //moveTriangle();
            viewController.removeDrawable(currentTriangle);
            delete();
            currentMarker.setX(currentTriangle.getX());

        }
    }

    public ListTriangle delete(){
        ListTriangle temp;
        triangleList.toFirst();
        while(triangleList.hasAccess()){
            temp = triangleList.getContent();
            triangleList.next();
            if(triangleList.hasAccess()){
                if(triangleList.getContent().equals(temp))
                    triangleList.remove();
                    currentTriangle = temp;
                    currentMarker.setX(currentTriangle.getX());
                return temp;
            }
        }
        return null;
    }

    public void moveMarker(){
        if(!triangleList.isEmpty()) {
            triangleList.next();
            if ((triangleList.getContent() == null)) {
                triangleList.toFirst();
                currentTriangle = triangleList.getContent();
                currentMarker.setX(currentTriangle.getX());
            }else{
                currentTriangle = triangleList.getContent();
                currentMarker.setX(currentTriangle.getX());
            }
        }
    }

    public void moveTriangle(){
        ListTriangle temp;
        triangleList.toFirst();
        while(triangleList.hasAccess()){
            temp = triangleList.getContent();
            if(!(triangleList.getContent().getX() - temp.getX() > 40)){
                triangleList.getContent().setX(temp.getX()+40);
            }
            triangleList.next();
        }
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
