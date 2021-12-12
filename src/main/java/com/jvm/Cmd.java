package com.jvm;

import lombok.Data;

@Data
public class Cmd {

    private boolean helpFlag;
    private boolean versionFlag;
    private String cpOption;
    private String jclass;
    private String[] args;
    private String XjreOption;


}
