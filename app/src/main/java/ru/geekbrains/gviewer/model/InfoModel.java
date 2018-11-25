package ru.geekbrains.gviewer.model;

import java.util.List;

import ru.geekbrains.gviewer.model.entity.GithubUser;
import rx.Observable;

public interface InfoModel {
	Observable<GithubUser> retrieveInfo();
	Throwable getThrowable();
}