package jvm.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class ReferenceTest {
    public static void main(String[] args){
        /*Java的四种引用类型*/
        Object strongObj = new Object();
        Object softObj = new Object();
        Object weakObj = new Object();
        Object phantomObj = new Object();
        /*引用队列*/
        ReferenceQueue<Object> queue = new ReferenceQueue<>();
        /*强引用, gc不会回收*/
        Object strongReference = strongObj;
        /*软引用，当内存不足时被回收*/
        SoftReference<Object> softReference = new SoftReference<>(softObj);
        /*弱引用，只要gc就被回收*/
        WeakReference<Object> weakReference = new WeakReference<>(weakObj);
        /*虚引用，不存在引用，当有引用对象被回收时，被添加到引用队列中*/
        PhantomReference<Object> phantomReference = new PhantomReference<>(phantomObj, queue);
        System.out.println("{引用对象：被引用对象}");
        System.out.println("调用gc前：");
        print(strongReference,strongObj,softReference.get(),softObj,
                weakReference.get(),weakObj,phantomReference.get(),phantomObj);
        System.out.println("引用队列中是否存在引用对象？" + (queue.poll() != null));
        /*显示设置对象为null，弱化对象引用，帮助垃圾回收器回收对象*/
        strongObj = null; // 强引用不会被回收
        softObj = null; // 软引用
        weakObj = null;
        phantomObj = null;
        System.out.println("回收被引用对象：");
        print(strongReference,strongObj,softReference.get(),softObj,
                weakReference.get(),weakObj,phantomReference.get(),phantomObj);
        System.out.println("引用队列中是否存在引用对象？" + (queue.poll() != null));
        /*调用gc*/
        System.gc();
        System.out.println("调用gc后：");
        print(strongReference,strongObj,softReference.get(),softObj,
                weakReference.get(),weakObj,phantomReference.get(),phantomObj);
        System.out.println("引用队列中是否存在引用对象？" + (queue.poll() != null));
        /*
        -Xms128m JVM初始分配的堆内存
        -Xmx512m JVM最大允许分配的堆内存，按需分配
        -XX:PermSize=64M JVM初始分配的非堆内存
        -XX:MaxPermSize=128M JVM最大允许分配的非堆内存，按需分配
        */
        try {
            System.out.println("申请内存达到阈值使gc：");
            byte[] bytes = new byte[250 * 1024 * 1024];
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            print(strongReference,strongObj,softReference.get(),softObj,
                weakReference.get(),weakObj,phantomReference.get(),phantomObj);
        }
    }

    private static void print(Object...objs){
        System.out.printf("强引用: {%s : %s}, 软引用: {%s : %s},\n" +
                        "弱引用: {%s : %s}, 虚引用: {%s : %s}\n",
                objs);
    }

}
