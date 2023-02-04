package appli;

public enum Role {
	NULL(0), SERVER(1), CLIENT(2);
	
	private final int value;

	Role(int i) {
		value = i;
	}
	
	public static Role get(int i) {
		for(Role r : Role.values()) {
			if(r.value == i) 
				return r;
		}
		return Role.NULL;
	}
}
