/**
 * Created by Administrator on 2017\11\3 0003.
 */
public class ExecuteRunnable implements Runnable {
    private int c;
    BusinessService businessService;
    public ExecuteRunnable(BusinessService businessService,int c){
        this.c = c;
        this.businessService = businessService;
    }

    @Override
    public void run() {
        System.out.println(c+"ExecuteRunnable-threadName:"+Thread.currentThread().getName());
        businessService.startThread(c);
    }
}
