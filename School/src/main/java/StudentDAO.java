import java.sql.*;

public class StudentDAO {

    /**
     * Method for checking if given id exists in databases students table
     * @param id students id
     * @return true/false depending if record exists
     */
    public boolean doesIdExist(int id) {
        try {
            String query = "SELECT id FROM students WHERE id = ?";

            Connection con = DBConnector.connect();

            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            boolean doesIdExist = rs.next();

            rs.close();
            pst.close();
            con.close();

            return doesIdExist;
        } catch (SQLException e) {
            return true;
        }
    }

    /**
     * Method for getting students data from database based on id parameter
     * @param id students id
     * @return Student object
     */
    public Student getStudent(int id) {
        try {
            String query = "SELECT * FROM students WHERE id = ?";
            Student student = new Student();

            Connection con = DBConnector.connect();

            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            rs.next();
            student.setId(rs.getInt("id"));
            student.setFirstName(rs.getString("first_name"));
            student.setLastName(rs.getString("last_name"));
            student.setGender(rs.getString("gender"));
            student.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
            student.setGrade(rs.getInt("grade"));
            student.setEmail(rs.getString("email"));

            rs.close();
            pst.close();
            con.close();

            return student;
        } catch (SQLException e) {
            return null;
        }
    }

    /**
     * Method for updating selected students record
     * @param columnLabel which column is changed
     * @param value new String value
     * @param student Student object
     */
    public void updateStudent(String columnLabel, String value, Student student) {
        try {
            String query = "UPDATE students SET " + columnLabel + " = ? WHERE id = ?";

            Connection con = DBConnector.connect();

            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, value);
            pst.setInt(2, student.getId());

            pst.executeUpdate();

            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Method for updating selected students record
     * @param columnLabel which column is changed
     * @param value new int value
     * @param student Student object
     */
    public void updateStudent(String columnLabel, int value, Student student) {
        try {
            String query = "UPDATE students SET " + columnLabel + " = ? WHERE id = ?";

            Connection con = DBConnector.connect();

            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, value);
            pst.setInt(2, student.getId());

            pst.executeUpdate();

            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    /**
     * Method for removing selected student from database
     * @param student Student object
     */
    public void deleteStudent(Student student) {
        try {
            String query = "DELETE FROM students WHERE id = ?";

            Connection con = DBConnector.connect();

            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, student.getId());

            pst.executeUpdate();

            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for inserting students data into database
     * @param student Student object
     */
    public void addStudent(Student student) {

        try {
            String query = "INSERT INTO students (id, first_name, last_name, gender, date_of_birth, grade) VALUES (?, ?, ?, ?, ?, ?)";

            Connection con = DBConnector.connect();

            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, student.getId());
            pst.setString(2, student.getFirstName());
            pst.setString(3, student.getLastName());
            pst.setString(4, student.getGender());
            pst.setDate(5, Date.valueOf(student.getDateOfBirth()));
            pst.setInt(6, student.getGrade());

            pst.executeUpdate();

            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
