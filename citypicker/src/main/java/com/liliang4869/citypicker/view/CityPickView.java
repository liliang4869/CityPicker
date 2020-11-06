package com.liliang4869.citypicker.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.liliang4869.citypicker.CommonUtil;
import com.liliang4869.citypicker.R;
import com.liliang4869.citypicker.adapters.PickAdapter;

import com.liliang4869.citypicker.databinding.CityPickerRootViewBinding;
import com.liliang4869.citypicker.db.CityPicker;
import com.liliang4869.citypicker.entity.District;
import com.liliang4869.citypicker.entity.City;
import com.liliang4869.citypicker.entity.Province;
import com.liliang4869.citypicker.entity.Street;

import java.util.ArrayList;
import java.util.List;

public class CityPickView extends LinearLayout implements View.OnClickListener {
    private CityPickerRootViewBinding cityPickerRootViewBinding;
    private CityPickerListener cityPickerListener;
    private String provinceStr;
    private String cityStr;
    private String districtStr;
    private Province selectedProvince;
    private City selectedCity;
    private District selectedDistrict;
    private Street selectedStreet;
    private List data;
    private PickAdapter pickAdapter;

    public void setData(String provinceStr, String cityStr, String districtStr, String streetStr) {
        selectedDistrict = null;
        selectedProvince = null;
        selectedCity = null;
        selectedStreet = null;
        if (CommonUtil.isNotBlank(provinceStr)) {
            selectedDistrict = null;
            selectedProvince = null;
            selectedCity = null;
            List<Province> provinceList = CityPicker.getInstance().getProvinceDao().loadAll();
            selectedProvince = CommonUtil.getSimilarityLang(provinceStr, provinceList);
            if (CommonUtil.isNotBlank(cityStr)) {
                List<City> cityList = CityPicker.getInstance().queryCityByProvinceCode(selectedProvince.getCode());
                selectedCity = CommonUtil.getSimilarityLang(cityStr, cityList);
                if (CommonUtil.isNotBlank(districtStr)) {
                    List<District> districtList = CityPicker.getInstance().queryDistrictByCityCode(selectedCity.getCode());
                    selectedDistrict = CommonUtil.getSimilarityLang(districtStr, districtList);
                    if (CommonUtil.isNotBlank(streetStr)) {
                        List<Street> streetList = CityPicker.getInstance().queryStreetByDistrictCodeCode(selectedDistrict.getCode());
                        selectedStreet = CommonUtil.getSimilarityLang(streetStr, streetList);
                    }
                }

            }
        }
        initHeadStatus();

    }

    public void setData(Province province, City city, District district, Street street) {
        selectedDistrict = district;
        selectedProvince = province;
        selectedCity = city;
        selectedStreet = street;
        initHeadStatus();
    }

    public Street getSelectedStreet() {
        return selectedStreet;
    }

    public Province getSelectedProvince() {
        return selectedProvince;
    }

    public void setSelectedProvince(Province selectedProvince) {
        this.selectedProvince = selectedProvince;
    }

    public City getSelectedCity() {
        return selectedCity;
    }

    public void setSelectedCity(City selectedCity) {
        this.selectedCity = selectedCity;
    }

    public District getSelectedDistrict() {
        return selectedDistrict;
    }

    public void setSelectedDistrict(District selectedDistrict) {
        this.selectedDistrict = selectedDistrict;
    }

    public void setSelectedStreet(Street selectedStreet) {
        this.selectedStreet = selectedStreet;
    }

    private void setTvSelected(TextView tv) {
        cityPickerRootViewBinding.province.setSelected(false);
        cityPickerRootViewBinding.city.setSelected(false);
        cityPickerRootViewBinding.district.setSelected(false);
        cityPickerRootViewBinding.street.setSelected(false);
        tv.setSelected(true);
    }

    private void initHeadStatus() {
        if (selectedProvince == null) {
            showProvinces();
        } else if (selectedCity == null) {
            showCities();
            cityPickerRootViewBinding.province.setText(selectedProvince.getName());
        } else if (selectedDistrict == null) {
            showDistricts();
            cityPickerRootViewBinding.city.setText(selectedCity.getName());
        } else {
            showStreets();
            cityPickerRootViewBinding.district.setText(selectedDistrict.getName());
            if (selectedStreet != null)
                cityPickerRootViewBinding.street.setText(selectedStreet.getName());
        }
    }

    ;

    private void initTvs() {
        cityPickerRootViewBinding.province.setText(selectedProvince != null ? selectedProvince.getName() : "省份");
        cityPickerRootViewBinding.city.setText(selectedCity != null ? selectedCity.getName() : "城市");
        cityPickerRootViewBinding.district.setText(selectedDistrict != null ? selectedDistrict.getName() : "区县");
        cityPickerRootViewBinding.street.setText(selectedStreet != null ? selectedStreet.getName() : "街道/镇");
    }

