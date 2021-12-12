package com.jvm.utils;

public class Filepath {





    public static  String join( String ... args){
        StringBuilder sb = new StringBuilder(args[0]);
        for(int i = 1 ;i < args.length ;i ++){
            if(i < args.length  ){
                sb.append("/");
            }
            sb.append(args[i]);
        }
        return sb.toString();
    }


}
