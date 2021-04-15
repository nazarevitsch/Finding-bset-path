package com.bida.testtask.service;

import com.bida.testtask.domain.DeliveryBoyConfig;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.io.FileReader;

@Service
public class DeliveryBoyService {

    public DeliveryBoyConfig readFromJSON(){
        DeliveryBoyConfig deliveryBoyConfig = null;
        Gson gson = new Gson();
        try {
            deliveryBoyConfig = gson.fromJson(new FileReader("src/main/resources/config.json"), DeliveryBoyConfig.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  deliveryBoyConfig;
    }
}
