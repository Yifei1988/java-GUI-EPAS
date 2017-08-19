package javagui.view;

import javafx.collections.ObservableSet;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import javagui.view.InterfaceController;

public class DialogFehlerController {
	@FXML
	private CheckBox fehlerCB_Kratzer;	
	@FXML
	private CheckBox fehlerCB_Loecher;	
	@FXML
	private CheckBox fehlerCB_F3;	
	@FXML
	private CheckBox fehlerCB_F4;	
	@FXML
	private CheckBox fehlerCB_F5;	
	@FXML
	private CheckBox fehlerCB_F6;
	
	@FXML
	private Button btnClearAll;
	@FXML
	private Button btnDefault;
	@FXML
	private Button btnAkzept;	
	@FXML
	private Button btnAbbrechen;

	private Stage dialogStage;
	
	private ObservableSet<CheckBox> selectedCheckBoxes = FXCollections.observableSet();
    private ObservableSet<CheckBox> unselectedCheckBoxes = FXCollections.observableSet();

    private IntegerBinding numCheckBoxesSelected = Bindings.size(selectedCheckBoxes);

    private final int maxNumSelected = 5;
    private int numFehlerSeleced = 0;

    public void initialize() {
        configureCheckBox(fehlerCB_Kratzer);
        configureCheckBox(fehlerCB_Loecher);
        configureCheckBox(fehlerCB_F3);
        configureCheckBox(fehlerCB_F4);
        configureCheckBox(fehlerCB_F5);
        configureCheckBox(fehlerCB_F6);
        
        checkBoxDefaultSet();
        
        if (numFehlerSeleced == maxNumSelected){
        	unselectedCheckBoxes.forEach(cb -> cb.setDisable(true));
        };

        numCheckBoxesSelected.addListener((obs, oldSelectedCount, newSelectedCount) -> {
            if (newSelectedCount.intValue() >= maxNumSelected) {
            	unselectedCheckBoxes.forEach(cb -> cb.setDisable(true));
            	}
            else {
            	unselectedCheckBoxes.forEach(cb -> cb.setDisable(false));
            	}
            });
    }
    
    public void checkBoxDefaultSet(){
    	checkBoxInitialSelection(InterfaceController.fehlerart1);
    	checkBoxInitialSelection(InterfaceController.fehlerart2);
    	checkBoxInitialSelection(InterfaceController.fehlerart3);
    	checkBoxInitialSelection(InterfaceController.fehlerart4);
    	checkBoxInitialSelection(InterfaceController.fehlerart5);
    }
    private void checkBoxInitialSelection(String fehlerart){
    	if(fehlerart != null){
    		numFehlerSeleced ++;
    		switch(fehlerart){
        	case "Kratzer": fehlerCB_Kratzer.setSelected(true);break;
        	case "Loecher": fehlerCB_Loecher.setSelected(true);break;
        	case "F3": fehlerCB_F3.setSelected(true);break;
        	case "F4": fehlerCB_F4.setSelected(true);break;
        	case "F5": fehlerCB_F5.setSelected(true);break;
        	case "F6": fehlerCB_F6.setSelected(true);break;
        	}
    	}
    }
    
    private void configureCheckBox(CheckBox checkBox) {
		if (checkBox.isSelected()) {
            selectedCheckBoxes.add(checkBox);
        } else {
            unselectedCheckBoxes.add(checkBox);
        }

        checkBox.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
            if (isNowSelected) {
                unselectedCheckBoxes.remove(checkBox);
                selectedCheckBoxes.add(checkBox);
            } else {
                selectedCheckBoxes.remove(checkBox);
                unselectedCheckBoxes.add(checkBox);
            }
        });
	}
    
    @FXML
    private void actionCleraAll(ActionEvent event){
    	fehlerCB_Kratzer.setSelected(false);
    	fehlerCB_Loecher.setSelected(false);
    	fehlerCB_F3.setSelected(false);
    	fehlerCB_F4.setSelected(false);
    	fehlerCB_F5.setSelected(false);
    	fehlerCB_F6.setSelected(false);
    }
    
    @FXML
    private void actionDefault(ActionEvent event){
    	actionCleraAll(event);
    	checkBoxDefaultSet();
    }
	
	@FXML
    private void actionAkzept(ActionEvent event) {
		String[] Fehler = new String[5];
		
		int i = 0;
		if(fehlerCB_Kratzer.isSelected()){
			Fehler[i] = "Kratzer";
			i++;
		}
		if(fehlerCB_Loecher.isSelected()){
			Fehler[i] = "Loecher";
			i++;
		}
		if(fehlerCB_F3.isSelected()){
			Fehler[i] = "F3";
			i++;
		}
		if(fehlerCB_F4.isSelected()){
			Fehler[i] = "F4";
			i++;
		}
		if(fehlerCB_F5.isSelected()){
			Fehler[i] = "F5";
			i++;
		}
		if(fehlerCB_F6.isSelected()){
			Fehler[i] = "F6";
			i++;
		}
		
		InterfaceController.fehlerart1 = Fehler[0];
		InterfaceController.fehlerart2 = Fehler[1];
		InterfaceController.fehlerart3 = Fehler[2];
		InterfaceController.fehlerart4 = Fehler[3];
		InterfaceController.fehlerart5 = Fehler[4];
		System.out.println(InterfaceController.fehlerart1);
		System.out.println(InterfaceController.fehlerart2);
		System.out.println(InterfaceController.fehlerart3);
		System.out.println(InterfaceController.fehlerart4);
		System.out.println(InterfaceController.fehlerart5);
		
		dialogStage.close();
	}
	
	@FXML
    private void actionAbbrechen(ActionEvent event) {
		dialogStage.close();
	}
	
	public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
}
