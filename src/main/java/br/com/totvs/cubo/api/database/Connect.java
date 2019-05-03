package br.com.totvs.cubo.api.database;

import br.com.totvs.cubo.api.util.PersistData;
import br.com.totvs.cubo.api.util.ResultSetToJsonMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.*;
import java.time.Instant;

public class Connect {

    private String connectionUrl;
    private String sql;
    private String json;
    private ResultSet result;
    private String target;

    public Connect(String connectionUrl, String sql) {
        this.connectionUrl = connectionUrl;
        this.sql = sql;
    }

    public void executa(String connectionUrl, String sql, String source) {

        try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement()) {

            ResultSet rs = stmt.executeQuery(sql);

            this.setResult(rs);

            ResultSetToJsonMapper json = new ResultSetToJsonMapper();

            JSONArray jsonArray = new JSONArray();

            while (rs.next()) {
                jsonArray = json.mapResultSet(rs);
            }

            PersistData.persistFile(jsonArray.toString(), stringUtil(source), source);

        } catch (SQLException e) {
            e.printStackTrace();
            this.setResult(null);
        } catch (IOException e) {
            e.printStackTrace();
            this.setResult(null);
        }
    }

    public JSONArray convertToJSON(ResultSet resultSet)
            throws Exception {
        JSONArray jsonArray = new JSONArray();
        while (resultSet.next()) {
            int total_rows = resultSet.getMetaData().getColumnCount();
            for (int i = 0; i < total_rows; i++) {
                JSONObject obj = new JSONObject();
                obj.put(resultSet.getMetaData().getColumnLabel(i + 1)
                        .toLowerCase(), resultSet.getObject(i + 1));
                jsonArray.put(obj);
            }
        }
        return jsonArray;
    }

    public String getJson() {
        try {
            return convertToJSON(this.getResult()).toString();
        } catch (Exception e) {
            return null;
        }
    }

    public String getConnectionUrl() {
        return connectionUrl;
    }

    public void setConnectionUrl(String connectionUrl) {
        this.connectionUrl = connectionUrl;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String stringUtil(String str) {
        setTarget(str + ((str.endsWith("\\") ? Instant.now().toString() : "\\" + Instant.now().toString())).replace("-", "").replace(":", "").replace(".", "") + ".txt");
        return getTarget();
    }

    public ResultSet getResult() {
        return result;
    }

    public void setResult(ResultSet result) {
        this.result = result;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}