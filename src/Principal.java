
public class Principal {

    public static void main(String[] args) {
        System.out.println("Se você vir o mesmo valor, o singleton foi reutilizado (yay!))" + "\n"
                + "Se você ver valores diferentes, então 2 singletons foram criados (booo!!)" + "\n\n"
                + "RESULTADO:" + "\n");
        
        Thread threadFoo = new Thread(new ThreadFoo());
        Thread threadBar = new Thread(new ThreadBar());
        
        threadFoo.start();
        threadBar.start();
    }
    
    static class ThreadFoo implements Runnable {
        @Override
        public void run() {
            Singleton singleton = Singleton.getInstance("FOO");
            System.out.println(singleton.valor);
        }
    }

    static class ThreadBar implements Runnable {
        @Override
        public void run() {
            Singleton singleton = Singleton.getInstance("BAR");
            System.out.println(singleton.valor);
        }
    }
}
