package me.ygy.jjvm.instructions.stores.xa;

import me.ygy.jjvm.instructions.base.NoOperandsInstruction;
import me.ygy.jjvm.instructions.loads.xa.AALoad;
import me.ygy.jjvm.rtda.Frame;
import me.ygy.jjvm.rtda.OperandStack;
import me.ygy.jjvm.rtda.heap.Objectz;

/**
 * Created by guangyuanyu on 2016/10/17.
 */
public class DAStore extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        double val = stack.popDouble();
        int index = stack.popInt();
        Objectz arrRef = stack.popRef();
        AALoad.checkNotNull(arrRef);
        double[] doubles = arrRef.doubles();
        AALoad.checkIndex(doubles.length, index);
        doubles[index] = val;
    }
}
