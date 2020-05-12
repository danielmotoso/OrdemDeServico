/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infox.dal;

import java.sql.*;

public class ModuloConexao {

    // Método da conexão do Banco de Dados
    public static Connection conector() {
        java.sql.Connection conexao = null;

        //Variavel para chamar o "Driver"     
        String driver = "com.mysql.jdbc.Driver";

        // Armazenandop informações referente ao banco
        String url = "jdbc:mysql://localhost:3306/dbinfox";
        String user = "root";
        String password = "";

        // Estabelecendo a conexao
        try {
            conexao = DriverManager.getConnection(url, user, password);
            System.out.println("Log: Sucesso na conexão com Banco de Dados!");
            return conexao;
        } catch (Exception e) {
            System.out.println("Log: Problema ao acessar o Banco de Dados");
            System.out.println(e);
            return null;
        }

    }

}
