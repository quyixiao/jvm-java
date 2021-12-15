package com.jvm.rtda.heap;

import com.jvm.classfile.ConstantValueAttribute;
import com.jvm.classfile.MemberInfo;

public class JField {

    public ClassMember classMember;
    public int constValueIndex;
    public int slotId;

    //修改newFields()方法，从字段属性表中读取constValueIndex， 代码改动如下:
    public JField[] newFields(JClass jClass, MemberInfo[] cfFields) {
        JField fields[] = new JField[cfFields.length];
        for (int i = 0; i < fields.length; i++) {
            MemberInfo cfField = cfFields[i];
            fields[i] = new JField();
            fields[i].classMember.jClass = jClass;
            fields[i].classMember.copyMemberInfo(cfField);
            fields[i].copyAttributes(cfField);
        }
        return fields;
    }

    public void copyAttributes(MemberInfo cfField) {
        ConstantValueAttribute valAttr = cfField.ConstantValueAttribute();
        if (valAttr != null) {
            this.constValueIndex = valAttr.ConstantValueIndex().Value();
        }
    }


    public boolean IsVolatile() {
        return 0 != (this.classMember.accessFlags.Value() & Constants.ACC_VOLATILE);
    }

    public boolean IsTransient() {
        return 0 != (this.classMember.accessFlags.Value() & Constants.ACC_TRANSIENT);
    }

    public boolean IsEnum() {
        return 0 != (this.classMember.accessFlags.Value() & Constants.ACC_ENUM);
    }

    public int ConstValueIndex() {
        return this.constValueIndex;
    }

    public int SlotId() {
        return this.slotId;
    }

    public boolean isLongOrDouble() {
        return this.classMember.descriptor == "J" || this.classMember.descriptor == "D";
    }


}
