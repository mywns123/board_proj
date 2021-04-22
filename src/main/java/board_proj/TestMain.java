package board_proj;

public class TestMain {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		String className = "board_proj.Sum";
		
		Class<?> cls = Class.forName(className);		
		Sum s = (Sum) cls.newInstance();
		s.add(5,3);

		
		Sum s2 = new Sum();
		s2.add(5, 2);
		
		
		
	}

}
