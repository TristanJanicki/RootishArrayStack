package comp2402a3;

import java.util.Arrays;

public class RootishArrayStack {

	
	//RootishArrayStack blocks;
	
	int n;
	Object[][] blocks;
	//int[] sqrtab = new int[];

	public RootishArrayStack() {
		n = 0;
		blocks = new Object[1][1];
	}
	
	private int indexToBlock(int i) {
		double eq = (-3.0 + Math.sqrt((9 + (i * 8)))) / 2.0;
		return (int) Math.ceil(eq);
	}

	public boolean isEmpty() {
		return n == 0;
	}

	public <T> T getLastElement() {
		//System.out.println("getLastEle, Blocks look like " + Arrays.deepToString(blocks));
		return blocks.length == 0 ? null : (T) get(n - 1);
	}
	
	public int getLastIndex() {
		int li = 0;
		
		for(Object o : getLastList()) {
			if(o == null) {
				return li;
			}
			li ++;
		}
		return li;
	}
	
	public <T> T removeLastElement() {
		return (T) remove(n-1);
	}
	
	public Object[] getLastList() {
		return blocks[blocks.length - 1];
	}
	
	public <T> void addToEnd(T x) {
		add(n, x);
	}

	public <T> T getFirst() {
		return (T) blocks[0][0];
	}
	
	private void grow() {

		if(blocks.length == 0){
			blocks = new Object[1][1];
			return;
		}

		Object[][] newBlocks = new Object[blocks.length + 1][];

		for(int i = 0; i < newBlocks.length; i ++){
			Object[] newestBlock = new Object[i + 1];
			if(i < blocks.length){

				for(int j = 0; j < blocks[i].length; j ++){
					newestBlock[j] = blocks[i][j];
				}
			}

			newBlocks[i] = newestBlock;
		}

		blocks = newBlocks;

		System.out.println("After growth " + Arrays.deepToString(blocks));
		System.out.flush();
	}
	
	public void print() {
		System.out.print("Max Stack = ");
		System.out.flush();
		for(Object [] oa : blocks) {
			for(Object o : oa) {
				//System.out.println("typeof o = " + o.getClass());
				System.out.print(o + ",");
				System.out.flush();
			}
		}
		System.out.print( " XXXXXXXXXXXXXXXXXXXXXXXXXXX ");
		System.out.flush();
	}
	
	public <T> T removeFirstElement() {
		return (T) remove(0);
	}
	
	public <T> T getFirstElement() {
		return blocks.length != 0 ? (T) blocks[0][0] : null;
	}
	
	public Object get(int i) {
		if (i < 0) return null;
		int block = indexToBlock(i);
		int index = (i - block * (block + 1) / 2);
		//System.out.println("block " + block + " = " + Arrays.toString(blocks.get(block)));
		return blocks[block][index];
	}
	
	public Object set(int i, Object x) {
		
		int block = indexToBlock(i);
		int index = i - block * (block + 1) / 2;
		//System.out.println("index = " + index);
		//System.out.println("Block = " + block);
		//System.out.println("Block " + block + " = " + Arrays.toString(blocks.get(index)));
		//System.out.flush();
		blocks[block][index] = x;
		return blocks[block][index];
		
	}
	
	public <T> void add(int i, Object x) {
		int r = blocks.length;
		//System.out.println("Adding object " + x.toString());
		if (r * (r + 1 ) / 2 < n + 1) grow();
		
		n ++;

		for(int j = n - 1; j > i; j --) {
			set(j, get(j - 1));
		}

		set(i, x);

		//System.out.println("After add Blocks looks like " + Arrays.deepToString(blocks));
		//System.out.println("Post add x looks like " + get(i));
	}

	public Object remove(int i) {
		Object removed = get(i);
		
		for(int j = i; j < n - 1; j ++) {
			set(j, get(j+1));
		}
		
		n --;
		
		int r = blocks.length;
		
		if ((      (r - 2)    *    (r - 1)      ) / 2 >= n) shrink();
		
		return removed;
	}
	
	private void shrink() {
		int r = blocks.length;

		/// TODO: FINISH THIS LATER. IT SHOULD PROBABLY DETERMINE THE INDEX UP TO WHICH SYSTEM.ARRAYCOPY SHOULD COPY UP UNTIL (& INCLUDING). THIS MIGHT BE EQUAL TO THE FINAL VALUE OF R

		while(r > 0 && (      (r - 2) * (r - 1)       ) / 2 >= n) {
			r--;
		}

		Object[][] newBlocks = new Object[r][];

		//System.out.println("Shrinking!!!");
		System.out.flush();

		for(int i = 0; i < r; i ++){

			newBlocks[i] = blocks[i];

			//System.out.println("newblock " + i + " Looks like " + Arrays.toString(newBlocks[i]));

			//System.arraycopy(blocks[i], 0, newBlocks[i], 0, blocks[i].length);

		}

		//System.out.println("Blocks after shrink looks like " + Arrays.deepToString(blocks));

		blocks = newBlocks;

	}
}
