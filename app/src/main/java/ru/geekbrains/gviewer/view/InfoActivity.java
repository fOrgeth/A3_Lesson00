package ru.geekbrains.gviewer.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceActivity;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.MvpLceViewStateActivity;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.RetainingLceViewState;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import ru.geekbrains.gviewer.R;
import ru.geekbrains.gviewer.model.InfoModelImpl;
import ru.geekbrains.gviewer.presenter.InfoPresenter;
import ru.geekbrains.gviewer.presenter.InfoPresenterImpl;

public class InfoActivity extends MvpLceViewStateActivity<TextView, String, InfoView, InfoPresenter>
        implements InfoView, SwipeRefreshLayout.OnRefreshListener {

    private static final String UNKNOWN_ERROR_MESSAGE = "Unknown error";
    private static final String NATIONALSECURITYAGENCY = "nationalsecurityagency";

    //    private TextView contentField;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setContentView(R.layout.screen_info);
        swipeRefreshLayout = findViewById(R.id.swipe_container);
        swipeRefreshLayout.setOnRefreshListener(this);


//        contentField = (TextView) findViewById(R.id.text_field);
//        contentView.setOnRefreshListener(this);
//        loadData(false);
    }

    @NonNull
    @Override
    public InfoPresenter createPresenter() {
        return new InfoPresenterImpl(new InfoModelImpl());
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        String errorMessage = e.getMessage();
        return errorMessage == null ? UNKNOWN_ERROR_MESSAGE : errorMessage;
    }

    @Override
    public void setData(String data) {
        contentView.setText(data);
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        getPresenter().loadInformation(false);
    }

    @Override
    public LceViewState<String, InfoView> createViewState() {
        return new RetainingLceViewState<>();
    }

    @Override
    public String getData() {
        return contentView.getText().toString();
    }

//    @Override
//    protected void animateContentViewIn() {
//        super.animateContentViewIn();
//    }

    @Override
    public void onRefresh() {
        loadData(true);
        swipeRefreshLayout.setRefreshing(false);
//        contentView.setRefreshing(false);
    }

    @Override
    public void showLoading(boolean pullToRefresh) {
    }
}
