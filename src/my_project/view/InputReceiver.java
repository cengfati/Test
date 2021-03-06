package my_project.view;

import KAGO_framework.control.Interactable;
import KAGO_framework.control.ViewController;
import my_project.control.ProgramController;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.security.Key;

/**
 * Realisiert ein Objekt, dass alle Eingaben empfängt und dann danach passende Methoden
 * im ProgramController aufruft
 */
public class InputReceiver implements Interactable {

    private ProgramController programController;
    private ViewController viewController;

    /**
     * Objekterzeugung
     * @param programController Nötig als Objekt vom Controllerbereich, das informiert wird
     * @param viewController Nötig, um den Aufruf der Interface-Methoden sicherzustellen
     */
    public InputReceiver(ProgramController programController, ViewController viewController){
        this.programController = programController;
        this.viewController = viewController;
        viewController.register(this);
    }

    @Override
    public void keyPressed(int key) {

    }

    @Override
    public void keyReleased(int key) {
        // Für List
      /*  if(key == KeyEvent.VK_SPACE){
            programController.addTriangle();
        }else if(key == KeyEvent.VK_BACK_SPACE){
            programController.deleteTriangle();
        } else if(key == KeyEvent.VK_RIGHT){
            programController.moveMarker();
        } */
        //Für Array
        if(key == KeyEvent.VK_SPACE){
            programController.addRectangleToArray();
        } else if(key == KeyEvent.VK_RIGHT){
            programController.moveRight();
        } else if(key == KeyEvent.VK_LEFT){
            programController.moveLeft();
        } else if(key == KeyEvent.VK_UP){
            programController.moveUp();
        } else if(key == KeyEvent.VK_DOWN){
            programController.moveDown();
        } else if(key == KeyEvent.VK_BACK_SPACE){
            programController.removeCircle();
        } else if(key == KeyEvent.VK_C){
            programController.changeColor();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       /* if(e.getButton() == MouseEvent.BUTTON1){ // falls die linke Maustaste geklickt wurde...
            programController.addBallToQueue();
        } else { // falls eine andere Maustaste geklickt wurde
            programController.deleteBallFromQueue();
        }
        if(e.getButton() == MouseEvent.BUTTON1){ // falls die linke Maustaste geklickt wurde...
            programController.addSquare();
        } else { // falls eine andere Maustaste geklickt wurde
            programController.deleteSquare();
        }*/
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }
}
