package com.monster.yapp.test;

import com.monster.yapp.GCTest;
import com.monster.yapp.GCTest1;
import com.monster.yapp.Generator;

import junit.framework.TestCase;

public class YTestCase extends TestCase {

	public void testLeaks()
	{
		GCTest.main(null);
	}
	
	public void testLeaks1()
	{
		GCTest1.main();
	}
	
	public void testGenerator()
	{
		byte[] key = new byte[3];
		key[0] = 1;
		key[1] = 2;
		key[2] = 3;

		byte[] key2 = key.clone();

		byte[] g1 = Generator.generate(key);
		byte[] g2 = Generator.generate(key2);
		
		System.out.println("key="+key.hashCode()+"; key2="+key2.hashCode());
		System.out.println("g1[0]="+g1[0]+"; g1[1]="+g1[1]);
		System.out.println("g2[0]="+g2[0]+"; g2[1]="+g2[1]);
		
		key[0] = 0;
		key[1] = 123;
		
		g1 = Generator.generate(key);
		g2 = Generator.generate(key2);
		
		System.out.println();
		System.out.println("key="+key.hashCode()+"; key2="+key2.hashCode());
		System.out.println("g1[0]="+g1[0]+"; g1[1]="+g1[1]);
		System.out.println("g2[0]="+g2[0]+"; g2[1]="+g2[1]);
	}
}
