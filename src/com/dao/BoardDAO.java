package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.dto.BoardDTO;

import util.dbConnection;

public class BoardDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
              public String findTitle(int boardNumber)
	{

		String title=null;
		
		try {
			conn = dbConnection.getConnection();
			String sql = "select title from board where board_number = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,  boardNumber);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				title = rs.getString(1);

			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return title;
	}
	
	
	public String findContent(int boardNumber)
	{
		String content=null;
		
		try {
			conn = dbConnection.getConnection();
			String sql = "select board_content from board where board_number = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,  boardNumber);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				content = rs.getString(1);

			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return content;
	}
	
	public int reWriteBoard(String title, String content, int boardNumber) {
		try {
			conn = dbConnection.getConnection();
			String sql = "update board set title=?, board_content=? where board_number=? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, boardNumber);
			
			return pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
			return -1;
		}
		
		
	}
	
	public int processLike(int board_number,int like)
	{
		if(like>0)
		{
			try
			{
				conn = dbConnection.getConnection();
				String SQL = "UPDATE board set likely=likely+1 where board_number=?";
				PreparedStatement pstmt = conn.prepareStatement(SQL);
				pstmt = conn.prepareStatement(SQL);
				
				pstmt.setInt(1, board_number);
				
				return pstmt.executeUpdate();
			}catch (Exception e) {
				e.printStackTrace();
				return -1;
			}
		}
		else
		{
			try
			{
				conn = dbConnection.getConnection();
				String SQL = "UPDATE board set likely=likely-1 where board_number=?";
				PreparedStatement pstmt = conn.prepareStatement(SQL);
				pstmt = conn.prepareStatement(SQL);
				
				pstmt.setInt(1, board_number);
				
				return pstmt.executeUpdate();
			}catch (Exception e) {
				e.printStackTrace();
				return -1;
			}
		}
	}
	
	public int processDisLike(int board_number,int dislike)
	{
		if(dislike>0)
		{
			try
			{
				conn = dbConnection.getConnection();
				String SQL = "UPDATE board set dislikely=dislikely+1 where board_number=?";
				PreparedStatement pstmt = conn.prepareStatement(SQL);
				pstmt = conn.prepareStatement(SQL);
				
				pstmt.setInt(1, board_number);
				
				return pstmt.executeUpdate();
			}catch (Exception e) {
				e.printStackTrace();
				return -1;
			}
		}
		else
		{
			try
			{
				conn = dbConnection.getConnection();
				String SQL = "UPDATE board set dislikely=dislikely-1 where board_number=?";
				PreparedStatement pstmt = conn.prepareStatement(SQL);
				pstmt = conn.prepareStatement(SQL);
				
				pstmt.setInt(1, board_number);
				
				return pstmt.executeUpdate();
			}catch (Exception e) {
				e.printStackTrace();
				return -1;
			}
		}
	}
	
	public int addBoard(String title, String content, String id) {
		try {
			conn = dbConnection.getConnection();
			String sql = "insert into board(title, board_content, id) values(?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, id);
			
			return pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public int deleteBoard(int board_number) {
		try {
			conn = dbConnection.getConnection();
			String sql = "delete from board where board_number = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, board_number);
			
			return pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public BoardDTO getBoardContent(int board_number) {
		BoardDTO board = null;
		
		try {
			conn = dbConnection.getConnection();
			String sql = "select * from board where board_number = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,  board_number);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String title = rs.getString(2);
				String board_content = rs.getString(3);
				String id = rs.getString(4);
				Timestamp datetime = rs.getTimestamp(5);
				int readCount = rs.getInt(6);
				int likeCount = rs.getInt(7);
				int dislikeCount = rs.getInt(8);
				
				board = new BoardDTO(title, board_content, id);
				board.setBoard_number(board_number);
				board.setDatetime(datetime);
				board.setReadCount(readCount);
				board.setLike(likeCount);
				board.setDislike(dislikeCount);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			conn = dbConnection.getConnection();
			String sql = "update board set read_count= read_count+1 where board_number = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,  board_number);
			System.out.println("In update read count");
			pstmt.executeUpdate();
			System.out.println("after execute");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return board;
	}
	public ArrayList<BoardDTO> getBoards() {
		ArrayList<BoardDTO> boards = new ArrayList<BoardDTO>();
		
		try {
			conn = dbConnection.getConnection();
			String sql = "select * from board order by board_number desc";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int board_number = rs.getInt(1);
				String title = rs.getString(2);
				String board_content = rs.getString(3);
				String id = rs.getString(4);
				Timestamp datetime = rs.getTimestamp(5);
				int readCount = rs.getInt(6);
				int likeCount = rs.getInt(7);
				int dislikeCount = rs.getInt(8);
				
				BoardDTO board = new BoardDTO(title, board_content, id);
				board.setBoard_number(board_number);
				board.setDatetime(datetime);
				board.setReadCount(readCount);
				board.setLike(likeCount);
				board.setDislike(dislikeCount);
				boards.add(board);
			}
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		return boards;		
	}
	
	
	
}
