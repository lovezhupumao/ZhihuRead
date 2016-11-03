package farmer.zpm.com.zhihuread.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import farmer.zpm.com.zhihuread.DetailActivity;
import farmer.zpm.com.zhihuread.R;
import farmer.zpm.com.zhihuread.entity.News;
import farmer.zpm.com.zhihuread.utils.ImageUtil;
import farmer.zpm.com.zhihuread.viewholder.NewsListViewHolder;

/**
 * Created by Administrator on 2016/11/2 0002.
 */
public class RecycleViewAdapter extends RecyclerView.Adapter<NewsListViewHolder> {
    private News news;
    private Context context;
    private int itemslength =0;
    public RecycleViewAdapter(News news,Context context) {
        this.news = news;
        this.context=context;
        itemslength=news.getTop_stories().size()+news.getTop_stories().size();
    }

    @Override
    public NewsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(context).inflate(R.layout.list_item_card_main,parent,false);
        NewsListViewHolder holder=new NewsListViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(NewsListViewHolder holder, int position) {
        if (position>=news.getTop_stories().size()) {
            ImageUtil.loadImg(holder.imageView, news.getStories().get(position).getImages().get(0));
            holder.tv_title.setText(news.getStories().get(position).getTitle());
            holder.tv_time.setText(news.getDate());
            holder.tv_des.setText("");
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent =new Intent(context, DetailActivity.class);
                    intent.putExtra("id",news.getStories().get(position).getId());
                    context.startActivity(intent);
                }
            });
        }
        else {
            ImageUtil.loadImg(holder.imageView, news.getTop_stories().get(position).getImage());
            holder.tv_title.setText(news.getTop_stories().get(position).getTitle());
            holder.tv_time.setText(news.getDate());
            holder.tv_des.setText("头条XX");
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent =new Intent(context, DetailActivity.class);
                    intent.putExtra("id",news.getTop_stories().get(position).getId());
                    context.startActivity(intent);
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return itemslength;
    }


}
