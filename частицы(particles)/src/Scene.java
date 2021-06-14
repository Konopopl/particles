import java.awt.*; // создаем точки
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Scene {
    private static Random rand = new Random(42); // задаем рандом
    private ThreadBag threadBag = new ThreadBag(1488); // задаем количество потоков,всего потоков 1489 ,один запускается при старте программы и создает точек
    private List<Particle> particles = new ArrayList<>(); // создаем массив  точек
    public void render(Graphics2D g){ // рисовка
        g.setColor(new Color(0,0,0)); // задаем цвет фона
        g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT); //заолняем ящик  точками
        for(Particle p : particles) //проход по всем частицам
            p.render(g); // рисовка точек
    }

    public void update(int dt){
        if(rand.nextDouble() * 1000000L < dt) // создаем точки со временем
            particles.add(new Particle(rand.nextInt(Main.WIDTH), rand.nextInt(Main.HEIGHT), particles));  // создаем новую точчку
        for(Particle p : particles) // цикл по всем частицам
            threadBag.addTask(()->p.update(dt)); // обновляем состояния частиц со временем ( они движутся ) демоны считают координаты частиц
    }
}
