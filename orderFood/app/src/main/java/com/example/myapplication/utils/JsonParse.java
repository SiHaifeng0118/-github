package com.example.myapplication.utils;

import com.example.myapplication.bean.ShopBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

//此类用于解析从服务器获取的店铺列表的json数据
public class JsonParse {
    private static JsonParse instance;

    public JsonParse() {
    }

    public static JsonParse getInstance() {
        if(null==instance){
            instance = new JsonParse();
        }
        return instance;
    }
    public List<ShopBean> getShopList(String json){
        Gson gson = new Gson();//使用Gson解析json数据
        //创建一个TypeToken匿名子类对象,并调用getType()方法
        Type listType = new TypeToken<List<ShopBean>>(){}.getType();
        //把获取的信息集合放到shopList中去
        List<ShopBean> shopList = gson.fromJson(json,listType);

        return shopList;
    }

}
