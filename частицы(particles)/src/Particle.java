import java.awt.*;
import java.util.List;

public class Particle { // задаем координаты точек и их скорости ,координаты приходят из вне через коструктур (Scene)
    volatile double x; //volatile  переменная с базовой синхронизацией
    volatile double y;
    volatile double vx;
    volatile double vy;
    private final List<Particle> particles;// обьявляем массив частиц
    private static final double F = 100;

    public Particle(double x, double y, List<Particle> particles){ // для частиц задаются скорости
        this.x = x;
        this.y = y;
        vx = 150;
        vy = 150;
        this.particles = particles;
    }
    public void update(int dt){ // метод Эйлера ,просчет движения
        double t = dt/1e+6;
        x += vx*t;
        y += vy*t;
        for(Particle p : particles){
            if(p.x > x)
                vx+=F*t;
            else if(p.x < x)
                vx-=F*t;
            if(p.y > y)
                vy+=F*t;
            else if(p.y < y)
                vy-=F*t;
        }
        vx *= 1 - t;
        vy *= 1 - t;
        if(x > Main.WIDTH && vx > 0 || this.x < 0 && vx < 0)
            vx = -vx - 100*Math.signum(vx); // делаем отбитие от стенок
        if(this.y > Main.HEIGHT && vy > 0 || this.y < 0 && vy < 0)
            vy = -vy - 100*Math.signum(vy);
    }
    public void render(Graphics2D g){
        g.setColor(new Color(255, 0, 0));// задаем цвет точек и визуализируем
        g.fillRect((int)x, (int)y, 3, 3);
    }
}
