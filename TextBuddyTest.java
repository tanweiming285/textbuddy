import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class TextBuddyTest {

	
	
	@Test
	public void testsortList(){
		TextBuddy tb =  new TextBuddy();
		
		//Creating the unsorted arrayList
		ArrayList<String> test = new ArrayList<String>();
		test.add("singapore");
		test.add("holland");
		test.add("austria");
		test.add("britain");
		//testing for case-sensitivity
		test.add("America");
		test.add("america");
		
		//Creating the expected sorted list
		ArrayList<String> expectedList = new ArrayList<String>();
		expectedList.add("America");
		expectedList.add("america");
		expectedList.add("austria");
		expectedList.add("britain");
		expectedList.add("holland");
		expectedList.add("singapore");
		
		//Checking if the expectedList matches the test list sorted by TextBuddy.sortList method
		assertEquals(expectedList,tb.sortList(test));
	}
	
	@Test
	public void testSearchList(){
		TextBuddy tb =  new TextBuddy();
		
		//Creating the test arrayList
		ArrayList<String> test = new ArrayList<String>();
		test.add("cat");
		test.add("bad cat");
		test.add("cta");
		//Testing for case-sensitivity
		test.add("Cat");
		
		//Creating the expected search results when I look for "cat" in the test list
		ArrayList<String> expectedList = new ArrayList<String>();
		expectedList.add("cat");
		expectedList.add("bad cat");
		expectedList.add("Cat");
		
		//Checking if the expectedList matches the list of search results returned from TextBuddy.searchList method
		assertEquals(expectedList,tb.searchList(test,"CaT"));
		
	}

}
