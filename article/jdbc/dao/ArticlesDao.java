package article.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import jdbc.bean.Article;
import jdbcutils.JDBCUtils;

public class ArticlesDao implements DaoInterface<Article, Integer> {

	@Override
	public Collection<Article> retrieveAll() {

		Collection<Article> allArticles = new ArrayList<>();
		String sqlquery = "select * from article_master";

		try (Connection conn = JDBCUtils.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rst = stmt.executeQuery(sqlquery);) {
			while (rst.next()) {
				int id = rst.getInt(1);
				String name = rst.getString(2);
				String category = rst.getString(3);
				String date = rst.getString(4);
				String crateorName = rst.getString(5);

				Article article = new Article(id, name, category, date, crateorName);
				allArticles.add(article);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return allArticles;
	}

	@Override
	public Article retrieveOne(Integer id) {
		Article getOne = null;

		String sqlquery = "select * from article_master where id = ?";

		try (Connection conn = JDBCUtils.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sqlquery);) {

			
			pstmt.setInt(1, id);
			ResultSet rst = pstmt.executeQuery();
			if (rst.next()) {
				int ids = rst.getInt(1);
				String name = rst.getString(2);
				String category = rst.getString(3);
				String date = rst.getString(4);
				String crateorName = rst.getString(5);

				getOne = new Article(ids, name, category, date, crateorName);
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return getOne;
	}

	@Override
	public void create(Article insertdata) {
		
		String sqlquery=
				"insert into article_master values(?,?,?,?,?)";
try (Connection conn = JDBCUtils.getConnection(); 
		PreparedStatement pstmt = conn.prepareStatement(sqlquery);) {
			
				int ids = insertdata.getId();
				String name = insertdata.getName();
				String category =insertdata.getCategory();
				String date = insertdata.getDateCreated();
				String crateorName = insertdata.getCreatorName();
				
				pstmt.setInt(1, ids);
				pstmt.setString(5, crateorName);
				pstmt.setString(2, name);
				pstmt.setString(3, category);
				pstmt.setString(4, date);

				int UpdateCount = pstmt.executeUpdate();
				System.out.println(UpdateCount + " Inserted details.");
				
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void update(Article updatedata) {
		String sqlquery=
				"update article_master set name = ?, category = ?,dateCreated=? ,creatorName=? where id = ?";
try (Connection conn = JDBCUtils.getConnection(); 
		PreparedStatement pstmt = conn.prepareStatement(sqlquery);) {
			
				int ids = updatedata.getId();
				String name = updatedata.getName();
				String category =updatedata.getCategory();
				String date = updatedata.getDateCreated();
				String crateorName = updatedata.getCreatorName();
				
				pstmt.setInt(5, ids);
				pstmt.setString(4, crateorName);
				pstmt.setString(1, name);
				pstmt.setString(2, category);
				pstmt.setString(3, date);

				int UpdateCount = pstmt.executeUpdate();
				System.out.println(UpdateCount + " Update details.");
				
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void delete(Integer id) {
		String sqlquery = "delete from article_master where id = ?";

		try (Connection conn = JDBCUtils.getConnection(); 
			PreparedStatement pstmt = conn.prepareStatement(sqlquery);) {

			
			pstmt.setInt(1, id);
			int UpdateCount = pstmt.executeUpdate();
			System.out.println(UpdateCount + " Delete one details.");
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
