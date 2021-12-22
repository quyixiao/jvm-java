package com.jvm.jnative.java.io;

public class Lazybuf  {

    public  String path       ;
    public  byte [] buf;
    public  int w          ;
    public  String volAndPath ;
    public  int volLen     ;

    public Lazybuf(String path, String volAndPath, int volLen) {
        this.path = path;
        this.volAndPath = volAndPath;
        this.volLen = volLen;
    }



    public  void  append(char b ) {
        byte  c = (byte)b;
        if (this.buf == null){
            if (this.w < this.path.length() && (int)getIndexChar(this.path , this.w)== (int)c ){
                this.w++;
                return;
            }
            this.buf = new byte[this.path.length()];
            char []x = this.path.toCharArray();

            for(int i =  0;i < this.w ;i ++){
                this.buf [i] = (byte)x[i];
            }
        }
        this.buf[this.w] = c;
        this.w++;
    }


    public char   index(int i )  {
        if (this.buf != null){
            return(char ) this.buf[i];
        }
        return this.path.toCharArray()[i];
    }

    public char getIndexChar(String s ,int i ){
        return s.toCharArray()[i];
    }

    public String  string() {
        if(this.buf == null){
            return this.volAndPath.substring(0,this.volLen+this.w);
        }
        byte s [] = new byte[this.w];
        System.arraycopy(this.buf, 0 ,  s, 0 , this.w);
        return this.volAndPath.substring(0,this.volLen) + new String(s);
    }


}
