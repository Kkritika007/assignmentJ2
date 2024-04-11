package com.example.a2java;

import com.google.gson.annotations.SerializedName;
import java.util.Map;

public class ApiResponse {

    // Serialized names for fields when serializing/deserializing JSON
    @SerializedName("result")
    private String result;

    @SerializedName("documentation")
    private String documentation;

    @SerializedName("terms_of_use")
    private String terms_of_use;

    // Unix timestamp of the last update time
    private Number time_last_update_unix;

    @SerializedName("time_last_update_utc")
    private String time_last_update_utc;

    // Unix timestamp of the next update time
    private Number time_next_update_unix;

    @SerializedName("time_next_update_utc")
    private String time_next_update_utc;

    // Base currency code
    @SerializedName("base_code")
    private String base_code;

    // Conversion rates from the base currency to other currencies
    @SerializedName("conversion_rates")
    private Map<String, Double> conversion_rates;

    // Getter method for 'result'
    public String getResult() {
        return result;
    }

    // Getter method for 'documentation'
    public String getDocumentation() {
        return documentation;
    }

    // Getter method for 'terms_of_use'
    public String getTerms_of_use() {
        return terms_of_use;
    }

    // Getter method for 'time_last_update_unix'
    public Number getTime_last_update_unix() {
        return time_last_update_unix;
    }

    // Getter method for 'time_last_update_utc'
    public String getTime_last_update_utc() {
        return time_last_update_utc;
    }

    // Getter method for 'time_next_update_unix'
    public Number getTime_next_update_unix() {
        return time_next_update_unix;
    }

    // Getter method for 'time_next_update_utc'
    public String getTime_next_update_utc() {
        return time_next_update_utc;
    }

    // Getter method for 'base_code'
    public String getBase_code() {
        return base_code;
    }

    // Getter method for 'conversion_rates'
    public Map<String, Double> getConversion_rates() {
        return conversion_rates;
    }
}
