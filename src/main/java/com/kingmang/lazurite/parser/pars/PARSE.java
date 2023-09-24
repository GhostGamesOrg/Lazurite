package com.kingmang.lazurite.parser.pars;

import com.kingmang.lazurite.core.Arguments;
import com.kingmang.lazurite.runtime.LZR.LZRNumber;
import com.kingmang.lazurite.runtime.Value;

public final class PARSE {
    private PARSE() { }

    static Value parseInt(Value[] args) {
        Arguments.checkOrOr(1, 2, args.length);
        final int radix = (args.length == 2) ? args[1].asInt() : 10;
        return LZRNumber.of(Integer.parseInt(args[0].asString(), radix));
    }
    static Value parseLong(Value[] args) {
        Arguments.checkOrOr(1, 2, args.length);
        final int radix = (args.length == 2) ? args[1].asInt() : 10;
        return LZRNumber.of(Long.parseLong(args[0].asString(), radix));
    }


}
