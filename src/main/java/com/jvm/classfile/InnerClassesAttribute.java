package com.jvm.classfile;


import com.jvm.data.Uint16;
import lombok.Data;

/*
InnerClasses_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
    u2 number_of_classes;
    {   u2 inner_class_info_index;
        u2 outer_class_info_index;
        u2 inner_name_index;
        u2 inner_class_access_flags;
    } classes[number_of_classes];
}
*/
@Data
public class InnerClassesAttribute implements AttributeInfo {

    public InnerClassInfo[] classes;


    @Override
    public void readInfo(ClassReader reader) {
        Uint16 numberOfClasses = reader.readUint16();
        this.classes = new InnerClassInfo[numberOfClasses.Value()];
        for (int i = 0; i < classes.length; i++) {
            this.classes[i] = new InnerClassInfo(
                    reader.readUint16(),
                    reader.readUint16(),
                    reader.readUint16(),
                    reader.readUint16());
        }
    }


}
