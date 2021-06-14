import java.util.Queue; // тут мы реализуем обмен данными между окнами
import java.util.concurrent.BlockingQueue; // обращаемся к блок очереди и кладем на нее ссылку ,тред запускатся в бесконечном цикле берем задачи и запускаем

public class ThreadRunner implements Runnable { // тут происходит обмен данными между потоками
    private BlockingQueue<Runnable> q;

    public ThreadRunner(BlockingQueue<Runnable> q){
        this.q = q;
    }

    @Override
    public void run(){
        while(0==0){ // бесконечный цикл
            try {
                q.take().run();//  взять что-то из очереди и запустить
            } catch(InterruptedException ignore){} //пропускаем исключения
        }
    }
}
