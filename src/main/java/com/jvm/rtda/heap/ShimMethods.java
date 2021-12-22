package com.jvm.rtda.heap;

import com.jvm.data.Uint16;

public class ShimMethods {

    public static JClass _shimClass = new JClass("~shim");
    public static byte[] _returnCode = new byte[]{(byte) 0xb1}; // return
    public static byte[] _athrowCode = new byte[]{(byte) 0xbf};// athrow

    public static JMethod _returnMethod = new JMethod(
            new ClassMember(
                    new Uint16(Constants.ACC_STATIC),
                    "<return>",
                    _shimClass
            ),
            _returnCode
    );

    public static JMethod _athrowMethod = new JMethod(
            new ClassMember(
                    new Uint16(Constants.ACC_STATIC),
                    "<athrow>",
                    _shimClass
            ),
            _athrowCode
    );

    public  static  JMethod ShimReturnMethod() {
        return _returnMethod;
    }


}
