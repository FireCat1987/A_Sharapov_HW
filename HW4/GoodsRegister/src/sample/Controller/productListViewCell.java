package sample.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;
import sample.Model.Product;

import java.io.IOException;


public class productListViewCell extends ListCell<Product> {
    @FXML
    private Label productId;
    @FXML
    private Label productName;
    @FXML
    private Label productPrice;
    @FXML
    private GridPane paneProduct;
    private FXMLLoader mLLoaderProduct;
    @Override
    protected void updateItem(Product product, boolean empty) {
        super.updateItem(product, empty);
        if(empty || product == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (mLLoaderProduct == null) {
                mLLoaderProduct = new FXMLLoader(getClass().getResource("../view/productItem.fxml"));
                mLLoaderProduct.setController(this);
                try {
                    mLLoaderProduct.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            productId.setText(String.valueOf(product.getProductId()));
            productName.setText(product.getProductName());
            productPrice.setText(product.getProductPrice().toString());
            setText(null);
            setGraphic(paneProduct);
        }

    }
}

