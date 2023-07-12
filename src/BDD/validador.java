package BDD;

import Objetos.Paciente;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Date;
import javax.swing.JOptionPane;

public class validador {
    String nome;
    String CPF;
    String telefone;
    String data;
    boolean cliente;
    
    public validador() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isCliente() {
        return cliente;
    }

    public void setCliente(boolean cliente) {
        this.cliente = cliente;
    }
    public boolean save() throws ParseException{
        boolean valid;
        if(validarFormat() == true){
            Paciente Paciente = new Paciente();
            Paciente.setNome(nome);
            Paciente.setCPF(CPF);
            Paciente.setTelefone(telefone);
            if(datavalid() == true){
                SimpleDateFormat ft = new SimpleDateFormat("dd/mm/yyyy");
                Date ftData = ft.parse(data);


                BancoDdados.savePacienteInBDD(Paciente);
                BancoDdados.saveConsultasInBDD(CPF, ftData, cliente);
                valid = true;
            }else{
                valid=false;
            }   
        }else{
            valid=false;
        }
        return valid;
    }
    
    boolean validarFormat(){
        boolean valid;
        
        if(this.validadorNumerico() == true){
            boolean validName = nome.matches("[0-9].*");
            boolean validCPF = CPF.matches("[0-9]{3}[.][0-9]{3}[.][0-9]{3}[-][0-9]{2}");
            boolean validFone = telefone.matches("[(][0-9]{2}[)][0-9]{5}[-][0-9]{4}");
            boolean validData = data.matches("[0-9]{2}[/][0-9]{2}[/][0-9]{4}");
            
            if(validName == true){
                JOptionPane.showMessageDialog(null,"Utilize apenas letras para nome");
                valid = false;
            }else if(validCPF == false){
                JOptionPane.showMessageDialog(null,"Utiliza o formato xxx.xxx.xxx-xx");
                valid = false;
            }else if(validFone == false){
                JOptionPane.showMessageDialog(null,"Utiliza o formato (xx)xxxxx-xxxx");
                valid = false;
            }else if(validData == false){
                JOptionPane.showMessageDialog(null,"Utiliza o formato dd/mm/aaaa");
                valid = false;
            }else{
                valid = true;
            }
            
            return valid;
        }
       
        
        return false;
    }
    
    boolean validadorNumerico(){
        
        boolean valid;
        boolean validCPF = CPF.matches("[a-zA-Z].*");
        boolean validFone = telefone.matches("[a-zA-Z].*");
        boolean validData = data.matches("[a-zA-Z].*");
        
        if(validCPF == true){
            JOptionPane.showMessageDialog(null, "Preencha os dados de CPF apenas com numeros");
            valid = false;
        }else if(validFone == true){
            JOptionPane.showMessageDialog(null, "Preencha os dados de Telefone apenas com numeros");
            valid = false;
        }else if(validData == true){
            JOptionPane.showMessageDialog(null, "Preencha os dados de Data apenas com numeros");
            valid = false;
        }else{
            valid = true;
        }
        return valid;
    }
    
    public boolean datavalid(){
        String dateFormat = "dd/MM/uuuu";
        
        DateTimeFormatter dataValid = DateTimeFormatter.ofPattern(dateFormat).withResolverStyle(ResolverStyle.STRICT);
        try{
            LocalDate date = LocalDate.parse(data,dataValid);
            return true;
        }catch(DateTimeParseException e){
            JOptionPane.showMessageDialog(null, "Digite uma data valida");
            return false;
        }
    }
    
}
