package farmer.zpm.com.zhihuread;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.webkit.WebView;

import butterknife.BindView;
import butterknife.ButterKnife;
import farmer.zpm.com.zhihuread.api.Api;
import farmer.zpm.com.zhihuread.entity.NewsContent;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.content_webview)
    WebView webview;
    private int id=-1;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        id=getIntent().getIntExtra("id",-1);
        Log.i("ididididi","------"+id);
        if (id!=-1){
            Api.getInstance().service.getContentById(id).subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<NewsContent>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e("******",e.getMessage());
                        }

                        @Override
                        public void onNext(NewsContent newsContent) {
                            String content=newsContent.getBody();
                            Log.i("---",content);
                            webview.loadDataWithBaseURL(null,content,"text/html", "utf-8",null);
                            getSupportActionBar().setTitle(newsContent.getTitle());
                        }
                    });
        }

    }
}
