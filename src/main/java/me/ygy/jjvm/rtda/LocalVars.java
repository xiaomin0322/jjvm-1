package me.ygy.jjvm.rtda;

import me.ygy.jjvm.rtda.heap.Objectz;

/**
 * Created by guangyuanyu on 2016/10/14.
 */
public class LocalVars {

    public static class Slot {
        public int num;
        public Objectz ref;
    }

    private Slot[] slots;

    public LocalVars(int maxLocals) {
        if (maxLocals > 0) {
            slots = new Slot[maxLocals];
            for (int i = 0; i < maxLocals; i++) {
                this.slots[i] = new Slot();
            }
        }
    }

    public void setInt(int index, int val) {
        this.slots[index].num = val;
    }

    public int getInt(int index) {
        return this.slots[index].num;
    }

    public void setFloat(int index, float val) {
        int i = Float.floatToIntBits(val);
        this.slots[index].num = i;
    }

    public float getFloat(int index) {
        int num = this.slots[index].num;
        return Float.intBitsToFloat(num);
    }

    public void setLong(int index, long val) {
        this.slots[index].num = (int)val;
        this.slots[index+1].num = (int)(val >>> 32);
    }

    public long getLong(int index) {
        long low = this.slots[index].num;
        long high = this.slots[index + 1].num;
        return (high << 32) + low;
    }

    public void setDouble(int index, double val) {
        long bits = Double.doubleToLongBits(val);
        this.setLong(index, bits);
    }

    public double getDouble(int index) {
        long bits = this.getLong(index);
        return Double.longBitsToDouble(bits);
    }

    public void setRef(int index, Objectz ref) {
        this.slots[index].ref = ref;
    }

    public Objectz getRef(int index) {
        return this.slots[index].ref;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("local vars: \n");
        Slot[] slots = this.slots;
        for (int i = 0; i < slots.length; i++) {
            Slot slot = slots[i];
            builder.append(i).append(" : num=").append(slot.num).append(" ref=").append(slot.ref).append("\n");
        }
        return builder.toString();
    }

    public void setSlot(int index, Slot slot) {
        this.slots[index].num = slot.num;
        this.slots[index].ref = slot.ref;
    }

}
