package com.example.lcom76.retrofitdemo.Activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lcom76.retrofitdemo.Adapers.NewsPagerAdapter;
import com.example.lcom76.retrofitdemo.CustomeViews.ReaderViewPagerTransformer;
import com.example.lcom76.retrofitdemo.Fragments.NewTemplate1;
import com.example.lcom76.retrofitdemo.GsonModel.Album;
import com.example.lcom76.retrofitdemo.R;
import com.example.lcom76.retrofitdemo.RealmController.RealmController;
import com.example.lcom76.retrofitdemo.Retrofit.MyApiEndpointInterface;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsActivity extends AppCompatActivity {
    private static final String TAG = "NewsActivity";
    String url = "http://api.androidhive.info/";
    ArrayList<Integer> colors = new ArrayList<>();
    @Bind(R.id.backdrop)
    ImageView backdrop;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.collapsing)
    CollapsingToolbarLayout collapsing;
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.appbar)
    AppBarLayout appbar;
    @Bind(R.id.viewPager)
    ViewPager viewPager;
    @Bind(R.id.scrollView)
    NestedScrollView scrollView;
    @Bind(R.id.design_bottom_sheet)
    NestedScrollView designBottomSheet;
    @Bind(R.id.fabShare)
    FloatingActionButton fabShare;
    private ArrayList<Fragment> fragments;
    private NewsPagerAdapter newsPagerAdapter;
    private BottomSheetBehavior<NestedScrollView> bottomSheetBehavior;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);
        scrollView.setFillViewport(true);

        this.realm = RealmController.with(this).getRealm();
        RealmController.with(this).refresh();
        bottomSheetBehavior =
                BottomSheetBehavior.from(designBottomSheet);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
        fragments = new ArrayList<>();

        Gson gson = new GsonBuilder()
                .setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        return f.getDeclaringClass().equals(RealmObject.class);
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }

                })
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        MyApiEndpointInterface.AlbumApis getAlbums = retrofit.create(MyApiEndpointInterface.AlbumApis.class);

        if (RealmController.getInstance().getAllAlbums() == null || RealmController.getInstance().getAllAlbums().size() == 0) {
            {
                Call<List<Album>> getbooks = getAlbums.getAlbums();

                getbooks.enqueue(new Callback<List<Album>>() {
                    @Override
                    public void onResponse(Call<List<Album>> call, final Response<List<Album>> response) {
                        for (int i = 0; i < response.body().size(); i++) {
                            Album album = response.body().get(i);

                            realm.beginTransaction();
                            realm.copyToRealm(album);
                            realm.commitTransaction();
                            fragments.add(NewTemplate1.newInstance(album.getTimestamp() + "To display a different animation from the default screen slide animation, implement the ViewPager.PageTransformer interface and supply it to the view pager. The interface exposes a single method, transformPage(). At each point in the screen's transition, this method is called once for each visible page (generally there's only one visible page) and for adjacent pages just off the screen. For example, if page three is visible and the user drags towards page four, transformPage() is called for pages two, three, and four at each step of the gesture.\n" +
                                    "\n" +
                                    "In your implementation of transformPage(), you can then create custom slide animations by determining which pages need to be transformed based on the position of the page on the screen, which is obtained from the position parameter of the transformPage() method.\n" +
                                    "\n" +
                                    "The position parameter indicates where a given page is located relative to the center of the screen. It is a dynamic property that changes as the user scrolls through the pages. When a page fills the screen, its position value is 0. When a page is drawn just off the right side of the screen, its position value is 1. If the user scrolls halfway between pages one and two, page one has a position of -0.5 and page two has a position of 0.5. Based on the position of the pages on the screen, you can create custom slide animations by setting page properties with methods such as setAlpha(), setTranslationX(), or setScaleY().\n" +
                                    "\n" +
                                    "When you have an implementation of a PageTransformer, call setPageTransformer() with your implementation to apply your custom animations. For example, if you have a PageTransformer named ZoomOutPageTransformer, you can set your custom animations like this:\n" +
                                    "\n", album.getUrl().getLarge(), album.getName()));
                            //                    newsPagerAdapter.notifyDataSetChanged();

                        }

                        getBitmapFromURL(RealmController.getInstance().getAllAlbums());
                        newsPagerAdapter = new NewsPagerAdapter(NewsActivity.this.getSupportFragmentManager(), fragments, RealmController.getInstance().getAllAlbums());
                        viewPager.setAdapter(newsPagerAdapter);
                        viewPager.setPageTransformer(false, new ReaderViewPagerTransformer(ReaderViewPagerTransformer.TransformType.FLOW));

                        tabLayout.setupWithViewPager(viewPager);
                        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
                        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

                        tabLayout.setAnimation(AnimationUtils.loadAnimation(NewsActivity.this, android.R.anim.fade_in));
                        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                            @Override
                            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                                if (colors.size() > 0 && position < colors.size())

                                    try {
                                        backdrop.setImageDrawable(new ColorDrawable(colors.get(position - 1)));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                            }


                            @Override
                            public void onPageSelected(int position) {

                            }

                            @Override
                            public void onPageScrollStateChanged(int state) {

                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<List<Album>> call, Throwable t) {

                    }
                });
            }

        } else {
            List<Album> albumList = RealmController.getInstance().getAllAlbums();
            for (int i = 0; i < albumList.size(); i++) {
                Album album = albumList.get(i);

                realm.beginTransaction();
                realm.copyToRealm(album);
                realm.commitTransaction();
                fragments.add(NewTemplate1.newInstance(album.getTimestamp() + "To display a different animation from the default screen slide animation, implement the ViewPager.PageTransformer interface and supply it to the view pager. The interface exposes a single method, transformPage(). At each point in the screen's transition, this method is called once for each visible page (generally there's only one visible page) and for adjacent pages just off the screen. For example, if page three is visible and the user drags towards page four, transformPage() is called for pages two, three, and four at each step of the gesture.\n" +
                        "\n" +
                        "In your implementation of transformPage(), you can then create custom slide animations by determining which pages need to be transformed based on the position of the page on the screen, which is obtained from the position parameter of the transformPage() method.\n" +
                        "\n" +
                        "The position parameter indicates where a given page is located relative to the center of the screen. It is a dynamic property that changes as the user scrolls through the pages. When a page fills the screen, its position value is 0. When a page is drawn just off the right side of the screen, its position value is 1. If the user scrolls halfway between pages one and two, page one has a position of -0.5 and page two has a position of 0.5. Based on the position of the pages on the screen, you can create custom slide animations by setting page properties with methods such as setAlpha(), setTranslationX(), or setScaleY().\n" +
                        "\n" +
                        "When you have an implementation of a PageTransformer, call setPageTransformer() with your implementation to apply your custom animations. For example, if you have a PageTransformer named ZoomOutPageTransformer, you can set your custom animations like this:\n" +
                        "\n", album.getUrl().getLarge(), album.getName()));
                //                    newsPagerAdapter.notifyDataSetChanged();

            }

            getBitmapFromURL(RealmController.getInstance().getAllAlbums());
            newsPagerAdapter = new NewsPagerAdapter(NewsActivity.this.getSupportFragmentManager(), fragments, RealmController.getInstance().getAllAlbums());
            viewPager.setAdapter(newsPagerAdapter);
            viewPager.setPageTransformer(false, new ReaderViewPagerTransformer(ReaderViewPagerTransformer.TransformType.FLOW));

            tabLayout.setupWithViewPager(viewPager);
            tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

            tabLayout.setAnimation(AnimationUtils.loadAnimation(NewsActivity.this, android.R.anim.fade_in));
            viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    if (colors.size() > 0 && position < colors.size())

                        try {
                            backdrop.setImageDrawable(new ColorDrawable(colors.get(position - 1)));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                }


                @Override
                public void onPageSelected(int position) {

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_news, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

    public void getBitmapFromURL(final List<Album> albumList) {
//
//        Thread thread = new Thread() {
//            @Override
//            public void run() {
//                for (int i = 0; i < albumList.size(); i++)
//
//                {
//                    try {
//
//                        Realm realm = RealmController.with(NewsActivity.this).getRealm();
//
//                        URL url = new URL(albumList.get(i).getUrl().getSmall());
//                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                        connection.setDoInput(true);
//                        connection.connect();
//                        InputStream input = connection.getInputStream();
//                        Bitmap bitmap = BitmapFactory.decodeStream(input);
//
//
//                        final int finalI = i;
//                        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
//                            public void onGenerated(Palette p) {
//                                if (p != null)
//                                    Log.d(TAG, "onGenerated: " + p.getDarkMutedColor(0xFFFFFF));
//                                colors.add(p.getDarkMutedColor(0xFFFFFFF));
//                                View view = getLayoutInflater().inflate(R.layout.tab_item, null);
//                                TextView textView = (TextView) view.findViewById(android.R.id.text1);
//                                textView.setTextColor(p.getDarkMutedColor(0xFFFFFFF));
//                                tabLayout.getTabAt(finalI).setCustomView(view);
//                            }
//                        });
//                    } catch (IOException e) {
//                        // Log exception
//                    }
//                }
//
//            }
//        };
//
//        thread.start();

    }

    @OnClick(R.id.fabShare)
    public void onClick() {
//        bottomSheetBehavior.setPeekHeight(300);
//        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);    }
    }
}
