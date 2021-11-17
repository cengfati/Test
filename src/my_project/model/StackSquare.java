package my_project.model;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

public class StackSquare extends GraphicalObject {

    private ViewController viewController;
    private StackSquare previousSquare;
    protected boolean ready;
    protected boolean removed;


    public StackSquare(double x, double y,StackSquare previousSquare, ViewController viewController){
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        ready = false;
        removed = false;
        this.viewController = viewController;
        viewController.draw(this);
        this.previousSquare = previousSquare;
    }

    public void draw(DrawTool drawTool){
        drawTool.drawRectangle(x,y,50,30);
    }

    public void update(double dt) {
        if(!ready){
            if(previousSquare == null || y < previousSquare.getY()-40){
                y += 100*dt;
            }else{
                ready = true;
            }
            if(y > 500){
                ready = true;
            }
        }
        if(removed){
            x -= 100*dt;
            if(x < -100){
                viewController.removeDrawable(this);
            }
        }
    }

    public boolean isDeletable(){
        if(ready){
            removed = true;
            return true;
        }
        return false;
    }
}
