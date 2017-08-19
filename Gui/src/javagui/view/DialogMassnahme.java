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
	
	public DialogMassnahme(){//constructor�������������һ�£��Ϳ���ֱ���ڱ������ô���˷�������������ϣ�
//		Stage st = new Stage();
//		BorderPane layout = new BorderPane();
		
		VBox vb = new VBox();
		
		Label labelTop = new Label();
		labelTop.setText(" Bestaetigen Sie bitte zutrefende Massnahme(n):");
		labelTop.setFont(new Font(20));
		
		Label labelBlank = new Label();//���һ���հױ�ǩ���ڿ���
		
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
		
		AnchorPane ap =new AnchorPane(vb);//���ܵ�VBoxװ�붨λ���������涨λ����
		AnchorPane.setTopAnchor(vb, 0.0);
		AnchorPane.setLeftAnchor(vb, 10.0);//�����λ������
		AnchorPane.setRightAnchor(vb, 0.0);
		
		ScrollPane sc = new ScrollPane(ap);//������Ķ�λ���װ���϶��������
		sc.setFitToHeight(true);
		
		Scene scene = new Scene(sc, 600, 650);
		
		this.setScene(scene);
		this.initModality(Modality.APPLICATION_MODAL);//����ģ̬
		this.setTitle("Massnahmeauswahl");
		this.centerOnScreen();
		this.setResizable(false);
	}
	
	//���ڽ�observablelist��massZuFehler1��5ת��ΪCheckBox�������������������VBox
	public VBox observablelistToVBox(int n, String fehlerart, ObservableList<String> massZuFehler){
		String s = massZuFehler.toString();
		s = s.replace("[", " ");//��ObservableListת��Ϊ�ַ���ʱϵͳ�Զ���ӵ�[�����滻Ϊ�ո�
		s = s.replace("]", " ");//��ObservableListת��Ϊ�ַ���ʱϵͳ�Զ���ӵ�]�����滻Ϊ�ո�

		String[] arrayF = s.split("LessIsMore,");
		//���ַ���"LessIsMore,"Ϊ�ϵ㽫�ַ���ת��Ϊ����(ע�����еĶ���Ϊ�������ݿ����ʱObservableListִ��add������ϵͳ�Զ���ӵ�)
		arrayF[arrayF.length-1] = arrayF[arrayF.length-1].replace("LessIsMore", "");
		//ȥ�����һ�������ĸ�����ĸ��"LessIsMore"

		VBox checkboxVBox = new VBox(2);
		
		Label labelFehler = new Label();//���Kommentar��ǩ
		labelFehler.setText("zur Fehler" + n + "(" + fehlerart + "):");
		labelFehler.setFont(new Font(18)); 
		checkboxVBox.getChildren().add(labelFehler);
		
		for(int i=0; i<arrayF.length; i++){
			char character = arrayF[i].charAt(1);//ȡ��ǰ�ַ����ĵڶ����ַ�(��Ϊ���ַ����ǿո�)
			int ascii = (int)character;
			if(ascii == 122 || ascii == 32){//�����ַ��ǿո�(����)��������ĸz(����zur)ʱ
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
	
	//���ڽ�������Ҫ������VBox���ֵ����ݶ�װ��ȥ
	public VBox allInVBox(VBox vb){
		VBox checkboxVBox = new VBox(2);
		
		checkboxVBox.getChildren().add(vb);//����֮ǰ��VBox
		
		Label labelKommentar = new Label();//���Kommentar��ǩ
		labelKommentar.setText("Kommentar");
		checkboxVBox.getChildren().add(labelKommentar);
		
		TextArea ta = new TextArea();//���Kommentar�ı���
		ta.setMinHeight(70);//�趨�ı������С�߶�
//		ta.setPrefRowCount(10);
		ta.setWrapText(true);//����������ȥ���ı��������϶���

		checkboxVBox.getChildren().add(ta);
		
		Label labelBlank = new Label();//���һ���հױ�ǩ���ڿ�һ��
		checkboxVBox.getChildren().add(labelBlank);
		
		return checkboxVBox;
	}
}
