package repository.drug;

import config.DBConfig;
import entity.Drug;
import repository.drug.interfaces.DrugRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DrugRepositoryImpl implements DrugRepository {

    @Override
    public void save(Drug drug) {
        String query = """
                    insert into drugs(name, price, count)
                    values(?,?,?)
                """;
        try {
            PreparedStatement preparedStatement = DBConfig.getConnection().prepareStatement(query);
            preparedStatement.setString(1, drug.getName());
            preparedStatement.setFloat(2, drug.getPrice());
            preparedStatement.setInt(3, drug.getCount());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(long id) {
        String query = """
                    delete from drugs
                    where id = ?
                """;
        try {
            PreparedStatement preparedStatement = DBConfig.getConnection().prepareStatement(query);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Drug load(long id) {
        String query = """
                    select * from drugs
                    where id = ?
                """;
        try {
            PreparedStatement preparedStatement = DBConfig.getConnection().prepareStatement(query);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next())
                return null;
            return new Drug(resultSet.getLong("id"), resultSet.getString("name"),
                    resultSet.getFloat("price"), resultSet.getInt("count"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Drug drug) {
        String query = """
                    update drugs
                    set price = ?, count = ?
                    where id = ?
                """;
        try {
            PreparedStatement preparedStatement = DBConfig.getConnection().prepareStatement(query);
            preparedStatement.setFloat(1, drug.getPrice());
            preparedStatement.setInt(2, drug.getCount());
            preparedStatement.setLong(3, drug.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Drug load(String name) {
        String query = """
                    select * from drugs
                    where name = ?
                """;
        try {
            PreparedStatement preparedStatement = DBConfig.getConnection().prepareStatement(query);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next())
                return null;
            return new Drug(resultSet.getLong("id"), resultSet.getString("name"),
                    resultSet.getFloat("price"), resultSet.getInt("count"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void increaseNumberOfDrug(int count, String name){
        String query = """
                    update drugs
                    set count = count + ?
                    where name = ?
                """;
        try {
            PreparedStatement preparedStatement = DBConfig.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, count);
            preparedStatement.setString(2, name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
