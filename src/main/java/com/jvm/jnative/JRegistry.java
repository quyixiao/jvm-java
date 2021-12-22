package com.jvm.jnative;

import com.jvm.jnative.java.io.JFileDescriptor;
import com.jvm.jnative.java.io.JFileOSWriteBytes;
import com.jvm.jnative.java.io.UnFSgetBooleanAttributes0;
import com.jvm.jnative.java.lang.RuntimeAvailableProcessors;
import com.jvm.jnative.java.lang.StringIntern;
import com.jvm.jnative.java.lang.ThrowableFillInStackTrace;
import com.jvm.jnative.java.lang.jclass.*;
import com.jvm.jnative.java.lang.jdouble.DoubleToRawLongBits;
import com.jvm.jnative.java.lang.jdouble.FloatToRawIntBits;
import com.jvm.jnative.java.lang.jdouble.IntBitsToFloat;
import com.jvm.jnative.java.lang.jdouble.LongBitsToDouble;
import com.jvm.jnative.java.lang.jobject.ObjectClone;
import com.jvm.jnative.java.lang.jobject.ObjectGetClass;
import com.jvm.jnative.java.lang.jobject.ObjectHashCode;
import com.jvm.jnative.java.lang.jobject.ObjectNotifyAll;
import com.jvm.jnative.java.lang.jsystem.*;
import com.jvm.jnative.java.lang.jthread.ThreadCurrentThread;
import com.jvm.jnative.java.lang.jthread.ThreadIsAlive;
import com.jvm.jnative.java.lang.jthread.ThreadSetPriority0;
import com.jvm.jnative.java.lang.jthread.ThreadStart0;
import com.jvm.jnative.java.security.AControllerDoPrivileged;
import com.jvm.jnative.java.security.AControllerGetStackAccessControlContext;
import com.jvm.jnative.java.sun.io.Win32ErrorModeSetErrorMode;
import com.jvm.jnative.java.sun.misc.URLClassPathgetLookupCacheURLs;
import com.jvm.jnative.java.sun.misc.VMInitialize;
import com.jvm.jnative.java.sun.misc.unsafe.*;
import com.jvm.jnative.java.sun.reflect.NativeCAccessorImplNewInstance0;
import com.jvm.jnative.java.sun.reflect.ReflectionGetCallerClass;
import com.jvm.jnative.java.sun.reflect.ReflectionGetClassAccessFlags;
import com.jvm.jnative.java.util.concurrent.atomic.AtomicLongVmSupportsCS8;

import java.util.HashMap;
import java.util.Map;

public class JRegistry {

    //把本地方法定义成一个函数，参数是Frame结构体指针
    //这个frame参数就是本地方法的工作空间，也就是连接Java 虚拟机和Java类库的桥梁，后面会看到它是如何发挥作用的。

    public final static Map<String, Class<? extends JNativeMethod>> registry = new HashMap<>();

