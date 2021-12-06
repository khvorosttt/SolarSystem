
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lenovo
 */
public class Electron {

    static List<Color> colors=new ArrayList<Color>();
    Color color; //цвет электрона(шара)
    int d; //диаметр электрона(шара)
    double m; //масса электрона
    double x, newX; //координата
    double y, newY; //координата
    double v; //скорость электрона
    double alpha; //угол(направление)
    double Q; //заряд электрона
    double vx,vy,ax,ay; //разложение скорости и ускорения по компонентам осей координат
    int step=10;
    
    static void ColorsName(){
        colors.add(Color.BLACK);
        colors.add(Color.RED);
        colors.add(Color.CYAN);
        colors.add(Color.BLUE);
        colors.add(Color.GREEN);
        colors.add(Color.MAGENTA);
        colors.add(Color.ORANGE);
        colors.add(Color.PINK);
        colors.add(Color.YELLOW);
        colors.add(Color.GRAY);
    }
    //Конструктор
    public Electron(double m, double x, double y, double v,double alpha, double Q) {
        ColorsName();
        this.d=new Random().nextInt(10)+7;
        this.m = m;
        this.x = x;
        this.y = y;
        this.v = v;
        this.alpha=alpha;
        this.Q = Q;
        this.vx=v*Math.cos(alpha*Math.PI/180);
        this.vy=v*Math.sin(alpha*Math.PI/180);
        this.ax=0;
        this.ay=0;
        this.color=this.Q>0? Color.RED:Color.BLACK;
    }
    
    public double newX(){
        return newX;
    }
    
    public void newX(double newX){
        this.newX=newX;
    }
    
    public double newY(){
        return newY;
    }
    
    public void newY(double newY){
        this.newY=newY;
    }
    
    public double M(){
        return m;
    }
    
    public void M(double m){
        this.m=m;
    }
    
    public double X(){
        return x;
    }
    
    public void X(double x){
        this.x=x;
    }
    
    public double Y(){
        return y;
    }
    
    public void Y(double y){
        this.y=y;
    }
    
    public double V(){
        return v;
    }
    
    public void V(double v){
        this.v=v;
    }
    
    public double Q(){
        return Q;
    }
    
    public void Q(double Q){
        this.Q=Q;
    }
    
    public double VX(){
        return vx;
    }
    
    public void VX(double vx){
        this.vx=vx;
    }
    
    public double VY(){
        return vy;
    }
    
    public void VY(double vy){
        this.vy=vy;
    }
    
    public double AX(){
        return ax;
    }
    
    public void AX(double ax){
        this.ax=ax;
    }
    
    public double AY(){
        return ay;
    }
    
    public void AY(double ay){
        this.ay=ay;
    }
    
    void draw(Graphics g){
        g.setColor(color);
        g.fillOval((int)Math.round(x)-d/2, (int)Math.round(y)-d/2, d, d);
    }
}
