package com.jvm.instructions.references;


import com.jvm.instructions.base.BytecodeReader;
import com.jvm.instructions.base.Instruction;
import com.jvm.instructions.base.MethodInvokeLogic;
import com.jvm.rtda.Frame;
import com.jvm.rtda.heap.*;
import com.jvm.utils.ExceptionUtils;

// Invoke interface method
//invokeinterface 指令用于调用接口方法，它会在运行时搜索由特定的对象所实现的这个接口方法，并找到适合的方法进行调用
//剩下的情况则属于动态绑定。如果是针对 接口类型的引用调用方法，就使用invokeinterface指令
// 当Java虚拟机通过invokevirtual调用方法时， this引用指向某个类(或其子类)的实例。因为类的继承层次是固定 的，
// 所以虚拟机可以使用一种叫作vtable(Virtual Method Table)的技 术加速方法查找。但是当通过invokeinterface指令调用接口方法时，
//因为this引用可以指向任何实现了该接口的类的实例，所以无法使 用vtable技术。
// 用以调用接口方法，在运行时搜索一个实现了这个接口方法的对象，找出适合的方法进行调用。（Invoke interface method）
public class INVOKE_INTERFACE implements Instruction {

    public int index;

    public int index1;
    public int index2;


    //注意，和其他三条方法调用指令略有不同，在字节码中， invokeinterface指令的操作码后面跟着4字节而非2字节。
    public void FetchOperands(BytecodeReader reader) {
        this.index = reader.ReadUint16().Value();    //前两字节 的含义和其他指令相同，是个uint16运行时常量池索引。
        // count 第3字节的 值是给方法传递参数需要的slot数，其含义和给Method结构体定义 的argSlotCount字段相同。
        //正如我们所知，这个数是可以根据方法描 述符计算出来的，它的存在仅仅是因为历史原因。
        this.index1 = reader.ReadUint8().Value();
        this.index2 = reader.ReadUint8().Value(); // must be 0 第4字节是留给 Oracle的某些Java虚拟机实现用的，它的值必须是0。该字节的存在 是为了保证Java虚拟机可以向后兼容。
    }


    //先从运行时常量池中拿到并解析接口方法符号引用，如果解 析后的方法是静态方法或私有方法，则抛出 IncompatibleClassChangeError异常。
    //从操作数栈中弹出this引用，如果引用是null，则抛出 NullPointerException异常。
    //如果引用所指对象的类没有实现解析出 来的接口，则抛出IncompatibleClassChangeError异常。
    //查找最终要调用的方法。如果找不到，或者找到的方法是抽象 的，则抛出Abstract-MethodError异常。
    //如果找到的方法不是public， 则抛出IllegalAccessError异常
    public void Execute(Frame frame) {
        JConstantPool cp = frame.Method().classMember.Class().ConstantPool();
        InterfaceMethodRef methodRef = (InterfaceMethodRef) cp.GetConstant(this.index);
        JMethod resolvedMethod = methodRef.ResolvedInterfaceMethod();

        if (resolvedMethod.classMember.IsStatic() || resolvedMethod.classMember.IsPrivate()) {
            ExceptionUtils.throwException("java.lang.IncompatibleClassChangeError");
        }

        JObject ref = frame.OperandStack().GetRefFromTop(resolvedMethod.ArgSlotCount() - 1);
        if (ref == null) {
            ExceptionUtils.throwException("java.lang.NullPointerException");
        }

        if (!ref.Class().IsImplements(methodRef.memberRef.symRef.ResolvedClass())) {
            ExceptionUtils.throwException("java.lang.IncompatibleClassChangeError");
        }
        JMethod methodToBeInvoked = MethodLookup.LookupMethodInClass(ref.Class(), methodRef.memberRef.Name(), methodRef.memberRef.Descriptor());
        if (methodToBeInvoked == null || methodToBeInvoked.IsAbstract()) {
            ExceptionUtils.throwException("java.lang.AbstractMethodError");
        }
        if (!methodToBeInvoked.classMember.IsPublic()) {
            ExceptionUtils.throwException("java.lang.IllegalAccessError");
        }
        MethodInvokeLogic.InvokeMethod(frame, methodToBeInvoked);
    }

    @Override
    public String toString() {
        return "INVOKE_INTERFACE{" +
                "index=" + index +
                ", index1=" + index1 +
                ", index2=" + index2 +
                '}';
    }
}
