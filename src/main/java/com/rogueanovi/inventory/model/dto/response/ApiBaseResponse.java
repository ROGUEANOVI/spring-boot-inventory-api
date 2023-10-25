package com.rogueanovi.inventory.model.dto.response;

import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;

@Data
public class ApiBaseResponse {
    private ArrayList<HashMap<String, String>> metadata = new ArrayList<>();

    private Object data;

    public void setMetadata( String code,String status, String message) {
        HashMap<String,String> map = new HashMap<>();
        map.put("code", code);
        map.put("status", status);
        map.put("message", message);
        metadata.add(map);
    }
}
