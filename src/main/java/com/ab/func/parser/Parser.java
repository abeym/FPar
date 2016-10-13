package com.ab.func.parser;

import java.io.*;

import com.ab.func.nodes.BaseNode;
import com.ab.func.nodes.DummyNode;
import com.ab.func.nodes.FFunction;
import com.ab.func.nodes.GFunction;
import com.ab.func.nodes.IntNode;
import com.ab.func.util.*;

import java.util.*;

public class Parser {

	private static Logger logger = new Logger(Parser.class.getName());

	private static String ERR_MSG_PARSE = "Malformed expression.";
	private static String ERR_MSG_LEXICAL = "Invalid input.";

	public String getInput()
	{
		
		System.out.print("Please enter the formula:");
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		String inp = null;
		do {
			try {
				inp = bufferRead.readLine();
			} catch (IOException e) {
				logger.log(e.toString());
				System.exit(0);
			}
		} while (inp == null);
		return inp;
	}
	public int handleInput(String inp) throws BaseException
	{
		StringBuffer buf = new StringBuffer(inp);
		parseLexical(buf);
		BaseNode dummyNode = new DummyNode();
		try {
			Stack stack = new Stack<BaseNode>();
			stack.add(dummyNode);
			char[] charBuf = convertBufferToCharArray(buf);
			bufIndex = 0;
			parseGrammar(stack, charBuf);
		} catch (BaseError e) {
			System.out.println(ERR_MSG_PARSE);
			logger.log(e);
		}
		return dummyNode.eval();
	}
	
	public static void main(String[] args) {
		Parser parser = new Parser();
		String inp = parser.getInput();
		try {
			int output = parser.handleInput(inp);
		} catch (LexicalException e) {
			System.out.println(e.getMessage());
			logger.log(e);
		} catch (ParserException e) {
			System.out.println(ERR_MSG_PARSE);
			logger.log(e);
		} catch (BaseException e) {
			e.printStackTrace();
		}
	}
	//
	private char[] convertBufferToCharArray(StringBuffer buf)
	{
		int len = buf.length();
		char [] charArray = new char[len];
		for(int j=0; j<len; j++)
		{
			charArray[j]=buf.charAt(j);
		}
		return charArray;
	}
	//
	private boolean isNum(char c) {
		switch (c) {
		case '0':
		case '1':
		case '2':
		case '3':
		case '4':
		case '5':
		case '6':
		case '7':
		case '8':
			return true;
		default:
			return false;
		}
	}
	//
	private void parseLexical(StringBuffer buf) throws LexicalException {
		int len = buf.length();
		boolean prevCharNum = false;
		for (int i = 0; i < len; i++) {
			char c = buf.charAt(i);
			switch (c) {
			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
				prevCharNum = true;
				break;
			case ' ':
				if (!prevCharNum) {
					buf.deleteCharAt(i);
				} else if (!isNum(buf.charAt(i + 1))) {
					buf.deleteCharAt(i);
				} else {
					throw new LexicalException(ERR_MSG_LEXICAL);
				}
				i--;
				len--;
				break;
			case 'g':
			case 'f':
			case '(':
			case ')':
			case ',':
				prevCharNum = false;
				break;
			default:
				throw new LexicalException("Invalid input.");
			}
		}
	}
	//
	private int deciIncrement(int decimal, char unit) {
		return decimal * 10 + unit;
	}

	private int bufIndex;

	/*
	 * private boolean isNum(char c) { return ((0<=c) && (c<=9)); }
	 */
	private void parseGrammar(Stack<BaseNode> stack, char[] buf) throws BaseException {
		if(bufIndex>=buf.length)
		{
			return;
		}
		if (buf[bufIndex] == 'g') {
			if (buf[++bufIndex] == '(') {
				BaseNode newNode = new GFunction();
				BaseNode oldNode = stack.peek();
				oldNode.addNode(newNode);
				stack.push(newNode);
				++bufIndex;
				parseGrammar(stack, buf);
				//
				if(buf[bufIndex]==',')
				{
					bufIndex++;
					parseGrammar(stack, buf);
				} 
				else {
					throw new ParserException(ERR_MSG_PARSE);
				}
				//
				if(buf[bufIndex] == ')')
				{
					bufIndex++;
					stack.pop();
					return;
				} else {
					throw new ParserException(ERR_MSG_PARSE);
				}
			} else {
				throw new ParserException(ERR_MSG_PARSE);
			}
		} else if (buf[bufIndex] == 'f') {
			if (buf[++bufIndex] == '(') {
				BaseNode newNode = new FFunction();
				BaseNode oldNode = stack.peek();
				oldNode.addNode(newNode);
				stack.push(newNode);
				++bufIndex;
				parseGrammar(stack, buf);
				//
				/*if(buf[bufIndex]==',')
				{
					bufIndex++;
					parseGrammar(stack, buf);
				} else*/ 
				if(buf[bufIndex] == ')')
				{
					bufIndex++;
					stack.pop();
					return;
				} else {
					throw new ParserException(ERR_MSG_PARSE);
				}
			} else {
				throw new ParserException(ERR_MSG_PARSE);
			}
		} else if (isNum(buf[bufIndex])) {
			char[] num = getNextNumber(buf, bufIndex);
			int numInt = convertToInt(num);
			BaseNode newNode = new IntNode(numInt);
			BaseNode oldNode = stack.peek();
			oldNode.addNode(newNode);
			bufIndex=bufIndex+num.length;
			/*if(buf[bufIndex]==',')
			{
				bufIndex++;
				parseGrammar(stack, buf);
			} else if(buf[bufIndex] == ')')
			{
				bufIndex++;
				stack.pop();
				return;
			} else {
				throw new ParserException(ERR_MSG_PARSE);
			}*/
		} else {
			throw new ParserException(ERR_MSG_PARSE);
		}
	}
	
	private int convertToInt(char[] buf)
	{
		int power = buf.length-1;
		double factor = Math.pow(10, power);
		int result=0;
		for(int i=0; i<=power; i++)
		{
			result+=(buf[i]-48)*factor;
			factor/=10;
		}
		return result;
	}

	private char[] getNextNumber(char[] buf, int i) {
		StringBuffer nextNumber = new StringBuffer();
		while(i<buf.length)
		{
			if(isNum(buf[i]))
			{
				nextNumber.append(buf[i]);
				i++;
			}
			else
				break;
		}
		int len = nextNumber.length();
		char [] nexNumberArray = new char[len];
		for(int j=0; j<len; j++)
		{
			nexNumberArray[j]=nextNumber.charAt(j);
		}
		return nexNumberArray;
	}
}
