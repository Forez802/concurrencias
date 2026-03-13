class CuentaBancaria {
    private int saldo = 1000;

    public synchronized void retirar(int monto, String cliente) {
        if (saldo >= monto) {
            System.out.println(cliente + " intenta retirar " + monto);
            
            try {
                Thread.sleep(100); // se simula el tiempo de procesamiento
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            saldo -= monto;
            System.out.println(cliente + " retiro " + monto + "- Saldo restante: " + saldo);
        } else {
            System.out.println(cliente + " intento retirar " + monto + " pero saldo insuficiente- Saldo actual: " + saldo);
        }
    }
}

class Cliente extends Thread {
    private CuentaBancaria cuenta;
    private String nombre;

    public Cliente(CuentaBancaria cuenta, String nombre) {
        this.cuenta = cuenta;
        this.nombre = nombre;
    }

    public void run() {
        cuenta.retirar(400, nombre);
    }
}

public class Banco {
    public static void main(String[] args) {

        CuentaBancaria cuenta = new CuentaBancaria();

        Cliente c1 = new Cliente(cuenta, "Cliente 1");
        Cliente c2 = new Cliente(cuenta, "Cliente 2");
        Cliente c3 = new Cliente(cuenta, "Cliente 3");

        c1.start();
        c2.start();
        c3.start();
    }
}