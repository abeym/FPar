package com.ab.func.nodes;

import com.ab.func.util.BaseError;

public class IntNode extends BaseNode {

	private int x;
	public IntNode(int x)
	{
		SIZE = 1;
		this.x = x;
	}
	public int eval()
	{
		return x;
	}
	@Override
	public BaseNode getNode(int i) {
		throw new BaseError("Int Node doesnt have children");
	}
	@Override
	public void addNode(BaseNode node) {
		throw new BaseError("Cant add nodes to IntNode");
	}
}
