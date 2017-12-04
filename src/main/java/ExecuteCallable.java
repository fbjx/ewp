import java.util.concurrent.Callable;

/**
 * Created by Administrator on 2017\11\3 0003.
 */
public class ExecuteCallable implements Callable<Long> {
    private int b;

    public ExecuteCallable(int b){
        this.b = b;
    }
    @Override
    public Long call() throws Exception {
        for(int i =0;i<1;i++){
            System.out.println("threadName:"+Thread.currentThread().getName()+",Callable-b:"+b+"i:"+i);
            //Thread.sleep(1);
        }
        return 1000L;
    }
}
