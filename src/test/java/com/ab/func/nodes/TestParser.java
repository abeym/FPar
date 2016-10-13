package com.ab.func.nodes;

import junit.framework.TestCase;
import com.ab.func.nodes.*;
import com.ab.func.parser.Parser;
import com.ab.func.util.BaseException;

public class TestParser extends TestCase {
	private static String ERR_MSG_PARSE = "Malformed expression.";
	private static String ERR_MSG_LEXICAL = "Invalid input.";
	//
	public void testParser1() {
		String inp;
		int output;
		int result;
		Parser parser = new Parser();
		//
		inp = "f(f(3))" ;
		result = 12;
		try {
			output = parser.handleInput(inp);
			assertEquals(result, output);
		} catch (BaseException e) {
			fail("Exception not expected : "+e);
			e.printStackTrace();
		}
	}
	
	/*private int testParser(String inp) throws BaseException{
		int output;
		Parser parser = new Parser();
		//
		output = parser.handleInput(inp);
		return output;
	}*/
	//
	public void testParser2() {
		String inp;
		int output;
		int result;
		Parser parser = new Parser();
		//
		inp = "g(f(2),g(f(1),5))" ;
		result = 11;
		try {
			output = parser.handleInput(inp);
			assertEquals(result, output);
		} catch (BaseException e) {
			fail("Exception not expected : "+e);
			e.printStackTrace();
		}
	}
	//
	public void testParser3() {
		String inp;
		int output;
		int result;
		Parser parser = new Parser();
		//
		inp = "15" ;
		result = 15;
		try {
			output = parser.handleInput(inp);
			assertEquals(result, output);
		} catch (BaseException e) {
			fail("Exception not expected : "+e);
			e.printStackTrace();
		}
	}
	//
	public void testParser4() {
		String inp;
		int output;
		Parser parser = new Parser();
		//
		inp = "x blargh f(y)" ;
		try {
			output = parser.handleInput(inp);
			fail("This should have failed with :"+ERR_MSG_LEXICAL);
		} catch (BaseException e) {
			assertEquals(ERR_MSG_LEXICAL, e.getMessage());
			e.printStackTrace();
		}
	}
	//
	public void testParser5() {
		String inp;
		int output;
		Parser parser = new Parser();
		//
		inp = "g(1)" ;
		try {
			output = parser.handleInput(inp);
			fail("This should have failed with :"+ERR_MSG_PARSE);
		} catch (BaseException e) {
			assertEquals(ERR_MSG_PARSE, e.getMessage());
			e.printStackTrace();
		}
	}
	//
	public void testParser6() {
		String inp;
		int output;
		Parser parser = new Parser();
		//
		inp = "f(1, 2, 3)" ;
		try {
			output = parser.handleInput(inp);
			fail("This should have failed with :"+ERR_MSG_PARSE);
		} catch (BaseException e) {
			assertEquals(ERR_MSG_PARSE, e.getMessage());
			e.printStackTrace();
		}
	}
	//
	public void testParser7() {
		String inp;
		int output;
		Parser parser = new Parser();
		//
		inp = "1 2 3 f(3)" ;
		try {
			output = parser.handleInput(inp);
			fail("This should have failed with :"+ERR_MSG_LEXICAL);
		} catch (BaseException e) {
			assertEquals(ERR_MSG_LEXICAL, e.getMessage());
			e.printStackTrace();
		}
	}
}
