package com.LesnoyTishka.StatusChecker.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class DefaultStatusChecker extends AbstractStatusChecker {
    private String title = "CryptoWallet";
    private String url = "http://localhost:8189";
    private int lastStatus = 0;
    private String lastChangeStatus = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date());

    private HttpURLConnection connection;

    public DefaultStatusChecker() {
    }

    public DefaultStatusChecker(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public void updateStatus(int status) {
        if (status != lastStatus) {
            lastChangeStatus = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date());
            lastStatus = status;
        }
    }

    public void checkStatus() {
        int responseCode;

        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("HEAD");
            responseCode = connection.getResponseCode();
            statusDescription = "connection success";
        } catch (ConnectException connectException) {
            responseCode = 0;
            statusDescription = "connection lost";
        } catch (Exception e) {
            responseCode = 0;
            statusDescription = "unknown error";
        }

        updateStatus(responseCode);
    }
}

