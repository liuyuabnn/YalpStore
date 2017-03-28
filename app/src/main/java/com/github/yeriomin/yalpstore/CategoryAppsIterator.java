package com.github.yeriomin.yalpstore;

import android.util.Log;

import com.github.yeriomin.playstoreapi.DocV2;
import com.github.yeriomin.playstoreapi.ListResponse;
import com.github.yeriomin.yalpstore.model.App;
import com.github.yeriomin.yalpstore.model.AppBuilder;

import java.util.ArrayList;
import java.util.List;

public class CategoryAppsIterator extends AppListIterator {

    public CategoryAppsIterator(com.github.yeriomin.playstoreapi.CategoryAppsIterator iterator) {
        super(iterator);
        this.setCategoryId(iterator.getCategoryId());
    }

    @Override
    public List<App> next() {
        List<App> apps = new ArrayList<>();
        ListResponse response = ((com.github.yeriomin.playstoreapi.CategoryAppsIterator) iterator).next();
        for (DocV2 details : response.getDocList().get(0).getChildList()) {
            App app = AppBuilder.build(details);
            if (shouldSkip(app)) {
                Log.i(this.getClass().getName(), "Skipping non-free/ad-containing app " + app.getPackageName());
            } else {
                apps.add(app);
            }
        }
        return apps;
    }
}
