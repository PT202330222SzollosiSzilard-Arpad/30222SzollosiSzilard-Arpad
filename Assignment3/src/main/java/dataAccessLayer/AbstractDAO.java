package dataAccessLayer;

import businessLayer.ConnectionFactory;
import model.Customer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AbstractDAO<T> {

    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public T findById(int id) {
        Connection connection = null;
        String query = createSelectQuery("id");
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            return createObjects(resultSet).get(0);

        } catch (Exception e) {
            LOGGER.log(java.util.logging.Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return null;
    }

    public List<T> findAll() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String query = selectAllQuery(type.getSimpleName());



        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            //System.out.println(query);
            return createObjects(resultSet);

        } catch (SQLException e) {
            LOGGER.log(java.util.logging.Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return null;
    }

    public T insert(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(this.createInsertQuery());

            Field[] fields = type.getDeclaredFields();
            List<Object> fieldValues = this.getFieldValues(t, fields);

            for(int i = 0; i < fieldValues.size(); i++) {
                statement.setObject(i + 1, fieldValues.get(i));
            }

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return t;
    }

    public T update(int id, T updatedEntity) {
        String updateQuery = createUpdateQuery();
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(updateQuery);
            int parameterIndex = 1;

            for (Field field : type.getDeclaredFields()) {
                field.setAccessible(true);
                String fieldName = field.getName();
                Object value = field.get(updatedEntity);
                statement.setObject(parameterIndex, value);
                parameterIndex++;
            }

            statement.setInt(parameterIndex, id);
            statement.executeUpdate();
        } catch (SQLException | IllegalAccessException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return updatedEntity;
    }

    public DefaultTableModel retriveProperties(List<T> objects) {

        DefaultTableModel tableModel = new DefaultTableModel();
        List<String> columnNames = new ArrayList<>();
        List<List<Object>> rows = new ArrayList<>();

        for (Field field : type.getDeclaredFields()) {
            columnNames.add(field.getName());
        }

        for (T object : objects) {
            List<Object> row = new ArrayList<>();

            for (Field field : type.getDeclaredFields()) {
                field.setAccessible(true);

                try {
                    row.add(field.get(object));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            rows.add(row);
        }

        for (String columnName : columnNames) {
            tableModel.addColumn(columnName);
        }

        for (List<Object> row : rows) {
            tableModel.addRow(row.toArray());
        }


        return tableModel;
    }

    public boolean delete(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean deleted = false;

        try {
            connection = ConnectionFactory.getConnection();
            String query = createDeleteQuery();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                deleted = true;
            } else {
                deleted = false;
            }
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return deleted;
    }

    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append("`");
        sb.append(type.getSimpleName());
        sb.append("`");
        sb.append(" WHERE ");
        sb.append(field);
        sb.append(" =?");

        return sb.toString();
    }

    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];

            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T)ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }




    private String selectAllQuery(String table) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append(" * ");
        sb.append("FROM `");
        sb.append(table);
        sb.append("`");

        return sb.toString();
    }

    private String createInsertQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(type.getSimpleName());
        sb.append(" (");

        Field[] fields = type.getDeclaredFields();
        int numFields = fields.length;

        for (int i = 0; i < numFields; i++) {
            Field field = fields[i];
            String fieldName = field.getName();
            sb.append(fieldName);

            if (i < numFields - 1) {
                sb.append(", ");
            }
        }

        sb.append(") VALUES (");

        for (int i = 0; i < numFields; i++) {
            sb.append("?");

            if (i < numFields - 1) {
                sb.append(", ");
            }
        }

        sb.append(");");

        return sb.toString();
    }

    private List<Object> getFieldValues(T t, Field[] fields) {
        List<Object> fieldValues = new ArrayList<>();

        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(t);
                fieldValues.add(value);
            } catch (IllegalAccessException e) {
                LOGGER.log(java.util.logging.Level.WARNING, type.getName() + "DAO:getFieldValues " + e.getMessage());
            }
        }

        return fieldValues;
    }

    private String createUpdateQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append('`');
        sb.append(type.getSimpleName());
        sb.append('`');
        sb.append(" SET ");

        Field[] fields = type.getDeclaredFields();
        int numFields = fields.length;

        for (int i = 0; i < numFields; i++) {
            Field field = fields[i];
            String fieldName = field.getName();
            sb.append(fieldName);
            sb.append(" = ?");

            if (i < numFields - 1) {
                sb.append(", ");
            }
        }

        sb.append(" WHERE id = ?");

        return sb.toString();
    }

    public String createDeleteQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append('`');
        sb.append(type.getSimpleName());
        sb.append('`');
        sb.append(" WHERE id = ?");

        return sb.toString();
    }
}