    private void showProvinces() {
        data.clear();
        initTvs();
        data.addAll(CityPicker.getInstance().getProvinceDao().loadAll());
        setTvSelected(cityPickerRootViewBinding.province);
        pickAdapter.setPickAdapterListener(new PickAdapter.PickAdapterListener() {
            @Override
            public void onItemClick(CommonUtil.ObjectNameCallback o) {
                if (o instanceof Province) {
                    selectedProvince = (Province) o;
                    selectedDistrict = null;
                    selectedCity = null;
                    selectedStreet = null;
                    showCities();
                }
            }
        });
        pickAdapter.setSelected(selectedProvince);
        cityPickerRootViewBinding.list.scrollToPosition(pickAdapter.getSelectedIndex());
    }

    private void showCities() {
        data.clear();
        initTvs();
        data.addAll(CityPicker.getInstance().queryCityByProvinceCode(selectedProvince.getCode()));
        setTvSelected(cityPickerRootViewBinding.city);
        pickAdapter.setPickAdapterListener(new PickAdapter.PickAdapterListener() {
            @Override
            public void onItemClick(CommonUtil.ObjectNameCallback o) {
                if (o instanceof City) {
                    selectedCity = (City) o;
                    selectedDistrict = null;
                    selectedStreet = null;
                    showDistricts();
                }
            }
        });
        pickAdapter.setSelected(selectedCity);
        cityPickerRootViewBinding.list.scrollToPosition(pickAdapter.getSelectedIndex());
    }

    private void showDistricts() {
        data.clear();
        initTvs();
        data.addAll(CityPicker.getInstance().queryDistrictByCityCode(selectedCity.getCode()));
        setTvSelected(cityPickerRootViewBinding.district);
        pickAdapter.setPickAdapterListener(new PickAdapter.PickAdapterListener() {
            @Override
            public void onItemClick(CommonUtil.ObjectNameCallback o) {
                if (o instanceof District) {
                    selectedDistrict = (District) o;
                    selectedStreet = null;
                    showStreets();
                }
            }
        });
        pickAdapter.setSelected(selectedDistrict);
        cityPickerRootViewBinding.list.scrollToPosition(pickAdapter.getSelectedIndex());
    }

    private void showStreets() {
        data.clear();
        initTvs();
        data.addAll(CityPicker.getInstance().queryStreetByDistrictCodeCode(selectedDistrict.getCode()));
        setTvSelected(cityPickerRootViewBinding.street);
        pickAdapter.setPickAdapterListener(new PickAdapter.PickAdapterListener() {
            @Override
            public void onItemClick(CommonUtil.ObjectNameCallback o) {
                if (o instanceof Street) selectedStreet = (Street) o;
                initTvs();
            }
        });
        pickAdapter.setSelected(selectedStreet);
        cityPickerRootViewBinding.list.scrollToPosition(pickAdapter.getSelectedIndex());
    }

    public CityPickView(Context context) {
        super(context);
        init();
    }

    public CityPickView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CityPickView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setCityPickerListener(CityPickerListener cityPickerListener) {
        this.cityPickerListener = cityPickerListener;
    }

    private void init() {
        data = new ArrayList();
        cityPickerRootViewBinding = CityPickerRootViewBinding.inflate(LayoutInflater.from(this.getContext()), this, true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        cityPickerRootViewBinding.list.setLayoutManager(linearLayoutManager);
        pickAdapter = new PickAdapter(data, null);
        cityPickerRootViewBinding.list.setAdapter(pickAdapter);
        cityPickerRootViewBinding.street.setOnClickListener(this);
        cityPickerRootViewBinding.district.setOnClickListener(this);
        cityPickerRootViewBinding.city.setOnClickListener(this);
        cityPickerRootViewBinding.province.setOnClickListener(this);
        cityPickerRootViewBinding.confirm.setOnClickListener(this);
        cityPickerRootViewBinding.cancel.setOnClickListener(this);
        showProvinces();
    }

    public interface CityPickerListener {
        void cancel();

        void confirm(Province province, City city, District district, Street street);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.province) {
            showProvinces();
        } else if (v.getId() == R.id.city) {
            if (selectedProvince != null)
                showCities();
        } else if (v.getId() == R.id.district) {
            if (selectedCity != null)
                showDistricts();
        } else if (v.getId() == R.id.street) {
            if (selectedDistrict != null) showStreets();
        } else if (v.getId() == R.id.confirm) {
            if (cityPickerListener != null)
                cityPickerListener.confirm(selectedProvince, selectedCity, selectedDistrict, selectedStreet);
        } else if (v.getId() == R.id.cancel) {
            if (cityPickerListener != null) cityPickerListener.cancel();
        }
    }
}
