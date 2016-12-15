package test.jvm;

import java.util.ArrayList;
import java.util.List;

public class Memery {
    static class OOMObject {
        public byte[] placeholder = new byte[64 * 1024];
    }

    public static void fillheap(int num) throws InterruptedException {
        List list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Thread.sleep(100);
            list.add(new OOMObject());
        }

    }

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(10000);
        fillheap(1000);
        System.gc();
    }
}
