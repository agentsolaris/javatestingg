package javaTest;

import main.Main;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.DisallowWriteToSystemOut;
import org.junit.contrib.java.lang.system.SystemOutRule;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class PlagiatTest {
    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();
    ArrayList<Integer> xWithSolution = new ArrayList<>() {
        {
            add(1);
            add(2);
            add(1);
            add(0);
            add(0);
        }
    };
    ArrayList<Integer> yWithSolution = new ArrayList<>() {
        {
            add(1);
            add(2);
            add(2);
            add(0);
            add(1);
        }
    };

    ArrayList<Integer> xNoSolution = new ArrayList<>() {
        {
            add(1);
            add(2);
            add(1);
            add(0);
            add(100);
        }
    };
    ArrayList<Integer> yNoSolution = new ArrayList<>() {
        {
            add(1);
            add(2);
            add(2);
            add(0);
            add(105);
        }
    };

    ArrayList<Integer> xOutOfBoundaries1 = new ArrayList<>() {
        {
            add(-1);
            add(2);
            add(1);
            add(0);
            add(0);
        }
    };
    ArrayList<Integer> yOutOfBoundaries1 = new ArrayList<>() {
        {
            add(1);
            add(2);
            add(2);
            add(0);
            add(1);
        }
    };

    ArrayList<Integer> xOutOfBoundaries2 = new ArrayList<>() {
        {
            add(1000000001);
            add(2);
            add(1);
            add(0);
            add(0);
        }
    };
    ArrayList<Integer> yOutOfBoundaries2 = new ArrayList<>() {
        {
            add(1);
            add(2);
            add(2);
            add(0);
            add(1);
        }
    };
    //Functional testing
    @Test
    public void equivalencePartitioning() {

        assertEquals("-1", Main.checkIfMapContainsTriangles(0, null, null, null));
        assertEquals("-1", Main.checkIfMapContainsTriangles(6, null, null, null));
        int t = 1;
        assertEquals("DA\n", Main.checkIfMapContainsTriangles(t, 5,xWithSolution, yWithSolution));
        assertEquals("NU\n", Main.checkIfMapContainsTriangles(t, 5,xNoSolution, yNoSolution));
        assertEquals("-1", Main.checkIfMapContainsTriangles(t, 0,null, null));
        assertEquals("-1", Main.checkIfMapContainsTriangles(t, 401,null, null));
        assertEquals("-1", Main.checkIfMapContainsTriangles(t, 5,xOutOfBoundaries1, yOutOfBoundaries1));
        assertEquals("-1", Main.checkIfMapContainsTriangles(t, 5,xOutOfBoundaries2, yOutOfBoundaries2));
    }

    @Test
    public void boundaryValues() {

        ArrayList<Integer> x = new ArrayList<>();
        ArrayList<Integer> y = new ArrayList<>();
        for(int i=0;i<400;i++){

            for (int j=0;j< xWithSolution.size();j++){
                x.add(xWithSolution.get(j));
                y.add(xWithSolution.get(j));
            }

        }
        assertEquals("DA\n", Main.checkIfMapContainsTriangles(1,400,x,y));
        x.clear();y.clear();
        x.add(1);y.add(1);
        assertEquals("NU\n", Main.checkIfMapContainsTriangles(5,1,xWithSolution,yWithSolution));

        int t=1;

        assertEquals("NU\n", Main.checkIfMapContainsTriangles(t,1,x,y));
        x.clear();y.clear();
        for(int i =0;i<5;i++){
            x.add(1);y.add(1);
        }
        assertEquals("NU\n", Main.checkIfMapContainsTriangles(5,1,x,y));
        x.clear();y.clear();
        x.add(-1);y.add(1);
        assertEquals("-1", Main.checkIfMapContainsTriangles(t,1,x,y));

        //to kill mutant
        x.clear();y.clear();
        x.add(1000000000);y.add(0);
        assertEquals("NU\n", Main.checkIfMapContainsTriangles(t,1,x,y));
        x.clear();y.clear();
        x.add(0);y.add(1000000000);
        assertEquals("NU\n", Main.checkIfMapContainsTriangles(t,1,x,y));
        //to kill mutant end

        x.clear();y.clear();
        x.add(1000000001);y.add(1);
        assertEquals("-1", Main.checkIfMapContainsTriangles(t,1,x,y));
        assertEquals("-1", Main.checkIfMapContainsTriangles(t,0,null,null));
        assertEquals("-1", Main.checkIfMapContainsTriangles(t,401,null,null));
        assertEquals("-1", Main.checkIfMapContainsTriangles(0,null,null,null));
        assertEquals("-1", Main.checkIfMapContainsTriangles(6,null,null,null));
    }

    @Test
    public void categoryPartitioning() {
        //1st part
        assertEquals(0, Main.checkTMaps(-2));
        assertEquals(0, Main.checkTMaps(0));
        assertEquals(1, Main.checkTMaps(1));
        assertEquals(1, Main.checkTMaps(2));
        assertEquals(1, Main.checkTMaps(5));
        assertEquals(0, Main.checkTMaps(6));
        assertEquals(0, Main.checkTMaps(8));
        //2nd part
        assertEquals("-1", Main.checkIfMapContainsTriangles(-2,null,null,null));
        assertEquals("-1", Main.checkIfMapContainsTriangles(0,null,null,null));
        assertEquals("-1", Main.checkIfMapContainsTriangles(401,null,null,null));
        assertEquals("-1", Main.checkIfMapContainsTriangles(403,null,null,null));

        ArrayList<Integer> x = new ArrayList<>();
        ArrayList<Integer> y = new ArrayList<>();
        x.add(1);y.add(1);
        assertEquals("NU\n", Main.checkIfMapContainsTriangles(1,1,x,y));
        x.add(2);y.add(2);
        assertEquals("NU\n", Main.checkIfMapContainsTriangles(1,2,x,y));
        for(int i=0;i<400;i++){

            for (int j=0;j< xWithSolution.size();j++){
                x.add(xWithSolution.get(j));
                y.add(xWithSolution.get(j));
            }

        }
        assertEquals("DA\n", Main.checkIfMapContainsTriangles(1,400,x,y));
        x.clear();y.clear();
        for(int i=0;i<400;i++){
            x.add(0);
            y.add(20000+i);
        }
        assertEquals("NU\n", Main.checkIfMapContainsTriangles(1,4,x,y));
    }

    //Structural
    @Test
    public void statementCoverage() {
        ArrayList<Integer> x = new ArrayList<>();
        ArrayList<Integer> y = new ArrayList<>();
        x.add(1);y.add(5);
        x.add(2);y.add(7);
        assertEquals("NU\n", Main.checkIfMapContainsTriangles(1,2,x,y));
        assertEquals("NU\n", Main.checkIfMapContainsTriangles(1,5,xNoSolution,yNoSolution));
        assertEquals("DA\n", Main.checkIfMapContainsTriangles(1,5,xWithSolution,yWithSolution));
    }

    @Test
    public void branchCoverage() {
        ArrayList<Integer> x = new ArrayList<>();
        ArrayList<Integer> y = new ArrayList<>();
        x.add(1);y.add(5);
        x.add(2);y.add(7);
        assertEquals("NU\n", Main.checkIfMapContainsTriangles(1,2,x,y));
        assertEquals("NU\n", Main.checkIfMapContainsTriangles(1,5,xNoSolution,yNoSolution));
        assertEquals("DA\n", Main.checkIfMapContainsTriangles(1,5,xWithSolution,yWithSolution));
    }

    @Test
    public void conditionCoverage() {
        ArrayList<Integer> x = new ArrayList<>();
        ArrayList<Integer> y = new ArrayList<>();
        x.add(-1);y.add(1);

        assertEquals("-1", Main.checkIfMapContainsTriangles(1,0,null,null));
        assertEquals("-1", Main.checkIfMapContainsTriangles(1,1,x,y));
        x.clear();y.clear();
        x.add(1);y.add(-1);
        assertEquals("-1", Main.checkIfMapContainsTriangles(1,1,x,y));
        assertEquals("DA\n", Main.checkIfMapContainsTriangles(1,5,xWithSolution,yWithSolution));
        assertEquals("NU\n", Main.checkIfMapContainsTriangles(1,5,xNoSolution,yNoSolution));
        x.clear();y.clear();
        for(int i=1;i<=5;i++){
            x.add(i);
            y.add(i+5);
        }
        assertEquals("DA\n", Main.checkIfMapContainsTriangles(1,5,x,y));
    }

}