package Practicas19.practica4;

class algHyman {
    public static int turn = 0, cont = 0;
    public static boolean flag[] = new boolean[2];

    public static void main(String[] args) throws InterruptedException {
        flag[0] = flag[1] = false;

        Runnable task = () -> {
            if (Thread.currentThread().getName() == "0") {
                while (true) {
                    flag[0] = true;
                    while (turn != 0) {
                        while (flag[1]) {
                            // do nothing
                        }
                        turn = 0;
                    }
                    cont++;
                    System.out.println(cont);
                    flag[0] = false;
                }
            } else {
                while (true) {
                    flag[1] = true;
                    while (turn != 1) {
                        while (flag[0]) {
                            // do nothing
                        }
                        turn = 1;
                    }
                    cont--;
                    System.out.println(cont);
                    flag[1] = false;
                }
            }
        };

        Thread hilo[] = new Thread[2];
        for (int i = 0; i < 2; i++) {
            hilo[i] = new Thread(task);
            hilo[i].start();
        }

        for (int i = 0; i < 2; i++)
            hilo[i].join();
    }
}
