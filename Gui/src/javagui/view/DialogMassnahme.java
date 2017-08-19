package javagui.view;

import java.io.File;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DialogMassnahme extends Stage{
	//public static ArrayList<String> massVorschlag = null;
	public static String massVorschlag;
	private static String F1[][];
	private static String F2[][];
	private static String F3[][];
	private static String F4[][];
	private static String F5[][];
	
	public DialogMassnahme(){//constructor�������������һ�£��Ϳ���ֱ���ڱ������ô���˷�������������ϣ�
		VBox vb = new VBox();
		
		Label labelTop = new Label();
		labelTop.setText(" Bestaetigen Sie bitte zutrefende Massnahme(n):");
		labelTop.setFont(new Font(20));
		
		Label labelBlank = new Label();//���һ���հױ�ǩ���ڿ���
		
		vb.getChildren().add(labelTop);
		vb.getChildren().add(labelBlank);
		
		if(InterfaceController.fehlerart1 != null){			
			TreeView<String> tree;
			tree = observablelistToTree(1,InterfaceController.fehlerart1,
					InterfaceController.massZuFehler1Dialog);
			vb.getChildren().add(allInVBox(tree));
		}
		if(InterfaceController.fehlerart2 != null){			
			TreeView<String> tree;
			tree = observablelistToTree(2,InterfaceController.fehlerart2,
					InterfaceController.massZuFehler2Dialog);
			vb.getChildren().add(allInVBox(tree));
		}
		if(InterfaceController.fehlerart3 != null){			
			TreeView<String> tree;
			tree = observablelistToTree(3,InterfaceController.fehlerart3,
					InterfaceController.massZuFehler3Dialog);
			vb.getChildren().add(allInVBox(tree));
		}
		if(InterfaceController.fehlerart4 != null){			
			TreeView<String> tree;
			tree = observablelistToTree(4,InterfaceController.fehlerart4,
					InterfaceController.massZuFehler4Dialog);
			vb.getChildren().add(allInVBox(tree));
		}
		if(InterfaceController.fehlerart5 != null){			
			TreeView<String> tree;
			tree = observablelistToTree(5,InterfaceController.fehlerart5,
					InterfaceController.massZuFehler5Dialog);
			vb.getChildren().add(allInVBox(tree));
		}
		
		//����DialogMassnahme��Ja��ť��ͼƬ
		File url_Ja = new File("resources/images/button_Ja.png");
        Image imageJa = new Image(url_Ja.toURI().toString());
        ImageView imageViewJa = new ImageView(imageJa);
        imageViewJa.setFitHeight(20);
        imageViewJa.setFitWidth(45);
        
      //����DialogMassnahme��Nein��ť��ͼƬ
        File url_Nein = new File("resources/images/button_Nein.png");
        Image imageNein = new Image(url_Nein.toURI().toString());
        ImageView imageViewNein = new ImageView(imageNein);
        imageViewNein.setFitHeight(20);
        imageViewNein.setFitWidth(45);
        
        Button btnJa = new Button("", imageViewJa);
        Button btnNein = new Button("", imageViewNein);
        
        btnJa.setOnAction(new EventHandler<ActionEvent>(){//��ֵmassZuFehlerVorschlag���ر�DialogMassnahme
        	@Override
        	public void handle(ActionEvent event) {
        		
 /*       		printlnArray(F1);
        		printlnArray(F2);
        		printlnArray(F3);
        		printlnArray(F4);
        		printlnArray(F5);*/
//        		Node  source = (Node)  event.getSource();
//        		Stage stage  = (Stage) source.getScene().getWindow();
//        		stage.getOnCloseRequest().handle(null);
        		
        	/*	switch(InterfaceController.faceSeNum){
        		case(0):break;
        		case(1):InterfaceController.massVorschlagFace1 = massVorschlag;break;
        		case(2):InterfaceController.massVorschlagFace2 = massVorschlag;break;
        		case(3):InterfaceController.massVorschlagFace3 = massVorschlag;break;
        		case(4):InterfaceController.massVorschlagFace4 = massVorschlag;break;
        		case(5):InterfaceController.massVorschlagFace5 = massVorschlag;break;
        		}
        		System.out.println(massVorschlag);*/
        		close();
//        		stage.close();
        	}
        });
        
        btnNein.setOnAction(new EventHandler<ActionEvent>(){//�ر�DialogMassnahme
        	@Override
        	public void handle(ActionEvent event) {
        		close();
        	}
        });
        
        Label lb = new Label("  ");//��һ���հ�Label�������Ja��Nein������ť
        lb.setVisible(false);
		HBox hb = new HBox(btnJa,lb,btnNein);
		hb.setAlignment(Pos.CENTER_RIGHT);//��HBox��Ϊ�Ҷ���
		vb.getChildren().add(hb);
		
		AnchorPane ap =new AnchorPane(vb);//���ܵ�VBoxװ�붨λ���������涨λ����
		AnchorPane.setTopAnchor(vb, 0.0);
		AnchorPane.setLeftAnchor(vb, 10.0);//���������
		AnchorPane.setRightAnchor(vb, 0.0);
		AnchorPane.setBottomAnchor(vb, 10.0);//���ױ�����
		
		ScrollPane sc = new ScrollPane(ap);//������Ķ�λ���װ���϶��������

		sc.setFitToHeight(true);
		
		Scene scene = new Scene(sc, 680, 650);
		sc.getStylesheets().add("@../../../bin/javagui/view/tree.css");//����TreeView��CSS�ļ�
		
		this.setScene(scene);
		this.initModality(Modality.APPLICATION_MODAL);//����ģ̬
		this.setTitle("Massnahmeauswahl");
		this.centerOnScreen();
		this.setResizable(false);
	}
	
	//���ڽ�observablelist��massZuFehler1��5ת��ΪCheckBox�������������������VBox
	public TreeView<String> observablelistToTree(int n, String fehlerart, ObservableList<String> massZuFehler){

		String s = massZuFehler.toString();
		s = s.replace("[", " ");//��ObservableListת��Ϊ�ַ���ʱϵͳ�Զ���ӵ�[�����滻Ϊ�ո�
		s = s.replace("]", " ");//��ObservableListת��Ϊ�ַ���ʱϵͳ�Զ���ӵ�]�����滻Ϊ�ո�
		
		VBox checkboxVBox = new VBox(2);
		
		Label labelFehler = new Label();//���Fehler��ǩ
		labelFehler.setText("zur Fehler" + n + "(" + fehlerart + "):");
		labelFehler.setFont(new Font(18)); 
		checkboxVBox.getChildren().add(labelFehler);
		
//		String sMssVorschlag = "zur Fehler" + n + "(" + fehlerart + "):";
		
//		System.out.println(s);
		String[] arrayU = s.split("Xiong,");
		arrayU[arrayU.length-1] = arrayU[arrayU.length-1].replace("Xiong", "");
		
		String[][] F;
		F = new String[arrayU.length][];//ȷ����ά����F�ĵ�1ά����
		
		for(int j=0; j<arrayU.length; j++){
			String stringU = arrayU[j];
//			System.out.println(stringU);
			String[] arrayM = stringU.split("LessIsMore,");
			arrayM[arrayM.length-1] = arrayM[arrayM.length-1].replace("LessIsMore", "");
			F[j] = new String[arrayM.length];//ȷ����ά����F�ĵ�2ά����

			for(int i=0; i<arrayM.length; i++){
				F[j][i] = arrayM[i];
			}//��ά���鸳ֵ
		}
//		System.out.println(F.length);
		
		TreeView<String> tree;
		TreeItem <String> rootF = new TreeItem<String>();//����
		rootF.setExpanded(true);
		
		int h = 0;//��������
		for(int j=0; j<F.length; j++){
			TreeItem<String> treeU;
			treeU = makeBranch(F[j][0],rootF);//����ά������ÿ�е�һ�е�Ԫ��(��zur Ursache������)����Ϊһ�����
			for(int i=1; i<F[j].length; i++){
				h++;
				makeBranch(F[j][i],treeU);//����ά������ÿ�е�һ���Ժ��Ԫ������Ϊ�������
			}
		}
		
		tree = new TreeView<>(rootF);
		tree.setShowRoot(false);
		tree.setBackground(null);
		
		tree.setMinWidth(660);
		tree.setMaxHeight(h*33);//�˹�����Ӧ��С��hΪ����������30����Ϊÿ�д�Լ��30
		tree.setMinHeight(h*33);
		
		tree.setCellFactory(e -> new CustomCell());

		switch(n){
		case(1):setArrayF1(F);break;
		case(2):setArrayF2(F);break;
		case(3):setArrayF3(F);break;
		case(4):setArrayF4(F);break;
		case(5):setArrayF5(F);break;
		}
		
	//	checkboxVBox.getChildren().add(tree);
	//	return checkboxVBox;
		return tree;
	}
	
	public void setArrayF1(String F[][]){
		F1 = new String[F.length][];//ȷ����ά����F�ĵ�1ά����
		for(int j=0; j<F.length; j++){
			F1[j] = new String[F[j].length];//ȷ����ά����F�ĵ�2ά����
			for(int i=0; i<F[j].length; i++){F1[j][i] = F[j][i];}
		}
	}
	public void setArrayF2(String F[][]){
		F2 = new String[F.length][];//ȷ����ά����F�ĵ�1ά����
		for(int j=0; j<F.length; j++){
			F2[j] = new String[F[j].length];//ȷ����ά����F�ĵ�2ά����
			for(int i=0; i<F[j].length; i++){F2[j][i] = F[j][i];}
		}
	}
	public void setArrayF3(String F[][]){
		F3 = new String[F.length][];//ȷ����ά����F�ĵ�1ά����
		for(int j=0; j<F.length; j++){
			F3[j] = new String[F[j].length];//ȷ����ά����F�ĵ�2ά����
			for(int i=0; i<F[j].length; i++){F3[j][i] = F[j][i];}
		}
	}
	public void setArrayF4(String F[][]){
		F4 = new String[F.length][];//ȷ����ά����F�ĵ�1ά����
		for(int j=0; j<F.length; j++){
			F4[j] = new String[F[j].length];//ȷ����ά����F�ĵ�2ά����
			for(int i=0; i<F[j].length; i++){F4[j][i] = F[j][i];}
		}
	}
	public void setArrayF5(String F[][]){
		F5 = new String[F.length][];//ȷ����ά����F�ĵ�1ά����
		for(int j=0; j<F.length; j++){
			F5[j] = new String[F[j].length];//ȷ����ά����F�ĵ�2ά����
			for(int i=0; i<F[j].length; i++){F5[j][i] = F[j][i];}
		}
	}
	
	public TreeItem<String> makeBranch(String title, TreeItem<String> parent){
		TreeItem<String> item = new TreeItem<>(title);
		item.setExpanded(true);
		parent.getChildren().add(item);
		return item;
	}
	public void printlnArray(String Fn[][]){
		System.out.println("+++++++++++++++++++++");
		if(Fn != null){
			for(int j=0; j<Fn.length; j++){
				for(int i=0; i<Fn[j].length; i++){
					System.out.println(Fn[j][i]);
				}
			}
		}
		System.out.println("=====================");
	}
	
	//���ڽ�������Ҫ������VBox���ֵ����ݶ�װ��ȥ
	public VBox allInVBox(TreeView<String> tree){
		VBox checkboxVBox = new VBox(0);
		
		checkboxVBox.getChildren().add(tree);//����֮ǰ����
		
		Label labelKommentar = new Label();//���Kommentar��ǩ
		labelKommentar.setText("Kommentar");
		checkboxVBox.getChildren().add(labelKommentar);
		
		TextArea ta = new TextArea();//���Kommentar�ı���
		ta.setMinHeight(80);//�趨�ı������С�߶�
//		ta.setPrefRowCount(10);
		ta.setWrapText(true);//����������ȥ���ı��������϶���

		checkboxVBox.getChildren().add(ta);
		
		Label labelBlank1 = new Label();//���һ���հױ�ǩ���ڿ�һ��(�߶�15)
		labelBlank1.setMinHeight(15);
		Separator separline = new Separator();
		separline.setStyle("-fx-background-color: Black;");
		Label labelBlank2 = new Label();//���һ���հױ�ǩ���ڿ�һ��(�߶�20)
		labelBlank2.setMinHeight(20);
		checkboxVBox.getChildren().addAll(labelBlank1,separline,labelBlank2);
		
		return checkboxVBox;
	}
}
