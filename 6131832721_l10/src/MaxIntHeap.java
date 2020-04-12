public class MaxIntHeap extends Heap{
	public void add(Object element) {
		if (++size == mData.length) {
			Object[] newHeap = new Object[2 * mData.length];
			System.arraycopy(mData, 0, newHeap, 0, size);
			mData = newHeap;
		}
		mData[size - 1] = element;
		percolateUp();
//		for(int i=0;i<mData.length;i++) {
//			System.out.println(mData[i]);
//		} 
	}
	public Object pop() throws Exception { //pop max value in heap
		// Add your code here
		if(size==0) 
			throw new Exception();
		Object temp=mData[0];//root(min value)
		int index = 0;
		for(int i=0;i<mData.length;i++) { // find max value
			if(mData[i]==null)
				break;
			if((int)mData[i]>(int)temp) {
				temp = mData[i];
				index =i;
			}
		}
		mData[index] = mData[size-1]; //put last element into that index
		size--; // delete data at last element
		percolateDown(index); //switch parent and child
		return temp;
	}
}
