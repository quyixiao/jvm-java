package com.jvm.rtda.heap;

import com.jvm.utils.ExceptionUtils;
import com.jvm.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class ClassNameHelper {

    public static Map<String, String> primitiveTypes = new HashMap();

    static {
        primitiveTypes.put("void", "V");
        primitiveTypes.put("boolean", "Z");
        primitiveTypes.put("byte", "B");
        primitiveTypes.put("short", "S");
        primitiveTypes.put("int", "I");
        primitiveTypes.put("long", "J");
        primitiveTypes.put("char", "C");
        primitiveTypes.put("float", "F");
        primitiveTypes.put("double", "D");
    }

    // [XXX -> [[XXX
// int -> [I
// XXX -> [LXXX;
    public static String getArrayClassName(String className) {
        return "[" + toDescriptor(className);
    }

    // [[XXX -> [XXX
// [LXXX; -> XXX
// [I -> int
    public static String getComponentClassName(String className) {
        if (className.toCharArray()[0] == '[') {
            String componentTypeDescriptor = className.substring(1, className.length());
            return toClassName(componentTypeDescriptor);
        }
        ExceptionUtils.throwException("Not array: " + className);
        return null;
    }

    // [XXX => [XXX
// int  => I
// XXX  => LXXX;
    public static String toDescriptor(String className) {
        //如果是数组类名，描述符就是类名，直接返回即可。
        if (className.toCharArray()[0] == '[') {
            // array
            return className;
        }
        //如果是基本类型名，返回对应的类型描述符，否则肯定是普通的类名，前面加上方括号，结尾加上分号即可得到类型描述符。
        String d = primitiveTypes.get(className);
        if (StringUtils.isNotBlank(d)) {
            // primitive
            return d;
        }
        // object
        return "L" + className + ";";
    }

    // [XXX  => [XXX        如果类型描述符以方括号开头，那么肯定是数组，描述符即是 类名。
// LXXX; => XXX       如果类型描述符以L开头，那么肯定是类描述符，去掉开头的 L和末尾的分号即是类名
// I     => int   否则判断是否是基本类型的描述符，如 果是，返回基本类型名称，否则调用panic()函数终止程序执行。
    public  static String toClassName(String descriptor) {
        if (descriptor.toCharArray()[0] == '[') {
            // array
            return descriptor;
        }
        if (descriptor.toCharArray()[0] == 'L') {
            // object
            return descriptor.substring(1, descriptor.length() - 1);
        }
        for (Map.Entry<String, String> map : primitiveTypes.entrySet()) {
            String className = map.getKey();
            String d = map.getValue();
            if (d.equals(descriptor) ){
                // primitive
                return className;
            }
        }
        ExceptionUtils.throwException("Invalid descriptor: " + descriptor);
        return null;
    }


}
