package com.jvm.jnative.java.lang.jsystem;

import com.jvm.jnative.JNativeMethod;
import com.jvm.jnative.java.lang.ClassHelper;
import com.jvm.rtda.Frame;
import com.jvm.rtda.LocalVars;
import com.jvm.rtda.heap.JClass;
import com.jvm.rtda.heap.JObject;
import com.jvm.utils.ExceptionUtils;

// public static native void arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
// (Ljava/lang/Object;ILjava/lang/Object;II)V
//先从局部变量表中拿到5个参数。源数组和目标数组都不能是 null，否则按照System类的Javadoc应该抛出NullPointerException异常
public class SystemArrayCopy implements JNativeMethod {
    @Override
    public void run(Frame frame) {
        LocalVars vars = frame.LocalVars();
        JObject src = vars.GetRef(0);
        int srcPos = vars.GetInt(1);
        JObject dest = vars.GetRef(2);
        int destPos = vars.GetInt(3);
        int length = vars.GetInt(4);

        if (src == null || dest == null) {
            ExceptionUtils.throwException("java.lang.NullPointerException");
        }
        //源数组和目标数组必须兼容才能拷贝，否则应该抛出 ArrayStoreException异常
        if (!checkArrayCopy(src, dest)) {
            ExceptionUtils.throwException("java.lang.ArrayStoreException");
        }
        //checkArrayCopy()函数的代码稍后给出。接下来检查srcPos、 destPos和length参数，如果有问题则抛出 IndexOutOfBoundsException异常
        if (srcPos < 0 || destPos < 0 || length < 0 ||
                srcPos + length > src.ArrayLength() ||
                destPos + length > dest.ArrayLength()) {
            ExceptionUtils.throwException("java.lang.IndexOutOfBoundsException");
        }

        //最后，参数合法，调用ArrayCopy()函数进行数组拷贝
        ClassHelper.ArrayCopy(src, dest, srcPos, destPos, length);
    }


    //checkArrayCopy()函数首先确保src和dest都是数组，然后检查 数组类型。如果两者都是引用数组，则可以拷贝，否则两者必须是 相同类型的基本类型数组
    public boolean checkArrayCopy(JObject src, JObject dest) {
        JClass srcClass = src.Class();
        JClass destClass = dest.Class();

        if (!srcClass.IsArray() || !destClass.IsArray()) {
            return false;
        }
        //Class结构体的IsPrimitive()函数判断类是否是基本类型的类
        if (srcClass.ComponentClass().IsPrimitive() ||
                destClass.ComponentClass().IsPrimitive()) {
            return srcClass == destClass;
        }
        return true;
    }

}
