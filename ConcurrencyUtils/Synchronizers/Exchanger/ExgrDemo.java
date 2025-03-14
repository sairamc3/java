import java.util.concurrent.Exchanger;

class ExgrDemo {
    public static void main(String[] args) {

        Exchanger<String> exgr = new Exchanger<String>();

        new Thread(new UseString(exgr)).start();
        new Thread(new MakeString(exgr)).start();
    }
}

class MakeString implements Runnable {

    Exchanger exgr;
    String str;

    MakeString(Exchanger exgr) {
        this.exgr = exgr;
        this.str = new String();
    }

    public void run() {
        char ch = 'A';

        for (int i = 0; i < 3; i++) {
            
            for (int j = 0; j < 5; j++) str += ch++;
                
            try {
                str = (String) exgr.exchange(str);
            } catch (InterruptedException exe) {
                System.out.println(exe);
            }
        }
    }
}

class UseString implements Runnable {

    Exchanger exgr;
    String str;

    UseString(Exchanger exgr) {
        this.exgr = exgr;
    }

    public void run() {

        for (int i = 0; i < 3; i++) {

            try {
                str = (String) exgr.exchange(new String());
                System.out.println("Got: " + str);
            } catch (InterruptedException exe) {
                System.out.println(exe);
            }
        }
    }
}