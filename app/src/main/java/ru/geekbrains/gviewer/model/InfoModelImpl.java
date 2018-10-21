package ru.geekbrains.gviewer.model;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Func1;

public class InfoModelImpl implements InfoModel {

    private static final String FUBAR = "FUBAR";
    private Throwable throwable;

    @Override
    public Observable<String> retrieveInfo() {
        return Observable.timer(1L, TimeUnit.SECONDS)
                .map(new Func1<Long, String>() {
                    @Override
                    public String call(Long aLong) {
                        return FUBAR;
                    }
                });
    }

    public Throwable getThrowable() {
        return throwable;
    }
}