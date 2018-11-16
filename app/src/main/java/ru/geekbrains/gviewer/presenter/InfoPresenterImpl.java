package ru.geekbrains.gviewer.presenter;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import ru.geekbrains.gviewer.model.InfoModel;
import ru.geekbrains.gviewer.view.InfoView;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class InfoPresenterImpl extends MvpBasePresenter<InfoView> implements InfoPresenter {

    private final InfoModel model;

    public InfoPresenterImpl(InfoModel model) {
        this.model = model;
    }

    @Override
    public void loadInformation(final boolean pullToRefresh) {
        getView().showLoading(pullToRefresh);
        model.retrieveInfo().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                if (isViewAttached()) {
                    if (s != null) {
                        InfoView infoView = getView();
                        infoView.setData(s);
                        infoView.showContent();
                    } else {
                        showError(model.getThrowable(), pullToRefresh);
                    }
                }
            }
        });
    }

    private void showError(Throwable e, boolean pullToRefresh) {
        getView().showError(e, pullToRefresh);
    }
}