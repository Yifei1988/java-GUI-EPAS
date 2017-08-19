package javagui.view;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class DialogFehlerController {
	@FXML
	private ComboBox<String> fehler1_Combobox;
	
	@FXML
	private ComboBox<String> fehler2_Combobox;
	
	@FXML
	private ComboBox<String> fehler3_Combobox;
	
	@FXML
	private ComboBox<String> fehler4_Combobox;
	
	@FXML
	private ComboBox<String> fehler5_Combobox;
	
	@FXML
	private Button btnOK;

	private Stage dialogStage;
	
	public void fehlerKorrigieren(String fehlerart_in_Dialog) throws ClassNotFoundException {
		System.out.println(fehlerart_in_Dialog + " ¡ú Dialog");
		initializeCombo();
		fehler1_Combobox.getSelectionModel().select(fehlerart_in_Dialog);

		System.out.println(fehlerart_in_Dialog);
		
//		return fehlerart_in_Dialog;
	}
	
	@FXML
    private void ActionOk(ActionEvent event) {
		javagui.view.InterfaceController.fehlerart1
 = fehler1_Combobox.getSelectionModel().getSelectedItem().toString();
		dialogStage.close();
	}
	
	public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
	
	@FXML
	public void initializeCombo() {
		List<String> FelerList = new ArrayList<String>();
		FelerList.add("Kratzer");
		FelerList.add("Loecher");
        ObservableList<String> FehlerObList = FXCollections.observableList(FelerList);
        fehler1_Combobox.getItems().clear();
        fehler1_Combobox.setItems(FehlerObList);
        
        fehler2_Combobox.getItems().clear();
        fehler2_Combobox.setItems(FehlerObList);
        
        fehler3_Combobox.getItems().clear();
        fehler3_Combobox.setItems(FehlerObList);
        
        fehler4_Combobox.getItems().clear();
        fehler4_Combobox.setItems(FehlerObList);
        
        fehler5_Combobox.getItems().clear();
        fehler5_Combobox.setItems(FehlerObList);
	}
}
