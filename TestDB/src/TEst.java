import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TEst {

/**
* @param args
*/
public static void main(String[] args) throws Exception {
// TODO Auto-generated method stub
Class.forName("org.h2.Driver").newInstance();
Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test",
"sa", "");

Statement st = conn.createStatement();
st.execute("INSERT INTO STUDENTS VALUES(1, 'Ivanov', '');");
ResultSet result = st.executeQuery("SELECT * FROM STUDENTS");
String ID = "", NAME = "", HOBBY = "";

while (result.next()) {
HOBBY = result.getString("HOBBY");
ID = result.getString("ID");
NAME = result.getString("NAME");
if (HOBBY == "") {
HOBBY = "Нет хобби";
}
System.out.printf("%3s | %-10s | %-10s \n", ID, NAME, HOBBY);
}
}

}