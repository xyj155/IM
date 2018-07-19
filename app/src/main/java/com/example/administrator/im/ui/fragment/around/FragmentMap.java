package com.example.administrator.im.ui.fragment.around;

import android.Manifest;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.im.R;
import com.example.administrator.im.base.BaseFragment;
import com.example.administrator.im.contract.FragmentMapContract;
import com.example.administrator.im.gson.Active;
import com.example.administrator.im.gson.UserGson;
import com.example.administrator.im.presenter.FragmentMapPresenter;
import com.example.administrator.im.util.ToastUtil;
import com.example.administrator.im.view.AppleDialog;
import com.example.administrator.im.view.CircleImageView;

import java.util.List;

import static com.example.administrator.im.R.id.map;

/**
 * Created by Administrator on 2018/7/9.
 */

public class FragmentMap extends BaseFragment implements FragmentMapContract.View, AMapLocationListener {
    private RecyclerView ry_map;
    private MapView mMapView = null;
    private AMap aMap;

    AMapLocationClient mlocationClient;
    AMapLocationClientOption mLocationOption;
    private NestedScrollView scrollview;
    private BottomSheetDialog bottomSheetDialog;
    private FragmentMapContract.Presenter presenter = new FragmentMapPresenter(this);
    private Dialog dialog;
    private BitmapDescriptor bitmapDescriptor;



    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_main_map;
    }

    @Override
    protected void setUpView(View view, Bundle savedInstanceState) {
        isStatusBar(false);
        mMapView = (MapView) view.findViewById(map);
        bottomSheetDialog = new BottomSheetDialog(getContext());
        scrollview = view.findViewById(R.id.scrollView);
        ry_map = view.findViewById(R.id.ry_map);
        mMapView.onCreate(savedInstanceState);
    }


    @Override
    protected void setUpData() {

        BottomSheetBehavior behavior = BottomSheetBehavior.from(scrollview);
        behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        ry_map.setLayoutManager(new LinearLayoutManager(getActivity()));
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }
        mLocationOption = new AMapLocationClientOption();
        aMap = mMapView.getMap();
        if (aMap == null) {
            aMap = mMapView.getMap();
        }
        mlocationClient = new AMapLocationClient(getActivity());
        MyLocationStyle myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.getUiSettings().setMyLocationButtonEnabled(false);
        aMap.getUiSettings().setZoomControlsEnabled(false);
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromBitmap(convertViewToBitmap(View.inflate(getActivity(), R.layout.map_location_marker, null))));
        myLocationStyle.strokeColor(Color.argb(0, 0, 0, 0));
        myLocationStyle.radiusFillColor(Color.argb(0, 0, 0, 0));
        aMap.setMyLocationStyle(myLocationStyle);
        aMap.getUiSettings().setLogoBottomMargin(-50);
        mLocationOption.setNeedAddress(true);
        mLocationOption.setOnceLocation(true);
        mLocationOption.setOnceLocationLatest(true);
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mlocationClient.setLocationOption(mLocationOption);
        mlocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation == null) {
                    ToastUtil.showToastWarning("定位错误");
                } else {
                    CameraUpdate cameraUpdate = CameraUpdateFactory.zoomTo(19);
                    mMapView.getMap().moveCamera(cameraUpdate);
                    aMap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude())));
                    aMap.setMyLocationEnabled(true);
                    presenter.Location(aMapLocation.getCity());
                    Log.i(TAG, "onLocationChanged: " + aMapLocation.getCity());
                }
                Log.i(TAG, "onLocationChanged: " + aMapLocation.getCity());
            }
        });
        mlocationClient.setLocationOption(mLocationOption);
        mlocationClient.startLocation();//启动定位
        CameraUpdate cameraUpdate = CameraUpdateFactory.zoomTo(19);
        mMapView.getMap().moveCamera(cameraUpdate);
    }


    @Override
    public void showMessage(String message) {
        dialog = AppleDialog.createLoadingDialog(getActivity(), message);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {
        dialog.dismiss();
    }

    @Override
    public void queryLocation(List<UserGson<Active>> data) {
        AroundAdapter aroundAdapter = new AroundAdapter(data);
        ry_map.setAdapter(aroundAdapter);
        LatLngBounds.Builder boundsBuilder = new LatLngBounds.Builder();//存放所有点的经纬度
        for (int i = 0; i < data.size(); i++) {
            addMarker(data.get(i));
            boundsBuilder.include(new LatLng(data.get(i).getLatintude(), data.get(i).getLongtitude()));//把所有点都include进去（LatLng类型）
        }
        aMap.animateCamera(CameraUpdateFactory.newLatLngBounds(boundsBuilder.build(), 15));//第二个参数为四周留空宽度
    }


    private static final String TAG = "FragmentMap";

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {

    }


    private class AroundAdapter extends BaseQuickAdapter<UserGson<Active>, BaseViewHolder> {


        public AroundAdapter(List<UserGson<Active>> data) {
            super(R.layout.map_ry_item_around, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, UserGson<Active> item) {
            CircleImageView convertView = helper.getView(R.id.img_user_head);
            Glide.with(getContext()).load(item.getUserhead()).asBitmap().into(convertView);
            helper.setText(R.id.tv_username, item.getUsername())
                    .setText(R.id.tv_age, item.getAge() + "岁")
                    .setText(R.id.tv_location, item.getLocation());
            TextView tvVip = helper.getView(R.id.tv_vip);
            if (item.getVip() == 1) {
                tvVip.setVisibility(View.VISIBLE);
            }
            if (item.getActive() != null) {
                TextView tvActive = helper.getView(R.id.tv_active);
                tvActive.setVisibility(View.VISIBLE);
                helper.setText(R.id.tv_active, "最新动态：" + item.getActive().getContent());
            }
            Log.i(TAG, "convert: " + item);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
        if (null != mlocationClient) {
            mlocationClient.onDestroy();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }


    /**
     * func:view转bitmap
     *
     * @param view
     * @return
     */
    public static Bitmap convertViewToBitmap(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        return bitmap;
    }

    /**
     * func:定制化marker的图标
     *
     * @param url
     * @param listener
     */
    private void customizeMarkerIcon(String url, final OnMarkerIconLoadListener listener) {
        final View markerView = LayoutInflater.from(getContext()).inflate(R.layout.map_location_marker, null);
        final CircleImageView icon = (CircleImageView) markerView.findViewById(R.id.img_user_head);
        Glide.with(getActivity())
                .load(url)
                .asBitmap()
                .thumbnail(0.2f)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .centerCrop()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {
                        //待图片加载完毕后再设置bitmapDes
                        icon.setImageBitmap(bitmap);
                        bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(convertViewToBitmap(markerView));
                        listener.markerIconLoadingFinished(markerView);
                    }
                });
    }

    public interface OnMarkerIconLoadListener {
        void markerIconLoadingFinished(View view);
    }


    /**
     * 解析数据添加到地图上面
     *
     * @param bean
     */
    private void addMarker(final UserGson<Active> bean) {
        double lon = bean.getLongtitude();
        double lat = bean.getLatintude();
        LatLng latLng = new LatLng(lat, lon);
        String url = bean.getUserhead();
        final MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        customizeMarkerIcon(url, new OnMarkerIconLoadListener() {
            @Override
            public void markerIconLoadingFinished(View view) {
                markerOptions.icon(bitmapDescriptor);
                aMap.addMarker(markerOptions);
            }
        });


    }

}
