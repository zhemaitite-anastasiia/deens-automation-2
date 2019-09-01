package io.testpro.deens.practice;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;

public class Practice {

    public static void main(String[] args) {

    }


    public String defangIPaddr(String address) {

//         String str = "";

//         for (int i = 0; i< address.length(); i++) {
//             if (address.charAt(i) == '.') {
//                 str = str + "[.]";
//             }
//             else {
//                 str = str + address.charAt(i);
//             }

//             }
//         return str;

        StringBuilder builder = new StringBuilder();
        for(char c: address.toCharArray()) {
            if (c == '.') {
                builder.append("[.]");
            }
            else {
                builder.append(c);
            }
        }
        return builder.toString();
    }

    public int numJewelsInStones(String J, String S) {

        int result = 0;
        Set setJ = new HashSet();

        for(char j: J.toCharArray()) {
            setJ.add(j);
        }
        for(char s: S.toCharArray()) {
            if(setJ.contains(s)) {
                result++;
            }
        }
        return result;

    }

    public int numJewelsInStonesFromMaksym(String J, String S) {

        int output =0;
        for (int i =0; i<J.length(); i++){
            for (int j=0; j<S.length(); j++){
                if (J.charAt(i)==S.charAt(j)){
                    output++;
                }
            }
        }
        return output;
    }



}
