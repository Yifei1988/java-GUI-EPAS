package javagui.view;

import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DialogMassnahme extends Stage{
	
	public DialogMassnahme(){//constructor：令方法名与类名一致，就可以直接在别的类调用此类此方法（还需查资料）
//		Stage st = new Stage();
//		BorderPane layout = new BorderPane();
		
		VBox vb = new VBox();
		
		Label labelTop = new Label();
		labelTop.setText(" Bestaetigen Sie bitte zutrefende Massnahme(n):");
		labelTop.setFont(new Font(20));
		
		Label labelBlank = new Label();//添加一个空白标签用于空行
		
		vb.getChildren().add(labelTop);
		vb.getChildren().add(labelBlank);
		
		if(InterfaceController.fehlerart1 != null){
			vb.getChildren().add(allInVBox(observablelistToVBox(
					1,InterfaceController.fehlerart1,
					InterfaceController.massZuFehler1Dialog)));
		}
		if(InterfaceController.fehlerart2 != null){
			vb.getChildren().add(allInVBox(observablelistToVBox(
					2,InterfaceController.fehlerart2,
					InterfaceController.massZuFehler2Dialog)));
		}
		if(InterfaceController.fehlerart3 != null){
			vb.getChildren().add(allInVBox(observablelistToVBox(
					3,InterfaceController.fehlerart3,
					InterfaceController.massZuFehler3Dialog)));
		}
		if(InterfaceController.fehlerart4 != null){
			vb.getChildren().add(allInVBox(observablelistToVBox(
					4,InterfaceController.fehlerart4,
					InterfaceController.massZuFehler4Dialog)));
		}
		if(InterfaceController.fehlerart5 != null){
			vb.getChildren().add(allInVBox(observablelistToVBox(
					5,InterfaceController.fehlerart5,
					InterfaceController.massZuFehler5Dialog)));
		}
		
		AnchorPane ap =new AnchorPane(vb);//将总的VBox装入定位面板便于下面定位留白
		AnchorPane.setTopAnchor(vb, 0.0);
		AnchorPane.setLeftAnchor(vb, 10.0);//给左侧位置留白
		AnchorPane.setRightAnchor(vb, 0.0);
		
		ScrollPane sc = new ScrollPane(ap);//将上面的定位面板装入拖动条面板中
		sc.setFitToHeight(true);
		
		Scene scene = new Scene(sc, 600, 650);
		
		this.setScene(scene);
		this.initModality(Modality.APPLICATION_MODAL);//设置模态
		this.setTitle("Massnahmeauswahl");
		this.centerOnScreen();
		this.setResizable(false);
	}
	
	//用于将observablelist量massZuFehler1至5转化为CheckBox，并增加其他内容组成VBox
	public VBox observablelistToVBox(int n, String fehlerart, ObservableList<String> massZuFehler){
		String s = massZuFehler.toString();
		s = s.replace("[", " ");//把ObservableList转换为字符串时系统自动添加的[符号替换为空格
		s = s.replace("]", " ");//把ObservableList转换为字符串时系统自动添加的]符号替换为空格

		String[] arrayF = s.split("LessIsMore,");
		//以字符串"LessIsMore,"为断点将字符串转换为数组(注：其中的逗号为访问数据库操作时ObservableList执行add过程中系统自动添加的)
		arrayF[arrayF.length-1] = arrayF[arrayF.length-1].replace("LessIsMore", "");
		//去除最后一项多出来的辅助字母串"LessIsMore"

		VBox checkboxVBox = new VBox(2);
		
		Label labelFehler = new Label();//添加Kommentar标签
		labelFehler.setText("zur Fehler" + n + "(" + fehlerart + "):");
		labelFehler.setFont(new Font(18)); 
		checkboxVBox.getChildren().add(labelFehler);
		
		for(int i=0; i<arrayF.length; i++){
			char character = arrayF[i].charAt(1);//取当前字符串的第二个字符(因为首字符都是空格)
			int ascii = (int)character;
			if(ascii == 122 || ascii == 32){//即该字符是空格(空行)或者是字母z(单词zur)时
				Label label = new Label();
				label.setText(arrayF[i]);
				checkboxVBox.getChildren().add(label);
			}
			else{
				CheckBox cb = new CheckBox(" " + arrayF[i] + " ");
				checkboxVBox.getChildren().add(cb);
			}
		}
		return checkboxVBox;
	}
	
	//用于将所有需要在最后的VBox呈现的内容都装进去
	public VBox allInVBox(VBox vb){
		VBox checkboxVBox = new VBox(2);
		
		checkboxVBox.getChildren().add(vb);//加载之前的VBox
		
		Label labelKommentar = new Label();//添加Kommentar标签
		labelKommentar.setText("Kommentar");
		checkboxVBox.getChildren().add(labelKommentar);
		
		TextArea ta = new TextArea();//添加Kommentar文本框
		ta.setMinHeight(70);//设定文本框的最小高度
//		ta.setPrefRowCount(10);
		ta.setWrapText(true);//这个命令可以去除文本框横向的拖动条

		checkboxVBox.getChildren().add(ta);
		
		Label labelBlank = new Label();//添加一个空白标签用于空一行
		checkboxVBox.getChildren().add(labelBlank);
		
		return checkboxVBox;
	}
}
