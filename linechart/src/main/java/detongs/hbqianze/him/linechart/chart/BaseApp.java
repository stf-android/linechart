package detongs.hbqianze.him.linechart.chart;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * @author 薛志辉
 * @Date: 2019/7/29
 * @Describe 标定管理
 */
public class BaseApp extends Application {
    private static BaseApp mAppInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        mAppInstance=this;
        initLeakCanary();
    }

    public static synchronized BaseApp getAppInstance() {
        return mAppInstance;
    }

private void initLeakCanary() {
if (LeakCanary.isInAnalyzerProcess(this)) {
return;
}
LeakCanary.install(this);
}

}
