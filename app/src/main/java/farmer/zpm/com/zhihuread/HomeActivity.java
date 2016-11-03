package farmer.zpm.com.zhihuread;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import farmer.zpm.com.zhihuread.adapter.FragmentAdapter;
import farmer.zpm.com.zhihuread.fragment.HomeFragment;

public class HomeActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawerlayout)
    DrawerLayout drawerLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.tabs)
    TabLayout tabs;
    private String[] mTabs = {"民谣", "摇滚", "电子", "流行", "爵士", "独立", "故事", "新世纪", "精品推荐", "原声", "乐评", "影评", "古典", "游记"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("主页");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.syncState();
        drawerLayout.setDrawerListener(mDrawerToggle);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(HomeFragment.newInstance("vh","111"));
        fragments.add(HomeFragment.newInstance("vh","222"));
        fragments.add(HomeFragment.newInstance("vh","333"));
        fragments.add(HomeFragment.newInstance("vh","444"));
        fragments.add(HomeFragment.newInstance("vh","555"));
        fragments.add(HomeFragment.newInstance("vh","666"));
        fragments.add(HomeFragment.newInstance("vh","777"));
        fragments.add(HomeFragment.newInstance("vh","888"));
        viewpager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), fragments, Arrays.asList(mTabs)));
        tabs.setupWithViewPager(viewpager);
        tabs.setTabsFromPagerAdapter(viewpager.getAdapter());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_overaction, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(Gravity.LEFT)) drawerLayout.closeDrawers();
        else super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            drawerLayout.openDrawer(GravityCompat.START);
        return super.onOptionsItemSelected(item);
    }

}
