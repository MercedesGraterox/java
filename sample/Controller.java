package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TextField;


public class Controller {

    @FXML
    private TextField Viaje;
    @FXML
    private TextField CantPasajeros;
    @FXML
    private TextField PrecioUnitario;
    @FXML
    private TextField NombreDelPasajero;
    @FXML
    private TextField DNI;
    @FXML
    private ComboBox ComboDestino;
    @FXML
    private ComboBox ComboDestino1;
    @FXML
    private TableView Tabla;
    @FXML
    private TextField TotalDestino;
    @FXML
    private TextField Cantpasajerosdestino;
    @FXML
    private TextField TotalCierre;

    ObservableList<String>ListaDestino= FXCollections.observableArrayList("Argentina","España","Republica dominicana");
    ObservableList<String>ListaCombo2= FXCollections.observableArrayList();

    public void initialize(){
        ComboDestino.setItems(ListaDestino);
        ComboDestino1.setItems(ListaCombo2);
        Grilla();
    }

    private void Grilla(){
        TableColumn col1=new TableColumn("Nro Viaje");
        col1.setCellValueFactory(new PropertyValueFactory<>("nroviaje"));

        TableColumn col2=new TableColumn("Pasajero");
        col2.setCellValueFactory(new PropertyValueFactory<>("nombrepasajero"));

        TableColumn col3=new TableColumn("DNI");
        col3.setCellValueFactory(new PropertyValueFactory<>("DNI"));

        TableColumn col4=new TableColumn("Destino");
        col4.setCellValueFactory(new PropertyValueFactory<>("combodestino"));

        TableColumn col5=new TableColumn("Cant Pasajes");
        col5.setCellValueFactory(new PropertyValueFactory<>("cantpasajes"));

        TableColumn col6=new TableColumn("Precio");
        col6.setCellValueFactory(new PropertyValueFactory<>("preciounitario"));

        Tabla.getColumns().addAll(col1,col2,col3,col4,col5,col6);
    }

    public void agregar(ActionEvent actionEvent) {
        if(Viaje.getText().equals("")){
            MostrarErrores("El campo Numero de viaje se encuentra vacío");
            Viaje.requestFocus();
            return;
        }

        if(!Numeros(Viaje.getText())){
            MostrarErrores("El campo Nro de viaje solo acepta números.");
            return;
        }

        if (ComboDestino.getSelectionModel().isEmpty()){
            MostrarErrores("Falta seleccionar un Destino");
            return;
        }

        if(CantPasajeros.getText().equals("")){
            MostrarErrores("El campo cantidad de pasajeros se encuentra vacío.");
            return;
        }

        if(!Numeros(CantPasajeros.getText())){
            MostrarErrores("El campo cantidad de pasajeros solo acepta números.");
            return;
        }

        if(PrecioUnitario.getText().equals("")){
            MostrarErrores("El campo precio se encuentra vacío.");
            return;
        }

        //valida maximo de caracteres
        if(NombreDelPasajero.getLength()<5) {
            MostrarErrores("El campo nombre tiene menos de 5 caracteres.");
            return;
        }



        if(NombreDelPasajero.getText().equals("")){
            MostrarErrores("El campo Cantidad se encuentra vacío.");
            NombreDelPasajero.requestFocus();
            return;
        }
        if(!Numeros(DNI.getText())){
            MostrarErrores("El campo DNI solo acepta numeros");
            return;
        }

        if(DNI.getText().equals("")){
            MostrarErrores("El campo Dni se encuentra vacío.");
            DNI.requestFocus();
            return;
        }
        if(!Numeros(PrecioUnitario.getText())){
            MostrarErrores("El campo precio solo acepta números.");
            return;
        }

        Pasajero A = new Pasajero();
        A.setNroviaje(Integer.parseInt(Viaje.getText()));
        A.setCombodestino(ComboDestino.getSelectionModel().getSelectedItem().toString());
        A.setCantpasajes(Integer.parseInt(CantPasajeros.getText()));
        A.setNombrepasajero(NombreDelPasajero.getText());
        A.setPreciounitario(Integer.parseInt(PrecioUnitario.getText()));
        A.setDNI(Integer.parseInt(DNI.getText()));

        Tabla.getItems().addAll(A);

        if (!ListaCombo2.contains(A.getCombodestino()))
            ListaCombo2.add(A.getCombodestino());

        PrecioUnitario.setText("");
        ComboDestino.setValue("");
        NombreDelPasajero.setText("");
        CantPasajeros.setText("");
        Viaje.setText("");
        DNI.setText("");
    }

    public void calcular(ActionEvent actionEvent) {

        String destino = (String) ComboDestino1.getSelectionModel().getSelectedItem();
        String des;
        int contador1=0;
        int preciototal=0;
        int imp=0;
        int can=0;
        int contador2=0;
        int contador3=0;
        int contador4=0;


        for (int i=0;i<Tabla.getItems().size();i++){
            Pasajero con=(Pasajero) Tabla.getItems().get(i);
            can=con.getCantpasajes();
            imp=con.getPreciounitario();
            des=con.getCombodestino();


            if (des.equals(destino)){
                contador1+=can;
            }

            if (des.equals(destino)){
                contador2=can+contador3;
                contador3=can*imp;
                contador4+=contador3;
            }


        }




        Cantpasajerosdestino.setText(String.valueOf(contador1));
        TotalDestino.setText(String.valueOf(contador4));



    }

    public void calcular1(ActionEvent actionEvent) {
        String des;
        int contador1 = 0;
        int preciototal = 0;
        int imp = 0;
        int can = 0;
        int contador2 = 0;
        int contador3 = 0;
        int contador4 = 0;

        for (int i = 0; i < Tabla.getItems().size(); i++) {
            Pasajero con = (Pasajero) Tabla.getItems().get(i);
            can = con.getCantpasajes();
            imp = con.getPreciounitario();


            contador2 = can + contador3;
            contador3 = can * imp;
            contador4 += contador3;

        }

        TotalCierre.setText(String.valueOf(contador4));
    }



    

    private void MostrarErrores(String MsgError) {
        Alert msgalert = new Alert(Alert.AlertType.ERROR);
        msgalert.setTitle("Error al Insertar  datos");
        msgalert.setContentText(MsgError);
        msgalert.showAndWait();
    }

    private boolean Numeros(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        }catch (Exception e){
            return false;
        }
    }



}
