package com.example.administrator.eighteenartisan;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import com.example.administrator.eighteenartisan.adapter.BannerAdapter;
import com.example.administrator.eighteenartisan.base.BannerLayout;
import com.example.administrator.eighteenartisan.base.LocalBanner;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.layout_banner)
    BannerLayout layoutBanner;
    @BindView(R.id.layout_banners)
    BannerLayout layoutBanners;
    @BindView(R.id.layout_banner_shequ)
    ViewPager viewPager;
    @BindView(R.id.home_xyd_2)
    Button homeXyd2;
    @BindView(R.id.home_xyd_1)
    Button homeXyd1;
    @BindView(R.id.home_xyd_3)
    Button homeXyd3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        initBanner();
        initBanners();
        initView();
    }

    // 轮播图
    private void initBanner() {

        BannerAdapter<LocalBanner> bannerAdapter = new BannerAdapter<LocalBanner>() {
            @Override
            protected void bind(ViewHolder holder, LocalBanner data) {
                Picasso.with(getApplicationContext()).load(data.getLocalRes()).into(holder.mImageView);
            }
        };
        layoutBanner.setAdapter(bannerAdapter);

        List<LocalBanner> localBanners = new ArrayList<>();
        localBanners.add(new LocalBanner(R.drawable.banner));
        localBanners.add(new LocalBanner(R.drawable.banner));
        localBanners.add(new LocalBanner(R.drawable.banner));
        localBanners.add(new LocalBanner(R.drawable.banner));
        bannerAdapter.reset(localBanners);

    }

    private void initBanners() {

        BannerAdapter<LocalBanner> bannerAdapter = new BannerAdapter<LocalBanner>() {
            @Override
            protected void bind(ViewHolder holder, LocalBanner data) {
                Picasso.with(getApplicationContext()).load(data.getLocalRes()).into(holder.mImageView);
            }
        };
        layoutBanners.setAdapter(bannerAdapter);

        List<LocalBanner> localBanners = new ArrayList<>();
        localBanners.add(new LocalBanner(R.drawable.banner));
        localBanners.add(new LocalBanner(R.drawable.banner));
        localBanners.add(new LocalBanner(R.drawable.banner));
        localBanners.add(new LocalBanner(R.drawable.banner));
        bannerAdapter.reset(localBanners);

    }

    //初始化视图
    private void initView() {
        //viewPager适配器
        viewPager.setAdapter(adapter);
        //viewPager监听->Button的切换
        viewPager.addOnPageChangeListener(listener);
        //首次进入默认选中在线新闻btn
        homeXyd1.setSelected(true);
    }

    //viewPager监听->Button的切换
    private ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            //Button，UI改变
            homeXyd1.setSelected(position == 0);
            homeXyd2.setSelected(position == 1);
            homeXyd3.setSelected(position == 2);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    //viewPager适配器
    private FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
        @Override
        public int getCount() {
            //一共三页，写死3
            return 3;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new HomeFragment();
                case 1:
                    return new HomeFragment();
                case 2:
                    return new HomeFragment();
                default:
                    throw new RuntimeException("未知错误");
            }
        }

    };

    @OnClick({R.id.home_xyd_2, R.id.home_xyd_1, R.id.home_xyd_3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_xyd_1:
                //不要平滑效果，第二个参数传false
                viewPager.setCurrentItem(0,false);
                break;
            case R.id.home_xyd_2:
                //不要平滑效果，第二个参数传false
                viewPager.setCurrentItem(1,false);
                break;
            case R.id.home_xyd_3:
                //不要平滑效果，第二个参数传false
                viewPager.setCurrentItem(2,false);
                break;
            default:
                throw new RuntimeException("未知错误");
        }
    }

}

