package ru.geekbrains.gviewer.presenter;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.ArrayList;
import java.util.List;

import ru.geekbrains.gviewer.model.InfoModel;
import ru.geekbrains.gviewer.view.InfoView;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class InfoPresenterImpl extends MvpBasePresenter<InfoView> implements InfoPresenter {

    private final InfoModel model;

    public InfoPresenterImpl(InfoModel model) {
        this.model = model;
    }

    @Override
    public void loadInformation(final boolean pullToRefresh) {
        getView().showLoading(pullToRefresh);
        model.retrieveInfo().observeOn(Schedulers.immediate()).subscribe(new Action1<List<String>>() {
            @Override
            public void call(List<String> s) {
                if (isViewAttached()) {
                    if (s != null) {
                        InfoView infoView = getView();
                        infoView.setData(s);
                        infoView.showContent();
                    }
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                InfoView iv = getView();
                List<String> errList = new ArrayList<>();
                errList.add("empty string");
                iv.setData(errList);
                iv.showContent();
//                getView().showError(throwable, pullToRefresh);s
            }
        });
    }

    private void showError(Throwable e, boolean pullToRefresh) {
        getView().showError(e, pullToRefresh);
    }

    /*@Override
    public void detachView(boolean retainInstance) {
        if(!retainInstance&&)
        super.detachView(retainInstance);
    }*/
}