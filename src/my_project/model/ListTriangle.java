package my_project.model;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

public class ListTriangle extends GraphicalObject {

    private ViewController viewController;
    private boolean arrived;
    private boolean deleted;

    public ListTriangle(double x, double y, ViewController viewController){
        this.viewController = viewController;
        this.x = x;
        this.y = y;
        this.arrived = arrived;
        this.deleted = deleted;
        viewController.draw(this);
    }

    public void update(double dt){
    }

    public void draw(DrawTool drawTool){
        drawTool.drawTriangle(x,y,x+20,y, x+10,y-30);
    }

    public void tryToDelete(){
        viewController.removeDrawable(this);
    }
}
