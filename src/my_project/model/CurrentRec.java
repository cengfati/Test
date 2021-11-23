package my_project.model;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

public class CurrentRec extends GraphicalObject {

    private ViewController viewController;

    public CurrentRec(double x, double y, ViewController viewController) {
        this.x = x;
        this.y = y;
        this.viewController = viewController;
        viewController.draw(this);
    }

    public void draw(DrawTool drawTool){
        drawTool.setCurrentColor(0,0,255,100);
        drawTool.drawFilledRectangle(x,y, 40,40);
    }

    public void update(double dt){

    }
}