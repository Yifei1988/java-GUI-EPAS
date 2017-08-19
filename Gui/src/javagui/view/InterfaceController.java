package javagui.view;

import java.io.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.DirectoryChooser;

import com.mathworks.toolbox.javabuilder.*;//connection from matlab to java
import Klss2.BKlss2;//bring Klassifikation-Function of Matlab into java

import javagui.controller.CustomOutputStream;

public class InterfaceController {
	@FXML
	private Button btnFolderOpen;
	
	@FXML
	private Button btnKlssifi;
	
	@FXML
	private Button btnFace1;
	
	@FXML
	private Button btnFace2;
	
	@FXML
	private Button btnFace3;
	
	@FXML
	private Button btnFace4;
	
	@FXML
	private Button btnFace5;
	
	@FXML
	private Label txtFolderPath;
	
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
	private TextField fehler1;
	
	@FXML
	private LineChart lineChartTp;
	
	@FXML
	private LineChart lineChartFq;
	
	@FXML
	private LineChart lineChartPw;
	
	static String folederPath = null;//folederPath can be used everywhere in this class
	
	@FXML
	public void openFolderAction(ActionEvent event){
		lineChartTp.getData().clear();
		lineChartFq.getData().clear();
		lineChartPw.getData().clear();//before open a new Folder clear the linechart
		DirectoryChooser folderOpen = new DirectoryChooser();
		//set initial Folder:
		folderOpen.setInitialDirectory(new File("C:\\Users\\Administrator\\Desktop\\Beispiel"));
		File selectedDirectory = folderOpen.showDialog(null);
//		String folederPath = null;
		if(selectedDirectory != null){
			folederPath = selectedDirectory.getAbsolutePath();//get path of the selected folder
			txtFolderPath.setText(folederPath);
		}
		else{System.out.println("Kein Ordner!");}
		
		tooltipFolderPath.setText(folederPath);//[tool-tip] get path of selected folder
		
	//¡¾read Einstellungen in Labels¡¿:		
		String filePath_log_part = "\\log.txt"; 		
		String filePath_log_full = folederPath + filePath_log_part;
		//get path of log.txt(Einstellungen) in the selected folder
		
		//¡¾print all the Information from log.txt line by line¡¿:
		try (BufferedReader br = new BufferedReader(new FileReader(filePath_log_full))) {
			String line;
			int i=1;
			while ((line = br.readLine()) != null) {
			//	System.out.println(line);
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
		} catch (IOException e) {e.printStackTrace();}
		
		readinTemp();//read the value of Temp. in program 
	}//end Method of OpenFolderButton
	
	public void switchFaceAction1(ActionEvent event){
		lineChartFq.getData().clear();
		lineChartPw.getData().clear();
		String filePath_freq_part2 = "_f1.txt";
		readinFrequenz(filePath_freq_part2);
		String filePath_pow_part2 = "_f1.txt";
		readinPower(filePath_pow_part2);
	}
	public void switchFaceAction2(ActionEvent event){
		lineChartFq.getData().clear();
		lineChartPw.getData().clear();
		String filePath_freq_part2 = "_f2.txt"; 
		readinFrequenz(filePath_freq_part2);
		String filePath_pow_part2 = "_f2.txt";
		readinPower(filePath_pow_part2);
	}
	public void switchFaceAction3(ActionEvent event){
		lineChartFq.getData().clear();
		lineChartPw.getData().clear();
		String filePath_freq_part2 = "_f3.txt"; 
		readinFrequenz(filePath_freq_part2);
		String filePath_pow_part2 = "_f3.txt";
		readinPower(filePath_pow_part2);
	}
	public void switchFaceAction4(ActionEvent event){
		lineChartFq.getData().clear();
		lineChartPw.getData().clear();
		String filePath_freq_part2 = "_f4.txt"; 
		readinFrequenz(filePath_freq_part2);
		String filePath_pow_part2 = "_f4.txt";
		readinPower(filePath_pow_part2);
	}
	public void switchFaceAction5(ActionEvent event){
		lineChartFq.getData().clear();
		lineChartPw.getData().clear();
		String filePath_freq_part2 = "_f5.txt"; 
		readinFrequenz(filePath_freq_part2);
		String filePath_pow_part2 = "_f5.txt";
		readinPower(filePath_pow_part2);
	}
	
	@FXML
	public void klassifikationAction(ActionEvent event) throws MWException{
		fehler1.setEditable(false);
		fehler1.clear();
		
		PrintStream printStream = new PrintStream(new CustomOutputStream(fehler1));
		System.setOut(printStream);
		
		BKlss2 f = new BKlss2();
		Object fr[] = null;
		MWNumericArray a = null;
		int m = 3;
		a = new MWNumericArray(Double.valueOf(m), MWClassID.DOUBLE);
		fr = f.Klss2(1,a);//1 means one value will return, a is Input
		System.out.println(fr[0]);//0 means the first value		
	}
	
	private void readinTemp() {
		String filePath_temp_part = "\\temp.txt"; 
		String filePath_temp_full = folederPath + filePath_temp_part;
		//get path of temp.txt(Temperature) in the selected folder
		
		System.out.println(filePath_temp_full);
		
		int tp1=0,tp2=0,tp3=0,tp4=0,tp5=0;
		try (BufferedReader br = new BufferedReader(new FileReader(filePath_temp_full))) {
			String line;//every line is a value of Temperature
			int i=1;
			while ((line = br.readLine()) != null) {
			//	System.out.println(line);
				if(i==1){tp1 = Integer.parseInt((String)line);System.out.println(tp1);}
				if(i==2){tp2 = Integer.parseInt((String)line);System.out.println(tp2);}
				if(i==3){tp3 = Integer.parseInt((String)line);System.out.println(tp3);}
				if(i==4){tp4 = Integer.parseInt((String)line);System.out.println(tp4);}
				if(i==5){tp5 = Integer.parseInt((String)line);System.out.println(tp5);}
				else{}
				i++;
			}//end while
			i=0;
		} catch (IOException e) {e.printStackTrace();}
		setLineChartTp(tp1,tp2,tp3,tp4,tp5);//after read the Temperature then send them to LineChart
	}
	
	private void setLineChartTp(int tp1,int tp2,int tp3,int tp4,int tp5) {//LineChart of Temperature
        lineChartTp.setTitle("Temperatur");
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.getData().addAll(
        		new XYChart.Data<>("1", tp1),
        		new XYChart.Data<>("2", tp2),
        		new XYChart.Data<>("3", tp3),
		        new XYChart.Data<>("4", tp4),
   		        new XYChart.Data<>("5", tp5) );

        lineChartTp.getData().add(series);
    }
	
	private void readinFrequenz(String filePath_freq_part2) {
		String filePath_freq_part1 = "\\freq"; 
		String filePath_freq_full = folederPath + filePath_freq_part1 + filePath_freq_part2;
		//get path of freq.txt(Frequenz) in the selected folder
		
		System.out.println(filePath_freq_full);
		
		int fq1=0,fq2=0,fq3=0,fq4=0,fq5=0;
		try (BufferedReader br = new BufferedReader(new FileReader(filePath_freq_full))) {
			String line;//every line is a value of Frequenz
			int i=1;
			while ((line = br.readLine()) != null) {
			//	System.out.println(line);
				if(i==1){fq1 = Integer.parseInt((String)line);System.out.println(fq1);}
				if(i==2){fq2 = Integer.parseInt((String)line);System.out.println(fq2);}
				if(i==3){fq3 = Integer.parseInt((String)line);System.out.println(fq3);}
				if(i==4){fq4 = Integer.parseInt((String)line);System.out.println(fq4);}
				if(i==5){fq5 = Integer.parseInt((String)line);System.out.println(fq5);}
				else{}
				i++;
			}//end while
			i=0;
		} catch (IOException e) {e.printStackTrace();}
		setLineChartFq(fq1,fq2,fq3,fq4,fq5);//after read the Frequenz then send them to LineChart
	}
	
	private void setLineChartFq(int fq1,int fq2,int fq3,int fq4,int fq5) {//LineChart of Frequenz
        lineChartFq.setTitle("Frequenz");
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.getData().addAll(
        		new XYChart.Data<>("1", fq1),
        		new XYChart.Data<>("2", fq2),
        		new XYChart.Data<>("3", fq3),
		        new XYChart.Data<>("4", fq4),
   		        new XYChart.Data<>("5", fq5) );

        lineChartFq.getData().add(series);
    }
	
	private void readinPower(String filePath_pow_part2) {
		String filePath_pow_part1 = "\\pow"; 
		String filePath_pow_full = folederPath + filePath_pow_part1 + filePath_pow_part2;
		//get path of pow.txt(Spannung/Power) in the selected folder
		
		System.out.println(filePath_pow_full);
		
		int pw1=0,pw2=0,pw3=0,pw4=0,pw5=0;
		try (BufferedReader br = new BufferedReader(new FileReader(filePath_pow_full))) {
			String line;//every line is a value of Spannung/Power
			int i=1;
			while ((line = br.readLine()) != null) {
			//	System.out.println(line);
				if(i==1){pw1 = Integer.parseInt((String)line);System.out.println(pw1);}
				if(i==2){pw2 = Integer.parseInt((String)line);System.out.println(pw2);}
				if(i==3){pw3 = Integer.parseInt((String)line);System.out.println(pw3);}
				if(i==4){pw4 = Integer.parseInt((String)line);System.out.println(pw4);}
				if(i==5){pw5 = Integer.parseInt((String)line);System.out.println(pw5);}
				else{}
				i++;
			}//end while
			i=0;
		} catch (IOException e) {e.printStackTrace();}
		setLineChartPw(pw1,pw2,pw3,pw4,pw5);//after read the Spannung/Power then send them to LineChart
	}
	
	private void setLineChartPw(int pw1,int pw2,int pw3,int pw4,int pw5) {//LineChart of Spannung/Power
        lineChartPw.setTitle("Spannung");
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.getData().addAll(
        		new XYChart.Data<>("1", pw1),
        		new XYChart.Data<>("2", pw2),
        		new XYChart.Data<>("3", pw3),
		        new XYChart.Data<>("4", pw4),
   		        new XYChart.Data<>("5", pw5) );

        lineChartPw.getData().add(series);
    }


}
