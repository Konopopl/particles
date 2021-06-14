import java.util.concurrent.BlockingQueue; // распределяем задачу по потокам
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadBag { // класс мешок потоков
    private BlockingQueue<Runnable> q = new LinkedBlockingQueue<>(); // создание блокирующей очереди
    public ThreadBag(int n){ //конструктор мешка потоков
        for(int i = 0; i < n; i++){
            Thread t = new Thread(new ThreadRunner(q)); // синхронизация выполняется только в плане чтения записи
            t.setDaemon(true);// программа говорит что она завершается тогда и только тогда когда все потоки не ,являющиеся демонами, завершаются . на демонов условия нет
            t.start();//  демоны просчитывают изменение координат частиц
        }
    }
    public void addTask(Runnable r){
        q.add(r);
    } // добавляем задачу (прикрепляем этот файл кода к другим частям)
}
