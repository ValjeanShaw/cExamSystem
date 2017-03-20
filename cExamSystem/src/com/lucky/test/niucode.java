package com.lucky.test;

import java.util.Scanner;

/**
 * Created by 风萧萧兮 on 2017/2/25.
 */
public class niucode {

    //测试跳出多重循环
    public static void main(String args[]){
        abc:
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                if(j == 6){
                    break abc;
                }
                System.out.println(i+"-"+j);
            }
        }

    }

}
