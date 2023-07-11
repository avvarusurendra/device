package com.device.repository;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import org.springframework.stereotype.Repository;

import com.device.model.Laptop;

@Repository
public class LaptopRepository {

	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	public int saveLaptop(Laptop laptop) {
		int value = 0;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/device", "root", "root");
			preparedStatement = connection.prepareStatement("insert into laptop values(?,?,?)");
			preparedStatement.setInt(1, laptop.getLapid());
			preparedStatement.setString(2, laptop.getLapname());
			preparedStatement.setString(3, laptop.getLappassword());
			preparedStatement.executeUpdate();
			value = 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;

	}

	public List<Laptop> getLaptop() {
		List<Laptop> laplist = new ArrayList<>();
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/device", "root", "root");
			preparedStatement = connection.prepareStatement("select * from laptop");
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Laptop lapobj = new Laptop();
				lapobj.setLapid(resultSet.getInt("lapid"));
				lapobj.setLapname(resultSet.getString("lapname"));
				lapobj.setLappassword(resultSet.getString("lappassword"));

				laplist.add(lapobj);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}

		}
		return laplist;

	}

	public List<Laptop> getLaptopById(String lapname) {
		List<Laptop> l = new ArrayList<>();
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/device", "root", "root");
			preparedStatement = connection.prepareStatement("select * from laptop where lapname = ?");
			preparedStatement.setString(1, lapname);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				Laptop lapinfo= new Laptop();
				lapinfo.setLapid(resultSet.getInt("lapid"));
				lapinfo.setLapname(resultSet.getString("lapname"));
				lapinfo.setLappassword(resultSet.getString("lappassword"));
				l.add(lapinfo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}
	
	public int updateLaptop(Laptop laptop) {
		int value = 0;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/device", "root", "root");
			preparedStatement = connection.prepareStatement("update laptop set lappassword = ?");
			preparedStatement.setString(1, laptop.getLappassword());
			//preparedStatement.setString(2, laptop.getLapname());
			preparedStatement.executeUpdate();
			value =1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
		
		
	}
	
	public int deleteLaptop(Integer lapid) {
		int value = 0;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/device", "root", "root");
			preparedStatement = connection.prepareStatement("delete from laptop where lapid = ?");
			preparedStatement.setInt(1, lapid);
			preparedStatement.executeUpdate();
			value =1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}
}
