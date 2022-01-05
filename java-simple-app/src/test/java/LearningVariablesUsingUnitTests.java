
    import org.junit.jupiter.api.DisplayName;
     import org.junit.jupiter.api.Test;
      import org.junit.jupiter.api.Assertions;
    public class LearningVariablesUsingUnitTests {

        @DisplayName("Check variable values are correct")
        @Test  //this annotation
        void learnTheVariablesValidCase(){
            int osNum=12;
            int expectedValue=12;
            Assertions.assertEquals(expectedValue, osNum);
        }
    @DisplayName("Check variable values are incorrect")
        @Test
        void learnTheVariablesInvalidCase(){
            int osNum=12;
            int expectedValue=13;
        Assertions.assertNotEquals(expectedValue, osNum);
    }
}
