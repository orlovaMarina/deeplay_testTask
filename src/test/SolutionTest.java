import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {
    public Solution solution = new Solution();

    @Test
    void testGetResult_Swamper_1() {
        int result = solution.getResult("SWTPPPSTWPPTWSSP", "Swamper" );
        assertEquals(12, result );
    }
    @Test
    void testGetResult_Human_1(){
        int result = solution.getResult("WWTPSTPWSPWSSTWP", "Human");
        assertEquals(11, result);
    }
    @Test
    void testResult_Woodman_1(){
        int result = solution.getResult("SPWTPSPWTSPWWSWP", "Woodman");
        assertEquals(14, result);
    }
    @Test
    void testGetResult_Swamper_2() {
        int result = solution.getResult("STWPTWPTSWWPTSPW", "Swamper" );
        assertEquals(15, result );
    }
    @Test
    void testGetResult_Human_2(){
        int result = solution.getResult("TPSTWPPTSWPTSTWT", "Human");
        assertEquals(9, result);
    }
    @Test
    void testResult_Woodman_2(){
        int result = solution.getResult("TWPSTWPPTSWTSPSW", "Woodman");
        assertEquals(14, result);
    }
    @Test
    void testGetResult_Swamper_3() {
        int result = solution.getResult("TWPPSTWPSTWPSPTS", "Swamper" );
        assertEquals(12, result );
    }
    @Test
    void testGetResult_Human_3(){
        int result = solution.getResult("TTPSWPTSWPTSWPST", "Human");
        assertEquals(13, result);
    }
    @Test
    void testResult_Woodman_3(){
        int result = solution.getResult("WTPSSTWPTSTWPSSP", "Woodman");
        assertEquals(14, result);
    }
}
