package ru.geekbrains.gviewer.presenter;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.ArrayList;
import java.util.List;

import ru.geekbrains.gviewer.model.InfoModel;
import ru.geekbrains.gviewer.view.InfoView;
import rx.Subscription;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class InfoPresenterImpl extends MvpBasePresenter<InfoView> implements InfoPresenter {

    private final InfoModel model;
    private Subscription subscription;

    public InfoPresenterImpl(InfoModel model) {
        this.model = model;
    }

    @Override
    public void loadInformation(final boolean pullToRefresh) {
        getView().showLoading(pullToRefresh);
        subscription = model.retrieveInfo().observeOn(AndroidSchedulers.mainThread()).subscribe(s-> {
                if (isViewAttached()) {
                    if (s != null) {
                        InfoView infoView = getView();
                        infoView.setData(s);
                        infoView.showContent();
                    }
                }

        }, throwable -> {
            InfoView iv = getView();
            List<String> errList = new ArrayList<>();
            errList.add("empty string");
            iv.setData(errList);
            iv.showContent();
//                getView().showError(throwable, pullToRefresh);
        });
    }

    private void showError(Throwable e, boolean pullToRefresh) {
        getView().showError(e, pullToRefresh);
    }

    @Override
    public void detachView(boolean retainInstance) {
        if (!retainInstance && !subscription.isUnsubscribed() && subscription != null) {
            subscription.unsubscribe();
            subscription = null;
        }
        super.detachView(retainInstance);
    }
}