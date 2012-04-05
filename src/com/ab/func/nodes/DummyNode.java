package com.ab.func.nodes;

import com.ab.func.util.BaseError;
import com.ab.func.util.BaseException;

public class DummyNode extends BaseNode {

	public DummyNode()
	{
		SIZE = 1;
	}
	public DummyNode(BaseNode x)
	{
		SIZE = 1;
		nodes.add(x);
	}
	public int eval() throws BaseException
	{
		if(!validate()) throw new BaseException(getClass()+" is not in valid state");
		return nodes.get(0).eval();
	}
	@Override
	public void addNode(BaseNode node) {
		if(nodes.size()>=SIZE)
		{
			throw new BaseError("Cant add more than 1 node to DummyNode.");
		}
		super.addNode(node);
	}
}
