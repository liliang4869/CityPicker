package com.liliang4869.citypicker;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;

public class CommonUtil {
    private static CommonUtil instance;

    public static CommonUtil getInstance() {
        if (instance == null) instance = new CommonUtil();
        return instance;
    }






    public static boolean isBlank(String s) {
        return s == null || s.length() == 0;
    }

    public static boolean isNotBlank(String s) {
        return !isBlank(s);
    }
    
    public String secondsToStr(long nums) {
        String ret = "00:00:00";
        if (nums > 0) {
            int hours = (int) nums / 3600;
            int munites = (int) (nums % 3600) / 60;
            int seconds = (int) (nums % 3600) % 60;
            ret = (hours < 10 ? "0" + hours : hours) + ":" + (munites < 10 ? "0" + munites : munites) + ":" + (seconds < 10 ? "0" + seconds : seconds);
        }
        return ret;
    }

    /**
     * 格式化日期和时间
     *
     * @return
     */
    public String getCurrentTimeStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        Calendar calendar = Calendar.getInstance();

        return sdf.format(calendar.getTime());
    }

    public String createUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public String getSafeString(String s) {
        return s == null ? "" : s;
    }




    @SuppressWarnings("unchecked")
    public static <T extends ObjectNameCallback > T getSimilarityLang(String targetStr, List<T> strings) {
        ArrayList<TempBean<T>> list = new ArrayList<TempBean<T>>();
        if(isBlank(targetStr))
            return null;
        for (ObjectNameCallback lang : strings) {
            list.add(new TempBean<T>(getSimilarityRatio(lang.getObjectName(), targetStr), (T)lang));
        }
        Collections.sort(list,new Comparator() {
            @Override
            public int compare(Object obj1, Object obj2) {
                if(obj1 instanceof TempBean&&obj2 instanceof TempBean) {
                    TempBean<T> o1=(TempBean<T>)obj1;
                    TempBean<T> o2=(TempBean<T>)obj2;
                    if(o1.getSimilarityRatio()<o2.getSimilarityRatio())
                        return 1;
                    if(o1.getSimilarityRatio()==o2.getSimilarityRatio())
                        return 0;
                }
                return -1;
            }
        });
        if(list.size()>0)
            return (T)list.get(0).getTargetLang();
        return null;
    }

    /***
     * 完全相似=1.0
     * 完全不相似=0.0
     */
    public static float getSimilarityRatio(String str, String target) {
        //去除空白字符、换行、标点符号
//        String regex = "[\\pP\\p{Punct}\\s]";
//        str=str.replaceAll(regex, "");
//        target=target.replaceAll(regex, "");
        return 1 - (float) compare(str, target) / Math.max(str.length(), target.length());
    }

    private static int compare(String str, String target) {
        int d[][]; // 矩阵
        int n = str.length();
        int m = target.length();
        int i; // 遍历str的
        int j; // 遍历target的
        char ch1; // str的
        char ch2; // target的
        int temp; // 记录相同字符,在某个矩阵位置值的增量,不是0就是1
        if (n == 0) {
            return m;
        }
        if (m == 0) {
            return n;
        }
        d = new int[n + 1][m + 1];
        for (i = 0; i <= n; i++) { // 初始化第一列
            d[i][0] = i;
        }

        for (j = 0; j <= m; j++) { // 初始化第一行
            d[0][j] = j;
        }

        for (i = 1; i <= n; i++) { // 遍历str
            ch1 = str.charAt(i - 1);
            // 去匹配target
            for (j = 1; j <= m; j++) {
                ch2 = target.charAt(j - 1);
                if (ch1 == ch2) {
                    temp = 0;
                } else {
                    temp = 1;
                }

                // 左边+1,上边+1, 左上角+temp取最小
                d[i][j] = min(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1] + temp);
            }
        }
        return d[n][m];
    }
    private static int min(int one, int two, int three) {
        return (one = one < two ? one : two) < three ? one : three;
    }
    static class TempBean<T extends ObjectNameCallback>{
        private float similarityRatio;
        private T targetLang;

        public TempBean(float similarityRatio, T targetLang) {
            super();
            this.similarityRatio = similarityRatio;
            this.targetLang = targetLang;
        }
        public TempBean() {
            super();
        }
        public float getSimilarityRatio() {
            return similarityRatio;
        }
        public void setSimilarityRatio(float similarityRatio) {
            this.similarityRatio = similarityRatio;
        }
        public T getTargetLang() {
            return targetLang;
        }
        public void setTargetLang(T targetLang) {
            this.targetLang = targetLang;
        }

    }

    /**
     * 将sp转换为px
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

public interface ObjectNameCallback{
        String getObjectName();
}
public interface NameAndCountObj{
        String getShowName();
        int getCount();
}

}
