/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejerciciosexamen.septiembre1213;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author antho
 */
public class suavimagenANTH implements Runnable{

    /**
     * @param args the command line arguments
     */
    private static float imagen[][] = new float[500][500];
    private static float escala[] = new float[20];
    private static BufferedImage Imagen = null;
    private int indexIni,indexFinal;
    private static int size;
    private static int rango;
    
    public suavimagenANTH(int indexIni,int indexFinal)
    {
        this.indexIni = indexIni;
        this.indexFinal = indexFinal;
    }
    
    public void run()
    {
        for(int i = indexIni;i<indexFinal;++i)
            for(int j = 0;j<size;++j)
            {
                imagen[i][j] = (4.0f*imagen[i][j]+imagen[(i-1+size)%size][j]+imagen[(i+1+size)%size][j]+imagen[i][(j-1+size)%size]+imagen[i][(j+1+size)%size])/8.0f;
                imagen[i][j] = escala[(int)(imagen[i][j]*19)];
            }
    }
    
    public static void main(String[] args) {
        System.out.println("Indique si desea la opcion 1 o 2\n1.Usar una imagen 500x500 con valores aleatorios\n2.Insertar dimesiones y valores\n");
        Scanner S = new Scanner(System.in);
        int opcion = S.nextInt();
        int nDim;
        float inc = 1.0f/19;
        for(int i = 0;i<20;++i)
            escala[i] = i*inc;
        int indice;
        int nCores = Runtime.getRuntime().availableProcessors();
        JFrame frame = new JFrame("Visualizador Inicial");
        JFrame frameEnd = new JFrame("Visualizador Final");
        ThreadPoolExecutor ejecutor = null;
        JLabel imag = null;
        Color c = null;
        JLabel imag2 = null;
        switch(opcion)
        {
            case 1://Opcion de vals aleatorios.
                Imagen = new BufferedImage(500,500,BufferedImage.TYPE_BYTE_GRAY);
                imag = new JLabel(new ImageIcon(Imagen));
                frame.getContentPane().add(imag);
                size = 500;
                for(int i = 0;i<500;++i)
                    for(int j = 0;j<500;++j)
                    {
                        imagen[i][j] = escala[truncateDecimal(Math.random()*20,9).intValue()];
                        c = new Color(imagen[i][j],imagen[i][j],imagen[i][j]);
                        Imagen.setRGB(i,j,c.getRGB());
                    }
                
                frame.pack();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setVisible(true);
                rango = 500/nCores;
                ejecutor = new ThreadPoolExecutor(nCores,nCores,6000L,TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>());
                int i;
                for(i = 0;i<nCores-1;++i)
                    ejecutor.execute(new suavimagenANTH(i*rango,(i+1)*rango));
                ejecutor.execute(new suavimagenANTH(i*rango,500));
                ejecutor.shutdown();
                while(!ejecutor.isTerminated());
                imag2 = new JLabel(new ImageIcon(Imagen));
                frameEnd.getContentPane().add(imag2);
                for(i = 0;i<500;++i)
                    for(int j = 0;j<500;++j)
                    {
                        c = new Color(imagen[i][j],imagen[i][j],imagen[i][j]);
                        Imagen.setRGB(i,j,c.getRGB());
                    }
                frameEnd.pack();
                frameEnd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frameEnd.setVisible(true);
                break;
            case 2:
                System.out.println("Indique la dimension de la imagen a emplear");
                nDim = S.nextInt();
                imagen = new float[nDim][nDim];
                Imagen = new BufferedImage(nDim,nDim,BufferedImage.TYPE_BYTE_GRAY);
                for(int j = 0;j<nDim;++j)
                {
                    //System.out.println("Indique todos lo valores(1 a 20) de la fila"+j+" de la imagen,se pasarÃ¡ a escala de grises.\n");
                    for(int k = 0;k<nDim;++k)
                    {
                        //indice = S.nextInt();
                        indice = truncateDecimal(Math.random()*19,9).intValue();
                        //imagen[j][k][p] = escala[indice-1];
                        imagen[j][k] = escala[indice];
                        c = new Color(imagen[j][k],imagen[j][k],imagen[j][k]);
                        Imagen.setRGB(j,k,c.getRGB());
                    }
                }
                frame = new JFrame("Image inicial");
                imag = new JLabel(new ImageIcon(Imagen));
                frame.getContentPane().add(imag);
                size = nDim;
                frame.pack();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setVisible(true);
                rango = nDim/nCores;
                ejecutor = new ThreadPoolExecutor(nCores,nCores,6000L,TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>());
                int m;
                for(m = 0;m<nCores-1;++m)
                    ejecutor.execute(new suavimagenANTH(m*rango,(m+1)*rango));
                ejecutor.execute(new suavimagenANTH(m*rango,nDim));
                ejecutor.shutdown();
                while(!ejecutor.isTerminated());
                frameEnd = new JFrame("Imagen final");
                imag2 = new JLabel(new ImageIcon(Imagen));
                frameEnd.getContentPane().add(imag2);
                for(i = 0;i<nDim;++i)
                    for(int j = 0;j<nDim;++j)
                    {
                        c = new Color(imagen[i][j],imagen[i][j],imagen[i][j]);
                        Imagen.setRGB(i,j,c.getRGB());
                    }
                frameEnd.pack();
                frameEnd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frameEnd.setVisible(true);
                break;
            default:
                System.err.println("Opcion Invalida.\n");
                break;
        }
        
    }
    public static BigDecimal truncateDecimal(double x,int numberofDecimals)
    {
        if ( x > 0) {
            return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, BigDecimal.ROUND_FLOOR);
        } else {
            return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, BigDecimal.ROUND_CEILING);
        }
    }
}