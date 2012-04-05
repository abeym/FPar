package com.ab.func.nodes;

import java.util.ArrayList;

import com.ab.func.util.BaseError;
import com.ab.func.util.BaseException;

public abstract class BaseNode
{
	protected ArrayList<BaseNode> nodes = new ArrayList<BaseNode>();
	public abstract int eval() throws BaseException;
	protected int SIZE;
	
	public BaseNode getNode(int i)
	{
		try
		{
			return nodes.get(i);
		}
		catch(IndexOutOfBoundsException e)
		{
			throw new BaseError("No nodes present at position :"+i);
		}
	}
	
	public void addNode(BaseNode node)
	{
		nodes.add(node);
	}

	public boolean validate() {
		return nodes.size() == SIZE;
	}

}
