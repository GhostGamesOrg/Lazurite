package com.kingmang.lazurite.libraries.system;

import com.kingmang.lazurite.core.Arguments;
import com.kingmang.lazurite.core.Function;
import com.kingmang.lazurite.libraries.Library;
import com.kingmang.lazurite.runtime.*;
import com.kingmang.lazurite.runtime.Lzr.LzrMap;
import com.kingmang.lazurite.runtime.Lzr.LzrNumber;
import com.kingmang.lazurite.runtime.Lzr.LzrString;
import com.kingmang.lazurite.utils.Handler;


import java.awt.*;
import java.util.Objects;

import static com.kingmang.lazurite.runner.RunnerInfo.VERSION;

public class system implements Library {

    public void init (){
        LzrMap system = new LzrMap(7);

        //functions
        system.set("currentTimeMillis", (Value...args) ->{
            Arguments.check(0, args.length);
            return LzrNumber.of(System.currentTimeMillis());
        });
        system.set("nanoTime", (Value...args) ->{
            Arguments.check(0, args.length);
            return LzrNumber.of(System.nanoTime());
        });

        system.set("exit", (Value... args) -> {
            Arguments.check(1, args.length);
            try {
                System.exit((int) args[0].asNumber());
            } finally {
                Thread.currentThread().interrupt();
            }
            return LzrNumber.MINUS_ONE;
        });

        system.set("getScreenResolution", new Function() {
            @Override
            public Value execute(Value... args) {
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                Dimension dim = toolkit.getScreenSize();
                LzrMap map = new LzrMap(2);
                map.set("width", new LzrNumber(dim.getWidth()));
                map.set("height", new LzrNumber(dim.getHeight()));
                return map;
            }
        });

        system.set("exec", new Function() {
            @Override
            public Value execute(Value... args) {
                Arguments.check(1, args.length);
                Handler.handle(args[0].toString(), "exec()", true, false);
                return LzrNumber.MINUS_ONE;
            }
        });
        system.set("getProperty", (Value...args) -> {
            if(Objects.equals(args[0].asString(), "lzr.version")){
                return new LzrString(VERSION());
            }
            return new LzrString(System.getProperty(args[0].asString()));

        });

        system.set("getUsedMemory", new Function() {
            @Override
            public Value execute(Value... args){
                return new LzrNumber((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));
            }
        });

        system.set("getTotalMemory", new Function() {
            @Override
            public Value execute(Value... args){
                return new LzrNumber((Runtime.getRuntime().totalMemory()));
            }
        });

        system.set("getMaxMemory", new Function() {
            @Override
            public Value execute(Value... args){
                return new LzrNumber((Runtime.getRuntime().maxMemory()));
            }
        });

        system.set("getFreeMemory", new Function() {
            @Override
            public Value execute(Value... args){
                return new LzrNumber((Runtime.getRuntime().freeMemory()));
            }
        });

        system.set("availableProcessors", new Function() {
            @Override
            public Value execute(Value... args){
                return new LzrNumber((Runtime.getRuntime().availableProcessors()));
            }
        });

        Variables.define("system",system);
    }

}
