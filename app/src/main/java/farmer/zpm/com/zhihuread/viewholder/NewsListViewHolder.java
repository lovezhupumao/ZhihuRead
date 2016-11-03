package farmer.zpm.com.zhihuread.viewholder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import farmer.zpm.com.zhihuread.R;

/**
 * Created by Administrator on 2016/11/2 0002.
 */
public class NewsListViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.image)
    public ImageView imageView;

    @BindView(R.id.tv_title)
    public TextView tv_title;
    @BindView(R.id.tv_des)
    public TextView tv_des;
    @BindView(R.id.tv_info)
    public TextView tv_info;
    @BindView(R.id.tv_time)
    public TextView tv_time;
    @BindView(R.id.item_card)
    public CardView cardView;
    public NewsListViewHolder(View itemView) {
        super(itemView);
        if (((ViewGroup) itemView).getChildCount() > 0)
            ButterKnife.bind(this, itemView);
    }
}
