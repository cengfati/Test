package my_project.model;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

public class CurrentMarker extends GraphicalObject {

    private ViewController viewController;
    private double height, width;
    private int a;

    public CurrentMarker(double x, double y, ViewController viewController) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.viewController = viewController;
        viewController.draw(this);
        this.a = 0;
    }

    public void draw(DrawTool drawTool) {
        // drawTool.setCurrentColor(0,0,0,0);
        drawTool.drawRectangle(x, y, 15, 40);
        drawTool.drawTriangle(x - 7, y + 40, x + 7.5, y + 60, x + 22.5, y + 40);

        drawTool.setCurrentColor(0, 0, 0, a);
        drawTool.drawText(250, 150, "Es gibt kein nächstes Objekt");
        drawTool.setCurrentColor(0, 0, 0, 255);
    }

    public void reposition(ListTriangle myObject) {
        if(myObject == null){

        }

      /*  if (myObject != null) {
            setX(myObject.getX());
            setY(myObject.getY());
        } else {
            setY(firstObject.getY());
            setX(firstObject.getX());
        } */
    }

    public void setA(int a) {
        this.a = a;
    }

}