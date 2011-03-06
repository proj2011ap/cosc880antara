package test;

import java.util.Vector;

import junit.framework.TestCase;

public class MyTestCase extends TestCase {

	private Vector container;

	public MyTestCase() {
		// TODO Auto-generated constructor stub
	}

	public MyTestCase(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setUp() throws Exception {
		container= new Vector();
		super.setUp();
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}

	public Vector getContainer() {
		return container;
	}

	public void setContainer(Vector container) {
		this.container = container;
	}

}
