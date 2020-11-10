# CityPicker
中国省市区街道四级选择器 Android CityPicker.

## 省市区街道数据来源
来自 https://github.com/modood/Administrative-divisions-of-China

## 数据库
使用了GreenDao https://github.com/greenrobot/greenDAO

## Setup
Gradle<br>
implementation 'com.liliang4869:CityPicker:1.0.1'</br>

## Usage
```java
//application中初始化
CityPicker.getInstance().init(this);
```

使用dialog
```java
 CityPickerDialog cityPickerDialog = CityPicker.getInstance().buildCityPickerDialog(MainActivity.this);
 //cityPickerDialog.setCheckedData("北京市",
 //                     "市辖区",
 //                   "东城区",
 //                 "朝阳门街道");
 cityPickerDialog.setCheckedData("河南省",
                        "郑州市",
                        null,
                        null);
   cityPickerDialog.setCityPickerListener(new CityPickView.CityPickerListener() {
            @Override
            public void cancel() {
                //点击取消
            }

            @Override
            public void confirm(Province province, City city, District district, Street street) {
              //选择完毕 点击确认
               Log.d("//Province",province==null?"NotSelect":province.getName());
               Log.d("//City",city==null?"NotSelect":city.getName());
               Log.d("//District",district==null?"NotSelect": district.getName());
               Log.d("//Street",street==null?"NotSelect":street.getName());
            }
        });
        cityPickerDialog.show();
```

不使用Dialog
```xml
<com.liliang4869.citypicker.view.CityPickView
    android:layout_width="match_parent"
    android:layout_height="match_parent"/>
```

## Screenshot
![img](https://github.com/liliang4869/CityPicker/blob/main/img/device-2020-11-06-170428.png)
