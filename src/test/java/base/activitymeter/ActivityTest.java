package base.activitymeter;

import org.junit.Test;
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
      Activity sut = new Activity(365, "A year is a long timespam, yet so short", "Year", "time","");
      boolean want = false;
      // act
      boolean have = sut.getValid();
      // assert
      Assert.assertEquals(want, have);
  }  
  @Test(timeout = 1_000) public void activityChangeValidTest() {
      // arrange
      Activity sut = new Activity(365, "A year is a long timespam, yet so short", "Year", "time","");
      sut.setValid(true);
      boolean want = true;
      // act
      boolean have = sut.getValid();
      // assert
      Assert.assertEquals(want, have);
  }  
  @Test(timeout = 1_000) public void activityCheckKeyTrue() {
      // arrange
      Activity sut = new Activity(365, "A year is a long timespam, yet so short", "Year", "time","");
      boolean want = true;
      // act
      boolean have = sut.checkKey(365);
      // assert
      Assert.assertEquals(want, have);
  }  
  @Test(timeout = 1_000) public void activityCheckKeyFalse() {
      // arrange
      Activity sut = new Activity(365, "A year is a long timespam, yet so short", "Year", "time","");
      boolean want = false;
      // act
      boolean have = sut.checkKey(10056);
      // assert
      Assert.assertEquals(want, have);
  }  
}
