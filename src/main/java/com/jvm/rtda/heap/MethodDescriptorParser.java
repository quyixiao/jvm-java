package com.jvm.rtda.heap;

import com.jvm.utils.ExceptionUtils;
import com.jvm.utils.StringUtils;

public class MethodDescriptorParser {

    public String raw;
    public int offset;
    public MethodDescriptor parsed;


    public MethodDescriptor parseMethodDescriptor(String descriptor) {
        return this.parse(descriptor);
    }

    public MethodDescriptor parse(String descriptor) {
        this.raw = descriptor;
        this.parsed = new MethodDescriptor();
        this.startParams();
        this.parseParamTypes();
        this.endParams();
        this.parseReturnType();
        this.finish();
        return this.parsed;
    }

    public void startParams() {
        if (this.readUint8() != '(') {
            this.causePanic();
        }
    }

    public void endParams() {
        if (this.readUint8() != ')') {
            this.causePanic();
        }
    }

    public void finish() {
        if (this.offset != this.raw.length()) {
            this.causePanic();
        }
    }

    public void causePanic() {
        ExceptionUtils.throwException("BAD descriptor: " + this.raw);
    }

    public int readUint8() {
        char b = this.raw.toCharArray()[this.offset];
        this.offset++;
        return b;
    }

    public void unreadUint8() {
        this.offset--;
    }

    public void parseParamTypes() {
        while (true) {
            String t = this.parseFieldType();
            if (StringUtils.isNotBlank(t)) {
                this.parsed.addParameterType(t);
            } else {
                break;
            }
        }
    }

    public void parseReturnType() {
        if (this.readUint8() == (int) 'V') {
            this.parsed.returnType = "V";
            return;
        }

        this.unreadUint8();
        String t = this.parseFieldType();
        if (StringUtils.isNotBlank(t)) {
            this.parsed.returnType = t;
            return;
        }
        this.causePanic();
    }

    public String parseFieldType() {
        switch (this.readUint8()) {
            case 'B':
                return "B";
            case 'C':
                return "C";
            case 'D':
                return "D";
            case 'F':
                return "F";
            case 'I':
                return "I";
            case 'J':
                return "J";
            case 'S':
                return "S";
            case 'Z':
                return "Z";
            case 'L':
                return this.parseObjectType();
            case '[':
                return this.parseArrayType();
            default:
                this.unreadUint8();
                return "";
        }
    }

    public String parseObjectType() {
        String unread = this.raw.substring(this.offset);
        int semicolonIndex = unread.indexOf(unread, ';');
        if (semicolonIndex == -1) {
            this.causePanic();
            return "";
        } else {
            int objStart = this.offset - 1;
            int objEnd = this.offset + semicolonIndex + 1;
            this.offset = objEnd;
            String descriptor = this.raw.substring(objStart, objEnd);
            return descriptor;
        }
    }

    public String parseArrayType() {
        int arrStart = this.offset - 1;
        this.parseFieldType();
        int arrEnd = this.offset;
        String descriptor = this.raw.substring(arrStart, arrEnd);
        return descriptor;
    }

}
