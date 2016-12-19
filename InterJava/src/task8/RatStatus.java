package task8;

public enum RatStatus {

	ALIVE {
		public String toString() {
			return "active";
		}
	},

	DEAD {
		public String toString() {
			return "dead";
		}
	},
	
	SLEEPING {
		public String toString() {
			return "sleeping";
		}
	}
}
