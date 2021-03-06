package me.ygy.jjvm.classfile.constant.impl;

import me.ygy.jjvm.classfile.ClassReader;
import me.ygy.jjvm.classfile.constant.ConstantInfo;
import me.ygy.jjvm.classfile.constant.ConstantPool;

/**
 * Author: guangyuanyu
 * Email: guangyuanyu@sohu-inc.com
 * Date: 2016/10/11 16:37.
 */
public class ConstantClassInfo implements ConstantInfo {

    private ConstantPool pool;
    private int nameIndex;

    public int getNameIndex() {
        return nameIndex;
    }

    public ConstantClassInfo(ConstantPool pool) {
        this.pool = pool;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.nameIndex = reader.readUint16();
    }

    public String name() {
        return this.pool.getUtf8(this.nameIndex);
    }
}
