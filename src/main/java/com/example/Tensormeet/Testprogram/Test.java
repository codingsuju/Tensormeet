package com.example.Tensormeet.Testprogram;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
class code{
    int x;
    int y;

    public code(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
public class Test {
    public static void main(String [] args){
        List<code> a=new ArrayList<>();
        a.add(new code(2,3));
        a.add(new code(4,2));
        a.add(new code(4,5));
        Collections.sort(a,(code i,code j)->{
            int res=0;
            if(i.y<j.y) res=-1;
            else if(i.y>j.y)res=1;
            return res;
        });
        for(code c:a) System.out.println(c.y);
    }
}

