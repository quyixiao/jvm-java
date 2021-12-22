package com.jvm;

import lombok.Data;

@Data
public class Cmd {

    public boolean helpFlag;
    public boolean versionFlag;
    public String cpOption;
    public String jclass;
    public String[] args;
    public String XjreOption;

    public  boolean verboseInstFlag;

}
