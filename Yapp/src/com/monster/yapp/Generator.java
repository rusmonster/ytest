package com.monster.yapp;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Generator {
	private static final Map<byte[], byte[]> cache = new HashMap<byte[], byte[]>();
	
	public static final byte[] generate(byte[] src) {
	    byte[] generated = cache.get(src);
	    if (generated == null) {
	        synchronized (cache) {
	            generated = cache.get(src);
	            if (generated == null) {
	                generated = doGenerate(src);
	                cache.put(src, generated);
	            }
	        }
	    }
	    return generated;
	}
	
	private static byte[] doGenerate(byte[] src) {
		byte[] ret = new byte[2];
		ret[0] = 0;
		for (int i=0; i<src.length; i++)
			ret[0]+=src[i];
		
		ret[1] = (byte)(Math.round( Math.random()*1000 ) & 0xFF);
		ret[0] += ret[1];
		return ret; 
	}
}
