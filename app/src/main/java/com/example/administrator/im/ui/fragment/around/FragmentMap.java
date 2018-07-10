package com.example.administrator.im.ui.fragment.around;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.MyLocationStyle;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.im.R;
import com.example.administrator.im.base.BaseFragment;
import com.example.administrator.im.view.banner.MZBannerView;
import com.example.administrator.im.view.banner.creator.MZHolderCreator;
import com.example.administrator.im.view.banner.creator.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/7/9.
 */

public class FragmentMap extends BaseFragment implements AMap.OnMyLocationChangeListener, LocationSource, AMapLocationListener, View.OnClickListener {
    private RecyclerView ry_map;
    private MapView mMapView = null;
    private AMap aMap;
    LocationSource.OnLocationChangedListener mListener;
    AMapLocationClient mlocationClient;
    AMapLocationClientOption mLocationOption;
    private MZBannerView mMZBanner;
    private NestedScrollView scrollview;
    private Toolbar toolbar;
    //    private View views;
    private int mHeight;
    private BottomSheetDialog bottomSheetDialog;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_main_map;
    }

    @Override
    protected void setUpView(View view, Bundle savedInstanceState) {
        isStatusBar(false);
        mMapView = (MapView) view.findViewById(R.id.map);
        bottomSheetDialog = new BottomSheetDialog(getContext());

//        views = view.findViewById(R.id.view);
        toolbar = view.findViewById(R.id.toolbar);
        scrollview = view.findViewById(R.id.scrollView);
        ry_map = view.findViewById(R.id.ry_map);
        mMapView.onCreate(savedInstanceState);
        mMZBanner = (MZBannerView) view.findViewById(R.id.banner_map);
    }

    public class BannerViewHolder implements MZViewHolder<String> {
        private ImageView mImageView;

        @Override
        public View createView(Context context) {
            // 返回页面布局
            View view = LayoutInflater.from(context).inflate(R.layout.banner_item, null);
            mImageView = (ImageView) view.findViewById(R.id.banner_image);
            return view;
        }

        @Override
        public void onBind(Context context, int position, String data) {
            // 数据绑定
            Glide.with(getContext()).load(data).asBitmap().into(mImageView);
        }
    }

    @Override
    protected void setUpData() {
        BottomSheetBehavior behavior = BottomSheetBehavior.from(scrollview);
        behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
//        if (behavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
//            behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
//        } else {
//            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
//        }
//        mHeight = views.getHeight();


        ry_map.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<String> lis = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            lis.add("条目：" + i);
        }
        TestAdapter a = new TestAdapter(lis);
        ry_map.setNestedScrollingEnabled(false);
        ry_map.setAdapter(a);
        List<String> list = new ArrayList<>();
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1531800547&di=6ec95f37fc156ffa85e9e1f6514560e7&imgtype=jpg&er=1&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F013d5b56fe13946ac725794803ca4e.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1531800547&di=6ec95f37fc156ffa85e9e1f6514560e7&imgtype=jpg&er=1&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F013d5b56fe13946ac725794803ca4e.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1531800547&di=6ec95f37fc156ffa85e9e1f6514560e7&imgtype=jpg&er=1&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F013d5b56fe13946ac725794803ca4e.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1531800547&di=6ec95f37fc156ffa85e9e1f6514560e7&imgtype=jpg&er=1&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F013d5b56fe13946ac725794803ca4e.jpg");
        mMZBanner.setPages(list, new MZHolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });
        mlocationClient = new AMapLocationClient(getActivity());
        mlocationClient = new AMapLocationClient(getActivity());
        aMap = mMapView.getMap();
        if (aMap == null) {
            aMap = mMapView.getMap();
        }
        MyLocationStyle myLocationStyle;
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.getUiSettings().setMyLocationButtonEnabled(false);
        aMap.getUiSettings().setZoomControlsEnabled(false);
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);
//        myLocationStyle.myLocationIcon(BitmapDescriptorFactory
//                .fromBitmap(UsagePresenter.convertViewToBitmap(inflater.inflate(R.layout.map_location_marker,null))));
        myLocationStyle.strokeColor(Color.argb(0, 0, 0, 0));
        myLocationStyle.radiusFillColor(Color.argb(0, 0, 0, 0));
        aMap.setMyLocationStyle(myLocationStyle);
        aMap.getUiSettings().setLogoBottomMargin(-50);
        mlocationClient.setLocationOption(mLocationOption);
        mlocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation amapLocation) {
                if (mListener != null && amapLocation != null) {
                    if (amapLocation != null) {
                        mListener.onLocationChanged(amapLocation);// 显示系统小蓝点
                        SharedPreferences.Editor editor = getActivity().getSharedPreferences("location", Context.MODE_PRIVATE).edit();
                        editor.putString("latitude", String.valueOf(amapLocation.getLatitude()));
                        editor.putString("longitude", String.valueOf(amapLocation.getLongitude()));
                        editor.putString("location", amapLocation.getAddress());
                        editor.apply();
                    } else {
                        Log.e("AmapErr", "定位错误" + amapLocation.getErrorCode());
                    }
                }
            }
        });
        aMap.setOnMyLocationChangeListener(new AMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location amapLocation) {

            }
        });
        mlocationClient.startLocation();
        CameraUpdate cameraUpdate = CameraUpdateFactory.zoomTo(19);
        mMapView.getMap().moveCamera(cameraUpdate);
    }

    private class TestAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public TestAdapter(List<String> data) {
            super(R.layout.test, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.tv, item);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        super.onDestroy();
        mMapView.onDestroy();
        if (null != mlocationClient) {
            mlocationClient.onDestroy();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }

    /**
     * 激活定位
     */
    @Override
    public void activate(OnLocationChangedListener listener) {
        mListener = listener;
        if (mlocationClient == null) {
            //初始化定位
            mlocationClient = new AMapLocationClient(getActivity());
            //初始化定位参数
            mLocationOption = new AMapLocationClientOption();
            //设置定位回调监听
            mlocationClient.setLocationListener(this);
            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mlocationClient.startLocation();//启动定位
        }
    }

    /**
     * 停止定位
     */
    @Override
    public void deactivate() {
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onMyLocationChange(Location location) {

    }
}
