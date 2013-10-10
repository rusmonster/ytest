package com.monster.yapp.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import junit.framework.TestCase;

public class StrTestCase extends TestCase {
	
	public static Character findMax(String str) {
		if (str==null || str.length()==0) return null;
		
		Map<Character, Integer> m = new HashMap<Character, Integer>();
		
		
		for (int i=0; i<str.length(); i++) {
			Character c = Character.valueOf(str.charAt(i));
			if (!m.containsKey(c)) {
				m.put(c, 1);
			}
			else {
				m.put(c, m.get(c)+1);
			}
		}
		
		Entry<Character, Integer> max = null;
		for (Entry<Character, Integer> e : m.entrySet()) {
			System.out.println("c: "+e.getKey()+"; n: "+e.getValue());
			if (max==null || e.getValue()>max.getValue()) {
				max = e;
			}
		}
		
		Character res = max.getKey();
		System.out.println("max: "+res);
		System.out.println("max: "+max.getValue());
		return res;
	}
	
	public void testFindMax() {
		Character c = findMax("kdsjfh d ffkjdhsfowueof dsnfwkhfwedweidjsadnsf sdkfjhwllasd");
		assertEquals(c.charValue(), 'f');
	}
}
