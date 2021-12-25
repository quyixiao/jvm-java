package com.jvm.rtda.heap;

import java.util.HashMap;
import java.util.Map;

public class StringPool {

    public static final Map<String, JObject> internedStrings = new HashMap<>();

// go string -> java.lang.String
    public static JObject JString(JClassLoader loader, String goStr) {
        JObject internedStr = internedStrings.get(goStr);
        if (internedStr != null) {
            return internedStr;
        }

        //否则先把Go字符串(UTF8 格式)转换成Java字符数组(UTF16格式)，然后创建一个Java字符串 实例，把它的value变量设置成刚刚转换而来的字符数组，最后把
        //Java字符串放入池中。注意，这里其实是跳过了String的构造函数，
        JObject jStr = loader.LoadClass("java/lang/String").NewObject();
        JObject jChars = new JObject(loader.LoadClass("[C"), goStr.toCharArray(), null);
        // 通过反射设置String对象的value值
        jStr.SetRefVar("value", "[C", jChars);    //通过反射赋值
        internedStrings.put(goStr, jStr);
        return jStr;
    }

    // java.lang.String -> go string
    public static String GoString(JObject jStr) {
        JObject charArr = jStr.GetRefVar("value", "[C");
        return String.valueOf(charArr.Chars());
    }


    public static JObject  InternString(JObject jStr) {
        String goStr = GoString(jStr);
        JObject internedStr =  internedStrings.get(goStr);
        if(internedStr !=null){
            return internedStr;
        }
        internedStrings.put(goStr,jStr);
        return jStr;
    }

}
