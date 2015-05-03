package ua.nure.bpid.hash.Sum;

public class LRCHashSum implements HashSum {
    @Override
    public long hash(byte[] source) {
        long hash = 0;
        for (byte b : source)
            hash = (hash + b) & 0xFF;
        hash = ((hash ^ 0xFF) + 1) & 0xFF;
        return hash;
    }
}
