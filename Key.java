package assignment5;
/* CRITTERS Key.java
* EE422C Project 5 submission by
* Kayla Tran
* knt627
* 16345
* Arvin Bhatti
* ab62733
* 16345
* Slip days used: <2>
* Fall 2018
*/

public class Key {
	private int x_pos;
	private int y_pos;
	
	public Key(int x_pos, int y_pos) {
		this.x_pos = x_pos;
		this.y_pos = y_pos;
	}
	
	@Override
	public boolean equals(Object object) {
		if(!(object instanceof Key)) {
			return false;
		}
		Key otherKey = (Key) object;
		return ((this.x_pos == otherKey.x_pos) && (this.y_pos == otherKey.y_pos));
	}
	
	
	public Integer[] keySet() {
		return new Integer[] {this.x_pos, this.y_pos};
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		result = 31*result + Integer.valueOf(this.x_pos).hashCode();
		result = 31*result + Integer.valueOf(this.y_pos).hashCode();
		return result;
	}
}
