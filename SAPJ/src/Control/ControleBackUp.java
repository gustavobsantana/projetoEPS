/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import View.CadastroAdvogados;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author gustavo
 */
public class ControleBackUp {

    private JFileChooser iniciarJFileChooser(CadastroAdvogados view, boolean selecionarMultiplosArquivos, int jFileChooser, String buttonText) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(selecionarMultiplosArquivos);
        fileChooser.setApproveButtonText(buttonText);
        fileChooser.setDialogTitle(buttonText);
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setFileSelectionMode(jFileChooser);
        int result = fileChooser.showOpenDialog(view);
        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser;
        }
        return null;
    }

    public void exportar(CadastroAdvogados view) {
        File folder = new File(".");
        System.out.println(folder.getAbsolutePath());
        List<File> files = new ArrayList<>();
        for (final File f : folder.listFiles()) {
            if (f.isFile()) {
                if (f.getName().matches(".*\\.dat")) {
                    files.add(f);
                }
            }
        }
        JFileChooser fileChooser = iniciarJFileChooser(view, false, JFileChooser.DIRECTORIES_ONLY, "Salvar");
        
        if (fileChooser != null) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                for (File f : files) {
                    File dest = new File(selectedFile.getAbsolutePath() + "\\" + f.getName());
                    Files.copy(f.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
                JOptionPane.showMessageDialog(null, "Backup exportado com sucesso: NUNCA ALTERE O NOME DOS ARQUIVOS!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao fazer backup!");
                Logger.getLogger(ControleCadastroAdvogado.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
        }
    }

    public void importar(CadastroAdvogados view) {
        JFileChooser fileChooser = iniciarJFileChooser(view, true, JFileChooser.FILES_ONLY, "Abrir");
        if (fileChooser != null) {
            try {
                File[] selectedFile = fileChooser.getSelectedFiles();
                for (File f : selectedFile) {
                    File dest = new File(".\\" + f.getName());
                    Files.copy(f.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);

                }
                JOptionPane.showMessageDialog(null, "Backup importado com sucesso!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao importar backup!");
                Logger.getLogger(ControleCadastroAdvogado.class.getName()).log(Level.SEVERE, null, ex);

            }
        }
    }
}
