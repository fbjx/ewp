import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by Administrator on 2017\11\3 0003.
 */
public class BusinessService {
    private static Logger logger = LoggerFactory.getLogger(BusinessService.class);
    private  ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10,30,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(6000),new ThreadFactoryBuilder().setNameFormat("package-activity-syn-pool-%d").build(),new ThreadPoolExecutor.AbortPolicy());

    private  ThreadPoolExecutor threadPoolExecutor1 = new ThreadPoolExecutor(10,30,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(6000),new ThreadFactoryBuilder().setNameFormat("package-activity-syn-pool-%d").build(),new ThreadPoolExecutor.AbortPolicy());


    public static void main(String [] args){
        new BusinessService().test();
    }

    public  String test(){
        for(int c=0;c<20;c++){
            threadPoolExecutor.execute(new ExecuteRunnable(this,c));
        }
        return "";
    }

    public  String startThread(int b){

        long count = 0;
        try {
            System.out.println("PACKAGE_ACTIVITY_SYN Begin...................................b:"+b);
            List<Long> idList = new ArrayList<Long>();
            for (int j=1;j<=1;j++){
                List<Callable<Long>> futureList = new ArrayList<Callable<Long>>();
                for (int i=0;i<200;i++){
                    futureList.add(new ExecuteCallable(i));
                }
                try {
                    List<Future<Long>> futureListResult = threadPoolExecutor1.invokeAll(
                            futureList);
                    futureListResult.forEach(listFuture -> {
                        try {
                            idList.add(listFuture.get());
                        } catch (InterruptedException ee) {
                            ee.printStackTrace();
                        } catch (ExecutionException ee) {
                            ee.printStackTrace();
                        }
                    });
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }catch (Exception ex) {
                   ex.printStackTrace();
                }
                count++;

            }
        }finally {
            System.out.println("PACKAGE_ACTIVITY_SYN result...................................b:"+b);
        }
        return "count="+count;
    }

}
