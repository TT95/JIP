package task2;


import static org.junit.Assert.assertEquals;



public class Test {

	@org.junit.Test(expected = WrongNameException.class)
    public void testConstructor1() throws WrongNameException, WrongSurnameException{
    	new Person("josko", "Obrovac");
    }
    
    @org.junit.Test
    public void testConstructor2() throws WrongNameException, WrongSurnameException{
    	Person jack = new Person("Oliver", "Josko-Misko");
    	assertEquals(jack.getFirstName(), "Oliver");
    	
    }
    
	@org.junit.Test(expected = WrongSurnameException.class)
    public void testConstructor3() throws WrongNameException, WrongSurnameException{
    	new Person("Oliver", "Jokso");
    }
	
    @org.junit.Test
    public void testConstructor4() throws WrongNameException, WrongSurnameException{
    	Person jack = new Person("Oliver", "Trisko-Pisko");
    	assertEquals(jack.getLastName(), "Trisko-Pisko");
    	
    }
    
}
