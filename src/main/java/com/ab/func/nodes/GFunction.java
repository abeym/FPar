package com.ab.func.nodes;

import com.ab.func.util.BaseError;
import com.ab.func.util.BaseException;

public class GFunction extends BaseNode {

	public GFunction()
	{
		SIZE = 2;
	}
	public GFunction(BaseNode x, BaseNode y)
	{
		SIZE = 2;
		nodes.add(0,x);
		nodes.add(1,y);
	}
	public int eval() throws BaseException
	{
		if(!validate()) throw new BaseException(getClass()+" is not in valid state");
		return nodes.get(0).eval()+nodes.get(1).eval();
	}
	@Override
	public void addNode(BaseNode node) {
		if(nodes.size()>=SIZE)
		{
			throw new BaseError("Cant add more than " + SIZE + " nodes to GFunction.");
		}
		super.addNode(node);
	}
}
