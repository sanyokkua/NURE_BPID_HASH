package com.kostenko.hash.sum;


public class FAQ6HashSum implements HashSum {

    @Override
    public long hash(byte[] source) {
        long hash = 0;
        for (byte b : source) {
            hash += b;
            hash += (hash << 10);
            hash ^= (hash >>> 6);
        }
        hash += (hash << 3);
        hash ^= (hash >>> 11);
        hash += (hash << 15);
        return hash;
    }
}
