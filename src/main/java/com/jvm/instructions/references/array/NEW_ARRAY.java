package com.jvm.instructions.references.array;

import com.jvm.data.Uint8;
import com.jvm.instructions.base.BytecodeReader;
import com.jvm.instructions.base.Instruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;
import com.jvm.rtda.heap.JClass;
import com.jvm.rtda.heap.JClassLoader;
import com.jvm.rtda.heap.JObject;
import com.jvm.utils.ExceptionUtils;
// Create new array
public class NEW_ARRAY implements Instruction {
    //Array Type  atype
    public final static int AT_BOOLEAN = 4;
    public final static int AT_CHAR = 5;
    public final static int AT_FLOAT = 6;
    public final static int AT_DOUBLE = 7;
    public final static int AT_BYTE = 8;
    public final static int AT_SHORT = 9;
    public final static int AT_INT = 10;
    public final static int AT_LONG = 11;

    //newarray指令需要两个操作数。第一个操作数是一个uint8整 数，在字节码中紧跟在指令操作码后面，表示要创建哪种类型的数 组。
    // Java虚拟机规范把这个操作数叫作atype，并且规定了它的有效 值 ,如 array type 常量
    public Uint8 atype;

    public void FetchOperands(BytecodeReader reader) {
        this.atype = reader.ReadUint8();
    }

    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        //newarray指令的第二个操作数是count，从操作数栈中弹出，表示数组长度。
        int count = stack.PopInt();
        //如果count小于0，则抛出NegativeArraySizeException异常
        if (count < 0) {
            ExceptionUtils.throwException("java.lang.NegativeArraySizeException");
        }
        //否则 根据atype值使用当前类的类加载器加载数组类，然后创建数组对象并推入操作数栈
        JClassLoader classLoader = frame.Method().classMember.Class().Loader();
        JClass arrClass = getPrimitiveArrayClass(classLoader, this.atype.Value());
        JObject arr = arrClass.NewArray(count);
        stack.PushRef(arr);
    }

    public JClass getPrimitiveArrayClass(JClassLoader loader, int atype) {
        switch (atype) {
            case AT_BOOLEAN:
                return loader.LoadClass("[Z");
            case AT_BYTE:
                return loader.LoadClass("[B");
            case AT_CHAR:
                return loader.LoadClass("[C");
            case AT_SHORT:
                return loader.LoadClass("[S");
            case AT_INT:
                return loader.LoadClass("[I");
            case AT_LONG:
                return loader.LoadClass("[J");
            case AT_FLOAT:
                return loader.LoadClass("[F");
            case AT_DOUBLE:
                return loader.LoadClass("[D");
            default:
                ExceptionUtils.throwException("Invalid atype!");
        }
        return null;
    }

}
