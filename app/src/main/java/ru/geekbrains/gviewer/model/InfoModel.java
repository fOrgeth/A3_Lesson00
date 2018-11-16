package ru.geekbrains.gviewer.model;

import rx.Observable;

public interface InfoModel {
	Observable<String> retrieveInfo();
	Throwable getThrowable();
}