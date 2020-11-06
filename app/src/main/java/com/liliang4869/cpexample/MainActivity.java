package com.liliang4869.cpexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.liliang4869.citypicker.CityPickerDialog;
import com.liliang4869.citypicker.db.CityPicker;
import com.liliang4869.citypicker.entity.District;
import com.liliang4869.citypicker.entity.City;
import com.liliang4869.citypicker.entity.Province;
import com.liliang4869.citypicker.entity.Street;
import com.liliang4869.citypicker.view.CityPickView;
import com.liliang4869.cpexample.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CityPickView.CityPickerListener {
    private ActivityMainBinding mainBinding;
    private CityPickerDialog cityPickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        mainBinding.bjdccy.setOnClickListener(this);
        mainBinding.hncsyhgq.setOnClickListener(this);
        mainBinding.hnzz.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (cityPickerDialog == null)
            cityPickerDialog = CityPicker.getInstance().buildCityPickerDialog(MainActivity.this);
        switch (v.getId()) {
            case R.id.bjdccy: {
                cityPickerDialog.setCheckedData("北京市",
                        "市辖区",
                        "东城区",
                        "朝阳门街道");

                break;
            }
            case R.id.hncsyhgq: {
                cityPickerDialog.setCheckedData("湖南省",
                        "长沙市",
                        "雨花区",
                        "高桥街道");

                break;
            }
            case R.id.hnzz: {
                cityPickerDialog.setCheckedData("河南省",
                        "郑州市",
                        null,
                        null);
                break;
            }

        }
        cityPickerDialog.setCityPickerListener(this);
        cityPickerDialog.show();
    }

    @Override
    public void cancel() {
        if (cityPickerDialog != null && cityPickerDialog.isShowing()) cityPickerDialog.dismiss();
    }

    @Override
    public void confirm(Province province, City city, District district, Street street) {
        Log.d("//Province",province==null?"NotSelect":province.getName());
        Log.d("//City",city==null?"NotSelect":city.getName());
        Log.d("//District",district==null?"NotSelect": district.getName());
        Log.d("//Street",street==null?"NotSelect":street.getName());
    }
}