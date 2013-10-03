package com.monster.yapp;

public class Garbage {
public static void main(String[] args) {

for (int i = 1; i < 5; i++) {
Nowa nowa = new Nowa();
}
System.out.println("Start GC_____");
System.gc();
System.out.println("Total finalized deleted obj-s = " + Nowa.finalized);

}
}