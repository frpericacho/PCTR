package ejercicios_para_practicar;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CIWS {
    static ReentrantLock cerr = new ReentrantLock();
    static Condition cond = cerr.newCondition();
    static boolean hayCIWS;
    static boolean[] usados = new boolean[4];
    static CIWS[] ciws = new CIWS[4];
    int id;

    public CIWS pedirCIWS(int id) {
        cerr.lock();
        CIWS res = null;
        try {
            while (!hayCIWS) {
                try {
                    cond.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            for (int i = 0; i < 4; ++i) {
                if (usados[i] == false) {
                    res = ciws[i];
                    usados[i] = true;
                    if (usados[0] && usados[1] && usados[2] && usados[3])
                        hayCIWS = false;

                    break;
                }
            }
            System.out.println("La estacion de combate " + id + " se dispone a usar CIWS-" + res.id);
            return res;
        } finally {
            cerr.unlock();
        }
    }

    public void dejarCIWS(int id, CIWS res) {
        cerr.lock();
        try {
            System.out.println("La estacion de combate " + id + " se dispone a dejar de usar CIWS-" + res.id);
            usados[res.id] = false;
            hayCIWS = true;
            cond.signalAll();
        } finally {
            cerr.unlock();
        }
    }
}