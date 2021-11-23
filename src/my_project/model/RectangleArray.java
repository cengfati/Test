package my_project.model;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

public class RectangleArray extends GraphicalObject {

    private ViewController viewController;

    RectangleArray[][] recArray;

    public RectangleArray(double x, double y, ViewController viewController) {
        this.x = x;
        this.y = y;
        this.viewController = viewController;
        viewController.draw(this);
    }

    public void draw(DrawTool drawTool){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 4; j++) {
                drawTool.drawRectangle(x + i * 40, y + j * 40, 40, 40);
                drawTool.drawFilledCircle(x + i *40,y + j*40,5);
            }
        }
        drawTool.drawRectangle(x,y, 8*40,4*40);
    }

    public void update(double dt){

    }
}
