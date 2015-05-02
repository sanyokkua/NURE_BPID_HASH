package ua.nure.bpid.hash.Sum;

/**
 * Created by Александр on 02.05.2015.
 */
public class FAQ6HashSum implements HashSum {

    @Override
    public long Hash(byte[] source) {
        long hash = 0;
        for (byte b : source) {
            hash += b;
            hash += (hash << 10);
            hash ^= (hash >> 6);
        }
        hash += (hash << 3);
        hash ^= (hash >> 11);
        hash += (hash << 15);
        return hash;
    }
}
