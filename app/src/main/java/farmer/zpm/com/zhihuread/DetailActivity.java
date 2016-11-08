package farmer.zpm.com.zhihuread;

import android.database.Observable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.webkit.WebView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import farmer.zpm.com.zhihuread.api.Api;
import farmer.zpm.com.zhihuread.entity.NewsContent;
import farmer.zpm.com.zhihuread.rxbus.RxManager;
import farmer.zpm.com.zhihuread.utils.ImageUtil;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.content_webview)
    WebView webview;
    private int id=-1;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private String imageurl;
    @BindView(R.id.image_bg)
    ImageView imageView;
    RxManager manager=new RxManager();
    private Subscription a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        id=getIntent().getIntExtra("id",-1);
        imageurl= getIntent().getStringExtra("imageurl");
        if (imageurl!=null)
            ImageUtil.loadImg(imageView, imageurl);
        Log.i("ididididi","------"+id);
        if (id!=-1){
             a = Api.getInstance().service.getContentById(id).subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<NewsContent>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e("******", e.getMessage());
                        }

                        @Override
                        public void onNext(NewsContent newsContent) {
                            String content = newsContent.getBody();

                            webview.loadDataWithBaseURL(null, content, "text/html", "utf-8", null);
                            getSupportActionBar().setTitle(newsContent.getTitle());
                            manager.post("a", newsContent);
                        }
                    });
//            manager.add(a);
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_overaction, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) onBackPressed();
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        try {
            return super.dispatchTouchEvent(ev);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        a.unsubscribe();

    }
    @Override
    public void onBackPressed() {
         super.onBackPressed();
    }
}
