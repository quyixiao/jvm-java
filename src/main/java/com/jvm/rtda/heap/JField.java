package com.jvm.rtda.heap;

import com.jvm.classfile.ConstantValueAttribute;
import com.jvm.classfile.MemberInfo;

public class JField {

    public ClassMember classMember;
    public int constValueIndex;
    public int slotId;


    public JField() {
        if (this.classMember == null){
            this.classMember = new ClassMember();
        }
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
