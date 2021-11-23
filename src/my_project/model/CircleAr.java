package my_project.model;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

public class CircleAr extends GraphicalObject {

    private ViewController viewController;
    RectangleField[][] recArray;
    private int reihe;
    private int spalte;
    private int r,g,b;

    public CircleAr(double x, double y, ViewController viewController) {
        this.x = x;
        this.y = y;
        this.reihe = 0;
        this.spalte = 0;
        this.r = 0;
        this.g = 0;
        this.b = 0;
        this.viewController = viewController;
        viewController.draw(this);
    }

    public void draw(DrawTool drawTool){
        drawTool.setCurrentColor(r,g,b,255);
        drawTool.drawFilledCircle((x +reihe  *40)+20,(y + spalte*40)+20,5);
    }

    public int getReihe() {
        return reihe;
    }

    public void setReihe(int reihe) {
        this.reihe = reihe;
    }

    public int getSpalte() {
        return spalte;
    }

    public void setSpalte(int spalte) {
        this.spalte = spalte;
    }

    public void setG(int g){
        this.g = g;
    }

    public int getG(){
        return g;
    }
}
