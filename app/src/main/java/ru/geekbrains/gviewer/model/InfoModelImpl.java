package ru.geekbrains.gviewer.model;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Func1;

public class InfoModelImpl implements InfoModel {

    private static final String FUBAR = "FUBAR";
    private static final String SUSFU = "SUSFU";
    private static final String BOHICA = "BOHICA";
    private Throwable throwable;

    @Override
    public Observable<String> retrieveInfo() {
        return Observable.timer(1L, TimeUnit.SECONDS)
                .flatMap(new Func1<Long, Observable<String>>() {
                    @Override
                    public Observable<String> call(Long aLong) {
                        Observable<String> result;
                        double random = Math.random();
                        if (random > 0.5 && random < 0.75) {
                            result = Observable.just(FUBAR);
                        } else if (random > 0.75) {
                            result = Observable.just(SUSFU);
                        } else {
                            result = Observable.error(new IllegalStateException(BOHICA));
                        }
                        return result;
                    }
                });
    }

    public Throwable getThrowable() {
        return throwable;
    }
}