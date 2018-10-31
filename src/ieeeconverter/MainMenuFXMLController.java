/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ieeeconverter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Controller.Converter;
import Model.Output;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    @FXML
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


        //Converts the input to binary and hexa fr IEEE-756/2008
        Start_Btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               Output o =  new Converter(Decimal_Text.getText(), Base_Text.getText(), Exponent_Text.getText()).convert();
               if(o != null)
                TextArea_Answer.setText(o.getSignBit() + " " + o.getsCombinationBits() + " " + o.getsExponentialComBits() + " " + o.getsMantissa());
               else {
                   TextArea_Console.setText("Invalid input!");
                   TextArea_Answer.setText("Invalid input!");
               }
            }
        });
    }


    
}
