package ru.geekbrains.gviewer.model.entity;

import android.support.annotation.Nullable;

public class GithubUser {

    private String login;
    private String id;

    @Nullable
    public String getLogin() {
        return login;
    }

    public String getId() {
        return id;
    }
}
