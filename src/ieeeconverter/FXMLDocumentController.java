/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ieeeconverter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Enoch
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private ComboBox optionBox;
    @FXML
    private Button Start;
    @FXML
    private Button Cancel_Btn;
    @FXML
    private AnchorPane MainPane;
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) MainPane.getScene().getWindow();
            stage.close();
            Parent root = FXMLLoader.load(getClass().getResource("/ieeeconverter/MainMenuFXML.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
    }
    
    @FXML
    private void handleCancel (ActionEvent event){
        Stage stage = (Stage) MainPane.getScene().getWindow();
        stage.close();
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
