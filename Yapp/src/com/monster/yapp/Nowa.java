package com.monster.yapp;

class Nowa {
static int objID = 0;
static int finalized = 0;
int i;

Nowa() {
i=++objID;
System.out.println("Object NOWA created: " + i);
}

public void finalize() {
System.out.println("Object NOWA deleted: id=" + i);
finalized++;

}
}