package practica8;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.io.*;

class Conductores{
    public synchronized Conductor da_conductor(int i){
        return drivers.get(i);
    }

    public synchronized List<Conductor> da_conductores(int a,int b){
        return drivers.subList(a,b);
    }

    public synchronized List<Conductor> da_conductores(){
        return drivers;
    }

    public synchronized void pon_conductor(Conductor c){
        if(drivers.contains(c)){
            drivers.add(c);
        }
    }
    public synchronized void poner() throws Exception{
        String a = drivers.stream().map(fun2).collect(Collectors.joining("\n"));
        File f = new File(file);
        BufferedWriter pon = new BufferedWriter(new FileWriter(f));
        pon.write("nombre, dni, marca, matricula");
        pon.write(a);
        pon.close();
    }

    public synchronized int tamaño(){
        return drivers.size();
    }

    private static Function<String , Conductor> fun1 = (linea) ->{
        String[] campo = linea.split(",");
        return new Conductor(campo[0], campo[1], campo[2], campo[3]);
    };

    private static Function<Conductor, String> fun2 = (conductor) ->{
        return String.format("%s,%s,%s,%s", conductor.da_nombre(),conductor.da_dni(),conductor.da_marca(),conductor.da_matricula());
    };

    public Conductores(){
        this.drivers = new ArrayList<Conductor>();
    }

    public Conductores(String file){
        this.file = file;
        try {
            File f = new File(file);
            InputStream I = new FileInputStream(f);
            BufferedReader B = new BufferedReader(new InputStreamReader(I));
            drivers = B.lines().skip(1).map(fun1).collect(Collectors.toList());
            B.close();
        } catch (IOException e) {}
    }

    private List<Conductor> drivers;
    private String file;
}