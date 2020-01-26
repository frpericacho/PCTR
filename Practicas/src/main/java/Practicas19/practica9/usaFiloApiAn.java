
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class usaFiloApiAn implements Runnable{
    public static filoApiAN filo = new filoApiAN();
    public int id;

    public usaFiloApiAn(int i){
        this.id = i;
    }

    public static void main(String[] args) {
        ExecutorService exe = Executors.newFixedThreadPool(5);
        for(int i = 0; i < 5; i++){
            exe.execute(new usaFiloApiAn(i));
        }
        exe.shutdown();
    }

    @Override
    public void run() {
        filo.takeforks(id);
    }
}