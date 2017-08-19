package javagui.view;

import java.io.File;
import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DialogMassnahme extends Stage {
	private static ArrayList<String> massVorschlag;

	private static TreeView<String> tree1;
	private static TreeView<String> tree2;
	private static TreeView<String> tree3;
	private static TreeView<String> tree4;
	private static TreeView<String> tree5;
	
	private static ArrayList<String> MaList_F1 = new ArrayList<String>();//����Default
	private static ArrayList<String> MaList_F2 = new ArrayList<String>();
	private static ArrayList<String> MaList_F3 = new ArrayList<String>();
	private static ArrayList<String> MaList_F4 = new ArrayList<String>();
	private static ArrayList<String> MaList_F5 = new ArrayList<String>();
	
	private static TextArea ta1;
	private static TextArea ta2;
	private static TextArea ta3;
	private static TextArea ta4;
	private static TextArea ta5;
	
	private static String kmt1 = new String();//����Default
	private static String kmt2 = new String();
	private static String kmt3 = new String();
	private static String kmt4 = new String();
	private static String kmt5 = new String();
	
	private static String fehlerChangeCheck_old;
	
	
//	public static ObservableList<String> massVorschlag = null;

	public DialogMassnahme() {// constructor�������������һ�£��Ϳ���ֱ���ڱ������ô���˷�������������ϣ�
		VBox vb = new VBox();

		Label labelTop = new Label();
		labelTop.setText(" Bestaetigen Sie bitte zutrefende Massnahme(n):");
		labelTop.setFont(new Font(20));

		Separator separline = new Separator();// ����һ���ָ���
		separline.setStyle("-fx-background-color: Black;");

		Label labelBlank = new Label();// ����һ���հױ�ǩ���ڿ���

		vb.getChildren().add(labelTop);
		vb.getChildren().add(separline);
		vb.getChildren().add(labelBlank);
		
		String fehlerChangeCheck_new = InterfaceController.fehlerart1 
				+ InterfaceController.fehlerart2
				+ InterfaceController.fehlerart3
				+ InterfaceController.fehlerart4
				+ InterfaceController.fehlerart5;
		if(fehlerChangeCheck_old !=null){//���Ի����ǰ��ȱ�����Ͳ�һ��(���ⲻ�ǶԻ����һ�δ�)������֮ǰ��MaList��kmt
			if (!fehlerChangeCheck_new.equals(fehlerChangeCheck_old)){
				MaListClear();
				KMTsetNull();
				}
		}
		fehlerChangeCheck_old = fehlerChangeCheck_new;

		if (InterfaceController.fehlerart1 != null) {
			// TreeView<String> tree;
			tree1 = observablelistToTree(1, InterfaceController.fehlerart1, InterfaceController.massZuFehler1Dialog, MaList_F1);
			vb.getChildren().add(allInVBox(tree1, 1));
		}
		if (InterfaceController.fehlerart2 != null) {
			// TreeView<String> tree;
			tree2 = observablelistToTree(2, InterfaceController.fehlerart2, InterfaceController.massZuFehler2Dialog, MaList_F2);
			vb.getChildren().add(allInVBox(tree2, 2));
		}
		if (InterfaceController.fehlerart3 != null) {
			// TreeView<String> tree;
			tree3 = observablelistToTree(3, InterfaceController.fehlerart3, InterfaceController.massZuFehler3Dialog, MaList_F3);
			vb.getChildren().add(allInVBox(tree3, 3));
		}
		if (InterfaceController.fehlerart4 != null) {
			// TreeView<String> tree;
			tree4 = observablelistToTree(4, InterfaceController.fehlerart4, InterfaceController.massZuFehler4Dialog, MaList_F4);
			vb.getChildren().add(allInVBox(tree4, 4));
		}
		if (InterfaceController.fehlerart5 != null) {
			// TreeView<String> tree;
			tree5 = observablelistToTree(5, InterfaceController.fehlerart5, InterfaceController.massZuFehler5Dialog, MaList_F5);
			vb.getChildren().add(allInVBox(tree5, 5));
		}

		// ����DialogMassnahme��Ja��ť��ͼƬ
		File url_Ja = new File("resources/images/button_Ja.png");
		Image imageJa = new Image(url_Ja.toURI().toString());
		ImageView imageViewJa = new ImageView(imageJa);
		imageViewJa.setFitHeight(20);
		imageViewJa.setFitWidth(45);

		// ����DialogMassnahme��Nein��ť��ͼƬ
		File url_Nein = new File("resources/images/button_Nein.png");
		Image imageNein = new Image(url_Nein.toURI().toString());
		ImageView imageViewNein = new ImageView(imageNein);
		imageViewNein.setFitHeight(20);
		imageViewNein.setFitWidth(45);

		Button btnJa = new Button("", imageViewJa);
		Button btnNein = new Button("", imageViewNein);

		btnJa.setOnAction(new EventHandler<ActionEvent>() {// ��ֵmassVorschlag���ر�DialogMassnahme
			@Override
			public void handle(ActionEvent event) {
				massVorschlag = new ArrayList<String>();

				MaListClear();//�ڹرնԻ���ǰ��ʼ��5����̬�����ַ���MaList
				KMTsetNull();//�ڹرնԻ���ǰ��ʼ��5����̬�ַ���kmt
				
				if (InterfaceController.fehlerart1 != null) {
					getInfoToString(tree1,1);
				}
				if (InterfaceController.fehlerart2 != null) {
					getInfoToString(tree2,2);
				}
				if (InterfaceController.fehlerart3 != null) {
					getInfoToString(tree3,3);
				}
				if (InterfaceController.fehlerart4 != null) {
					getInfoToString(tree4,4);
				}
				if (InterfaceController.fehlerart5 != null) {
					getInfoToString(tree5,5);
				}
				
				System.out.println(MaList_F1);
				System.out.println(MaList_F2);
				System.out.println(MaList_F3);
				System.out.println(MaList_F4);
				System.out.println(MaList_F5);
				
				//��û������һ�������ϲ��Ŀ���????
				String s = massVorschlag.toString();
				s = s.replace("[", "");// ��ArrayListת��Ϊ�ַ���ʱϵͳ�Զ����ӵ�[����ȥ��
				s = s.replace("]", "");// ��ArrayListת��Ϊ�ַ���ʱϵͳ�Զ����ӵ�]����ȥ��
				String[] arrayMV = s.split("Yifei, ");
				arrayMV[arrayMV.length - 1] = arrayMV[arrayMV.length - 1].replace("Yifei", "");

				InterfaceController.massVorschlagForTxt = new String[arrayMV.length];//ȷ�����鳤�Ȳ�ʵ����
				for(int i=0; i<arrayMV.length;i++){
					InterfaceController.massVorschlagForTxt[i] = arrayMV[i];
				}//�������ַ���massVorschlag�����ݸ�ֵ������massVorschlagForTxt�����ڴ�ӡ��.txt
	//			System.out.println(arrayMV.length);
				close();
			}
		});

		btnNein.setOnAction(new EventHandler<ActionEvent>() {// �ر�DialogMassnahme
			@Override
			public void handle(ActionEvent event) {
				close();
			}
		});

		Label lb = new Label("  ");// ��һ���հ�Label�������Ja��Nein������ť
		lb.setVisible(false);
		HBox hb = new HBox(btnJa, lb, btnNein);
		hb.setAlignment(Pos.CENTER_RIGHT);// ��HBox��Ϊ�Ҷ���
		vb.getChildren().add(hb);

		AnchorPane ap = new AnchorPane(vb);// ���ܵ�VBoxװ�붨λ���������涨λ����
		AnchorPane.setTopAnchor(vb, 0.0);
		AnchorPane.setLeftAnchor(vb, 10.0);// ���������
		AnchorPane.setRightAnchor(vb, 15.0);// ���Ҳ�����
		AnchorPane.setBottomAnchor(vb, 10.0);// ���ױ�����

		ScrollPane sc = new ScrollPane(ap);// ������Ķ�λ���װ���϶��������
		ap.setStyle("-fx-background-color:#A9D0F5;");

		sc.setFitToHeight(true);

		sc.getStylesheets().add("@../../../bin/javagui/view/dialog_mass.css");// ����TreeView��ScrollBar��CSS�ļ�
		sc.setStyle("-fx-background-color:#A9D0F5;"
				+ "-fx-background-radius: 10 10 10 10;"
				+ "-fx-border-color: white;"
				+ "-fx-border-radius: 10 10 10 10;" 
				+ "-fx-border-width: 5;");

		Scene scene = new Scene(sc, 690, 670);

		this.setScene(scene);
		this.initModality(Modality.APPLICATION_MODAL);// ����ģ̬
		this.setTitle("Massnahmeauswahl");
		this.centerOnScreen();
		this.setResizable(false);
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// ���ڽ�observablelist��massZuFehler1��5ת��ΪTree
	public TreeView<String> observablelistToTree(int n, String fehlerart,
			ObservableList<String> massZuFehler, ArrayList<String> MaList) {

		String s = massZuFehler.toString();
		s = s.replace("[", " ");// ��ObservableListת��Ϊ�ַ���ʱϵͳ�Զ����ӵ�[�����滻Ϊ�ո�
		s = s.replace("]", " ");// ��ObservableListת��Ϊ�ַ���ʱϵͳ�Զ����ӵ�]�����滻Ϊ�ո�

		// System.out.println(s);
		String[] arrayU = s.split("Xiong,");
		arrayU[arrayU.length - 1] = arrayU[arrayU.length - 1].replace("Xiong", "");

		String[][] F;
		F = new String[arrayU.length][];// ȷ����ά����F�ĵ�1ά����

		for (int j = 0; j < arrayU.length; j++) {
			String stringU = arrayU[j];
			// System.out.println(stringU);
			String[] arrayM = stringU.split("LessIsMore,");
			arrayM[arrayM.length - 1] = arrayM[arrayM.length - 1].replace("LessIsMore", "");
			F[j] = new String[arrayM.length];// ȷ����ά����F�ĵ�2ά����

			for (int i = 0; i < arrayM.length; i++) {
				F[j][i] = arrayM[i];
			} // ��ά���鸳ֵ
		}
		// System.out.println(F.length);

		TreeView<String> tree;
		CheckBoxTreeItem<String> rootF = new CheckBoxTreeItem<>("zur Fehler" + n + "(" + fehlerart + "):");// ����
		// Label labelFehler = new Label();//����Fehler��ǩ
		// labelFehler.setText("zur Fehler" + n + "(" + fehlerart + "):");
		// labelFehler.setFont(new Font(18));
		// rootF.setGraphic(labelFehler);//������ʾFehler��
		rootF.setExpanded(true);

		int h = 1;//����tree����������1��ʼ��Ϊrootռλ
		int x = 0;//���ڵ����ַ���MaList��Ԫ��
		for (int j = 0; j < F.length; j++) {
			CheckBoxTreeItem<String> treeU;
			treeU = makeBranch(F[j][0], rootF);// ����ά������ÿ�е�һ�е�Ԫ��(��zur Ursache������)����Ϊһ�����
			
			for (int i = 1; i < F[j].length; i++) {
				if (F[j][i].length() > 4) {// ��ֵ���ǿո�(�ո���ĳ��ȱ�ȻС��4��һ����2����3)
					h++;
					CheckBoxTreeItem<String> treeM;
					treeM = makeBranch(F[j][i], treeU);// ����ά������ÿ�е�һ���Ժ��Ԫ������Ϊ�������
					if(MaList.size() != 0){
						if(MaList.get(x) == "S"){treeM.setSelected(true);}
						x++;
					}
				}
			}
		}///////////////////////////////////////////////////////////////////////////////////////////////////////////////

		tree = new TreeView<>(rootF);
		tree.setCellFactory(CheckBoxTreeCell.<String> forTreeView());
		// tree.setShowRoot(false);//����ʾ����
		tree.setBackground(null);

		tree.setMinWidth(660);
		tree.setMaxHeight(h * 35);// �˹�����Ӧ��С��hΪ����������35����Ϊÿ�д�Լ��35
		tree.setMinHeight(h * 35);

		return tree;
	}

	public CheckBoxTreeItem<String> makeBranch(String title, CheckBoxTreeItem<String> parent) {//�������
		CheckBoxTreeItem<String> item = new CheckBoxTreeItem<String>(title);
		item.setExpanded(true);
		parent.getChildren().add(item);
		return item;
	}

	// ���ڽ�������Ҫ������VBox���ֵ����ݶ�װ��ȥ
	public VBox allInVBox(TreeView<String> tree, int Fn) {
		VBox checkboxVBox = new VBox(0);

		checkboxVBox.getChildren().add(tree);// ����֮ǰ����

		Label labelKommentar = new Label();// ����Kommentar��ǩ
		labelKommentar.setText("Kommentar");
		labelKommentar.setFont(new Font(15));
		checkboxVBox.getChildren().add(labelKommentar);
		
		switch (Fn){
		case (1): 
			ta1 = new TextArea();// ����Kommentar�ı���
		    ta1.setMinHeight(80);// �趨�ı������С�߶�
		    ta1.setWrapText(true);// ����������ȥ���ı��������϶���
		    checkboxVBox.getChildren().add(ta1);
		    if(kmt1 != null){ta1.setText(kmt1);}
		    break;
		case (2): 
			ta2 = new TextArea();// ����Kommentar�ı���
		    ta2.setMinHeight(80);// �趨�ı������С�߶�
		    ta2.setWrapText(true);// ����������ȥ���ı��������϶���
		    checkboxVBox.getChildren().add(ta2);
		    if(kmt2 != null){ta2.setText(kmt2);}
		    break;
		case (3): 
			ta3 = new TextArea();// ����Kommentar�ı���
		    ta3.setMinHeight(80);// �趨�ı������С�߶�
		    ta3.setWrapText(true);// ����������ȥ���ı��������϶���
		    checkboxVBox.getChildren().add(ta3);
		    if(kmt3 != null){ta3.setText(kmt3);}
		    break;
		case (4): 
			ta4 = new TextArea();// ����Kommentar�ı���
		    ta4.setMinHeight(80);// �趨�ı������С�߶�
		    ta4.setWrapText(true);// ����������ȥ���ı��������϶���
		    checkboxVBox.getChildren().add(ta4);
		    if(kmt4 != null){ta4.setText(kmt4);}
		    break;
		case (5): 
			ta5 = new TextArea();// ����Kommentar�ı���
		    ta5.setMinHeight(80);// �趨�ı������С�߶�
		    ta5.setWrapText(true);// ����������ȥ���ı��������϶���
		    checkboxVBox.getChildren().add(ta5);
		    if(kmt5 != null){ta5.setText(kmt5);}
		    break;
		}

		Label labelBlank1 = new Label();// ����һ���հױ�ǩ���ڿ�һ��(�߶�15)
		labelBlank1.setMinHeight(15);
		Separator separline = new Separator();// ����һ���ָ���
		separline.setStyle("-fx-background-color: Black;");
		Label labelBlank2 = new Label();// ����һ���հױ�ǩ���ڿ�һ��(�߶�20)
		labelBlank2.setMinHeight(20);
		checkboxVBox.getChildren().addAll(labelBlank1, separline, labelBlank2);

		return checkboxVBox;
	}
	
	private void getInfoToString(TreeView<String> tree, int Fn) {
		System.out.println(tree.getRoot().getValue());
		massVorschlag.add(tree.getRoot().getValue() + "Yifei");//¼����������
		getSelectedCB(tree.getRoot(), Fn);//������������¼���ѹ�ѡ�����
		
		massVorschlag.add("Kommentar: " + "Yifei");
		switch(Fn){
		case (1):
			massVorschlag.add(ta1.getText() + "Yifei");
		    kmt1 = ta1.getText();
		    break;
		case (2):
			massVorschlag.add(ta2.getText() + "Yifei");
	        kmt2 = ta2.getText();
	        break;
		case (3):
			massVorschlag.add(ta3.getText() + "Yifei");
            kmt3 = ta3.getText();
            break;
		case (4):
			massVorschlag.add(ta4.getText() + "Yifei");
            kmt4 = ta4.getText();
            break;
		case (5):
			massVorschlag.add(ta5.getText() + "Yifei");
            kmt5 = ta5.getText();
            break;
		}
		massVorschlag.add("Yifei");//Ϊ�˿�һ�и�������Fehler
	}
	private void getSelectedCB(TreeItem<String> root, int Fn) {
		// System.out.println("�ѽ���getSelectedCB����");
		for (TreeItem<String> treeitem : root.getChildren()) {
			if ((treeitem.getChildren()).isEmpty()) {//�������û�������(��Ҷ��)���ж����޹�ѡ
				String SorU;
				if (((CheckBoxTreeItem<String>) treeitem).isSelected()) {
					System.out.println(treeitem.getValue());
					massVorschlag.add(treeitem.getValue() + "Yifei");
					SorU = "S";//�й�ѡ
				}
				else {SorU = "U";}//�޹�ѡ
				switch(Fn){
				case (1):MaList_F1.add(SorU);break;
				case (2):MaList_F2.add(SorU);break;
				case (3):MaList_F3.add(SorU);break;
				case (4):MaList_F4.add(SorU);break;
				case (5):MaList_F5.add(SorU);break;
				}
				
			} else {//��������������(�����)���ж����޹�ѡ���ѡ
				if (((CheckBoxTreeItem<String>) treeitem).isSelected()) {
					System.out.println(treeitem.getValue());
					massVorschlag.add(treeitem.getValue() + "Yifei");
				} else if (((CheckBoxTreeItem<String>) treeitem).isIndeterminate()) {
					System.out.println(treeitem.getValue());
					massVorschlag.add(treeitem.getValue() + "Yifei");
				}
				getSelectedCB(treeitem, Fn);
			}
		}
	}
	
	private void MaListClear(){//���ü�¼����ѡMassnahme��Ŀ�������ַ���MaList
		MaList_F1.clear();
		MaList_F2.clear();
		MaList_F3.clear();
		MaList_F4.clear();
		MaList_F5.clear();
	}
	private void KMTsetNull(){//���ü�¼����ѡKommentar���ݵ��ַ���kmt
		kmt1 = null;
		kmt2 = null;
		kmt3 = null;
		kmt4 = null;
		kmt5 = null;
	}
}