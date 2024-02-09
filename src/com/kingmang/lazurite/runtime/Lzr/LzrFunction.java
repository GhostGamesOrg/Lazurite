package com.kingmang.lazurite.runtime.Lzr;

import com.kingmang.lazurite.exceptions.LZRException;
import com.kingmang.lazurite.core.Function;
import com.kingmang.lazurite.core.Types;
import com.kingmang.lazurite.runtime.Value;
import lombok.Getter;

import java.util.Objects;


public class LzrFunction implements Value {

    public static final LzrFunction EMPTY = new LzrFunction(args -> LzrNumber.ZERO);

    @Getter
    private final Function value;

    public LzrFunction(Function value) {
        this.value = value;
    }
    
    @Override
    public int type() {
        return Types.FUNCTION;
    }
    
    @Override
    public Object raw() {
        return value;
    }
    
    @Override
    public int asInt() {
        throw new LZRException("TypeExeption","Cannot cast function to integer");
    }
    
    @Override
    public double asNumber() {
        throw new LZRException("TypeExeption","Cannot cast function to number");
    }

    @Override
    public String asString() {
        return value.toString();
    }

    @Override
    public int[] asArray() {
        return new int[0];
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.value);
        return hash;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass())
            return false;
        final LzrFunction other = (LzrFunction) obj;
        return Objects.equals(this.value, other.value);
    }
    
    @Override
    public int compareTo(Value o) {
        return asString().compareTo(o.asString());
    }
    
    @Override
    public String toString() {
        return asString();
    }
}
