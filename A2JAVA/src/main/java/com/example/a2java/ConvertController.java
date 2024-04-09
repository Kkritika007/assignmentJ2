package com.example.a2java;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ConvertController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        ApiResponse response = ApiUtility.getDataFromFile("apiData.json");
    }



    @FXML
    private TextField amountField;

    @FXML
    private Label convertedAmountLabel;

    @FXML
    private ComboBox<?> fromCurrencyComboBox;

    @FXML
    private ComboBox<?> toCurrencyComboBox;

    @FXML
    void convertButtonClicked(ActionEvent event) {

    }

}
