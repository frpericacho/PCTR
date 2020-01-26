// package Practicas18.practica10;

// import java.io.RandomAccessFile;
// import java.util.concurrent.locks.ReentrantLock;

// public class RWMonitorAN {
//     private final ReentrantLock cerrojo = new ReentrantLock();
//     private final ReentrantLock cerrojo2 = new ReentrantLock();
//     volatile int R = 0;
//     volatile boolean Esc = false;
//     volatile boolean W = false;
//     private RandomAccessFile file = null;

//     public RWMonitorAN() throws Exception{
//         file = new RandomAccessFile("data.dat", "rw");
//     }

//     synchronized void StartRead() throws Exception{
//         while(W || !Esc){
//             wait();
//         }
//         cerrojo.lock();
//         R = R + 1;
//         System.out.println("Lector inicia lectura...");
//         file.seek(0);
//         file.readInt();
//         file.close();
//         notifyAll();
//         cerrojo.unlock();
//         EndRead();
//     }

//     synchronized void EndRead() throws Exception{
//         R = R - 1;
//         if(R == 0)
//             notifyAll();
//         System.out.println("Lector finaliza lectura...");
//     }

//     synchronized void StartWrite() throws Exception{
//         while(W || R != 0){
//             wait();
//         }
//         cerrojo2.lock();
//         W = true;
//         System.out.println("Escritor inicia escritura...");
//         file.seek(file.getFilePointer() - 1);
//         file.writeInt(100);
//         file.close();
//         notifyAll();
//         cerrojo2.unlock();
//         EndWrite();
//     }

//     synchronized void EndWrite() throws Exception{
//         W = false;
//         Esc = true;
//         notifyAll();
//         System.out.println("Escritor finaliza escritura...");
//     }
// }