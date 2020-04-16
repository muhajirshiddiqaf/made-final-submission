package com.ids.madesubmission4.ui.tv.tvshowdetail;

import com.ids.madesubmission4.base.BaseNavigator;
import com.ids.madesubmission4.data.model.tvshow.TvShow;

public interface DetailTvNavigator extends BaseNavigator {
    void initView(TvShow movie);
}
