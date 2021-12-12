package com.jvm.classfile;


import com.jvm.data.Uint16;

/*
BootstrapMethods_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
    u2 num_bootstrap_methods;
    {   u2 bootstrap_method_ref;
        u2 num_bootstrap_arguments;
        u2 bootstrap_arguments[num_bootstrap_arguments];
    } bootstrap_methods[num_bootstrap_methods];
}

*/
public class BootstrapMethodsAttribute implements AttributeInfo {
    public BootstrapMethod[] bootstrapMethods;

    @Override
    public void readInfo(ClassReader reader) {
        Uint16 numBootstrapMethods = reader.readUint16();
        this.bootstrapMethods = new BootstrapMethod[numBootstrapMethods.Value()];
        for (int i = 0; i < this.bootstrapMethods.length; i++) {
            this.bootstrapMethods[i] = new BootstrapMethod(reader.readUint16(), reader.readUint16s());
        }
    }
}
