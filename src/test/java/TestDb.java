import org.junit.Assert;
import org.junit.Test;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TestDb {

    private DbConnection dbConnection = new DbConnection();

    public List<String> createSetOfScientists(){
        List<String> expectedScientists = new ArrayList<>();
        expectedScientists.add("einstein");
        expectedScientists.add("newton");
        expectedScientists.add("curie");
        return expectedScientists;
    }

    public List<String> createSetOfDiscoveries(){
        List<String> expectedCountries = new ArrayList<>();
        expectedCountries.add("theory of relativity");
        expectedCountries.add("gravity");
        expectedCountries.add("polonium");
        return expectedCountries;
    }

    @Test
    public void testEasyQuery() throws SQLException{
        Connection conn = dbConnection.dbConnect();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT lastname FROM scientist WHERE id=1");
        while (rs.next()){
            Assert.assertEquals("Wrong last name", createSetOfScientists().get(0), rs.getString("lastname"));
            System.out.println(createSetOfScientists().get(0) + "=" + rs.getString("lastname"));
        }
        dbConnection.dbEndConnect();
    }

    @Test
    public void testJoinQuery() throws SQLException{
        Connection conn = dbConnection.dbConnect();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT lastname, discovery FROM scientist JOIN science ON id = scientist_id WHERE scientist_id = 3;");
        while (rs.next()){
            Assert.assertEquals("Wrong discovery", createSetOfDiscoveries().get(2), rs.getString("discovery"));
            System.out.println(createSetOfDiscoveries().get(2) + "=" + rs.getString("discovery"));
        }
        dbConnection.dbEndConnect();
    }
}
