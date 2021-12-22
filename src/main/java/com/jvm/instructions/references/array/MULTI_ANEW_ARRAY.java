package com.jvm.instructions.references.array;

import com.jvm.data.Uint16;
import com.jvm.data.Uint8;
import com.jvm.instructions.base.BytecodeReader;
import com.jvm.instructions.base.Instruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;
import com.jvm.rtda.heap.ClassRef;
import com.jvm.rtda.heap.JClass;
import com.jvm.rtda.heap.JConstantPool;
import com.jvm.rtda.heap.JObject;
import com.jvm.utils.ExceptionUtils;

// Create new multidimensional array
public class MULTI_ANEW_ARRAY implements Instruction {
    public Uint16 index;
    public Uint8 dimensions;


    @Override
    public void FetchOperands(BytecodeReader reader) {
        this.index = reader.ReadUint16();        //指令的第一个操作数是个uint16索引，通过这个 索引可以从运行时常量池中找到一个类符号引用，解析这个引用就 可以得到多维数组类。
        this.dimensions = reader.ReadUint8();    //第二个操作数是个uint8整数，表示数组维度。
    }

    @Override
    public void Execute(Frame frame) {
        JConstantPool cp = frame.Method().classMember.Class().ConstantPool();
        ClassRef classRef = (ClassRef) cp.GetConstant(this.index.Value());
        JClass arrClass = classRef.symRef.ResolvedClass();

        OperandStack stack = frame.OperandStack();
        int[] counts = popAndCheckCounts(stack, this.dimensions.Value());  //函数从操作数栈中弹出n个int值，并且确保 它们都大于等于0。如果其中任何一个小于0
        JObject arr = newMultiDimensionalArray(counts, arrClass);
        stack.PushRef(arr);
    }


    public int[] popAndCheckCounts(OperandStack stack, int dimensions) {
        int[] counts = new int[dimensions];
        for (int i = dimensions - 1; i >= 0; i--) {
            counts[i] = stack.PopInt();
            if (counts[i] < 0) {
                ExceptionUtils.throwException("java.lang.NegativeArraySizeException");
            }
        }

        return counts;
    }


    public JObject newMultiDimensionalArray(int[] counts, JClass arrClass) {
        int count = counts[0];
        JObject arr = arrClass.NewArray(count);
        if (counts.length > 1) {
            JObject[] refs = arr.Refs();
            for (int i = 0; i < refs.length; i++) {
                int[] countnew = new int[counts.length - 1];
                System.arraycopy(counts, 1, countnew, 0, counts.length - 1);
                //ComponentClass()方法先根据数组类名推测出数组元素类名， 然后用类加载器加载元素类即可
                refs[i] = newMultiDimensionalArray(countnew, arrClass.ComponentClass());
            }
        }
        return arr;
    }

}
