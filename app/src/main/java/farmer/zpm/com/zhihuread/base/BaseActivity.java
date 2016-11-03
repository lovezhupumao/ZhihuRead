package farmer.zpm.com.zhihuread.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;

import butterknife.ButterKnife;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.Utils;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityBase;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityHelper;

/**
 * Created by Administrator on 2016/10/30 0030.
 */
public abstract class BaseActivity extends AppCompatActivity implements SwipeBackActivityBase {

    private SwipeBackActivityHelper mHelper;
    private SwipeBackLayout mSwipeBackLayout;

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (mHelper!=null)
        this.mHelper.onPostCreate();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mHelper = new SwipeBackActivityHelper(this);
        this.mHelper.onActivityCreate();
        mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeSize(200);
        //设定滑动关闭的方向，SwipeBackLayout.EDGE_ALL表示向下、左、右滑动均可。EDGE_LEFT，EDGE_RIGHT，EDGE_BOTTOM
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        this.setContentView(this.getLayoutId());
        ButterKnife.bind(this);
        this.initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
    @Override
    public void onResume(){
        overridePendingTransition(Animation.INFINITE, Animation.INFINITE);
        super.onResume();
    }
    @Override
    public void setContentView(int layoutResID) {
            super.setContentView(layoutResID);
    }

    @Override
    public SwipeBackLayout getSwipeBackLayout() {
        return  this.mHelper.getSwipeBackLayout();
    }

    @Override
    public void setSwipeBackEnable(boolean enable) {
        this.getSwipeBackLayout().setEnableGesture(enable);
    }

    @Override
    public void scrollToFinishActivity() {
        Utils.convertActivityToTranslucent(this);
        this.getSwipeBackLayout().scrollToFinishActivity();
    }

    //    private View getContainer() {
//
//        return container;
//    }
    public abstract int getLayoutId();

    public abstract void initView();

}
