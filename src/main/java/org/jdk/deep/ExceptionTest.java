package org.jdk.deep;

public class ExceptionTest {
	public static int test(){
	    int ret = 0;
	    try{
	        return ret;
	    }finally{
	        ret = 2;
	    }
	}
	
	public static void main(String[] args) {
		int result = ExceptionTest.test();
		
		System.out.println("result:" + result);

	}

}
