package ru.geekbrains.gviewer.model;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.Random;

public class InfoModelImpl implements InfoModel {

    private Throwable throwable;
    private Random random = new Random();

    @Override
    public void retrieveInfo(final MyAction<String> onNext) {
        new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void... params) {
                try {
                    Thread.sleep(1500);
                    if (random.nextBoolean()) {
                        throw new IOException();
                    }
                } catch (InterruptedException | IOException e) {
                    throwable = e;
                    e.printStackTrace();
                    return null;
                }
                return "Foo Bar " + (int) (Math.random() * 100);
            }

            @Override
            protected void onPostExecute(String s) {
                onNext.onDownloadCallback(s);
            }
        }.execute();
    }

    public Throwable getThrowable() {
        return throwable;
    }
}