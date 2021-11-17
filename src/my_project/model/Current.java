package my_project.model;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.GraphicalObject;

public class Current extends GraphicalObject {

    private ViewController viewController;
    private double height, width;

    public Current(double x, double y, double height, double width, ViewController viewController){
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.viewController = viewController;
        viewController.draw(this);
    }


}
