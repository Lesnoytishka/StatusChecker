package com.LesnoyTishka.StatusChecker.entities;

import lombok.Data;

import java.net.HttpURLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public abstract class AbstractStatusChecker {
    protected boolean checkerStatus = true;
    protected String title;
    protected String url;
    protected int lastStatus = 0;
    protected String statusDescription = "";
    protected HttpURLConnection connection;

    protected String lastChangeStatus = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date());

    public AbstractStatusChecker() {
    }

    public abstract void updateStatus(int status);

    public abstract void checkStatus();
}
