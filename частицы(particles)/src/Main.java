import javax.swing.*;// тут создаётся окно
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Main {
    static final int WIDTH = 800; // создание окна
    static final int HEIGHT = 600;
    private static JFrame frame;
    private static Canvas canvas;
    private static BufferStrategy bufferStrategy;
    private static Scene scene;
    private static boolean running = true;
    public static void main(String[] args) { //точка входа в программу
        frame = new JFrame("very clever thing");
        JPanel panel = (JPanel) frame.getContentPane();
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        panel.setLayout(null); // настройка окна

        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT); // установка размеров окна
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();

        canvas.requestFocus();

        scene = new Scene();
        run();
    }

    public static void update(int dt){
        scene.update(dt);
    }

    public static void run(){
        long desiredDelta = 40000; // смена кадров
        long lastTime = System.nanoTime()/1000;
        while(running){
            long currentTime = System.nanoTime()/1000;
            render();
            update((int) (currentTime - lastTime));
            long finishTime = System.nanoTime()/1000;
            while(finishTime < lastTime + desiredDelta){
                try{
                    Thread.sleep((lastTime + desiredDelta - finishTime)/1000);
                }catch(InterruptedException e){
                    //Do nothing
                }
                finishTime = System.nanoTime();
            }
            lastTime = currentTime;
        }
    }

    public static void render() { // рисовка
        //suicide();
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT); // очистить
        //g.translate(WIDTH/2, HEIGHT/2);
        scene.render(g);
        g.dispose();
        bufferStrategy.show();
    }

    public static void terminate(){ // завершение работы
        running = false;
    }

    public static void suicide(){
        throw new RuntimeException("героям слава ");
    }
}
