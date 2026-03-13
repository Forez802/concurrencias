class Sumador implements Runnable {

    private int inicio;
    private int fin;
    private long resultado;

    public Sumador(int inicio, int fin) {
        this.inicio = inicio;
        this.fin = fin;
    }

    public void run() {
        for (int i = inicio; i <= fin; i++) {
            resultado += i;
        }
        System.out.println("Suma parcial de " + inicio + " a " + fin + " = " + resultado);
    }

    public long getResultado() {
        return resultado;
    }
}

public class Paralelismo {

    public static void main(String[] args) {

        Sumador s1 = new Sumador(1, 250000);
        Sumador s2 = new Sumador(250001, 500000);
        Sumador s3 = new Sumador(500001, 750000);
        Sumador s4 = new Sumador(750001, 1000000);

        Thread h1 = new Thread(s1);
        Thread h2 = new Thread(s2);
        Thread h3 = new Thread(s3);
        Thread h4 = new Thread(s4);

        h1.start();
        h2.start();
        h3.start();
        h4.start();

        try {
            h1.join();
            h2.join();
            h3.join();
            h4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long sumaTotal = s1.getResultado() + s2.getResultado() + s3.getResultado() + s4.getResultado();

        System.out.println("Suma total = " + sumaTotal);
    }
}