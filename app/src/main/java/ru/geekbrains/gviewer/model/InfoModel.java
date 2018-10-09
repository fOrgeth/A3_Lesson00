package ru.geekbrains.gviewer.model;

public interface InfoModel {
	void retrieveInfo(MyAction<String> onNext);
	Throwable getThrowable();
}