package main;
import java.util.*;

// Working program with FastReader
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Code {
    // Driver code
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(
                    new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        ThreadPool pool=new ThreadPool(3);
        Job1 j1=new Job1();
        Job2 j2=new Job2();
        Job3 j3=new Job3();
        pool.submit_job(j1);
        pool.submit_job(j2);
        pool.submit_job(j3);
    }
}

class ThreadPool
{
    private int DEFAULT_INITIAL_CAPACITY=5;
    int capacity;
    BlockingQueue<Job> job_queue=new LinkedBlockingQueue<>();
    ArrayList<MyThread> thread_list=new ArrayList<>();
    ThreadPool(int size)
    {
        if(size<1)
        {
            throw new IllegalArgumentException("Thread poll size cant be non-positive");
        }
        capacity=size;
        init_pool();
    }
    ThreadPool()
    {
        capacity=DEFAULT_INITIAL_CAPACITY;
        init_pool();
    }
    private void init_pool()
    {
        for(int i=0;i<capacity;i++)
        {
            MyThread t=new MyThread(job_queue);
            t.start();
            thread_list.add(t);
        }
    }
    public void submit_job(Job job)
    {
        job_queue.add(job);
    }
}
class MyThread extends Thread
{
    BlockingQueue<Job> q;
    MyThread(BlockingQueue<Job> q)
    {
        this.q=q;
    }
   public void run()
   {
       while(true)
       {
           Job job= null;
           try {
               job = q.take();
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           job.run();
       }
   }
}
abstract class Job implements Runnable
{

}

class Job1 extends Job
{
    public void run()
    {
        System.out.println("Job 1 is given for execution....");
    }
}
class Job2 extends Job
{
    public void run()
    {
        System.out.println("Job 2 is given for execution....");
    }
}
class Job3 extends Job
{
    public void run()
    {
        System.out.println("Job 3 is given for execution....");
    }
}