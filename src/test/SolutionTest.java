import org.junit.jupiter.api.BeforeEach;
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

//Open the project you want to share.
//
//From the main menu, choose Git | GitHub | Share Project on GitHub.
//
//If you have already registered your GitHub account in IntelliJ IDEA, connection will be established using these credentials.
//
//When connection to GitHub has been established, the Share Project on GitHub dialog opens.
// Specify the new repository name, the name of the remote , and enter a description of your project.
//
//You can select the Private option if you do not want to allow public access to your repository for other GitHub users.
//
//Click Share to initiate a new repository and upload project sources to it.