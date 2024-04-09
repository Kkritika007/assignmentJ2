package com.example.a2java;

import com.google.gson.annotations.SerializedName;

public class ApiResponse {

    @SerializedName("result")
     private String result;

    @SerializedName("documentation")
     private String documentation;

    @SerializedName("terms_of_use")
    private String terms_of_use;

    private Number time_last_update_unix;

    @SerializedName("time_last_update_utc")
    private String time_last_update_utc;

    private Number time_next_update_unix;

    @SerializedName("time_next_update_utc")
    private String time_next_update_utc;

    @SerializedName("base_code")
    private String base_code;

    @SerializedName("conversion_rates")
    private Object conversion_rates;

    public String getResult() {
        return result;
    }

    public String getDocumentation() {
        return documentation;
    }

    public String getTerms_of_use() {
        return terms_of_use;
    }

    public Number getTime_last_update_unix() {
        return time_last_update_unix;
    }

    public String getTime_last_update_utc() {
        return time_last_update_utc;
    }

    public Number getTime_next_update_unix() {
        return time_next_update_unix;
    }

    public String getTime_next_update_utc() {
        return time_next_update_utc;
    }

    public String getBase_code() {
        return base_code;
    }

    public Object getConversion_rates() {
        return conversion_rates;
    }
}
