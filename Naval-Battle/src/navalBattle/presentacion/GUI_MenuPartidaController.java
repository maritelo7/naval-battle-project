/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package navalBattle.presentacion;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import navalBattle.logica.CuentaUsuario;
import navalBattle.recursos.animaciones.Barco;
import navalBattle.recursos.animaciones.SpriteAnimation;

/**
 * FXML Controller class
 * 
 * @author Maribel Tello Rodriguez
 * @author José Alí Valdivia Ruiz
 */
public class GUI_MenuPartidaController implements Initializable {
   CuentaUsuario cuentaLogueada;
   
   @FXML
   private JFXButton buttonReglas;
   @FXML
   private JFXButton buttonPuntuacion;
   @FXML
   private JFXButton buttonSalir;
   @FXML
   private JFXButton buttonCrearPartida;
   @FXML
   private JFXButton buttonUnirmePartida;
   @FXML
   private JFXButton buttonConfigurar;
   @FXML
   private Label labelSalir;
   @FXML
   private Label labelConfigurar;
   @FXML
   private Label labelIniciando;
   @FXML
   private ImageView imageVBoat;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
      cargarIdioma();
      cargarAnimacion();
      labelIniciando.setVisible(false);
      
      buttonCrearPartida.setOnAction( event -> {
         labelIniciando.setVisible(true);
         //ESTABLECER CONEXIÓN Y SER SERVER
         boolean conexion = true;
         if (conexion) {
           irPrepararPartida(event);
         }
      });
      
      buttonUnirmePartida.setOnAction( event -> {
         labelIniciando.setVisible(true);
         //ESTABLECER CONEXIÓN Y SER CLIENTE
         boolean conexion = true;
         if (conexion) {
           irPrepararPartida(event);
         }
      });
      
      buttonConfigurar.setOnAction( event ->  {   
         Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            try {
               FXMLLoader loader = new FXMLLoader(getClass().getResource("GUI_Registrar.fxml")); 
               GUI_RegistrarController controller = new GUI_RegistrarController(cuentaLogueada);
               loader.setController(controller);
               Scene scene = new Scene(loader.load());
               stage.setScene(scene);
               stage.setResizable(false);
               stage.show();
            } catch (IOException ex) {
               Logger.getLogger(GUI_IniciarSesionController.class.getName()).log(Level.SEVERE, null, ex);
            } 
      });
      
      buttonSalir.setOnAction( event -> {
         cargarVentana(event, "Gui_IniciarSeion");
      });
      
      buttonReglas.setOnAction( event -> {
        //cargarVentana(event, "Gui_Reglas.fxml"); 
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            try {
               FXMLLoader loader = new FXMLLoader(getClass().getResource("GUI_Reglas.fxml")); 
               GUI_ReglasController controller = new GUI_ReglasController(cuentaLogueada);
               loader.setController(controller);
               Scene scene = new Scene(loader.load());
               stage.setScene(scene);
               stage.setResizable(false);
               stage.show();
            } catch (IOException ex) {
               Logger.getLogger(GUI_IniciarSesionController.class.getName()).log(Level.SEVERE, null, ex);
            } 
      });
      
      buttonPuntuacion.setOnAction( event -> {
         cargarVentana(event, "GUI_Puntuaciones.fxml");         
      });      
    }    

    public GUI_MenuPartidaController(CuentaUsuario cuenta){
     cuentaLogueada = cuenta;
    }
    
    public void irPrepararPartida(Event event){
       Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            try {
               FXMLLoader loader = new FXMLLoader(getClass().getResource("GUI_PrepararPartida.fxml")); 
               GUI_PrepararPartidaController controller = new GUI_PrepararPartidaController(cuentaLogueada);
               loader.setController(controller);
               Scene scene = new Scene(loader.load());
               stage.setScene(scene);
               stage.setResizable(false);
               stage.show();
            } catch (IOException ex) {
               Logger.getLogger(GUI_IniciarSesionController.class.getName()).log(Level.SEVERE, null, ex);
            } 
    }
   /**
    * Método para cargar el idioma seleccionado por default en etiquetas y botones 
    */
   public void cargarIdioma(){
      Locale locale = Locale.getDefault();
      ResourceBundle resources = ResourceBundle.getBundle("navalBattle.recursos.idiomas.Idioma", locale);
      buttonReglas.setText(resources.getString("buttonReglas"));
      buttonPuntuacion.setText(resources.getString("buttonPuntuacion"));
      buttonCrearPartida.setText(resources.getString("buttonCrearPartida"));
      buttonUnirmePartida.setText(resources.getString("buttonUnirmePartida"));
      labelSalir.setText(resources.getString("labelSalir"));
      labelConfigurar.setText(resources.getString("labelConfigurar"));
      labelIniciando.setText(resources.getString("labelIniciando"));
   }
   
   public void cargarVentana(Event event, String url) {
         Node node = (Node) event.getSource();
         Stage stage = (Stage) node.getScene().getWindow();
         Parent root;
         try {
            root = FXMLLoader.load(getClass().getResource(url));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);   
            stage.show();
         } catch (IOException ex) {
            Logger.getLogger(GUI_MenuPartidaController.class.getName()).log(Level.SEVERE, null, ex);
         }
   }
   /**
    * Método para cargar animación de barco 
    */
   public void cargarAnimacion() {
      final Image IMAGE = new Image(Barco.class.getResourceAsStream("/navalBattle/recursos/imagenes/boatMove.png"));
      final int WIDTH = 464;
      final int HEIGHT = 237;
      final int COLUMNS = 3;
      final int COUNT = 6;
      final int OFFSET_X = 8;
      final int OFFSET_Y = 0;

      imageVBoat.setImage(IMAGE);
      imageVBoat.setViewport(new Rectangle2D(OFFSET_X, OFFSET_Y, WIDTH, HEIGHT));
      final Animation animation = new SpriteAnimation(
          imageVBoat,
          Duration.millis(4000),
          COUNT, COLUMNS,
          OFFSET_X, OFFSET_Y,
          WIDTH, HEIGHT
      );
      animation.setCycleCount(Animation.INDEFINITE);
      animation.play();
   }
}
