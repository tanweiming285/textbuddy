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
		test.add("America");
		test.add("holland");
		test.add("austria");
		test.add("britain");
		
		//Creating the expected sorted list
		ArrayList<String> expectedList = new ArrayList<String>();
		expectedList.add("America");
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
		test.add("dog");
		
		//Creating the expected search results when I look for "cat" in the test list
		ArrayList<String> expectedList = new ArrayList<String>();
		expectedList.add("cat");
		expectedList.add("bad cat");
		
		assertEquals(expectedList,tb.searchList(test,"cat"));
		
	}

}
