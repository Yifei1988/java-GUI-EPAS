package javagui.view;

import java.io.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.stage.DirectoryChooser;

public class InterfaceController {
	@FXML
	private Button btnFolderOpen;
	
	@FXML
	private TextField txtFolderPath;
	
	@FXML
	private Tooltip tooltipFolderPath;
	
	@FXML
	private Label chargenNum;
	
	@FXML
	private Label bauteilNum;
	
	@FXML
	private Label scheibNum;
	
	@FXML
	private Label scheibTyp;
	
	@FXML
	private Label scheibDrm;
	
	@FXML
	private Label tool;
	
	@FXML
	private Label paste;
	
	@FXML
	private Label przGswO;
	
	@FXML
	private Label schGsw;
	
	@FXML
	private Label fu1;
	
	@FXML
	private Label przGswT;
	
	@FXML
	private Label zuFl1;
	
	@FXML
	private Label zuFl2;
	
	@FXML
	private Label zuFl3;
	
	@FXML
	private Label zuFl4;
	
	@FXML
	private Label zuFl5;
	
	@FXML
	public void openFolderAction(ActionEvent event){
		DirectoryChooser folderOpen = new DirectoryChooser();
		//set initial Folder:
		folderOpen.setInitialDirectory(new File("C:\\Users\\Administrator\\Desktop\\Beispiel"));
		File selectedDirectory = folderOpen.showDialog(null);
		String folederPath = null;
		if(selectedDirectory != null){
			folederPath = selectedDirectory.getAbsolutePath();
			txtFolderPath.setText(folederPath);
		}
		else{System.out.println("Kein Ordner!");}
		tooltipFolderPath.setText(folederPath);
		String filePathNameSet = "\\set.txt"; 
		String filePathSet = folederPath + filePathNameSet;
		//get path of set.txt(Einstellungen) in the selected folder
		
	
		//read TXT file:
/*        StringBuilder contents = new StringBuilder();
        try {
            BufferedReader input =  new BufferedReader(new FileReader("C:\\Users\\Administrator\\Desktop\\Beispiel\\Workpiece1\\set.txt"));
            try {
                String line = null;
                while (( line = input.readLine()) != null) {
                    contents.append(line);
                    contents.append(System.getProperty("line.separator"));
                }//end while
            }//end try
            finally {
                input.close();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        System.out.println(contents.toString());
        String contents0 = contents.toString();
        chargenNum.setText(contents0);
**/

//		String fileName = "C:\\Users\\Administrator\\Desktop\\Beispiel\\Workpiece1\\set.txt";
		String fileName = filePathSet;
		//print all the Information in set.txt line by line:
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line;
			int i=1;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				if(i==1){chargenNum.setText(line);}
				if(i==2){bauteilNum.setText(line);}
				if(i==3){scheibNum.setText(line);}
				if(i==4){scheibTyp.setText(line);}
				if(i==5){scheibDrm.setText(line);}
				if(i==6){tool.setText(line);}
				if(i==7){paste.setText(line);}
				if(i==8){przGswO.setText(line);}
				if(i==9){schGsw.setText(line);}
				if(i==10){fu1.setText(line);}
				if(i==11){przGswT.setText(line);}
				if(i==12){zuFl1.setText(line);}
				if(i==13){zuFl2.setText(line);}
				if(i==14){zuFl3.setText(line);}
				if(i==15){zuFl4.setText(line);}
				if(i==16){zuFl5.setText(line);}
				else{}
				i++;
			}//end while
			i=0;

		} catch (IOException e) {
			e.printStackTrace();
		}//end try-catch
        
		
	}//end Method of OpenFolderButton

}
