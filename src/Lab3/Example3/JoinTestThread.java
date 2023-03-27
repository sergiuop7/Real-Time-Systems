package Lab3.Example3;

import java.util.Objects;

class JoinTestThread extends Thread{
    Thread t;
    String name;

    JoinTestThread(String n, Thread t){
        this.setName(n);
        this.t=t;
        this.name = n;
    }
    public void run() {
        System.out.println("Thread "+name+" has entered the run() method");
        try {
            if (t != null) t.join();
            System.out.println("Thread "+name+" executing operation.");
            Thread.sleep(3000);
            if (Objects.equals(name, "Thread 1")) {
                int s = 0;
                int random = (int)Math.floor(Math.random() * (60000 - 50000 + 1) + 50000);
                for (int i=1;i<=random;i++)
                    if (random%i==0)
                        s = s+i;
                System.out.println("Thread 1 sum: "+s);
                Main.sumOfDivisors = s;
            }else if(Objects.equals(name,"Thread 2")){
                int s = 0;
                int random = (int)Math.floor(Math.random() * (30000 - 20000 + 1) + 20000);
                for (int i=1;i<=random;i++)
                    if (random%i==0)
                        s = s+i;
                System.out.println("Thread 2 sum: "+s);
                Main.sumOfDivisors += s;
            }
            System.out.println("Thread "+name+" has terminated operation.");
        }
        catch(Exception e){e.printStackTrace();}
    }
}




