/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package navalBattle.gui;

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
 * @author javr
 */
public class GUI_RegistrarController implements Initializable {
   @FXML
   private Label labelNick;
   @FXML
   private Label labelClave;
   @FXML
   private Label labelConfirClave;
   @FXML
   private Label labelIdioma;
   @FXML
   private JFXButton buttonGuardar;
   @FXML
   private JFXButton buttonBaja;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      Locale locale = Locale.getDefault();
      cargarIdioma(locale);
    }    
    public void cargarIdioma(Locale localeSelect){
      
      ResourceBundle resources = ResourceBundle.getBundle("navalBattle.recursos.Idioma", localeSelect);
      
      buttonGuardar.setText(resources.getString("buttonGuardar"));
      buttonBaja.setText(resources.getString("buttonBaja"));
      labelNick.setText(resources.getString("labelNick"));
      labelConfirClave.setText(resources.getString("labelConfirClave"));
      labelIdioma.setText(resources.getString("labelIdioma"));
      labelClave.setText(resources.getString("labelClave"));
   }
}
