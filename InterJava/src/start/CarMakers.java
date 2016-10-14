package start;

public enum CarMakers {
	FIAT, FORD, VOLVO, BMW, CHRYSLER, GM, NOTKNOWN;
	
	static CarMakers fromString(String s) {
		s=s.trim().toUpperCase();
		for (CarMakers enumValue : CarMakers.values()) {
			return enumValue;
		}
		return(NOTKNOWN);
	}
	
	public String toString() {
		String name = this.name().toLowerCase();
		if(name().equals("BMW")||this.name().equals("GM")) {
			return name.toUpperCase();
		}
		name = name.substring(0, 1).toUpperCase() + name.substring(1);
		return name;
	}
	
	static CarMakers toValue(String string) {
		for(CarMakers maker : CarMakers.values()) {
			if(maker.name().equals(string.toUpperCase())) {
				return maker;
			}
		}
		return CarMakers.NOTKNOWN;
	}
}
