package farmer.zpm.com.zhihuread.api;



import farmer.zpm.com.zhihuread.entity.News;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/3/23.
 */
public interface ApiService {
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @GET("4/news/latest")
    Observable<News> login();

}
