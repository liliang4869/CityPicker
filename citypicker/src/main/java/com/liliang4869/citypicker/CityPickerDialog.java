package com.liliang4869.citypicker;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.liliang4869.citypicker.db.CityPicker;
import com.liliang4869.citypicker.entity.District;
import com.liliang4869.citypicker.entity.City;
import com.liliang4869.citypicker.entity.Province;
import com.liliang4869.citypicker.entity.Street;
import com.liliang4869.citypicker.view.CityPickView;

public class CityPickerDialog extends Dialog {
    private CityPickView cityPickView;

    public CityPickerDialog(@NonNull Context context) {
        super(context);
        init();
    }

    public CityPickerDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        init();
    }

    public CityPickerDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init();
    }

    private void init() {
        CityPicker.getInstance().init(getContext());
        cityPickView = new CityPickView(getContext());
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(cityPickView);
    }

    public void setCityPickerListener(CityPickView.CityPickerListener cityPickerListener) {
        cityPickView.setCityPickerListener(cityPickerListener);
    }

    public void setCheckedData(String provinceStr, String cityStr, String districtStr, String streetStr) {
        if (cityPickView != null) {
            cityPickView.setData(provinceStr, cityStr, districtStr, streetStr);
        }
    }

    public void setData(Province province, City city, District district, Street street) {
        if (cityPickView != null) {
            cityPickView.setData(province, city, district, street);
        }
    }

}
