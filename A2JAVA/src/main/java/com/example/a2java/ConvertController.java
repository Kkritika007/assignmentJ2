package com.example.a2java;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
    private List<String> currencyHistory;

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
            String conversionResult = String.format("%.2f %s = %.2f %s", amount, fromCurrency, convertedAmount, toCurrency);
            convertedAmountLabel.setText(conversionResult);

            // Add the conversion result to history
            String historyEntry = String.format("%s to %s: %.2f", fromCurrency, toCurrency, convertedAmount);
            currencyHistory.add(historyEntry);
        } else {
            // Handle the scenario where conversion rates are null
            convertedAmountLabel.setText("Conversion rate not available for selected currencies.");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize currency history list
        currencyHistory = new ArrayList<>();

        // Read data from local JSON file
        try (FileReader reader = new FileReader("apiData.json")) {
            // Parse JSON data using Gson
            Gson gson = new Gson();
            Type type = new TypeToken<ApiResponse>() {}.getType();
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

    @FXML
    void viewHistoryButtonClicked(ActionEvent event) {
        try {
            // Load the History view
            FXMLLoader loader = new FXMLLoader(getClass().getResource("history.fxml"));
            Parent root = loader.load();

            // Get the controller associated with the History view
            HistoryController historyController = loader.getController();

            // Pass currency history data to the HistoryController
            historyController.setCurrencyHistory(currencyHistory);

            // Set up the stage and display the History view
            Stage stage = new Stage();
            stage.setTitle("Currency Conversion History");
            stage.setScene(new Scene(root));
            stage.getIcons().add(new Image("file:src/main/resources/com/example/a2java/cclogo.jpg"));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to save currency history to a local JSON file
    private void saveCurrencyHistoryToFile() {
        try (FileWriter writer = new FileWriter("currencyHistory.json")) {
            // Convert currency history list to JSON and write to file
            Gson gson = new Gson();
            gson.toJson(currencyHistory, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