    static {
        Register("java/io/FileDescriptor", "set", "(I)J", JFileDescriptor.class);
        Register("java/io/FileOutputStream", "writeBytes", "([BIIZ)V", JFileOSWriteBytes.class);
        Register("java/io/UnixFileSystem", "getBooleanAttributes0", "(Ljava/io/File;)I", UnFSgetBooleanAttributes0.class);
        Register("java/lang/Class", "getPrimitiveClass", "(Ljava/lang/String;)Ljava/lang/Class;", ClassGetPrimitiveClass.class);
        Register("java/lang/Class", "getName0", "()Ljava/lang/String;", ClassGetName0.class);
        Register("java/lang/Class", "desiredAssertionStatus0", "(Ljava/lang/Class;)Z", ClassDesiredAssertionStatus0.class);
        Register("java/lang/Class", "isInterface", "()Z", ClassIsInterface.class);
        Register("java/lang/Class", "isPrimitive", "()Z", ClassIsPrimitive.class);
        Register("java/lang/Class", "getDeclaredFields0", "(Z)[Ljava/lang/reflect/Field;", ClassGetDeclaredFields0.class);
        Register("java/lang/Class", "forName0", "(Ljava/lang/String;ZLjava/lang/ClassLoader;Ljava/lang/Class;)Ljava/lang/Class;", ClassForName0.class);
        Register("java/lang/Class", "getDeclaredConstructors0", "(Z)[Ljava/lang/reflect/Constructor;", ClassGetDeclaredConstructors0.class);
        Register("java/lang/Class", "getModifiers", "()I", ClassGetModifiers.class);
        Register("java/lang/Class", "getSuperclass", "()Ljava/lang/Class;", ClassGetSuperclass.class);
        Register("java/lang/Class", "getInterfaces0", "()[Ljava/lang/Class;", ClassGetInterfaces0.class);
        Register("java/lang/Class", "isArray", "()Z", ClassIsArray.class);
        Register("java/lang/Class", "getDeclaredMethods0", "(Z)[Ljava/lang/reflect/Method;", ClassGetDeclaredMethods0.class);
        Register("java/lang/Class", "getComponentType", "()Ljava/lang/Class;", ClassGetComponentType.class);
        Register("java/lang/Class", "isAssignableFrom", "(Ljava/lang/Class;)Z", ClassIsAssignableFrom.class);

        Register( "java/lang/Double" , "doubleToRawLongBits", "(D)J", DoubleToRawLongBits.class);
        Register( "java/lang/Double" ,   "longBitsToDouble", "(J)D", LongBitsToDouble.class);

        Register("java/lang/Float", "floatToRawIntBits", "(F)I", FloatToRawIntBits.class);
        Register("java/lang/Float", "intBitsToFloat", "(I)F", IntBitsToFloat.class);


        Register("java/lang/Object", "getClass", "()Ljava/lang/Class;", ObjectGetClass.class);
        Register("java/lang/Object", "hashCode", "()I", ObjectHashCode.class);
        Register("java/lang/Object", "clone", "()Ljava/lang/Object;", ObjectClone.class);
        Register("java/lang/Object", "notifyAll", "()V", ObjectNotifyAll.class);

        Register("java/lang/Runtime", "availableProcessors", "()I", RuntimeAvailableProcessors.class);

        Register("java/lang/String", "intern", "()Ljava/lang/String;", StringIntern.class);


        Register("java/lang/System", "arraycopy", "(Ljava/lang/Object;ILjava/lang/Object;II)V", SystemArrayCopy.class);
        Register("java/lang/System", "initProperties", "(Ljava/util/Properties;)Ljava/util/Properties;", SystemInitProperties.class);
        Register("java/lang/System", "setIn0", "(Ljava/io/InputStream;)V", SystemSetIn0.class);
        Register("java/lang/System", "setOut0", "(Ljava/io/PrintStream;)V", SystemSetOut0.class);
        Register("java/lang/System", "setErr0", "(Ljava/io/PrintStream;)V", SystemSetErr0.class);
        Register("java/lang/System", "currentTimeMillis", "()J", SystemCurrentTimeMillis.class);

        Register("java/lang/Thread", "currentThread", "()Ljava/lang/Thread;", ThreadCurrentThread.class);
        Register("java/lang/Thread", "setPriority0", "(I)V", ThreadSetPriority0.class);
        Register("java/lang/Thread", "isAlive", "()Z", ThreadIsAlive.class);
        Register("java/lang/Thread", "start0", "()V", ThreadStart0.class);

        Register("java/lang/Throwable", "fillInStackTrace", "(I)Ljava/lang/Throwable;", ThrowableFillInStackTrace.class);

        Register("java/security/AccessController", "doPrivileged", "(Ljava/security/PrivilegedAction;)Ljava/lang/Object;", AControllerDoPrivileged.class);
        Register("java/security/AccessController", "doPrivileged", "(Ljava/security/PrivilegedExceptionAction;)Ljava/lang/Object;", AControllerDoPrivileged.class);
        Register("java/security/AccessController", "getStackAccessControlContext", "()Ljava/security/AccessControlContext;", AControllerGetStackAccessControlContext.class);

        Register("java/util/concurrent/atomic/AtomicLong", "VMSupportsCS8", "()Z", AtomicLongVmSupportsCS8.class);
        Register("sun/io/Win32ErrorMode", "setErrorMode", "(J)J", Win32ErrorModeSetErrorMode.class);

        Register("sun/misc/Unsafe", "arrayBaseOffset", "(Ljava/lang/Class;)I", UnsafeArrayBaseOffset.class);
        Register("sun/misc/Unsafe", "arrayIndexScale", "(Ljava/lang/Class;)I", UnsafeArrayIndexScale.class);
        Register("sun/misc/Unsafe", "addressSize", "()I", UnsafeAddressSize.class);
        Register("sun/misc/Unsafe", "objectFieldOffset", "(Ljava/lang/reflect/Field;)J", UnsafeObjectFieldOffset.class);
        Register("sun/misc/Unsafe", "compareAndSwapObject", "(Ljava/lang/Object;JLjava/lang/Object;Ljava/lang/Object;)Z", UnsafeCompareAndSwapObject.class);
        Register("sun/misc/Unsafe", "getIntVolatile", "(Ljava/lang/Object;J)I", UnsafeGetInt.class);
        Register("sun/misc/Unsafe", "compareAndSwapInt", "(Ljava/lang/Object;JII)Z", UnsafeCompareAndSwapInt.class);
        Register("sun/misc/Unsafe", "getObjectVolatile", "(Ljava/lang/Object;J)Ljava/lang/Object;", UnsafeGetObject.class);
        Register("sun/misc/Unsafe", "compareAndSwapLong", "(Ljava/lang/Object;JJJ)Z", UnsafeCompareAndSwapLong.class);
        Register("sun/misc/Unsafe", "allocateMemory", "(J)J", UnsafeAllocateMemory.class);
        Register("sun/misc/Unsafe", "reallocateMemory", "(JJ)J", UnsafeReallocateMemory.class);
        Register("sun/misc/Unsafe", "freeMemory", "(J)V", UnsafeFreeMemory.class);
        Register("sun/misc/Unsafe", "getByte", "(J)B", UnsafeMem_getByte.class);
        Register("sun/misc/Unsafe", "putLong", "(JJ)V", UnsafeMem_putLong.class);

        Register("sun/misc/URLClassPath", "getLookupCacheURLs", "(Ljava/lang/ClassLoader;)[Ljava/net/URL;", URLClassPathgetLookupCacheURLs.class);

        Register("sun/misc/VM", "initialize", "()V", VMInitialize.class);


        Register("sun/reflect/NativeConstructorAccessorImpl", "newInstance0", "(Ljava/lang/reflect/Constructor;[Ljava/lang/Object;)Ljava/lang/Object;", NativeCAccessorImplNewInstance0.class);

        Register("sun/reflect/Reflection", "getCallerClass", "()Ljava/lang/Class;", ReflectionGetCallerClass.class);
        Register("sun/reflect/Reflection", "getClassAccessFlags", "(Ljava/lang/Class;)I", ReflectionGetClassAccessFlags.class);


    }

    public static void Register(String className, String methodName, String methodDescriptor, Class<? extends JNativeMethod> method) {
        //类名、方法名和方法描述符加在一起才能唯一确定一个方法， 所以把它们的组合作为本地方法注册表的键
        String key = className + "~" + methodName + "~" + methodDescriptor;
        registry.put(key, method);
    }

    //FindNativeMethod()方法根据类名、方法名和方法描述符查找 本地方法实现，如果找不到，则返回nil
    public static JNativeMethod FindNativeMethod(String className, String methodName, String methodDescriptor) {
        String key = className + "~" + methodName + "~" + methodDescriptor;
        Class<? extends JNativeMethod> method = registry.get(key);
        if (method != null) {
            try {
                return method.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (methodDescriptor.equals("()V")) {
            if (methodName.equals("registerNatives") || methodName.equals("initIDs")) {
                return new JEmptyNativeMethod();
            }
        }
        return null;
    }
}
