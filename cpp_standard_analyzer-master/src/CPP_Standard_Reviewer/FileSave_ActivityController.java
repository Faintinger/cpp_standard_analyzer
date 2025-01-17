/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CPP_Standard_Reviewer;

import static CPP_Standard_Reviewer.CriteriaFill_ActivityController.fList;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Faintinger
 */
//&p-FileSave
public class FileSave_ActivityController implements Initializable {
    public static ArrayList<File> fList;
    public static int iArr[];
    /**
     * Initializes the controller class.
     */
    File fArchivo = null;
    //&i
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fList = FXMLDocumentController.fList;
        for (int i = 0; i < fList.size(); i++) {
            System.out.println(fList.get(i).getName());
        }
        iArr = CriteriaFill_ActivityController.iArrCriteria;
        for (int i = 0; i < iArr.length; i++) {
            System.out.print(iArr[i] + " ");
        }
        System.out.println();
    }
    //&i
    private Stage getStage(ActionEvent event) {
        Button btnButton = (Button) event.getSource();
        Stage stStage = (Stage) btnButton.getScene().getWindow();
        return stStage;
    }
    //&i
    private void setPanel(Stage stStage, String sPanel, String sDirOrName){
        AnchorPane spAux = (AnchorPane) stStage.getScene().lookup("#" + sPanel);
        ListView<String> lvContent = new ListView();
        ArrayList<String> sListFNames = new ArrayList<String>();
        sListFNames.add(sDirOrName);
        ObservableList<String> obList = FXCollections.observableArrayList(sListFNames);
        lvContent.setItems(obList);
        VBox vbContent = new VBox();
        vbContent.getChildren().addAll(lvContent);
        //VBox.setVgrow(lvContent, Priority.ALWAYS);
        vbContent.setPrefWidth(spAux.getWidth());
        spAux.getChildren().add(vbContent);
    }
    //&i
    @FXML
    public void browseFolder(ActionEvent event) {
        Stage stStage = getStage(event);
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Excel Files (*.xlsx)", "*.xlsx");
        fileChooser.getExtensionFilters().add(extFilter);
        fArchivo = fileChooser.showSaveDialog(stStage);
        setPanel(stStage, "Path_Save", fArchivo.getPath());
        setPanel(stStage, "FileName_Save", fArchivo.getName());
    }
    //&i
    @FXML
    public void Report(ActionEvent event) throws Exception {
        if(fArchivo != null) {
            //SaveFile(Data, file);
            Report rReport = new Report(fList, iArr);
            rReport.generateReport(fArchivo.getPath(),fArchivo.getName());
            Parent root = FXMLLoader.load(getClass().getResource("Results_Activity.fxml"));
            Scene scene = new Scene(root);
            Button btnNext = (Button) event.getSource();
            Stage stage = (Stage) btnNext.getScene().getWindow();

            stage.setScene(scene);
            stage.show();
        } else {
            //JOptionPane.showMessageDialog(null, 
            //        "There are no file path and name selected.");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("CPP Analizer");
            alert.setHeaderText(null);
            alert.setContentText("There are no file path and name selected.");
            alert.showAndWait();
        }
        
    }
    //&i
    @FXML
    public void Back(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("CriteriaFill_Activity.fxml"));
        Scene scene = new Scene(root);
        Button btnNext = (Button) event.getSource();
        Stage stage = (Stage) btnNext.getScene().getWindow();
        
        stage.setScene(scene);
        stage.show();
    }
    
}
