package com.xuranus.amx;

import org.junit.Test;

import java.util.ArrayList;

public class EUnitTest {

    @Test
    public void test() {
        ArrayList<> os = new Object[]{new A(), new B()};
        for (Object o: os) {
            System.out.println(o.getClass());
        }
    }

    private class A {

    }

    private class B {

    }
}
