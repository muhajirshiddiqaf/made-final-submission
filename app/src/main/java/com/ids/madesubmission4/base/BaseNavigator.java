package com.ids.madesubmission4.base;

public interface BaseNavigator {
    void onSuccess();

    void onError();

    void showLoading();

    void hideLoading();

    void showMessage(String msg);

}
