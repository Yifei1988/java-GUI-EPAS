package javagui.view;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TreeCell;
import javafx.scene.layout.HBox;

public class CustomCell extends TreeCell<String> {
    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        // If the cell is empty or 这一单元的值是空格(这种情况下它的长度必然小于4，一般是2或者3) we don't show anything.
        if (this.isEmpty()||this.getItem().length()<4) {
            setGraphic(null);
            setText(null);
        } else {
            // We only show the custom cell if it is a leaf, meaning it has
            // no children.
            if (this.getTreeItem().isLeaf()) {

                // A custom HBox that will contain your check box, label 
                HBox cellBox = new HBox(1);

                CheckBox checkBox = new CheckBox();
                Label label = new Label(item);

                // Here we bind the pref height of the label to the height of the checkbox. This way the label and the checkbox will have the same size. 
                label.prefHeightProperty().bind(checkBox.heightProperty());

                cellBox.getChildren().addAll(checkBox, label);
                
       /*         checkBox.selectedProperty().addListener((obs, unSelected, Selected) -> {
                	if (Selected) {
                		
                        // add to checkedTree, will take all sub-nodes with it...
                        checkedTreeRoot.getChildren().add(item);
                    } else {
                    }
                });
*/
                // We set the cellBox as the graphic of the cell.
                setGraphic(cellBox);
                setText(null);
            } else {
                // If this is the root we just display the text.
                setGraphic(null);
                setText(item);
            }
        }
    }
}
