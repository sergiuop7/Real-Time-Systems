package Lab2;

class Main2{

    public static void main(String[] args) {

        CounterRunnable c1 = new CounterRunnable();

        CounterRunnable c2 = new CounterRunnable();

        CounterRunnable c3 = new CounterRunnable();

        Thread t1 = new Thread(c1,"counter1");

        Thread t2 = new Thread(c2,"counter2");

        Thread t3 = new Thread(c3,"counter3");

        t1.start();

        t2.start();

        t3.start();

    }

}

public class CounterRunnable implements Runnable {

    public void run(){

        Thread t = Thread.currentThread();

        for(int i =0; i<20; i++){

            System.out.println(t.getName() + " i = "+i);

            try { Thread.sleep((int)(Math.random() * 1000));}

            catch (InterruptedException e) {e.printStackTrace();}

        }

        System.out.println(t.getName() + " job finalised.");

    }

}


