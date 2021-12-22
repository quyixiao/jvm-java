package com.jvm.jnative.java.lang;

import com.jvm.rtda.Frame;
import com.jvm.rtda.JThread;
import com.jvm.rtda.OperandStack;
import com.jvm.rtda.heap.*;
import com.jvm.utils.ExceptionUtils;

public class ClassHelper {


    // []*Class => Class[]
    public static JObject toClassArr(JClassLoader loader, JClass classes[]) {
        int arrLen=classes.length;

        JClass classArrClass=loader.LoadClass("java/lang/Class").ArrayClass();
        JObject classArr=classArrClass.NewArray(arrLen);

        if( arrLen > 0 ){
            JObject [] classObjs=classArr.Refs();
            for (int i = 0 ;i < classes.length ;i ++) {
                classObjs[i] = classes[i].JObject();
            }
        }

        return classArr;
    }

    // []byte => byte[]
    public static JObject toByteArr(JClassLoader loader, byte[] goBytes) {
        if (goBytes != null) {
            return JClass.NewByteArray(loader, goBytes);
        }
        return null;
    }


    public static JObject getSignatureStr(JClassLoader loader, String signature) {
        if (!signature.equals("")) {
            return StringPool.JString(loader, signature);
        }
        return null;
    }

    public static Frame NewShimFrame(JThread thread, OperandStack ops) {
        return new Frame(
                thread,
                ShimMethods.ShimReturnMethod(),
                ops);
    }

    public static void ArrayCopy(JObject src, JObject dst, int srcPos, int dstPos, int length) {
        if (src.data instanceof byte[]) {
            byte _src[] = (byte[]) src.data;
            byte _dst[] = (byte[]) dst.data;
            System.arraycopy(_src, srcPos, _dst, dstPos, length);
        } else if (src.data instanceof short[]) {
            short _src[] = (short[]) src.data;
            short _dst[] = (short[]) dst.data;
            System.arraycopy(_src, srcPos, _dst, dstPos, length);
        } else if (src.data instanceof char[]) {
            char _src[] = (char[]) src.data;
            char _dst[] = (char[]) dst.data;
            System.arraycopy(_src, srcPos, _dst, dstPos, length);
        } else if (src.data instanceof int[]) {
            int _src[] = (int[]) src.data;
            int _dst[] = (int[]) dst.data;
            System.arraycopy(_src, srcPos, _dst, dstPos, length);
        } else if (src.data instanceof float[]) {
            float _src[] = (float[]) src.data;
            float _dst[] = (float[]) dst.data;
            System.arraycopy(_src, srcPos, _dst, dstPos, length);
        } else if (src.data instanceof long[]) {
            long _src[] = (long[]) src.data;
            long _dst[] = (long[]) dst.data;
            System.arraycopy(_src, srcPos, _dst, dstPos, length);
        } else if (src.data instanceof double[]) {
            double _src[] = (double[]) src.data;
            double _dst[] = (double[]) dst.data;
            System.arraycopy(_src, srcPos, _dst, dstPos, length);
        } else if (src.data instanceof JObject[]) {
            JObject _src[] = (JObject[]) src.data;
            JObject _dst[] = (JObject[]) dst.data;
            System.arraycopy(_src, srcPos, _dst, dstPos, length);
        } else {
            ExceptionUtils.throwException("Not array!");
        }

    }
}
