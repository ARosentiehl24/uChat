package com.arrg.android.app.uchat.entities;

import java.util.Map;

public class User {

    private String email;
    private boolean isOnLine;
    private Map<String, Boolean> contacts;

    public static final boolean ONLINE = true;
    public static final boolean OFFLINE = false;

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isOnLine() {
        return isOnLine;
    }

    public void setOnLine(boolean onLine) {
        isOnLine = onLine;
    }

    public Map<String, Boolean> getContacts() {
        return contacts;
    }

    public void setContacts(Map<String, Boolean> contacts) {
        this.contacts = contacts;
    }
}
