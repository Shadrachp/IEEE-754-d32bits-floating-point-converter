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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Enoch
 */
public class MainMenuFXMLController implements Initializable {

    @FXML
    private Button Start_Btn;
    @FXML
    private AnchorPane mainmenuPane;
    @FXML
    private TextField Decimal_Text;
    @FXML
    private TextField Base_Text;
    @FXML
    private TextField Exponent_Text;
    
    @FXML 
    private TextArea TextArea_Console;
    private TextArea TextArea_Answer;
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        
        
        
        
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
