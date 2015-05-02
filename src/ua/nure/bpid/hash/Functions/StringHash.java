package ua.nure.bpid.hash.Functions;

/**
 * Created by Александр on 02.05.2015.
 */
public class StringHash implements HashFunction {
    @Override
    public int[] hash(byte[] source) {
        if (source.length < 8 || source.length % 8 != 0)
            throw new IllegalArgumentException();
        StringBuilder sb = new StringBuilder();

        for (byte b : source)
        sb.append(b);
        String hashCode = sb.toString().hashCode() + "";
        hashCode.getBytes();

        return new int[0];
    }
}
