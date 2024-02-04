
<%@ page import="java.sql.*" %>
<%@ page import="com.poi.valeri_poi.DBConnection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String commentId = request.getParameter("commentId");
    if (commentId != null && !commentId.trim().isEmpty()) {
        DBConnection dbConnection = new DBConnection();
        try {
            String deleteSql = "DELETE FROM Comments WHERE Comment_Id = ?";
            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(deleteSql);
            preparedStatement.setInt(1, Integer.parseInt(commentId));
            int affectedRows = preparedStatement.executeUpdate();
            preparedStatement.close();
            if (affectedRows > 0) {
                out.println("<script>alert('Comment deleted successfully.'); window.location.href = 'CommentsAdmin.jsp';</script>");
            } else {
                out.println("<script>alert('Failed to delete the comment.'); window.location.href = 'CommentsAdmin.jsp';</script>");
            }
        } catch (Exception e) {
            out.println("Error deleting comment: " + e.getMessage());
            e.printStackTrace();
        } finally {
            dbConnection.close();
        }
    } else {
        response.sendRedirect("CommentsAdmin.jsp");
    }
%>
