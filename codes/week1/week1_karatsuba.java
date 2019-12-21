import java.math.BigInteger;
import java.util.*;

public class week1_karatsuba{
    public static BigInteger karatsuba(BigInteger num1, BigInteger num2){
        if(num1.compareTo(BigInteger.valueOf(10)) < 0 || num2.compareTo(BigInteger.valueOf(10)) < 0){
            return num1.multiply(num2);
        }
        
        int max_len = Math.max(num1.toString().length(), num2.toString().length());
        int split_pos = max_len/2 ;

        BigInteger[] low_high_num1 = num1.divideAndRemainder(BigInteger.valueOf(10).pow(split_pos));
        BigInteger high1 = low_high_num1[0];
        BigInteger low1 = low_high_num1[1];
        BigInteger[] low_high_num2 = num2.divideAndRemainder(BigInteger.valueOf(10).pow(split_pos));
        BigInteger high2 = low_high_num2[0];
        BigInteger low2 = low_high_num2[1];

        BigInteger z0 = karatsuba(low1,low2);
        BigInteger z1 = karatsuba(high1.add(low1), high2.add(low2));
        BigInteger z2 = karatsuba(high1, high2);
        BigInteger z4 = z1.subtract(z2).subtract(z0);
        // (z2*10^(2*m2))+((z1-z2-z0)*10^(m2))+(z0)
        return z2.multiply(BigInteger.valueOf(10).pow(2*split_pos)).add(z4.multiply(BigInteger.valueOf(10).pow(split_pos))).add(z0)  ;
    }
    public static void main(String[] args) { 
        String num1Str = "3141592653589793238462643383279502884197169399375105820974944592";
        String num2Str = "2718281828459045235360287471352662497757247093699959574966967627";
        BigInteger num1 = new BigInteger(num1Str);
        BigInteger num2 = new BigInteger(num2Str);
        System.out.println(num1.multiply(num2));
        System.out.println(karatsuba(num1, num2));
    }
}
