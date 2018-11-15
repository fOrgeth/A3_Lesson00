package ru.geekbrains.gviewer.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Func1;
import rx.functions.Func2;

public class InfoModelImpl implements InfoModel {

    private static final String FUBAR = "FUBAR";
    private static final String SUSFU = "SUSFU";
    private static final String BOHICA = "BOHICA";
    private Throwable throwable;

    @Override
    public Observable<List<String>> retrieveInfo() {
        Observable<String> observable1 = Observable.timer(1L, TimeUnit.SECONDS)
                .flatMap(new Func1<Long, Observable<String>>() {
                    @Override
                    public Observable<String> call(Long aLong) {
                        Observable<String> result;
                        double random = Math.random();
                        if (random > 0.25 ) {
                            result = Observable.from(new String[]{"A","B","C","D","E"});

                        } else {
                            result = Observable.error(new IllegalStateException(BOHICA));
                        }
                        return result;
                    }
                });
        Observable<Integer> observable2 = Observable.timer(1L, TimeUnit.SECONDS)
                .flatMap(new Func1<Long, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> call(Long aLong) {
                        return Observable.from(new Integer[]{1,2,3,4,5});
                    }
                });
        return observable1.zipWith(observable2, new Func2<String, Integer, String>() {
            @Override
            public String call(String s, Integer s2) {
                return s + " " + s2;
            }
        }).buffer(3);
    }

    public Throwable getThrowable() {
        return throwable;
    }
}