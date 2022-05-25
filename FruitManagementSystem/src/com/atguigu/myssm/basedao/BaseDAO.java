package com.atguigu.myssm.basedao;

import javax.security.auth.login.CredentialException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/** Provide basic Add, edit, delete and query method in database
 * @author Diyang Li
 * @create 2022-04-13 12:22 PM
 */
public abstract class BaseDAO<T> {

    protected Connection conn;
    protected PreparedStatement psmt;
    protected ResultSet rs;

    private Class entityClass;

    public BaseDAO() {

        Type genericType = getClass().getGenericSuperclass();
        //ParameterizedType
        Type[] actualTypeArguments = ((ParameterizedType) genericType).getActualTypeArguments();
        //Get the <T> Type
        Type actualType = actualTypeArguments[0];
        try {
            entityClass = Class.forName(actualType.getTypeName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new DAOException("Base DAO Constructor has Exception, maybe there is no type in <>");
        }

    }

    protected Connection getConn() {
        return ConnUtil.getConn();

    }

    protected void close(ResultSet rs, PreparedStatement psmt, Connection conn) {

    }

    /**
     * Set parameter to PreparedStatement
     *
     * @param psmt
     * @param params
     * @throws SQLException
     */
    private void setParams(PreparedStatement psmt, Object... params) throws SQLException {
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                psmt.setObject(i + 1, params[i]);
            }
        }
    }

    /**
     * Execute Update
     *
     * @param sql
     * @param params
     * @return row number affected
     */
    protected int executeUpdate(String sql, Object... params) {
        boolean insertFlag = false;
        insertFlag = sql.trim().toUpperCase().startsWith("INSERT");
        conn = getConn();
        try {

            if (insertFlag) {
                psmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            } else {
                psmt = conn.prepareStatement(sql);
            }
            setParams(psmt, params);
            int count = psmt.executeUpdate();
            // Genereate auto_increment key when insert
            if (insertFlag) {
                rs = psmt.getGeneratedKeys();
                if (rs.next()) {
                    return ((Long) rs.getLong(1)).intValue();
                }
            }
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("Base DAO executeUpdate has Exception");
        }
    }

    /**
     * Set value to property of Object by reflection
     *
     * @param obj
     * @param property
     * @param propertyValue
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    private void setValue(Object obj, String property, Object propertyValue) throws NoSuchFieldException, IllegalAccessException {
        Class clazz = obj.getClass();

        Field field = clazz.getDeclaredField(property);
        if (field != null) {
            field.setAccessible(true);
            field.set(obj, propertyValue);
        }
    }

    /**
     * Complex query
     *
     * @param sql
     * @param params
     * @return number of rows effected
     */
    protected Object[] executeComplexQuery(String sql, Object... params) {

        conn = getConn();
        try {
            psmt = conn.prepareStatement(sql);
            setParams(psmt, params);
            rs = psmt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            Object[] columnValueArr = new Object[columnCount];
            if (rs.next()) {
                for (int i = 0; i < columnCount; i++) {
                    Object columnValue = rs.getObject(i + 1);     //33    苹果      5
                    columnValueArr[i] = columnValue;
                }
                return columnValueArr;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("Base DAO executeComplexQuery has Exception");
        }

        return null;
    }

    /**
     * execute query
     * @param sql
     * @param params
     * @return single object
     */
    protected T load(String sql, Object... params) {

        conn = getConn();
        try {
            psmt = conn.prepareStatement(sql);
            setParams(psmt, params);
            rs = psmt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            if (rs.next()) {
                T entity = (T) entityClass.newInstance();

                for (int i = 0; i < columnCount; i++) {
                    String columnName = rsmd.getColumnName(i + 1);
                    Object columnValue = rs.getObject(i + 1);
                    setValue(entity, columnName, columnValue);
                }
                return entity;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("Base DAO load has Exception");
        }
        return null;
    }


    /**
     * execute query
     * @param sql
     * @param params
     * @return list of query result
     */
    protected List<T> executeQuery(String sql, Object... params) {
        List<T> list = new ArrayList<>();

        conn = getConn();
        try {
            psmt = conn.prepareStatement(sql);
            setParams(psmt, params);
            rs = psmt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            while (rs.next()) {
                T entity = (T) entityClass.newInstance();

                for (int i = 0; i < columnCount; i++) {
                    String columnName = rsmd.getColumnName(i + 1);            //fid   fname   price
                    Object columnValue = rs.getObject(i + 1);     //33    苹果      5
                    setValue(entity, columnName, columnValue);
                }
                list.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("Base DAO executeQuery has Exception");
        }

        return list;
    }
}
