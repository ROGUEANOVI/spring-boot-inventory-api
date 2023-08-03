package com.rogueanovi.inventory.response;

import java.util.ArrayList;
import java.util.HashMap;

public class ApiBaseResponse {
    private ArrayList<HashMap<String, String>> metadata = new ArrayList<>();

    public ArrayList<HashMap<String, String>> getMetadata() {
        return metadata;
    }

    public void setMetadata(String state, String code, String message) {
        HashMap<String,String> map = new HashMap<>();
        map.put("state", state);
        map.put("code", code);
        map.put("message", message);
        metadata.add(map);
    }
}
