package farmer.zpm.com.zhihuread;

import android.animation.AnimatorSet;
import android.animation.FloatEvaluator;
import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.id_toolbar)
    Toolbar toolbar;
    @BindView(R.id.layout)
    LinearLayout layout;
    @OnClick(R.id.enter)
    public void enter(){
        Intent intent=new Intent(MainActivity.this,HomeActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        ValueAnimator animator= ObjectAnimator.ofFloat(layout,"translationY",-240f);
        animator.setStartDelay(1000);

        animator.setEvaluator(new FloatEvaluator());
        animator.setRepeatCount(0);
        animator.setRepeatMode(ObjectAnimator.REVERSE);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(2000);
        animator.setStartDelay(1000);
        animator.start();

    }
    class MyEvaluator extends LinearInterpolator {
        @Override
        public float getInterpolation(float input) {
            return (float) (Math.sin(input*Math.PI-Math.PI/2)+1)/2;
        }
    }
}
