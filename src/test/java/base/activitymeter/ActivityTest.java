package base.activitymeter;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;

import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.Validator;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.rule.impl.SetterMustExistRule;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;

public class ActivityTest {

  @Test
  public void validateSettersAndGetters() {


    PojoClass activityPojo = PojoClassFactory.getPojoClass(Activity.class);


    Validator validator = ValidatorBuilder.create()
        // Lets make sure that we have a getter and a setter for every field defined.
        .with(new SetterMustExistRule()).with(new GetterMustExistRule())

        // Lets also validate that they are behaving as expected
        .with(new SetterTester()).with(new GetterTester()).build();

    // Start the Test
    validator.validate(activityPojo);
  }
  @Test(timeout = 1_000) public void activityDefaultValidTest() {
      // arrange
      Activity sut = new Activity(365, "A year is a long timespam, yet so short", "Year", "time","","");
      boolean want = false;
      // act
      boolean have = sut.getValid();
      // assert
      Assert.assertEquals(want, have);
  }  
  @Test(timeout = 1_000) public void activityChangeValidTest() {
      // arrange
      Activity sut = new Activity(365, "A year is a long timespam, yet so short", "Year", "time","","");
      sut.setValid(true);
      boolean want = true;
      // act
      boolean have = sut.getValid();
      // assert
      Assert.assertEquals(want, have);
  }  
  @Test(timeout = 1_000) public void activityCheckKeyTrue() {
      // arrange
      Activity sut = new Activity(365, "A year is a long timespam, yet so short", "Year", "time","","");
      boolean want = true;
      // act
      boolean have = sut.checkKey(365);
      // assert
      Assert.assertEquals(want, have);
  }  
  @Test(timeout = 1_000) public void activityCheckKeyFalse() {
      // arrange
      Activity sut = new Activity(365, "A year is a long timespam, yet so short", "Year", "time","","");
      boolean want = false;
      // act
      boolean have = sut.checkKey(10056);
      // assert
      Assert.assertEquals(want, have);
  }  
  @Test(timeout = 1_000) public void activityCheckAdminKeyTrue() {
      // arrange
      Activity sut = new Activity(365, "A year is a long timespam, yet so short", "Year", "time","","");
      boolean want = true;
      // act
      boolean have = sut.checkKey(999999);
      // assert
      Assert.assertEquals(want, have);
  }
  @Test(timeout = 1_000) public void activityCheckIdTrue()
  {
	  // arrange
	  Activity sut = new Activity(365, "A year is a long timespam, yet so short", "Year", "time","","");
      boolean want = true;
      // act
      boolean have = sut.checkKey(365);
      // assert
      Assert.assertEquals(want, have);
  }
  @Test(timeout = 1_000) public void activityCheckIdFalse()
  {
	  // arrange
	  Activity sut = new Activity(365, "A year is a long timespam, yet so short", "Year", "time","","");
      boolean want = false;
      // act
      boolean have = sut.checkId(200);
      // assert
      Assert.assertEquals(want, have);
  }  
  @Test(timeout = 1_000) public void activityAddTag() {
      // arrange
      Activity sut = new Activity(365, "A year is a long timespam, yet so short", "Year", "time","","");
      Tag toAdd = new Tag("Heute");
      Set<Tag> want = new HashSet<>();
      want.add(toAdd);
      // act
      sut.setTags(want);
      Set<Tag> have = sut.getTags();
      // assert
      Assert.assertEquals(want, have);
  } 
  @Test(timeout = 1_000) public void activityContainsTagTrue() {
      // arrange
      Activity sut = new Activity(365, "A year is a long timespam, yet so short", "Year", "time","","");
      String toCheck = "Heute";
      Set<Tag> set = new HashSet<>();
      set.add(new Tag(toCheck));
      boolean want = true;
      // act
      sut.setTags(set);
      boolean have = sut.containsTag(toCheck);
      // assert
      Assert.assertEquals(want, have);
  } 
  @Test(timeout = 1_000) public void activityContainsTagFalse() {
      // arrange
      Activity sut = new Activity(365, "A year is a long timespam, yet so short", "Year", "time","","");
      String toCheck = "Heute";
      Set<Tag> set = new HashSet<>();
      set.add(new Tag(toCheck));
      boolean want = false;
      // act
      sut.setTags(set);
      boolean have = sut.containsTag("Morgen");
      // assert
      Assert.assertEquals(want, have);
  }
}
