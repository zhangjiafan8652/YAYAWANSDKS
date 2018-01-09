package nihaoya;

public class testbean implements Comparable<testbean>{

	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int compareTo(testbean o) {
		// TODO Auto-generated method stub
		if (id==o.getId()) {
			return 0;
		}else {
			return 1;
		}
		//return 0;
	}
	
}
