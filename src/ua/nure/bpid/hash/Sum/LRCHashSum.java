package ua.nure.bpid.hash.Sum;

/**
 * Created by Александр on 02.05.2015.
 */
public class LRCHashSum implements HashSum {
    @Override
    public long Hash(byte[] source) {
        long hash = 0;
        for (byte b : source)
            hash = (hash + b) & 0xFF;
        hash = ((hash ^ 0xFF) + 1) & 0xFF;
        return hash;
    }
}
