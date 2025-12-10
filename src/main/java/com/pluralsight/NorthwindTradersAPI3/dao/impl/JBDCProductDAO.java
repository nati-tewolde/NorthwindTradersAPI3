package com.pluralsight.NorthwindTradersAPI3.dao.impl;

import com.pluralsight.NorthwindTradersAPI3.dao.interfaces.IProductDAO;
import com.pluralsight.NorthwindTradersAPI3.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class JBDCProductDAO implements IProductDAO {

    private DataSource dataSource;

    @Autowired
    public JBDCProductDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Product> getAll() {
        List<Product> Products = new ArrayList<>();
        String getAllQuery = "SELECT * FROM products";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement selectStatement = connection.prepareStatement(getAllQuery);
             ResultSet resultSet = selectStatement.executeQuery()) {
            while (resultSet.next()) {
                int productID = resultSet.getInt("ProductID");
                String name = resultSet.getString("ProductName");
                int categoryID = resultSet.getInt("CategoryID");
                double unitPrice = resultSet.getDouble("UnitPrice");

                Products.add(new Product(productID, name, categoryID, unitPrice));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Products;
    }

    @Override
    public Product getById(int ProductId) {
        Product Product = null;
        String getByIdQuery = "SELECT * FROM products WHERE ProductID = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement selectStatement = connection.prepareStatement(getByIdQuery)) {

            selectStatement.setInt(1, ProductId);

            try (ResultSet resultSet = selectStatement.executeQuery()) {
                if (resultSet.next()) {
                    int productID = resultSet.getInt("ProductID");
                    String name = resultSet.getString("ProductName");
                    int categoryID = resultSet.getInt("CategoryID");
                    double unitPrice = resultSet.getDouble("UnitPrice");

                    Product = new Product(productID, name, categoryID, unitPrice);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Product;
    }
}