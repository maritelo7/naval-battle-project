/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package navalBattle.presentacion;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Maribel Tello Rodriguez
 * @author José Alí Valdivia Ruiz
 */
public class GUI_PuntuacionesController implements Initializable {
   @FXML 
   private JFXButton buttonRegresar;
   @FXML
   private Label labelPuntuaciones;
   /**
    * Initializes the controller class.
    */
   @Override
   public void initialize(URL url, ResourceBundle rb) {
      cargarIdioma();  
      buttonRegresar.setOnAction( event -> {
         
      });
      
   }      

   /**
    * Método para carga el idioma seleccionado por default en botones y etiquetas 
    */
   public void cargarIdioma(){
      Locale locale = Locale.getDefault();
      ResourceBundle resources = ResourceBundle.getBundle("navalBattle.recursos.idiomas.Idioma", locale);
      labelPuntuaciones.setText(resources.getString("labelPuntuaciones"));
   }   
}