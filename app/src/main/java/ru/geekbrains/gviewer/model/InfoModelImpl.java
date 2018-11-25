package ru.geekbrains.gviewer.model;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import rx.Observable;
import rx.functions.Func0;
import rx.schedulers.Schedulers;


public class InfoModelImpl implements InfoModel {

    private static final String HTTPS_API_GITHUB_COM_USERS = "https://api.github.com/users/";
    private Throwable throwable;

    private final String user;
    private final OkHttpClient client;

    public InfoModelImpl(@NonNull String user, @NonNull OkHttpClient client) {
        this.user = user;
        this.client = client;
    }

    @Override
    public Observable<String> retrieveInfo() {
        return Observable.defer(() -> {
            Observable<String> result;
            try {
                result = Observable.just(client
                        .newCall(new Request.Builder()
                                .url(HTTPS_API_GITHUB_COM_USERS + user)
                                .build())
                        .execute()
                        .body()
                        .string());
            } catch (IOException e) {
                result = Observable.error(e);
            }
            return result;
        }).subscribeOn(Schedulers.io());


    }

    public Throwable getThrowable() {
        return throwable;
    }
}