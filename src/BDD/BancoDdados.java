package BDD;

import Objetos.Paciente;
import Objetos.consulta;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BancoDdados {
    private static final List<consulta> Consultas = new ArrayList<>();
    private static final List<Paciente> Pacientes = new ArrayList<>();
    int cont;

    public BancoDdados() {
    }
    
    public static void savePacienteInBDD(Paciente Paciente){
        Pacientes.add(Paciente);
    }
    
    public static void saveConsultasInBDD(String CPF, Date data, boolean cliente){
       
       var indexPaciente = seach(CPF);
      
       Paciente pacienteAtual = Pacientes.get(indexPaciente);
       
       consulta Consulta = new consulta();
       Consulta.setPaciente(pacienteAtual);
       Consulta.setData(data);
       Consulta.setCliente(cliente);
       Consultas.add(Consulta);
    }
    
    public static int seach(String CPF){
        for (int i = 0; i < Pacientes.size(); i++) {
            if(Pacientes.get(i).getCPF().equals(CPF)){
                return i;
            }
        }
        return -1;
    }
    
    public static consulta getConsulta(){
        int  consulta = Consultas.size() -1;
        return Consultas.get(consulta);
    }
    
    public static void remover(int index){
        Consultas.remove(index);
    }
    
    public static void alterar(int index, String receita, boolean realizada){
        Consultas.get(index).setReceita(receita);
        Consultas.get(index).setRealizada(realizada);
        
    }
    
    public static consulta setConsulta(int linha){
        return Consultas.get(linha);
    }
    
    public static boolean validRealizada(int index){
        return Consultas.get(index).isRealizada();
    }
    
    public static boolean pacienteIsEmpty(){
        return Pacientes.isEmpty();
    }
    
}