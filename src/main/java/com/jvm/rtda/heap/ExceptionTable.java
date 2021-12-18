package com.jvm.rtda.heap;

import com.jvm.classfile.ExceptionTableEntry;

public class ExceptionTable {

    public ExceptionHandler exceptionHandlers[];

    //newExceptionTable()函数把class文件中的异常处理表转换成 ExceptionTable类型。有一点需要特别说明:异常处理项的catchType 有可能是0。
    //我们知道0是无效的常量池索引，但是在这里0并非表 示catch-none，而是表示catch-all，它的用法马上就会看到。
    //getCatchType()函数从运行时常量池中查找类符号引用
    public ExceptionTable(ExceptionTableEntry entries[], JConstantPool cp) {
        exceptionHandlers = new ExceptionHandler[entries.length];
        for (int i = 0; i < entries.length; i++) {
            ExceptionTableEntry entry = entries[i];
            exceptionHandlers[i] = new ExceptionHandler(
                    entry.StartPc().Value(),
                    entry.EndPc().Value(),
                    entry.HandlerPc().Value(),
                    getCatchType(entry.CatchType().Value(), cp));
        }
    }

    //getCatchType()函数从运行时常量池中查找类符号引用
    public ClassRef getCatchType(int index, JConstantPool cp) {
        if (index == 0) {
            return null; // catch all
        }
        return (ClassRef) cp.GetConstant(index);
    }

    //异常处理表查找逻辑前面已经描述过，此处不再赘述。这里注 意两点。第一，startPc给出的是try{}语句块的第一条指令，
    //endPc给 出的则是try{}语句块的下一条指令。第二，如果catchType是nil(在 class文件中是0)，表示可以处理所有异常，这是用来实现finally子句的。
    // 第一，startPc给出的是try{}语句块的第一条指令，endPc给 出的则是try{}语句块的下一条指令。
    // 第二，如果catchType是nil(在 class文件中是0)，表示可以处理所有异常，这是用来实现finally子 句的。
    public ExceptionHandler findExceptionHandler(JClass exClass, int pc) {
        for (ExceptionHandler handler : this.exceptionHandlers) {
            // jvms: The start_pc is inclusive and end_pc is exclusive
            if (pc >= handler.startPc && pc < handler.endPc) {
                if (handler.catchType == null) {
                    return handler;
                }
                JClass catchClass = handler.catchType.symRef.ResolvedClass();
                if (catchClass == exClass || catchClass.IsSuperClassOf(exClass)) {
                    return handler;
                }
            }
        }
        return null;
    }

}
