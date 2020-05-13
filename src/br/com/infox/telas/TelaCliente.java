package br.com.infox.telas;

import br.com.infox.dal.ModuloConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class TelaCliente extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet resultadoSQL = null;

    public TelaCliente() {
        initComponents();
        conexao = ModuloConexao.conector();
    }

    public int consultar() {
        int retorno = 1;
        if (txtUsoID.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor inserir o ID do usuário.");
            retorno = 1;
        } else {
            String sql = "select*from tbusuarios where iduser=?";
            try {
                //Pegar Texto digitado no campo ID 
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtUsoID.getText());
                //Executar a Quary
                resultadoSQL = pst.executeQuery();

                if (resultadoSQL.next()) {
                    //Recuperar dados e gravar nos campos
                    txtUsoNome.setText(resultadoSQL.getString(2));
                    txtUsoFone.setText(resultadoSQL.getString(3));
                    txtUsologin.setText(resultadoSQL.getString(4));
                    cbxUsoPerfil.setSelectedItem(resultadoSQL.getString(6));
                    txtUsoPrimeiraSenha.setText(resultadoSQL.getString(5));
                    txtUsoSenhaVerificada.setText(resultadoSQL.getString(5));
                    return 0;

                } else {
                    JOptionPane.showMessageDialog(null, "Usuário não cadastrado!");
                    txtUsoNome.setText(null);
                    txtUsoFone.setText(null);
                    txtUsologin.setText(null);
                    cbxUsoPerfil.setSelectedItem("user");
                    txtUsoPrimeiraSenha.setText(null);
                    txtUsoSenhaVerificada.setText(null);
                    retorno = 1;
                }

            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return retorno;
    }

   
    public int cadastrar() {
        int retorno = 1;

        String sql = "insert into tbclientes(nomecli,enderecocli,fonecli,emailcli) value (?,?,?,?)";

        //Verifica se campos obrigatorios estão sem preencher, e valida se as duas senhas correspondem.
        if (txtCliNome.getText().isEmpty() || txtCliFone.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Algum campo obrigatório não foi preenchido.");
            retorno = 1;

        } else {
            try {

                //Pegar Texto digitado nos campos do cadastro do Usuario 
                pst = conexao.prepareStatement(sql);     
                
                pst.setString(1, txtCliNome.getText());
                pst.setString(2, txtCliEndereco.getText());
                pst.setString(3, txtCliFone.getText());                
                pst.setString(4, txtCliEmail.getText());

                //Executar a Quary com comando Update e resultado é gravado na variavel
                int execucaoDoUpdate = pst.executeUpdate();

                //Executar a Quary com comando Update e verificar resultado igual a 1
                if (execucaoDoUpdate > 0) {
                    JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso.");
                    txtCliNome.setText(null);
                    txtCliEndereco.setText(null);
                    txtCliFone.setText(null);                   
                    txtCliEmail.setText(null);
                    retorno = 0;
                } else {
                    System.out.println("Erro ao cadastrar novo cliente!");
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar novo cliente, verifique se o mesmo já esteja cadastrado." + e);
                retorno = 1;
            }
        }
        return retorno;
    }
    
 /*
    public int deletar() {
        int retorno = 1;
        // int resposta = consultar();
        if (consultar() == 0) {

            int excluir = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir este usuário?", "Atenção", JOptionPane.YES_NO_OPTION);

            if (excluir == JOptionPane.YES_NO_OPTION) {
                try {
                    String sql = "delete from  tbusuarios where iduser=?";
                    //Pegar Texto digitado no campo ID 
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1, txtUsoID.getText());
                    //Executar a Quary
                    int execucaoDelete = 0;
                    execucaoDelete = pst.executeUpdate();
                    //System.out.println(execucaoDelete);

                    if (execucaoDelete == 1) {
                        JOptionPane.showMessageDialog(null, "Usuário excluído com sucesso!");
                        txtUsoID.setText("");
                        txtUsoNome.setText("");
                        txtUsoFone.setText("");
                        txtUsologin.setText("");
                        txtUsoPrimeiraSenha.setText("");
                        txtUsoSenhaVerificada.setText("");
                        retorno = 0;
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
        return retorno;
    }

    public int editar() {
        int retorno = 1;
            if ((txtUsoID.getText().isEmpty() || txtUsoNome.getText().isEmpty() || txtUsologin.getText()
                    .isEmpty() || txtUsoPrimeiraSenha.getText().isEmpty()) || (txtUsoSenhaVerificada.getText()
                    == null ? txtUsoPrimeiraSenha.getText() != null : !txtUsoSenhaVerificada.getText().equals(txtUsoPrimeiraSenha.getText()))) {
                JOptionPane.showMessageDialog(null, "Algum campo não foi preenchido ou senhas digitadas não correspondem.");
                retorno = 1;

            } else {
                String sql = "update tbusuarios set usuario=?,fone=?,login=?,senha=?,perfil=? where iduser=?";

                try {
                    //Pegar Texto digitado nos campos do cadastro do Usuario 
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1, txtUsoNome.getText());
                    pst.setString(2, txtUsoFone.getText());
                    pst.setString(3, txtUsologin.getText());
                    pst.setString(4, txtUsoSenhaVerificada.getText());
                    pst.setString(5, cbxUsoPerfil.getSelectedItem().toString());
                    pst.setString(6, txtUsoID.getText());

                    int execucaoUpdate = pst.executeUpdate();

                    System.out.println(execucaoUpdate);

                    if (execucaoUpdate == 1) {
                        JOptionPane.showMessageDialog(null, "Usuário atualizado.");
                        retorno = 0;
                    }

                } catch (Exception e) {
                    System.out.println(e);
                }
                 consultar();
            }        
        return retorno;
       
    }
*/

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btnCliAdicionar = new javax.swing.JButton();
        btnCliEditar = new javax.swing.JButton();
        btnCliDeletar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtCliNome = new javax.swing.JTextField();
        txtCliEndereco = new javax.swing.JTextField();
        txtCliEmail = new javax.swing.JTextField();
        txtCliFone = new javax.swing.JTextField();
        txtCliNomePesquisa = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaClientes = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Clientes");
        setToolTipText("");
        setMinimumSize(new java.awt.Dimension(775, 480));
        setPreferredSize(new java.awt.Dimension(640, 480));

        jInternalFrame1.setClosable(true);
        jInternalFrame1.setIconifiable(true);
        jInternalFrame1.setMaximizable(true);
        jInternalFrame1.setTitle("Usuários");
        jInternalFrame1.setToolTipText("");
        jInternalFrame1.setMinimumSize(new java.awt.Dimension(775, 480));
        jInternalFrame1.setPreferredSize(new java.awt.Dimension(640, 480));

        jLabel7.setText("jLabel1");
        jLabel7.setPreferredSize(new java.awt.Dimension(45, 14));

        jLabel8.setText("jLabel1");
        jLabel8.setPreferredSize(new java.awt.Dimension(45, 14));

        jLabel9.setText("jLabel1");
        jLabel9.setPreferredSize(new java.awt.Dimension(45, 14));

        jLabel10.setText("jLabel1");
        jLabel10.setPreferredSize(new java.awt.Dimension(45, 14));

        jLabel11.setText("jLabel1");
        jLabel11.setPreferredSize(new java.awt.Dimension(45, 14));

        jLabel12.setText("jLabel1");
        jLabel12.setPreferredSize(new java.awt.Dimension(45, 14));

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(207, 207, 207))
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnCliAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/create.png"))); // NOI18N
        btnCliAdicionar.setToolTipText("Adicionar");
        btnCliAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliAdicionarActionPerformed(evt);
            }
        });

        btnCliEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/update.png"))); // NOI18N
        btnCliEditar.setToolTipText("Editar");
        btnCliEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCliEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliEditarActionPerformed(evt);
            }
        });

        btnCliDeletar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/delete.png"))); // NOI18N
        btnCliDeletar.setToolTipText("Delete");
        btnCliDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCliDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliDeletarActionPerformed(evt);
            }
        });

        jLabel1.setText("(*)Campos Obrigatórios");

        jLabel15.setText("*Nome:");

        jLabel16.setText("Endereço:");

        jLabel17.setText("*Telefone:");

        jLabel18.setText("E-mail:");

        txtCliNomePesquisa.setFont(new java.awt.Font("Arial Rounded MT Bold", 2, 12)); // NOI18N
        txtCliNomePesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCliNomePesquisaActionPerformed(evt);
            }
        });

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/pesquisar.png"))); // NOI18N

        tabelaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
            }
        ));
        jScrollPane1.setViewportView(tabelaClientes);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCliNome, javax.swing.GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
                            .addComponent(txtCliEndereco)
                            .addComponent(txtCliFone)
                            .addComponent(txtCliEmail)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(124, 124, 124)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(315, 315, 315)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnCliAdicionar)
                                    .addGap(46, 46, 46)
                                    .addComponent(btnCliEditar)
                                    .addGap(48, 48, 48)
                                    .addComponent(btnCliDeletar))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGap(50, 50, 50)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(txtCliNomePesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel19))))))
                .addGap(50, 50, 50))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 322, Short.MAX_VALUE)
                    .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 323, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCliNomePesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtCliNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCliEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtCliFone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtCliEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCliAdicionar)
                    .addComponent(btnCliDeletar)
                    .addComponent(btnCliEditar))
                .addContainerGap(55, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 277, Short.MAX_VALUE)
                    .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 278, Short.MAX_VALUE)))
        );

        getAccessibleContext().setAccessibleName("");

        setBounds(0, 0, 640, 585);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCliAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliAdicionarActionPerformed
        // TODO add your handling code here:
        cadastrar();
    }//GEN-LAST:event_btnCliAdicionarActionPerformed

    private void btnCliDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliDeletarActionPerformed
        // TODO add your handling code here:
       // deletar();
    }//GEN-LAST:event_btnCliDeletarActionPerformed

    private void btnCliEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliEditarActionPerformed
        // TODO add your handling code here:        
      //  editar();
    }//GEN-LAST:event_btnCliEditarActionPerformed

    private void txtCliNomePesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCliNomePesquisaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCliNomePesquisaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCliAdicionar;
    private javax.swing.JButton btnCliDeletar;
    private javax.swing.JButton btnCliEditar;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaClientes;
    private javax.swing.JTextField txtCliEmail;
    private javax.swing.JTextField txtCliEndereco;
    private javax.swing.JTextField txtCliFone;
    private javax.swing.JTextField txtCliNome;
    private javax.swing.JTextField txtCliNomePesquisa;
    // End of variables declaration//GEN-END:variables
}
