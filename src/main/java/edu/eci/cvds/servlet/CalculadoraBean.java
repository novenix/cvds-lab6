package edu.eci.cvds.servlet;

import java.util.ArrayList;
import java.lang.Math;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
@ManagedBean(name = "calculadoraBean")
@SessionScoped
public class CalculadoraBean {
    private ArrayList<Double> data;
	private String oldData = new String();
    private double mode;
    private double variance;
    private double standar;
    private double mean;
    private int size;
    /*
    calculateMean: Debe recibir como parámetro el listado de valores y retornar el promedio de los números en ella.
    calculateStandardDeviation: Debe recibir como parámetro el listado de valores y retornar el la desviación estandar de los números en ella.
    calculateVariance: Debe recibir como parámetro el listado de valores y retornar la varianza de los números en ella.
    calculateMode: Debe recibir como parámetro el listado de valores y retornar la moda de los números en ella.
    restart: Debe volver a iniciar la aplicación (Borrar el campo de texto para que el usuario agregue los datos).
    */
    public void calculateMean(ArrayList<Double> list){
    	mean=0;
        for (int i = 0; i < size; i++) {
            mean+= list.get(i);
        }
       mean = mean/ size;
    }
    public void calculateMode(ArrayList<Double> list){
    	int repeat = -1;
		int cont=0;
        mode = 0;
		
        for(int i = 0; i < size; i++){
            double act = data.get(i);
            cont = 0;
            for (int j = i; j < size; j++){
                if (list.get(j) == act ){
                    cont++;
                }
            }
            if (cont > repeat){
                mode = act;
                repeat = cont;
            }
        }
    }
    public void calculateStandardDeviation(ArrayList<Double> list){
    	standar = Math.sqrt(calculateVariance(list));
    }
	public double calculateVariance(ArrayList<Double> list){
        double act = 0;
        variance =0;
        for (int i = 0; i < size; i++){
            act +=  Math.pow(mean -list.get(i),2f);
        }
        variance = act/(size-1);
        return variance;
    }
   public void restart(){
    	data= new ArrayList<Double>();
		oldData = new String("");
    	data.add(0.0);
    	startCalculate();
    }
	private void startCalculate() {
    	setData(data);
		setOldData(oldData);
    	calculateMean(data);
    	calculateMode(data);
    	calculateVariance(data);
    	calculateStandardDeviation(data);
    	
    }
	 public void initList(String list) {
    	try {
    		data = new ArrayList<Double>();
        	String[] array = list.split(",");
        	for( String string: array) {
        		data.add( Double.parseDouble(string) );
        	}
			oldData=oldData+"(";
			for(double i :data){
				oldData=oldData+String.valueOf(i)+",";

			}
			oldData.substring(0, oldData.length()-1);
			oldData=oldData+")";
        	startCalculate();
    	}
    	catch(Exception e) {
    		restart();
    	}
    	
    }

	public ArrayList<Double> getData() {
		return this.data;
	}

	public void setData(ArrayList<Double> data) {
		this.data = data;
        setSize(data.size());
	}

	public double getMode() {
		return this.mode;
	}

	public void setMode(double mode) {
		this.mode = mode;
	}

	public double getVariance() {
		return this.variance;
	}

	public void setVariance(double variance) {
		this.variance = variance;
	}

	public double getStandar() {
		return this.standar;
	}

	public void setStandar(double standar) {
		this.standar = standar;
	}

	public double getMean() {
		return this.mean;
	}

	public void setMean(double mean) {
		this.mean = mean;
	}

	public int getSize() {
		return this.size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	public String getOldData() {
		return this.oldData;
	}

	public void setOldData(String oldData) {
		this.oldData = oldData;
	}

}