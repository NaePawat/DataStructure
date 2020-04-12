
public class HeapRunTime implements PriorityQ {
	public static void main(String[] args) throws Exception {
		long time =0l;
		long sum =0l;
		int[] a = new int[100];
		for(int i =0 ;i<a.length;i++) {
			a[i] = (int) Math.random();
		}
		int[] b = new int[10000];
		for(int j =0 ;j<b.length;j++) {
			b[j] = (int) Math.random();
		}
		int[] c = new int[1000000];
		for(int k =0 ;k<c.length;k++) {
			c[k] = (int) Math.random();
		}
		//--------Heap--------//
		Heap test1 = new Heap();
		Heap test2 = new Heap();
		Heap test3 = new Heap();
		for(int i =0 ;i<a.length;i++) {
			long start = System.currentTimeMillis();
			test1.add(a[i]);
			time = System.currentTimeMillis()- start;
			sum+=time;
		}
		System.out.println("Heap:add 100 data:"+sum);
		sum=0l;
		for(int j =0 ;j<b.length;j++) {
			long start = System.currentTimeMillis();
			test2.add(b[j]);
			time = System.currentTimeMillis()- start;
			sum+=time;
		}
		System.out.println("Heap:add 10000 data:"+sum);
		sum=0l;
		for(int k =0 ;k<c.length;k++) {
			long start = System.currentTimeMillis();
			test3.add(c[k]);
			time = System.currentTimeMillis()- start;
			sum+=time;
		}
		System.out.println("Heap:add 1000000 data:"+sum);
		sum=0l;
		for(int i =0 ;i<a.length;i++) {
			long start = System.currentTimeMillis();
			test1.pop();
			time = System.currentTimeMillis()- start;
			sum+=time;
		}
		System.out.println("Heap:pop 100 data:"+sum);
		sum=0l;
		for(int j =0 ;j<b.length;j++) {
			long start = System.currentTimeMillis();
			test2.pop();
			time = System.currentTimeMillis()- start;
			sum+=time;
		}
		System.out.println("Heap:pop 10000 data:"+sum);
		sum=0l;
		for(int k =0 ;k<c.length;k++) {
			long start = System.currentTimeMillis();
			test3.pop();
			time = System.currentTimeMillis()- start;
			sum+=time;
		}
		System.out.println("Heap:pop 1000000 data:"+sum);
		//--------PriorityQ--------//
		PriorityQ test4 = new HeapRunTime();
		PriorityQ test5 = new HeapRunTime();
		PriorityQ test6 = new HeapRunTime();
		for(int i =0 ;i<a.length;i++) {
			long start = System.currentTimeMillis();
			test4.add(a[i]);
			time = System.currentTimeMillis()- start;
			sum+=time;
		}
		System.out.println("PQ:add 100 data:"+sum);
		sum=0l;
		for(int j =0 ;j<b.length;j++) {
			long start = System.currentTimeMillis();
			test5.add(b[j]);
			time = System.currentTimeMillis()- start;
			sum+=time;
		}
		System.out.println("PQ:add 10000 data:"+sum);
		sum=0l;
		for(int k =0 ;k<c.length;k++) {
			long start = System.currentTimeMillis();
			test6.add(c[k]);
			time = System.currentTimeMillis()- start;
			sum+=time;
		}
		System.out.println("PQ:add 1000000 data:"+sum);
		sum=0l;
		for(int i =0 ;i<a.length;i++) {
			long start = System.currentTimeMillis();
			test4.pop();
			time = System.currentTimeMillis()- start;
			sum+=time;
		}
		System.out.println("PQ:pop 100 data:"+sum);
		sum=0l;
		for(int j =0 ;j<b.length;j++) {
			long start = System.currentTimeMillis();
			test5.pop();
			time = System.currentTimeMillis()- start;
			sum+=time;
		}
		System.out.println("PQ:pop 10000 data:"+sum);
		sum=0l;
		for(int k =0 ;k<c.length;k++) {
			long start = System.currentTimeMillis();
			test6.pop();
			time = System.currentTimeMillis()- start;
			sum+=time;
		}
		System.out.println("PQ:pop 1000000 data:"+sum);
	}

	@Override
	public int size() {
		return 0;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public void add(Object element) throws Exception {
		
	}

	@Override
	public Object top() throws Exception {
		return null;
	}

	@Override
	public Object pop() throws Exception {
		return null;
	}
}
