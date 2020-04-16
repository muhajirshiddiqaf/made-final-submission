package com.ids.madesubmission4.ui.tv.tvshow;

import com.ids.madesubmission4.base.BaseNavigator;
import com.ids.madesubmission4.data.model.tvshow.TvShow;

public interface TvShowNavigator extends BaseNavigator {
    void showDetail(TvShow data);

    void loadData();
}
