package com.example.carcompany.process;

import java.util.ArrayList;

public class Vehicle {


    private String placa;
    private String marca;
    private String modelo;
    private String year;
    private String color;
    private String clienteNombre;
    private String clienteApellido;
    private String clienteCedula;
    private String tipoServicio;
    private String imagenServicio;

    public Vehicle(String placa, String marca, String modelo, String year,
                   String color, String clienteNombre,String clienteApellido, String clienteCedula, String tipoServicio, String imagenServicio) {
        this.setPlaca(placa);
        this.setMarca(marca);
        this.setModelo(modelo);
        this.setYear(year);
        this.setColor(color);
        this.setClienteNombre(clienteNombre);
        this.setClienteApellido(clienteApellido);
        this.setClienteCedula(clienteCedula);
        this.setTipoServicio(tipoServicio);
        this.setImagenServicio(imagenServicio);
    }










    private static ArrayList<ArrayList<String>> vehicleList = new ArrayList<ArrayList<String>>();


    // this method gonna store the type of service such (car washing, car maintenance, car painting)
    // setting default car washing
    private static String serviceType = "Car washing";

    // wash     maintenance_2       paint_2
    private static String imageName = "wash";

    public static ArrayList<ArrayList<String>> getVehicleList() {
        return vehicleList;
    }





    public static void setVehicleList(ArrayList<ArrayList<String>> vehicleList) {
        Vehicle.vehicleList = vehicleList;
    }

    public static String getServiceType() {
        return serviceType;
    }

    public static void setServiceType(String serviceType) {
        Vehicle.serviceType = serviceType;
    }

    public static String getImageName() {
        return imageName;
    }

    public static void setImageName(String imageName) {
        Vehicle.imageName = imageName;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getClienteNombre() {
        return clienteNombre;
    }

    public void setClienteNombre(String clienteNombre) {
        this.clienteNombre = clienteNombre;
    }

    public String getClienteApellido() {
        return clienteApellido;
    }

    public void setClienteApellido(String clienteApellido) {
        this.clienteApellido = clienteApellido;
    }

    public String getClienteCedula() {
        return clienteCedula;
    }

    public void setClienteCedula(String clienteCedula) {
        this.clienteCedula = clienteCedula;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public String getImagenServicio() {
        return imagenServicio;
    }

    public void setImagenServicio(String imagenServicio) {
        this.imagenServicio = imagenServicio;
    }

    /**
     * @param plate plate car
     * @param color color car
     * @param make make car
     * @param model model car
     * @param year year, whatever you want!!
     * @param ownerName propietary name
     * @param ownerCredential credentials
     * @param ownerLastname lastname propietary
     * */
    /*public void addToArray(String plate, String make,String model, String year,
                           String color, String ownerName, String ownerLastname,String ownerCredential, String service, String imgName){
        ArrayList<String> vector = new ArrayList<String>(Arrays.asList(plate, make, model, year, color, ownerName, ownerLastname, ownerCredential, service, imgName));

        vehicleList.add(vector);
    }*/

}
