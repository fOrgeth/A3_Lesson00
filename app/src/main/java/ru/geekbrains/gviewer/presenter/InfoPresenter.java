package ru.geekbrains.gviewer.presenter;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;

import ru.geekbrains.gviewer.view.InfoView;

public interface InfoPresenter extends MvpPresenter<InfoView> {
	void loadInformation(boolean pullToRefresh);
}