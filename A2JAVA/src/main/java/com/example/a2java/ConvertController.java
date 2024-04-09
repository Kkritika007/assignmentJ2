package com.example.a2java;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class ConvertController implements Initializable {

    @FXML
    private TextField amountField;

    @FXML
    private Label convertedAmountLabel;

    @FXML
    private ComboBox<String> fromCurrencyComboBox;

    @FXML
    private ComboBox<String> toCurrencyComboBox;

    private Map<String, Double> conversionRates;

    @FXML
    void convertButtonClicked(ActionEvent event) {
        // Get the selected currencies from ComboBoxes
        String fromCurrency = fromCurrencyComboBox.getValue();
        String toCurrency = toCurrencyComboBox.getValue();

        // Get the conversion rates for the selected currencies
        Double fromRate = conversionRates.get(fromCurrency);
        Double toRate = conversionRates.get(toCurrency);

        // Check if conversion rates are not null
        if (fromRate != null && toRate != null) {
            // Convert the amount
            double amount = Double.parseDouble(amountField.getText());
            double convertedAmount = amount * (toRate / fromRate);

            // Display the converted amount
            convertedAmountLabel.setText(String.format("%.2f %s = %.2f %s", amount, fromCurrency, convertedAmount, toCurrency));
        } else {
            // Handle the scenario where conversion rates are null
            convertedAmountLabel.setText("Conversion rate not available for selected currencies.");
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Read data from local JSON file
        try (FileReader reader = new FileReader("apiData.json")) {
            // Parse JSON data using Gson
            Gson gson = new Gson();
            Type type = new TypeToken<ApiResponse>() {
            }.getType();
            ApiResponse apiResponse = gson.fromJson(reader, type);

            // Check if the API response is successful
            if ("success".equals(apiResponse.getResult())) {
                // Populate conversionRates map
                conversionRates = (Map<String, Double>) apiResponse.getConversion_rates();

                // Populate ComboBoxes with currency options
                fromCurrencyComboBox.getItems().addAll(conversionRates.keySet());
                toCurrencyComboBox.getItems().addAll(conversionRates.keySet());

                // Set default selection for ComboBoxes
                fromCurrencyComboBox.setValue("USD");
                toCurrencyComboBox.setValue("EUR");

                // Ensure that the "to" currency ComboBox does not contain the same currency as the "from" currency
                fromCurrencyComboBox.setOnAction(event -> {
                    if (!toCurrencyComboBox.getItems().isEmpty()) {
                        String selectedCurrency = fromCurrencyComboBox.getValue();
                        toCurrencyComboBox.getItems().remove(selectedCurrency);
                    }
                });

                // Ensure that the "from" currency ComboBox does not contain the same currency as the "to" currency
                toCurrencyComboBox.setOnAction(event -> {
                    if (!fromCurrencyComboBox.getItems().isEmpty()) {
                        String selectedCurrency = toCurrencyComboBox.getValue();
                        fromCurrencyComboBox.getItems().remove(selectedCurrency);
                    }
                });

            } else {
                System.err.println("API request was not successful: " + apiResponse.getResult());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}