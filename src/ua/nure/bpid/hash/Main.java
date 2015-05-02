package ua.nure.bpid.hash;

import ua.nure.bpid.hash.Functions.HashFunction;
import ua.nure.bpid.hash.Functions.PearsonHash;
import ua.nure.bpid.hash.Functions.StringHash;
import ua.nure.bpid.hash.Sum.FAQ6HashSum;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        String temp = "Hello world!";
        StringBuilder sb = new StringBuilder();
        for (long i = 0; i < 12*8; i++)
            sb.append(temp);
        byte[] arr = sb.toString().getBytes();
        HashFunction hf = new StringHash();
        int[] res = hf.hash(arr);
        System.out.println(Arrays.toString(res));
    }
}
